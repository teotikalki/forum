package org.exoplatform.forum.service.rest.post.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.api.PostForumRest;

@Path("v1/forum/posts")
public class PostForumRestServiceV1 implements PostForumRest {

  @GET
  public Response getPosts(@Context UriInfo uriInfo,
                            @QueryParam("returnSize") boolean returnSize,
                            @QueryParam("offset") int offset,
                            @QueryParam("limit") int limit) throws Exception {
    System.out.println("PostForumRestServiceV1");
    return null;
  }
}
