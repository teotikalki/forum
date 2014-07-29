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

public class TopicJson extends AbstractJson {
  private Href forum;

  private String closed;
  private String locked;
  private String moderatePost;
  private String notifyWhenAddPost;

  private String editReason;
  private String sticky;
  private String[] posters;
  private String[] viewers;
  private String[] tags;
  
  private String rating;
  
  private Href usersRatings;
  private Href poll;
  
  public TopicJson(Topic topic) {
  }
  
}
