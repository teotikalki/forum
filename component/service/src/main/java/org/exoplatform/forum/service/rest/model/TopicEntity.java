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

public class TopicEntity extends AbstractEntity {

  public TopicEntity(Topic topic) {
    setProperty("id", topic.getId());
    setProperty("name", topic.getTopicName());
    setProperty("owner", topic.getOwner());
    setProperty("createdDate", RestUtils.formatDateToISO8601(topic.getCreatedDate()));
    setProperty("updatedDate", RestUtils.formatDateToISO8601(topic.getModifiedDate()));
    //
    setProperty("title", topic.getTopicName());
    setProperty("description", topic.getDescription());
    setProperty("closed", String.valueOf(topic.getIsClosed()));
    setProperty("locked", String.valueOf(topic.getIsClosed()));
    setProperty("sticky", String.valueOf(topic.getIsSticky()));
    
    setProperty("notifyWhenAddPost", String.valueOf(topic.getIsNotifyWhenAddPost()));
    setProperty("moderatePost", String.valueOf(topic.getIsModeratePost()));
    
    setProperty("posters", topic.getCanPost());
    setProperty("viewers", topic.getCanView());
    setProperty("tags", topic.getTagId());
    setProperty("usersRatings", topic.getUserVoteRating());
    //
    setProperty("rating", String.valueOf(topic.getVoteRating()));
  }

  public void setForum(BaseEntity forum) {
    setProperty("forum", forum.getData());
  }

  public void setPoll(BaseEntity poll) {
    setProperty("poll", poll.getData());
  }

  public void setTitle(String title) {
    setProperty("title", title);
  }

  public void setDescription(String description) {
    setProperty("description", description);
  }

  public void setClosed(String closed) {
    setProperty("closed", closed);
  }

  public void setLocked(String locked) {
    setProperty("locked", locked);
  }

  public void setModeratePost(String moderatePost) {
    setProperty("moderatePost", moderatePost);
  }

  public void setNotifyWhenAddPost(String notifyWhenAddPost) {
    setProperty("notifyWhenAddPost", notifyWhenAddPost);
  }

  public void setEditReason(String editReason) {
    setProperty("editReason", editReason);
  }

  public void setSticky(String sticky) {
    setProperty("sticky", sticky);
  }

  public void setPosters(String[] posters) {
    setProperty("posters", posters);
  }

  public void setViewers(String[] viewers) {
    setProperty("viewers", viewers);
  }

  public void setTags(String[] tags) {
    setProperty("tags", tags);
  }

  public void setRating(String rating) {
    setProperty("rating", rating);
  }

  public void setAttachments(BaseEntity[] attachments) {
    setProperty("attachments", attachments);
  }
}
