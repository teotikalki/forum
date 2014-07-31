package org.exoplatform.forum.service.rest.vote.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.exoplatform.forum.service.rest.AbstractForumRestServiceImpl;
import org.exoplatform.forum.service.rest.api.VoteForumRestService;

@Path("v1/forum/votes")
public class VoteForumRestServiceV1 extends AbstractForumRestServiceImpl implements VoteForumRestService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getVotes(@Context SecurityContext sc, @Context UriInfo uriInfo,
                            @QueryParam("returnSize") boolean returnSize,
                            @QueryParam("offset") int offset,
                            @QueryParam("limit") int limit) throws Exception {
    try {

      return Response.ok(null, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
}
