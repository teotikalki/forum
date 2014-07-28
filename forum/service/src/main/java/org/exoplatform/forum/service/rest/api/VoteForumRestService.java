package org.exoplatform.forum.service.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.AbstractForumRest;


public interface VoteForumRestService extends AbstractForumRest{

  /**
   * Process to return all votes in json format
   * 
   * @param uriInfo
   * @param returnSize true if the response must contain the total size of all votes found
   * @param offset index of the first vote to return 
   * @param limit the maximum number of votes to return
   * @return
   * @throws Exception
   */
  @GET
  public Response getVotes(@Context UriInfo uriInfo,
                            @QueryParam("returnSize") boolean returnSize,
                            @QueryParam("offset") int offset,
                            @QueryParam("limit") int limit) throws Exception;
}
