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

import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.Forum;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.MessageBuilder;
import org.exoplatform.forum.service.Post;
import org.exoplatform.forum.service.UserProfile;
import org.exoplatform.forum.service.rest.category.impl.CategoryForumRestServiceV1.ResultCategories;
import org.exoplatform.forum.service.rest.category.impl.CategoryForumRestServiceV1.ResultForums;
import org.exoplatform.forum.service.rest.model.CategoryEntity;
import org.exoplatform.forum.service.test.AbstractResourceTest;
import org.exoplatform.services.rest.impl.ContainerResponse;
import org.exoplatform.services.rest.impl.MultivaluedMapImpl;
import org.json.JSONWriter;

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
    assertEquals(1, bean.getCategories().size());
    assertEquals("localhost:8080/rest/v1/forum/categories/" + category.getId(), bean.getCategories().get(0).get("href"));
    
  }
  
  public void testGetUpdateDeleteCategory() throws Exception {
    ForumService forumService = getService(ForumService.class);
    Category category = forumService.getCategory(categoryId);
    assertNotNull(category);
    
    startSessionAs("root");
    String eventURI = "/categories/" + categoryId;
    ContainerResponse response = performTestCase("GET", eventURI);
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    
    //assert data
    Map<String, Object> entity = (Map<String, Object>) response.getEntity();
    assertEquals(category.getId(), entity.get("id").toString());
    assertEquals(category.getCategoryName(), entity.get("name").toString());
    assertEquals(category.getOwner(), entity.get("owner").toString());
    
    StringWriter writer = new StringWriter();
    JSONWriter jsonWriter = new JSONWriter(writer);
    jsonWriter
        .object()
        .key("title")
        .value("hello world")
        .endObject();
    byte[] data = writer.getBuffer().toString().getBytes("UTF-8");

    MultivaluedMap<String, String> h = new MultivaluedMapImpl();
    h.putSingle("content-type", "application/json");
    h.putSingle("content-length", "" + data.length);
    
    UserProfile profile = forumService.getUserSettingProfile(USER_ROOT);
    profile.setUserRole(UserProfile.ADMIN);
    forumService.saveUserSettingProfile(profile);
    
    //update category name
    eventURI = "/categories/" + categoryId;
    response = service("PUT", fullPath + eventURI, "", h, data);
    assertNotNull(response);
    //assertEquals(200, response.getStatus());
    
    //delete category
    response = performTestCase("DELETE", eventURI);
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    category = forumService.getCategory(categoryId);
    assertNull(category);
  }
  
  public void testGetForums() throws Exception {
    ForumService forumService = getService(ForumService.class);
    
    Forum forum1 = createdForum();
    forum1.setIsClosed(true);
    forum1.setModerators(new String[] {USER_ROOT});
    Forum forum2 = createdForum();
    forum2.setIsLock(true);
    forum2.setModerators(new String[] {USER_ROOT});
    Forum forum3 = createdForum();
    forum3.setOwner(USER_JOHN);
    forumService.saveForum(categoryId, forum1, true);
    forumService.saveForum(categoryId, forum2, true);
    forumService.saveForum(categoryId, forum3, true);
    
    startSessionAs("root");
    
    //get all forums
    String eventURI = "/categories/" + categoryId + "/forums";
    ContainerResponse response = performTestCase("GET", eventURI);
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    ResultForums result = (ResultForums) response.getEntity();
    assertEquals(4, result.getForums().size());
    
    //get all forums owner by root
    eventURI = "/categories/" + categoryId + "/forums?owner=root";
    response = performTestCase("GET", eventURI);
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    result = (ResultForums) response.getEntity();
    assertEquals(3, result.getForums().size());
    
    //Get all locked forums
    eventURI = "/categories/" + categoryId + "/forums?locked=true";
    response = performTestCase("GET", eventURI);
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    result = (ResultForums) response.getEntity();
    assertEquals(1, result.getForums().size());
    
    //Get all closed forums
    eventURI = "/categories/" + categoryId + "/forums?closed=true";
    response = performTestCase("GET", eventURI);
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    result = (ResultForums) response.getEntity();
    assertEquals(1, result.getForums().size());
  }
  
  public void testGetRss() throws Exception {
    ForumService forumService = getService(ForumService.class);
    
    Post post = createdPost();
    post.setMessage("new post created");
    forumService.savePost(categoryId, forumId, topicId, post, true, new MessageBuilder());
    
    startSessionAs("root");
    
    String eventURI = "/categories/" + categoryId + "/rss";
    ContainerResponse response = performTestCase("GET", eventURI);
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    
    assertTrue(response.getEntity() instanceof InputStream);
  }
}
