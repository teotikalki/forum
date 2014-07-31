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

import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.rest.category.impl.CategoryForumRestServiceV1.ResultCategories;
import org.exoplatform.forum.service.test.AbstractResourceTest;
import org.exoplatform.services.rest.impl.ContainerResponse;

public class CategoryForumRestServiceTest extends AbstractResourceTest {
  static final String fullPath = "http://localhost:8080/v1/forum";
  
  private CategoryForumRestService categoryRestService;
  

  public void setUp() throws Exception {
    super.setUp();
    System.setProperty("gatein.email.domain.url", "localhost:8080");
    //
    categoryRestService = getService(CategoryForumRestService.class);
    registry(categoryRestService);
    //
    initDefaultForumData();
  }

  public void tearDown() throws Exception {
    removeAllData();
    super.tearDown();
  }
  
  public ContainerResponse performTestCase(String methodType, String path) throws Exception {
    return performTestCase(methodType, fullPath, path);
  }
  
  public void testGetCategories() throws Exception  {
    ForumService forumService = getService(ForumService.class);
    Category category = forumService.getCategory(categoryId);
    assertNotNull(category);
    
    startSessionAs("root");
    String eventURI = "/categories";
    ContainerResponse response = performTestCase("GET", eventURI);
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    
    // assert data
    ResultCategories bean = (ResultCategories)response.getEntity();
    assertEquals(1, bean.categories.size());
    assertEquals("localhost:8080/rest/v1/forum/categories/" + category.getId(), bean.categories.get(0).getHref());
    
    endSession();
  }
}
