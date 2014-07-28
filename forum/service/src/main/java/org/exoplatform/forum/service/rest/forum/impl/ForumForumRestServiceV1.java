package org.exoplatform.forum.service.rest.forum.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.api.ForumForumRest;

@Path("v1/forum/forums")
public class ForumForumRestServiceV1 implements ForumForumRest {

  @GET
  public Response getForums(@Context UriInfo uriInfo,
                             @QueryParam("returnSize") boolean returnSize,
                             @QueryParam("offset") int offset,
                             @QueryParam("limit") int limit) throws Exception {
    System.out.println("ForumForumRestServiceV1");
    return null;
  }
}
