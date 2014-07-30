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

import org.exoplatform.forum.service.ForumAttachment;

public class AttachmentJson extends HrefLink {
  private static final long serialVersionUID = 1L;

  protected String id;
  protected String name;
  protected String mimeType;
  protected Long weight;
  
  public AttachmentJson(ForumAttachment att) {
    this.id = att.getId();
    this.name = att.getName();
    this.mimeType = att.getMimeType();
    this.weight = att.getSize();
  }
}