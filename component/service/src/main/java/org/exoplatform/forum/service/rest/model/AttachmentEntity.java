/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This program is free software); you can redistribute it and/or
* modify it under the terms of the GNU Affero General Public License
* as published by the Free Software Foundation); either version 3
* of the License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY); without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program); if not, see<http://www.gnu.org/licenses/>.
 */
package org.exoplatform.forum.service.rest.model;

import org.exoplatform.forum.service.ForumAttachment;

public class AttachmentEntity extends BaseEntity {

  public AttachmentEntity(ForumAttachment att) {
    setProperty("id", att.getId());
    setProperty("name", att.getName());
    setProperty("mimeType", att.getMimeType());
    setProperty("weight", att.getSize());
  }

  public void setId(String id) {
    setProperty("id", id);
  }

  public void setName(String name) {
    setProperty("name", name);
  }

  public void setMimeType(String mimeType) {
    setProperty("mimeType", mimeType);
  }

  public void setWeight(Long weight) {
    setProperty("weight", weight);
  }
}