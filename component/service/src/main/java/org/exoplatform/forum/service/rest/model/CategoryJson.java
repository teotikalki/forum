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
import org.exoplatform.forum.service.rest.RestUtils;

public class CategoryJson extends AbstractJson {
  private static final long serialVersionUID = 1L;

  protected final String position;
  protected final String description;
  protected final String[] moderators;
  protected final String[] topicCreators;
  protected final String[] posters;
  protected final String[] viewers;
  protected final String[] userPrivates;

  public CategoryJson(Category cat) {
    this.id = cat.getId();
    this.name = cat.getCategoryName();
    this.owner = cat.getOwner();
    this.createdDate = RestUtils.formatDateToISO8601(cat.getCreatedDate());
    this.updatedDate = RestUtils.formatDateToISO8601(cat.getModifiedDate());
    this.description = cat.getDescription();
    this.position = String.valueOf(cat.getCategoryOrder());
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
