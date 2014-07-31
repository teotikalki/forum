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
  
  public PostJson(Post post) {
    put("id", post.getId());
    put("name", post.getName());
    put("owner", post.getOwner());
    put("createdDate", RestUtils.formatDateToISO8601(post.getCreatedDate()));
    put("updatedDate", RestUtils.formatDateToISO8601(post.getModifiedDate()));
    //
    put("title", post.getName());
    put("message", post.getMessage());
    put("editReason", post.getEditReason());
    put("hidden", String.valueOf(post.getIsHidden() || post.getIsWaiting() || 
                                 !post.getIsApproved() || !post.getIsActiveByTopic()));
  }

  public void setAttachments(HrefLink[] attachments) {
    put("attachments", attachments);
  }

  public void setTopic(HrefLink topic) {
    put("topic", topic);
  }

  public void setTitle(String title) {
    put("title", title);
  }

  public void setMessage(String message) {
    put("message", message);
  }

  public void setEditReason(String editReason) {
    put("editReason", editReason);
  }

  public void setHidden(String hidden) {
    put("hidden", hidden);
  }
}
