/*
 * Copyright (C) 2003-2011 eXo Platform SAS.
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
package org.exoplatform.forum.service.conf;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.ForumServiceUtils;
import org.exoplatform.services.organization.Group;
import org.exoplatform.services.organization.GroupEventListener;

public class ForumGroupListener extends GroupEventListener {

  public ForumGroupListener() {
  }

  public void postSave(Group group, boolean isNew) throws Exception{
    ForumServiceUtils.clearCache();
  }

  public void preDelete(Group group) throws Exception {
    CommonsUtils.getService(ForumService.class).calculateDeletedGroup(group.getId(), group.getGroupName());
    //
    ForumServiceUtils.clearCache();
  }

}
