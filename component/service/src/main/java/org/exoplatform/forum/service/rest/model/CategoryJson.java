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

import org.exoplatform.forum.service.Category;

public class CategoryJson extends AbstractJson {
  private static final long serialVersionUID = 1L;

  private final String position;
  private final String[] moderators;
  private final String[] topicCreators;
  private final String[] posters;
  private final String[] viewers;
  private final String[] userPrivates;

  public CategoryJson(Category cat) {
    this.id = cat.getId();
    this.position = String.valueOf(cat.getCategoryOrder());
    this.name = cat.getCategoryName();
    this.description = cat.getDescription();
    this.owner = cat.getOwner();
    this.createdDate = cat.getCreatedDate();
    this.updatedDate = cat.getModifiedDate();
    //
    this.userPrivates = cat.getUserPrivate();
    this.moderators = cat.getModerators();
    this.topicCreators = cat.getCreateTopicRole();
    this.posters = cat.getPoster();
    this.viewers = cat.getViewer();
  }

  public CategoryJson(Category cat, String href) {
    this(cat);
    setHref(href);
  }
  
}
