package org.exoplatform.forum.service.rest.category.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.api.AbstractForumRestServiceImpl;
import org.exoplatform.forum.service.rest.api.CategoryForumRest;

@Path("v1/forum/categories")
public class CategoryForumRestServiceV1 extends AbstractForumRestServiceImpl implements CategoryForumRest {

  @Override
  @GET
  public Response getCategories(@Context UriInfo uriInfo,
                                @QueryParam("returnSize") boolean returnSize,
                                @QueryParam("offset") int offset,
                                @QueryParam("limit") int limit) throws Exception {
    System.out.println("CategoryForumRestServiceV1");
    return null;
  }

}
