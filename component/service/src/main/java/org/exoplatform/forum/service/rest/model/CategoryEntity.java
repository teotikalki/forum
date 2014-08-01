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

public class CategoryEntity extends AbstractEntity {

  public CategoryEntity(Category cat) {
    setProperty("id", cat.getId());
    setProperty("name", cat.getCategoryName());
    setProperty("owner", cat.getOwner());
    setProperty("createdDate", RestUtils.formatDateToISO8601(cat.getCreatedDate()));
    setProperty("updatedDate", RestUtils.formatDateToISO8601(cat.getModifiedDate()));
    
    setProperty("description", cat.getDescription());
    setProperty("position", String.valueOf(cat.getCategoryOrder()));
    //
    setProperty("userPrivates", cat.getUserPrivate());
    setProperty("moderators", cat.getModerators());
    setProperty("topicCreators", cat.getCreateTopicRole());
    setProperty("posters", cat.getPoster());
    setProperty("viewers", cat.getViewer());
  }

  public void setPosition(String position) {
    setProperty("position", position);
  }

  public void setDescription(String description) {
    setProperty("description", description);
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

  public void setUserPrivates(String[] userPrivates) {
    setProperty("userPrivates", userPrivates);
  }
  
}
