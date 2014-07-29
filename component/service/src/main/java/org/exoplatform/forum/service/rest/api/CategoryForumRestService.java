/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.forum.service.rest.api;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import org.exoplatform.forum.service.rest.AbstractForumRest;


public interface CategoryForumRestService extends AbstractForumRest {

  /**
   * Process to return all categories in json format
   * 
   * @param uriInfo
   * @param returnSize true if the response must contain the total size of all categories found
   * @param offset index of the first category to return 
   * @param limit the maximum number of categories to return
   * @return
   * @throws Exception
   */
  @GET
  public Response getCategories(@Context SecurityContext sc, @Context UriInfo uriInfo,
                                 @QueryParam("returnSize") boolean returnSize,
                                 @QueryParam("offset") int offset,
                                 @QueryParam("limit") int limit) throws Exception;
}
