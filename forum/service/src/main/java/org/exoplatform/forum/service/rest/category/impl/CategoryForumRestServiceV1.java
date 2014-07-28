package org.exoplatform.forum.service.rest.category.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.AbstractForumRestServiceImpl;
import org.exoplatform.forum.service.rest.api.CategoryForumRestService;

@Path("v1/forum/categories")
public class CategoryForumRestServiceV1 extends AbstractForumRestServiceImpl implements CategoryForumRestService {

  @Override
  @GET
  public Response getCategories(@Context UriInfo uriInfo,
                                @QueryParam("returnSize") boolean returnSize,
                                @QueryParam("offset") int offset,
                                @QueryParam("limit") int limit) throws Exception {
    try {

      return Response.ok(null, MediaType.APPLICATION_XML).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
}
