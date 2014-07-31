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

import org.exoplatform.forum.service.Topic;
import org.exoplatform.forum.service.rest.RestUtils;

public class TopicJson extends AbstractJson {
  private static final long serialVersionUID = 1L;

  public TopicJson(Topic topic) {
    put("id", topic.getId());
    put("name", topic.getTopicName());
    put("owner", topic.getOwner());
    put("createdDate", RestUtils.formatDateToISO8601(topic.getCreatedDate()));
    put("updatedDate", RestUtils.formatDateToISO8601(topic.getModifiedDate()));
    //
    put("title", topic.getTopicName());
    put("description", topic.getDescription());
    put("closed", String.valueOf(topic.getIsClosed()));
    put("locked", String.valueOf(topic.getIsClosed()));
    put("sticky", String.valueOf(topic.getIsSticky()));
    
    put("notifyWhenAddPost", String.valueOf(topic.getIsNotifyWhenAddPost()));
    put("moderatePost", String.valueOf(topic.getIsModeratePost()));
    
    put("posters", topic.getCanPost());
    put("viewers", topic.getCanView());
    put("tags", topic.getTagId());
    //
    put("rating", String.valueOf(topic.getVoteRating()));
  }

  public void setForum(HrefLink forum) {
    put("forum", forum);
  }

  public void setUsersRatings(HrefLink usersRatings) {
    put("usersRatings", usersRatings);
  }

  public void setPoll(HrefLink poll) {
    put("poll", poll);
  }

  public void setTitle(String title) {
    put("title", title);
  }

  public void setDescription(String description) {
    put("description", description);
  }

  public void setClosed(String closed) {
    put("closed", closed);
  }

  public void setLocked(String locked) {
    put("locked", locked);
  }

  public void setModeratePost(String moderatePost) {
    put("moderatePost", moderatePost);
  }

  public void setNotifyWhenAddPost(String notifyWhenAddPost) {
    put("notifyWhenAddPost", notifyWhenAddPost);
  }

  public void setEditReason(String editReason) {
    put("editReason", editReason);
  }

  public void setSticky(String sticky) {
    put("sticky", sticky);
  }

  public void setPosters(String[] posters) {
    put("posters", posters);
  }

  public void setViewers(String[] viewers) {
    put("viewers", viewers);
  }

  public void setTags(String[] tags) {
    put("tags", tags);
  }

  public void setRating(String rating) {
    put("rating", rating);
  }

  public void setAttachments(HrefLink[] attachments) {
    put("attachments", attachments);
  }
}
