package org.exoplatform.forum.service.rest.topic.impl;

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

import org.exoplatform.forum.service.impl.model.TopicFilter;
import org.exoplatform.forum.service.rest.AbstractForumRestServiceImpl;
import org.exoplatform.forum.service.rest.api.TopicForumRestService;
import org.exoplatform.forum.service.rest.model.AbstractListJson;
import org.exoplatform.forum.service.rest.model.TopicJson;

@Path("v1/forum/topics")
public class TopicForumRestServiceV1 extends AbstractForumRestServiceImpl implements TopicForumRestService {

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getTopics(@Context SecurityContext sc, @Context UriInfo uriInfo,
                             @QueryParam("returnSize") boolean returnSize,
                             @QueryParam("offset") int offset,
                             @QueryParam("limit") int limit) throws Exception {
    try {
      String userId = getUserId(sc, uriInfo);
      TopicFilter filter = new TopicFilter(userId);
      
      
      return Response.ok(null, MediaType.APPLICATION_JSON).cacheControl(cc).build();
    } catch (Exception e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
  }
  
  public class ResultTopics extends AbstractListJson {
    List<TopicJson> topics;

    public ResultTopics(List<TopicJson> jsons) {
      topics = jsons;
    }
  }
}
