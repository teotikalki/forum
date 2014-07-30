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

  protected HrefLink category;
  protected String title;
  protected String position;
  protected String closed;
  protected String locked;
  protected String description;
  protected String autoAddEmailNotify;
  protected String moderateTopic;
  protected String moderatePost;

  protected String[] notifyWhenAddTopic;
  protected String[] notifyWhenAddPost;

  protected String[] moderators;
  protected String[] topicCreators;
  protected String[] posters;
  protected String[] viewers;
  protected String[] bannedIPs;

  public ForumJson(Forum forum) {
    this.id = forum.getId();
    this.name = forum.getForumName();
    this.owner = forum.getOwner();
    this.createdDate = RestUtils.formatDateToISO8601(forum.getCreatedDate());
    this.updatedDate = RestUtils.formatDateToISO8601(forum.getModifiedDate());
    //
    this.title = forum.getForumName();
    this.description = forum.getDescription();
    this.position = String.valueOf(forum.getForumOrder());
    //
    this.closed = String.valueOf(forum.getIsClosed());
    this.locked = String.valueOf(forum.getIsLock());
    this.autoAddEmailNotify = String.valueOf(forum.getIsAutoAddEmailNotify());
    this.moderateTopic = String.valueOf(forum.getIsModerateTopic());
    this.moderatePost = String.valueOf(forum.getIsModeratePost());
    //
    this.moderators = forum.getModerators();
    this.topicCreators = forum.getCreateTopicRole();
    this.posters = forum.getPoster();
    this.viewers = forum.getViewer();
    this.bannedIPs = forum.getBanIP().toArray(new String[] {});
    //
    this.notifyWhenAddTopic = forum.getNotifyWhenAddTopic();
    this.notifyWhenAddPost = forum.getNotifyWhenAddPost();
  }

  public void setCategory(HrefLink category) {
    this.category = category;
  }
}
