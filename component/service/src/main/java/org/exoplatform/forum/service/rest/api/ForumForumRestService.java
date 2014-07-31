package org.exoplatform.forum.service.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.AbstractForumRest;


public interface ForumForumRestService extends AbstractForumRest {

  /**
   *  Return a forum
   * @param sc
   * @param uriInfo
   * @param fields
   * @param expand
   * @param id
   * @return
   * @throws Exception
   */
  @GET
  public Response getForum(@Context SecurityContext sc, @Context UriInfo uriInfo,
                            @QueryParam("fields") String fields,
                            @QueryParam("expand") String expand,
                            @PathParam("id") String id) throws Exception;
}
