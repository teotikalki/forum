package org.exoplatform.forum.service.rest.topic.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.api.TopicForumRest;

@Path("v1/forum/topics")
public class TopicForumRestServiceV1 implements TopicForumRest {

  @GET
  public Response getTopics(@Context UriInfo uriInfo,
                             @QueryParam("returnSize") boolean returnSize,
                             @QueryParam("offset") int offset,
                             @QueryParam("limit") int limit) throws Exception {
    System.out.println("TopicForumRestServiceV1");
    return null;
  }
}
