package org.exoplatform.forum.service.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


public interface PollForumRest extends AbstractForumRest {

  /**
   * Process to return all polls in json format
   * 
   * @param uriInfo
   * @param returnSize true if the response must contain the total size of all polls found
   * @param offset index of the first poll to return 
   * @param limit the maximum number of polls to return
   * @return
   * @throws Exception
   */
  @GET
  public Response getPolls(@Context UriInfo uriInfo,
                            @QueryParam("returnSize") boolean returnSize,
                            @QueryParam("offset") int offset,
                            @QueryParam("limit") int limit) throws Exception;
}
