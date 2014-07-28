package org.exoplatform.forum.service.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


public interface ForumForumRest extends AbstractForumRest {

  /**
   * Process to return all forums in json format
   * 
   * @param uriInfo
   * @param returnSize true if the response must contain the total size of all forums found
   * @param offset index of the first forum to return 
   * @param limit the maximum number of forums to return
   * @return
   * @throws Exception
   */
  @GET
  public Response getForums(@Context UriInfo uriInfo,
                             @QueryParam("returnSize") boolean returnSize,
                             @QueryParam("offset") int offset,
                             @QueryParam("limit") int limit) throws Exception;
}
