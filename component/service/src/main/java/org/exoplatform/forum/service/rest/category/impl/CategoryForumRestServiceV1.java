package org.exoplatform.forum.service.rest.category.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.filter.model.CategoryFilter;
import org.exoplatform.forum.service.rest.AbstractForumRestServiceImpl;
import org.exoplatform.forum.service.rest.RestUtils;
import org.exoplatform.forum.service.rest.api.CategoryForumRestService;
import org.exoplatform.forum.service.rest.model.AbstractListJson;
import org.exoplatform.forum.service.rest.model.CategoryJson;

@Path("v1/forum")
public class CategoryForumRestServiceV1 extends AbstractForumRestServiceImpl implements CategoryForumRestService {

  @GET
  @Path("/categories")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCategories(@Context SecurityContext sc, @Context UriInfo uriInfo,
                                @QueryParam("returnSize") boolean returnSize,
                                @QueryParam("offset") int offset,
                                @QueryParam("limit") int limit) throws Exception {
    try {
      
      limit = limit <= 0 ? DEFAULT_LIMIT : Math.min(HARD_LIMIT, limit);
      offset = offset < 0 ? DEFAULT_OFFSET : offset;

      ForumService forumService = getForumService();
      ListAccess<Category> listAccess = forumService.getCategoriesWithListAccess(new CategoryFilter());
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

  public class ResultCategories extends AbstractListJson {
    public List<CategoryJson> categories;

    public ResultCategories(List<CategoryJson> jsons) {
      categories = jsons;
    }
  }

}
