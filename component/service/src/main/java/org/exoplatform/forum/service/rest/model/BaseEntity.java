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
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class BaseEntity {
  protected Map<String, Object> datas = new HashMap<String, Object>();

  public BaseEntity() {
  }

  protected void setProperty(String name, Object value) {
    datas.put(name, value);
  }

  protected Object getProperty(String name) {
    return datas.get(name);
  }

  public BaseEntity(String href) {
    if (!StringUtils.isEmpty(href)) {
      setProperty("href", href);
    }
  }

  public BaseEntity setHref(String href) {
    setProperty("href", href);
    return this;
  }

  public BaseEntity setHref(BaseEntity href) {
    setProperty("href", href);
    return this;
  }

  public String getHref() {
    return (String) datas.get("href");
  }

  public Map<String, Object> getData() {
    return datas;
  }
}