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

public class ForumJson extends AbstractJson {
  private static final long serialVersionUID = 1L;

  public ForumJson(Forum forum) {
    put("id", forum.getId());
    put("name", forum.getForumName());
    put("owner", forum.getOwner());
    put("createdDate", RestUtils.formatDateToISO8601(forum.getCreatedDate()));
    put("updatedDate", RestUtils.formatDateToISO8601(forum.getModifiedDate()));
    //
    put("title", forum.getForumName());
    put("description", forum.getDescription());
    put("position", String.valueOf(forum.getForumOrder()));
    //
    put("closed", String.valueOf(forum.getIsClosed()));
    put("locked", String.valueOf(forum.getIsLock()));
    put("autoAddEmailNotify", String.valueOf(forum.getIsAutoAddEmailNotify()));
    put("moderateTopic", String.valueOf(forum.getIsModerateTopic()));
    put("moderatePost", String.valueOf(forum.getIsModeratePost()));
    //
    put("moderators", forum.getModerators());
    put("topicCreators", forum.getCreateTopicRole());
    put("posters", forum.getPoster());
    put("viewers", forum.getViewer());
    put("bannedIPs", forum.getBanIP().toArray(new String[] {}));
    //
    put("notifyWhenAddTopic", forum.getNotifyWhenAddTopic());
    put("notifyWhenAddPost", forum.getNotifyWhenAddPost());
  }

  public void setCategory(HrefLink category) {
    put("category", category);
  }

  public void setTitle(String title) {
    put("title", title);
  }

  public void setPosition(String position) {
    put("position", position);
  }

  public void setClosed(String closed) {
    put("closed", closed);
  }

  public void setLocked(String locked) {
    put("locked", locked);
  }

  public void setDescription(String description) {
    put("description", description);
  }

  public void setAutoAddEmailNotify(String autoAddEmailNotify) {
    put("autoAddEmailNotify", autoAddEmailNotify);
  }

  public void setModerateTopic(String moderateTopic) {
    put("moderateTopic", moderateTopic);
  }

  public void setModeratePost(String moderatePost) {
    put("moderatePost", moderatePost);
  }

  public void setNotifyWhenAddTopic(String[] notifyWhenAddTopic) {
    put("notifyWhenAddTopic", notifyWhenAddTopic);
  }

  public void setNotifyWhenAddPost(String[] notifyWhenAddPost) {
    put("notifyWhenAddPost", notifyWhenAddPost);
  }

  public void setModerators(String[] moderators) {
    put("moderators", moderators);
  }

  public void setTopicCreators(String[] topicCreators) {
    put("topicCreators", topicCreators);
  }

  public void setPosters(String[] posters) {
    put("posters", posters);
  }

  public void setViewers(String[] viewers) {
    put("viewers", viewers);
  }

  public void setBannedIPs(String[] bannedIPs) {
    put("bannedIPs", bannedIPs);
  }
}
