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
package org.exoplatform.forum.service.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.forum.service.rest.model.AbstractJson;
import org.exoplatform.forum.service.rest.model.HrefLink;

public class RestUtils {

  public static String formatDateToISO8601(Date date) {
    if (date == null) {
      return "";
    }
    TimeZone tz = TimeZone.getTimeZone("UTC");
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    df.setTimeZone(tz);
    return df.format(date);
  }
  
  /** 
   * Get base URL of rest service
   * 
   * @param type the type of rest service
   * @param id the id of object
   * @param restPath the path of rest /v1/forum/categories
   * 
   * @return base rest URL like : http://localhost:8080/rest/v1/forum/categories/123456
   */
  public static String getRestUrl(String type, String id, String restPath) {
    String version = restPath.split("/")[1];// v1
    String forumResource = restPath.split("/")[2]; // forum
    
    return new StringBuilder(getBaseRestUrl())
    .append("/").append(version)
    .append("/").append(forumResource)
    .append("/").append(type)
    .append("/").append(id).toString();
  }
  
  /** 
   * Get base url of rest service
   * 
   * @return base rest url like : http://localhost:8080/rest
   */
  public static String getBaseRestUrl() {
    return new StringBuilder(CommonsUtils.getCurrentDomain()).append("/").append(CommonsUtils.getRestContextName()).toString();
  }
  
  /**
   * @param attJson
   * @param isExtract
   * @return
   */
  public HrefLink getJsonHref(AbstractJson attJson, boolean isExtract) {
    if (isExtract) {
      return attJson;
    }
    return new HrefLink(attJson.getHref());
  }

  /**
   * @param attJsons
   * @param isExtract
   * @return
   */
  public HrefLink[] getJsonHrefByArray(AbstractJson[] attJsons, boolean isExtract) {
    HrefLink[] hrefs = new HrefLink[attJsons.length];
    for (int i = 0; i < attJsons.length; i++) {
      hrefs[i] = getJsonHref(attJsons[i], isExtract);
    }
    return hrefs;
  }
  
}
