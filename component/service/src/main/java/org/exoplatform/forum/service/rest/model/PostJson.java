/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
* modify it under the terms of the GNU Affero General Public License
* as published by the Free Software Foundation; either version 3
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.forum.service.rest.model;

import org.exoplatform.forum.service.Post;
import org.exoplatform.forum.service.rest.RestUtils;

public class PostJson extends AbstractJson {
  private static final long serialVersionUID = 1L;
  
  protected HrefLink topic;
  protected String title;
  protected String message;
  protected String editReason;
  protected String hidden;
  
  protected HrefLink[] attachments;
  
  public PostJson(Post post) {
    this.id = post.getId();
    this.name = post.getName();
    this.owner = post.getOwner();
    this.createdDate = RestUtils.formatDateToISO8601(post.getCreatedDate());
    this.updatedDate = RestUtils.formatDateToISO8601(post.getModifiedDate());
    //
    this.title = post.getName();
    this.message = post.getMessage();
    this.editReason = post.getEditReason();
    this.hidden = String.valueOf(post.getIsHidden() || post.getIsWaiting() || 
                                 !post.getIsApproved() || !post.getIsActiveByTopic());
  }

  public void setAttachments(HrefLink[] attachments) {
    this.attachments = attachments;
  }
}
