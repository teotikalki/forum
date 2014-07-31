package org.exoplatform.forum.service.rest.category.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.filter.model.CategoryFilter;
import org.exoplatform.forum.service.rest.AbstractForumRestServiceImpl;
import org.exoplatform.forum.service.rest.RestUtils;
import org.exoplatform.forum.service.rest.api.CategoryForumRestService;
import org.exoplatform.forum.service.rest.model.AbstractListJson;
import org.exoplatform.forum.service.rest.model.CategoryJson;
import org.exoplatform.services.security.ConversationState;

@Path("v1/forum/categories")
public class CategoryForumRestServiceV1 extends AbstractForumRestServiceImpl implements CategoryForumRestService {

  @GET
  @RolesAllowed("users")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategories(@Context SecurityContext sc, @Context UriInfo uriInfo,
                                 @QueryParam("fields") String fields,
                                 @QueryParam("returnSize") boolean returnSize,
                                 @QueryParam("offset") int offset,
                                 @QueryParam("limit") int limit) throws Exception {
    try {
      String authenticatedUser = ConversationState.getCurrent().getIdentity().getUserId();
      
      limit = limit <= 0 ? DEFAULT_LIMIT : Math.min(HARD_LIMIT, limit);
      offset = offset < 0 ? DEFAULT_OFFSET : offset;

      ForumService forumService = getForumService();
      CategoryFilter filter = new CategoryFilter();
      filter.setAuthenticatedUser(authenticatedUser);
      
      ListAccess<Category> listAccess = forumService.getCategoriesWithListAccess(filter);
      Category[] categories = listAccess.load(offset, limit);
      List<CategoryJson> jsons = new ArrayList<CategoryJson>();
      for (int i = 0; i < categories.length; i++) {
        CategoryJson json = new CategoryJson(categories[i]);
        json.setHref(RestUtils.getRestUrl(CATEGORIES, categories[i].getId(), uriInfo.getPath()));
        jsons.add(json);
      }
      
      ResultCategories result = new ResultCategories(jsons);
      result.setLimit(limit);
      result.setOffset(offset);
      if (returnSize) {
        result.setSize(jsons.size());
      }
      
      return Response.ok(result, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategoryById(@Context SecurityContext sc,
                                   @Context UriInfo uriInfo,
                                   @QueryParam("fields") String fields,
                                   @PathParam("id") String id) throws Exception {
    try {
      String authenticatedUser = ConversationState.getCurrent().getIdentity().getUserId();

      ForumService forumService = getForumService();
      Category category = forumService.getCategory(id);
      
      if (category == null || ! hasViewCategoryPermission(category, authenticatedUser)) {
        throw new WebApplicationException(Response.Status.UNAUTHORIZED); 
      }
      
      CategoryJson json = new CategoryJson(category);
      json.setHref(RestUtils.getRestUrl(CATEGORIES, category.getId(), uriInfo.getPath()));
      
      return Response.ok(json, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @PUT
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response updateCategoryById(@Context SecurityContext sc,
                                      @Context UriInfo uriInfo,
                                      @QueryParam("fields") String fields,
                                      @PathParam("id") String id) throws Exception {
    try {
      String authenticatedUser = ConversationState.getCurrent().getIdentity().getUserId();

      ForumService forumService = getForumService();
      Category category = forumService.getCategory(id);
      
      if (category == null || ! hasViewCategoryPermission(category, authenticatedUser)) {
        throw new WebApplicationException(Response.Status.UNAUTHORIZED); 
      }
      
      CategoryJson json = new CategoryJson(category);
      json.setHref(RestUtils.getRestUrl(CATEGORIES, category.getId(), uriInfo.getPath()));
      
      return Response.ok(json, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  @DELETE
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteCategoryById(@Context SecurityContext sc,
                                      @Context UriInfo uriInfo,
                                      @QueryParam("fields") String fields,
                                      @PathParam("id") String id) throws Exception {
    try {
      String authenticatedUser = ConversationState.getCurrent().getIdentity().getUserId();

      ForumService forumService = getForumService();
      Category category = forumService.getCategory(id);
      
      if (category == null || ! hasViewCategoryPermission(category, authenticatedUser)) {
        throw new WebApplicationException(Response.Status.UNAUTHORIZED); 
      }
      
      CategoryJson json = new CategoryJson(category);
      json.setHref(RestUtils.getRestUrl(CATEGORIES, category.getId(), uriInfo.getPath()));
      //
      forumService.removeCategory(id);
      
      return Response.ok(json, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

  public class ResultCategories extends AbstractListJson {
    private List<CategoryJson> categories;

    public ResultCategories(List<CategoryJson> jsons) {
      setCategories(jsons);
    }

    public List<CategoryJson> getCategories() {
      return categories;
    }

    public void setCategories(List<CategoryJson> categories) {
      this.categories = categories;
    }
  }

}
