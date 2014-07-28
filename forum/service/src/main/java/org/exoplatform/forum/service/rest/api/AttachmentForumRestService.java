package org.exoplatform.forum.service.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.AbstractForumRest;


public interface AttachmentForumRestService extends AbstractForumRest {

  /**
   * Process to return all attachments in json format
   * 
   * @param uriInfo
   * @param returnSize true if the response must contain the total size of all attachments found
   * @param offset index of the first attachment to return 
   * @param limit the maximum number of attachments to return
   * @return
   * @throws Exception
   */
  @GET
  public Response getAttachments(@Context UriInfo uriInfo,
                                  @QueryParam("returnSize") boolean returnSize,
                                  @QueryParam("offset") int offset,
                                  @QueryParam("limit") int limit) throws Exception;
}
