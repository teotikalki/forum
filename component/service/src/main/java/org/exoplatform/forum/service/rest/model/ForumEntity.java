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

import org.exoplatform.forum.service.Forum;
import org.exoplatform.forum.service.rest.RestUtils;

public class ForumEntity extends AbstractEntity {

  public ForumEntity(Forum forum) {
    setProperty("id", forum.getId());
    setProperty("name", forum.getForumName());
    setProperty("owner", forum.getOwner());
    setProperty("createdDate", RestUtils.formatDateToISO8601(forum.getCreatedDate()));
    setProperty("updatedDate", RestUtils.formatDateToISO8601(forum.getModifiedDate()));
    //
    setProperty("title", forum.getForumName());
    setProperty("description", forum.getDescription());
    setProperty("position", String.valueOf(forum.getForumOrder()));
    //
    setProperty("closed", String.valueOf(forum.getIsClosed()));
    setProperty("locked", String.valueOf(forum.getIsLock()));
    setProperty("autoAddEmailNotify", String.valueOf(forum.getIsAutoAddEmailNotify()));
    setProperty("moderateTopic", String.valueOf(forum.getIsModerateTopic()));
    setProperty("moderatePost", String.valueOf(forum.getIsModeratePost()));
    //
    setProperty("moderators", forum.getModerators());
    setProperty("topicCreators", forum.getCreateTopicRole());
    setProperty("posters", forum.getPoster());
    setProperty("viewers", forum.getViewer());
    setProperty("bannedIPs", forum.getBanIP().toArray(new String[] {}));
    //
    setProperty("notifyWhenAddTopic", forum.getNotifyWhenAddTopic());
    setProperty("notifyWhenAddPost", forum.getNotifyWhenAddPost());
  }

  public ForumEntity() {
  }

  public void setCategory(BaseEntity category) {
    setProperty("category", category.getData());
  }

  public void setTitle(String title) {
    setProperty("title", title);
  }

  public void setPosition(String position) {
    setProperty("position", position);
  }

  public void setClosed(String closed) {
    setProperty("closed", closed);
  }

  public void setLocked(String locked) {
    setProperty("locked", locked);
  }

  public void setDescription(String description) {
    setProperty("description", description);
  }

  public void setAutoAddEmailNotify(String autoAddEmailNotify) {
    setProperty("autoAddEmailNotify", autoAddEmailNotify);
  }

  public void setModerateTopic(String moderateTopic) {
    setProperty("moderateTopic", moderateTopic);
  }

  public void setModeratePost(String moderatePost) {
    setProperty("moderatePost", moderatePost);
  }

  public void setNotifyWhenAddTopic(String[] notifyWhenAddTopic) {
    setProperty("notifyWhenAddTopic", notifyWhenAddTopic);
  }

  public void setNotifyWhenAddPost(String[] notifyWhenAddPost) {
    setProperty("notifyWhenAddPost", notifyWhenAddPost);
  }

  public void setModerators(String[] moderators) {
    setProperty("moderators", moderators);
  }

  public void setTopicCreators(String[] topicCreators) {
    setProperty("topicCreators", topicCreators);
  }

  public void setPosters(String[] posters) {
    setProperty("posters", posters);
  }

  public void setViewers(String[] viewers) {
    setProperty("viewers", viewers);
  }

  public void setBannedIPs(String[] bannedIPs) {
    setProperty("bannedIPs", bannedIPs);
  }
}
