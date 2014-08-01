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

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

public class HrefLink extends HashMap<String, Object> {
  private static final long serialVersionUID = 1L;

  public HrefLink() {
  }

  public HrefLink(String href) {
    if (!StringUtils.isEmpty(href)) {
      put("href", href);
    }
  }
  
  public HrefLink setHref(String href) {
    put("href", href);
    return this;
  }

  public HrefLink setHref(HrefLink href) {
    put("href", href);
    return this;
  }
  
  public String getHref() {
    return (String) get("href");
  }
}