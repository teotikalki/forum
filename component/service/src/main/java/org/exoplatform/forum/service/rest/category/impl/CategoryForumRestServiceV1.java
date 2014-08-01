package org.exoplatform.forum.service.rest.category.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.Forum;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.filter.model.CategoryFilter;
import org.exoplatform.forum.service.filter.model.ForumFilter;
import org.exoplatform.forum.service.rest.AbstractForumRestServiceImpl;
import org.exoplatform.forum.service.rest.RestUtils;
import org.exoplatform.forum.service.rest.api.CategoryForumRestService;
import org.exoplatform.forum.service.rest.model.AbstractListEntity;
import org.exoplatform.forum.service.rest.model.CategoryEntity;
import org.exoplatform.forum.service.rest.model.ForumEntity;

@Path("v1/forum/categories")
public class CategoryForumRestServiceV1 extends AbstractForumRestServiceImpl implements CategoryForumRestService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategories(@Context SecurityContext sc, @Context UriInfo uriInfo) throws Exception {
    try {
      String fields = getQueryValueFields(uriInfo);
      boolean returnSize = getQueryValueReturnSize(uriInfo);
      int offset = getQueryValueOffset(uriInfo);
      int limit = getQueryValueLimit(uriInfo);
      limit = limit <= 0 ? DEFAULT_LIMIT : Math.min(HARD_LIMIT, limit);
      offset = offset < 0 ? DEFAULT_OFFSET : offset;

      String authenticatedUser = getUserId(sc, uriInfo);
      ForumService forumService = getForumService();
      CategoryFilter filter = new CategoryFilter();
      filter.setAuthenticatedUser(authenticatedUser);
      
      ListAccess<Category> listAccess = forumService.getCategoriesWithListAccess(filter);
      Category[] categories = listAccess.load(offset, limit);
      List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
      for (int i = 0; i < categories.length; i++) {
        CategoryEntity entity = new CategoryEntity(categories[i]);
        entity.setHref(RestUtils.getRestUrl(CATEGORIES, categories[i].getId(), uriInfo.getPath()));
        data.add(entity.getData());
      }

      ResultCategories result = new ResultCategories(data);
      result.setLimit(limit);
      result.setOffset(offset);
      if (returnSize) {
        result.setSize(data.size());
      }
      
      return Response.ok(result, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategoryById(@Context SecurityContext sc, @Context UriInfo uriInfo,
                                   @PathParam("id") String id) throws Exception {
    try {
      String fields = getQueryValueFields(uriInfo);
      String authenticatedUser = getUserId(sc, uriInfo);

      ForumService forumService = getForumService();
      Category category = forumService.getCategory(id);
      
      if (category == null || ! hasCanViewCategory(category, authenticatedUser)) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
      }
      
      CategoryEntity entity = new CategoryEntity(category);
      entity.setHref(RestUtils.getRestUrl(CATEGORIES, category.getId(), uriInfo.getPath()));
      
      return Response.ok(entity.getData(), MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @PUT
  @Path("{id}")
  @RolesAllowed("users")
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateCategoryById(@Context SecurityContext sc, @Context UriInfo uriInfo,
                                      @PathParam("id") String id) throws Exception {
    try {
      String fields = getQueryValueFields(uriInfo);
      String authenticatedUser = getUserId(sc, uriInfo);

      ForumService forumService = getForumService();
      Category category = forumService.getCategory(id);
      
      if (category == null || !isManagerCategory(authenticatedUser)) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
      }
      
      CategoryEntity entity = new CategoryEntity(category);
      entity.setHref(RestUtils.getRestUrl(CATEGORIES, category.getId(), uriInfo.getPath()));
      
      return Response.ok(entity.getData(), MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @DELETE
  @Path("{id}")
  @RolesAllowed("users")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteCategoryById(@Context SecurityContext sc, @Context UriInfo uriInfo,
                                      @PathParam("id") String id) throws Exception {
    try {
      String fields = getQueryValueFields(uriInfo);
      String authenticatedUser = getUserId(sc, uriInfo);;

      ForumService forumService = getForumService();
      Category category = forumService.getCategory(id);
      
      if (category == null || !isManagerCategory(authenticatedUser)) {
        return Response.status(Response.Status.UNAUTHORIZED).build();
      }
      
      CategoryEntity entity = new CategoryEntity(category);
      entity.setHref(RestUtils.getRestUrl(CATEGORIES, category.getId(), uriInfo.getPath()));
      //
      forumService.removeCategory(id);
      
      return Response.ok(entity.getData(), MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GET
  @Path("{id}/forums")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getForums(@Context SecurityContext sc, @Context UriInfo uriInfo,
                            @QueryParam("owner") String owner,
                            @QueryParam("locked") String locked,
                            @QueryParam("closed") String closed,
                            @PathParam("id") String id) throws Exception {
    try {
      String authenticatedUser = getUserId(sc, uriInfo);
      
      ForumService forumService = getForumService();
      Category category = forumService.getCategory(id);
      
      if (category == null || ! hasCanViewCategory(category, authenticatedUser)) {
        return Response.status(Status.UNAUTHORIZED).build();
      }
      //
      String fields = getQueryValueFields(uriInfo);
      boolean returnSize = getQueryValueReturnSize(uriInfo);
      int offset = getQueryValueOffset(uriInfo);
      int limit = getQueryValueLimit(uriInfo);
      limit = limit <= 0 ? DEFAULT_LIMIT : Math.min(HARD_LIMIT, limit);
      offset = offset < 0 ? DEFAULT_OFFSET : offset;
      
      ForumFilter filter = new ForumFilter(id, authenticatedUser, owner);
      filter.setClosed(closed != null && ("true".equals(closed) || closed.equals("false")) ? closed : null);
      filter.setLocked(locked != null && ("true".equals(locked) || locked.equals("false")) ? locked : null);
      ListAccess<Forum> listAccess = forumService.getForumsWithListAccess(filter);
      Forum[] forums = listAccess.load(offset, limit);

      List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
      for (int i = 0; i < forums.length; i++) {
        ForumEntity forumEntity = new ForumEntity(forums[i]);
        forumEntity.setHref(RestUtils.getRestUrl(FORUMS, forums[i].getId(), uriInfo.getPath()));
        data.add(forumEntity.getData());
      }

      ResultForums result = new ResultForums(data);
      result.setLimit(limit);
      result.setOffset(offset);
      if (returnSize) {
        result.setSize(listAccess.getSize());
      }
      return Response.ok(result, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @POST
  @Path("{id}/forums")
  @Produces(MediaType.APPLICATION_JSON)
  public Response createForum(@Context SecurityContext sc, @Context UriInfo uriInfo,
                                ForumEntity forumData,
                                @PathParam("id") String id) throws Exception {
    
    return null;
  }
  
  public class ResultCategories extends AbstractListEntity {
    private List<Map<String, Object>> categories;

    public ResultCategories(List<Map<String, Object>> data) {
      setCategories(data);
    }

    public List<Map<String, Object>> getCategories() {
      return categories;
    }

    public void setCategories(List<Map<String, Object>> categories) {
      this.categories = categories;
    }
  }
  
  public class ResultForums extends AbstractListEntity {
    private List<Map<String, Object>> forums;

    public ResultForums(List<Map<String, Object>> data) {
      setForums(data);
    }

    public List<Map<String, Object>> getForums() {
      return forums;
    }

    public void setForums(List<Map<String, Object>> forums) {
      this.forums = forums;
    }
  }

}
