package org.exoplatform.forum.service.rest.attachment.impl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.api.AttachmentForumRest;

@Path("v1/forum/attachments")
public class AttachmentForumRestServiceV1 implements AttachmentForumRest {

  @GET
  public Response getAttachments(@Context UriInfo uriInfo,
                                  @QueryParam("returnSize") boolean returnSize,
                                  @QueryParam("offset") int offset,
                                  @QueryParam("limit") int limit) throws Exception {
    System.out.println("AttachmentForumRestServiceV1");
    return null;
  }
}
