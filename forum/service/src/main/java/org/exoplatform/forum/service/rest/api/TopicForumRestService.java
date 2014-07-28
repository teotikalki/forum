package org.exoplatform.forum.service.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.AbstractForumRest;


public interface TopicForumRestService extends AbstractForumRest {

  /**
   * Process to return all topics in json format
   * 
   * @param uriInfo
   * @param returnSize true if the response must contain the total size of all topics found
   * @param offset index of the first topic to return 
   * @param limit the maximum number of topics to return
   * @return
   * @throws Exception
   */
  @GET
  public Response getTopics(@Context UriInfo uriInfo,
                             @QueryParam("returnSize") boolean returnSize,
                             @QueryParam("offset") int offset,
                             @QueryParam("limit") int limit) throws Exception;
}
