package org.exoplatform.forum.service.rest.vote.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.api.VoteForumRest;

@Path("v1/forum/votes")
public class VoteForumRestServiceV1 implements VoteForumRest {

  @GET
  public Response getVotes(@Context UriInfo uriInfo,
                            @QueryParam("returnSize") boolean returnSize,
                            @QueryParam("offset") int offset,
                            @QueryParam("limit") int limit) throws Exception {
    System.out.println("VoteForumRestServiceV1");
    return null;
  }
}
