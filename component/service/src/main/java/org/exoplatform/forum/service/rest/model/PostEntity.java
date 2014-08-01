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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.exoplatform.forum.service.Post;
import org.exoplatform.forum.service.rest.RestUtils;

public class PostEntity extends AbstractEntity {
  
  public PostEntity(Post post) {
    setProperty("id", post.getId());
    setProperty("name", post.getName());
    setProperty("owner", post.getOwner());
    setProperty("createdDate", RestUtils.formatDateToISO8601(post.getCreatedDate()));
    setProperty("updatedDate", RestUtils.formatDateToISO8601(post.getModifiedDate()));
    //
    setProperty("title", post.getName());
    setProperty("message", post.getMessage());
    setProperty("editReason", post.getEditReason());
    setProperty("hidden", String.valueOf(post.getIsHidden() || post.getIsWaiting() || 
                                 !post.getIsApproved() || !post.getIsActiveByTopic()));
  }

  public void setAttachments(BaseEntity[] attachments) {
    List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
    for (int i = 0; i < attachments.length; i++) {
      data.add(attachments[i].getData());
    }
    setProperty("attachments", data);
  }

  public void setTopic(BaseEntity topic) {
    setProperty("topic", topic.getData());
  }

  public void setTitle(String title) {
    setProperty("title", title);
  }

  public void setMessage(String message) {
    setProperty("message", message);
  }

  public void setEditReason(String editReason) {
    setProperty("editReason", editReason);
  }

  public void setHidden(String hidden) {
    setProperty("hidden", hidden);
  }
}
