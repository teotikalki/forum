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
  private final static long serialVersionUID = 1L;

  public CategoryJson(Category cat) {
    put("id", cat.getId());
    put("name", cat.getCategoryName());
    put("owner", cat.getOwner());
    put("createdDate", RestUtils.formatDateToISO8601(cat.getCreatedDate()));
    put("updatedDate", RestUtils.formatDateToISO8601(cat.getModifiedDate()));
    
    put("description", cat.getDescription());
    put("position", String.valueOf(cat.getCategoryOrder()));
    //
    put("userPrivates", cat.getUserPrivate());
    put("moderators", cat.getModerators());
    put("topicCreators", cat.getCreateTopicRole());
    put("posters", cat.getPoster());
    put("viewers", cat.getViewer());
  }

  public void setPosition(String position) {
    put("position", position);
  }

  public void setDescription(String description) {
    put("description", description);
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

  public void setUserPrivates(String[] userPrivates) {
    put("userPrivates", userPrivates);
  }
  
}
