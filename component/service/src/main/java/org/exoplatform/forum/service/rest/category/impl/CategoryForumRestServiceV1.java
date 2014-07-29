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

import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.rest.AbstractForumRestServiceImpl;
import org.exoplatform.forum.service.rest.api.CategoryForumRestService;
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
      List<CategoryJson> jsons = new ArrayList<CategoryJson>();

      CategoryJson json = new CategoryJson(new Category());

      jsons.add(json);

      return Response.ok(jsons, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }

}
