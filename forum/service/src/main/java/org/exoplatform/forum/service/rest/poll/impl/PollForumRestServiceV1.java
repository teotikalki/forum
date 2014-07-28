package org.exoplatform.forum.service.rest.poll.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.api.PollForumRest;

@Path("v1/forum/polls")
public class PollForumRestServiceV1 implements PollForumRest {

  @GET
  public Response getPolls(@Context UriInfo uriInfo,
                            @QueryParam("returnSize") boolean returnSize,
                            @QueryParam("offset") int offset,
                            @QueryParam("limit") int limit) throws Exception {
    System.out.println("PollForumRestServiceV1");
    return null;
  }
}
