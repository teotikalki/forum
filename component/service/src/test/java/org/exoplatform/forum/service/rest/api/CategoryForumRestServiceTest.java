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
package org.exoplatform.forum.service.rest.api;

import java.util.List;

import org.exoplatform.forum.service.rest.model.CategoryJson;
import org.exoplatform.forum.service.test.AbstractResourceTest;
import org.exoplatform.services.rest.impl.ContainerResponse;

public class CategoryForumRestServiceTest extends AbstractResourceTest {
  static final String fullPath = "http://localhost:8080/v1/forum";
  
  private CategoryForumRestService categoryRestService;
  

  public void setUp() throws Exception {
    super.setUp();
    //
    categoryRestService = getService(CategoryForumRestService.class);
    registry(categoryRestService);
    //
    initDefaultForumData();
  }

  public void tearDown() throws Exception {
    super.tearDown();
  }
  
  public ContainerResponse performTestCase(String methodType, String path) throws Exception {
    return performTestCase(methodType, fullPath, path);
  }
  
  public void testGetCategories() throws Exception  {
    String eventURI = "/categories";
    ContainerResponse response = performTestCase("GET", eventURI);
    assertNotNull(response);
    assertEquals(response.getStatus(), 200);
    
    // assert data
    List<?> bean = (List<?>)response.getEntity();
    assertEquals(bean.size(), 1);
    assertTrue(bean.get(0) instanceof CategoryJson);
  }
}
