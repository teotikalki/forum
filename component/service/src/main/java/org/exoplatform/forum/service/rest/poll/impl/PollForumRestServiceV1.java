package org.exoplatform.forum.service.rest.poll.impl;

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

import org.exoplatform.forum.service.rest.AbstractForumRestServiceImpl;
import org.exoplatform.forum.service.rest.api.PollForumRestService;
import org.exoplatform.forum.service.rest.model.AbstractListEntity;
import org.exoplatform.forum.service.rest.model.PollEntity;

@Path("v1/forum/polls")
public class PollForumRestServiceV1 extends AbstractForumRestServiceImpl implements PollForumRestService {

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getPolls(@Context SecurityContext sc, @Context UriInfo uriInfo,
                            @QueryParam("returnSize") boolean returnSize,
                            @QueryParam("offset") int offset,
                            @QueryParam("limit") int limit) throws Exception {
    try {

      return Response.ok(null, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  public class ResultPoll extends AbstractListEntity {
    List<PollEntity> polls;

    public ResultPoll(List<PollEntity> jsons) {
      polls = jsons;
    }
  }
  
}
