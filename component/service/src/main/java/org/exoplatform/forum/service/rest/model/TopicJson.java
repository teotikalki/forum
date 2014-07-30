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
  protected HrefLink forum;

  protected String title;
  protected String description;
  protected String closed;
  protected String locked;
  protected String moderatePost;
  protected String notifyWhenAddPost;

  protected String editReason;
  protected String sticky;
  protected String[] posters;
  protected String[] viewers;
  protected String[] tags;
  
  protected String rating;
  
  protected HrefLink usersRatings;
  protected HrefLink poll;
  
  protected HrefLink[] attachments;
  
  public TopicJson(Topic topic) {
    this.id = topic.getId();
    this.name = topic.getTopicName();
    this.owner = topic.getOwner();
    this.createdDate = RestUtils.formatDateToISO8601(topic.getCreatedDate());
    this.updatedDate = RestUtils.formatDateToISO8601(topic.getModifiedDate());
    //
    this.title = topic.getTopicName();
    this.description = topic.getDescription();
    this.closed = String.valueOf(topic.getIsClosed());
    this.locked = String.valueOf(topic.getIsClosed());
    this.sticky = String.valueOf(topic.getIsSticky());
    
    this.notifyWhenAddPost = String.valueOf(topic.getIsNotifyWhenAddPost());
    this.moderatePost = String.valueOf(topic.getIsModeratePost());
    
    this.posters = topic.getCanPost();
    this.viewers = topic.getCanView();
    this.tags = topic.getTagId();
    //
    this.rating = String.valueOf(topic.getVoteRating());
  }

  public void setForum(HrefLink forum) {
    this.forum = forum;
  }

  public void setUsersRatings(HrefLink usersRatings) {
    this.usersRatings = usersRatings;
  }

  public void setPoll(HrefLink poll) {
    this.poll = poll;
  }
}
