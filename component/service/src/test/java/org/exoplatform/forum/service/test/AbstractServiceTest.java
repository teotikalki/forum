/*
 * Copyright (C) 2003-2014 eXo Platform SAS.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.exoplatform.forum.service.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.RuntimeDelegate;

import org.exoplatform.commons.testing.BaseExoTestCase;
import org.exoplatform.component.test.ConfigurationUnit;
import org.exoplatform.component.test.ConfiguredBy;
import org.exoplatform.component.test.ContainerScope;
import org.exoplatform.component.test.KernelBootstrap;
import org.exoplatform.forum.common.jcr.KSDataLocation;
import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.Forum;
import org.exoplatform.forum.service.ForumAdministration;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.MessageBuilder;
import org.exoplatform.forum.service.Post;
import org.exoplatform.forum.service.Tag;
import org.exoplatform.forum.service.Topic;
import org.exoplatform.forum.service.UserProfile;
import org.exoplatform.forum.service.Utils;
import org.exoplatform.forum.service.impl.JCRDataStorage;
import org.exoplatform.forum.service.impl.model.UserProfileFilter;
import org.exoplatform.portal.config.UserACL;
import org.exoplatform.services.jcr.ext.app.SessionProviderService;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.jcr.util.IdGenerator;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;
import org.exoplatform.services.rest.impl.ApplicationContextImpl;
import org.exoplatform.services.rest.impl.ProviderBinder;
import org.exoplatform.services.rest.impl.RequestHandlerImpl;
import org.exoplatform.services.rest.impl.ResourceBinder;
import org.exoplatform.services.rest.impl.ResourcePublicationException;
import org.exoplatform.services.rest.impl.RuntimeDelegateImpl;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.services.security.Identity;
import org.exoplatform.services.security.MembershipEntry;

@ConfiguredBy({
  @ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/exo.portal.component.portal-configuration.xml"),
  @ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/exo.portal.component.test.jcr-configuration.xml"),
  @ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/exo.portal.component.identity-configuration.xml"),
  @ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/standalone/exo.forum.test.jcr-configuration.xml"),
  @ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/standalone/exo.forum.test.portal-configuration.xml"),
  @ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/standalone/exo.forum.component.core.test.configuration.xml"),
  @ConfigurationUnit(scope = ContainerScope.PORTAL, path = "conf/standalone/exo.forum.component.service.rest.configuration.xml")
})
public abstract class AbstractServiceTest extends BaseExoTestCase {
  protected static Log LOG = ExoLogger.getLogger(AbstractServiceTest.class.getName());
  protected static SessionProviderService sessionProviderService;
  protected Collection<MembershipEntry> membershipEntries = new ArrayList<MembershipEntry>();
  protected SessionProvider sessionProvider;
  protected ProviderBinder providerBinder;
  protected ResourceBinder resourceBinder;
  protected RequestHandlerImpl requestHandler;
  protected KSDataLocation dataLocation;

  protected static final String USER_ROOT = "root";
  protected static final String USER_DEMO = "demo";
  protected static final String USER_JOHN = "john";
  protected static final String USER_MARY = "mary";
  
  protected String categoryId ;
  protected String forumId ;
  protected String topicId ;

  public static KernelBootstrap bootstrap_ = null;


  protected void setUp() throws Exception {
    begin();
    //
    RuntimeDelegate.setInstance(new RuntimeDelegateImpl());
    //
    sessionProviderService = getService(SessionProviderService.class);
    resourceBinder = getService(ResourceBinder.class);
    requestHandler = getService(RequestHandlerImpl.class);
    // Reset providers to be sure it is clean
    ProviderBinder.setInstance(new ProviderBinder());
    providerBinder = ProviderBinder.getInstance();
    ApplicationContextImpl.setCurrent(new ApplicationContextImpl(null, null, providerBinder));
    resourceBinder.clear();
    configures();
  }
  
  private void configures() {
    UserACL acl = getService(UserACL.class);
    acl.setAdminMSType("manager");
  }

  protected void tearDown() throws Exception {
    endSession();
    end();
  }
  
  public <T> T getService(Class<T> clazz) {
    return (T) getContainer().getComponentInstanceOfType(clazz);
  }
  
  /**
   * registry resource object
   *
   * @param resource
   * @return
   * @throws Exception
   */
  public boolean registry(Object resource) throws Exception {
    try {
      addResource(resource, null);
      return true;
    } catch (ResourcePublicationException e) {
      return false;
    }
  }

  /**
   * registry resource class
   *
   * @param resourceClass
   * @return
   * @throws Exception
   */
  public boolean registry(Class<?> resourceClass) throws Exception {
    try {
      addResource(resourceClass, null);
      return true;
    } catch (ResourcePublicationException e) {
      return false;
    }
  }

   /**
    * Registers supplied class as per-request root resource if it has valid
    * JAX-RS annotations and no one resource with the same UriPattern already
    * registered.
    *
    * @param resourceClass class of candidate to be root resource
    * @param properties optional resource properties. It may contains additional
    *        info about resource, e.g. description of resource, its
    *        responsibility, etc. This info can be retrieved
    *        {@link org.exoplatform.services.rest.ObjectModel#getProperties()}. This parameter may be
    *        <code>null</code>
    */
  public void addResource(final Class<?> resourceClass, MultivaluedMap<String, String> properties) {
    resourceBinder.addResource(resourceClass, properties);
  }

  /**
    * Registers supplied Object as singleton root resource if it has valid JAX-RS
    * annotations and no one resource with the same UriPattern already
    * registered.
    *
    * @param resource candidate to be root resource
    * @param properties optional resource properties. It may contains additional
    *        info about resource, e.g. description of resource, its
    *        responsibility, etc. This info can be retrieved
    *        {@link org.exoplatform.services.rest.ObjectModel#getProperties()}. This parameter may be
    *        <code>null</code>
   */
  public void addResource(final Object resource, MultivaluedMap<String, String> properties) {
    resourceBinder.addResource(resource, properties);
  }

  /**
   * removeResource resource object
   *
   * @param resource
   * @return
   */
  public boolean removeResource(Object resource) {
    try {
      resourceBinder.removeResource(resource.getClass());
      return true;
    } catch (ResourcePublicationException e) {
      return false;
    }
  }

  /**
   * Removes the resource instance of provided class from root resource container.
   *
   * @param clazz the class of resource
   */
  public void removeResource(Class<?> clazz) {
    resourceBinder.removeResource(clazz);
  }

  protected void startSystemSession() {
    sessionProvider = sessionProviderService.getSystemSessionProvider(null);
  }

  protected void startSessionAs(String user) {
    Identity identity = new Identity(user);
    ConversationState state = new ConversationState(identity);
    ConversationState.setCurrent(state);
    sessionProviderService.setSessionProvider(null, new SessionProvider(state));
    sessionProvider = sessionProviderService.getSessionProvider(null);
  }

  protected void endSession() {
    sessionProviderService.removeSessionProvider(null);
    ConversationState.setCurrent(null);
    startSystemSession();
  }
  
  public void initDefaultForumData() throws Exception {
    ForumService forumService = getService(ForumService.class);
    Category cat = createCategory(getId(Utils.CATEGORY));
    this.categoryId = cat.getId();
    forumService.saveCategory(cat, true);
    Forum forum = createdForum();
    this.forumId = forum.getId();
    forumService.saveForum(categoryId, forum, true);
    Topic topic = createdTopic("root");
    forumService.saveTopic(categoryId, forumId, topic, true, false, new MessageBuilder());
    this.topicId = topic.getId();
  }

  public void removeAllData() throws Exception {
    ForumService forumService = getService(ForumService.class);
    List<Category> cats = getService(JCRDataStorage.class).getCategories();
    if (cats.size() > 0) {
      for (Category category : cats) {
        forumService.removeCategory(category.getId());
      }
    }
    JCRDataStorage storage = getService(JCRDataStorage.class);
    List<UserProfile> profiles = storage.searchUserProfileByFilter(new UserProfileFilter(""), 0, 10);
    for (UserProfile p : profiles) {
      storage.deleteUserProfile(p.getUserId());
    }
  }
  
  protected void resetAllUserProfile() throws Exception {
    // reset all user profile
    Node node = dataLocation.getSessionManager().openSession().getRootNode();
    NodeIterator iterator = node.getNode(dataLocation.getUserProfilesLocation()).getNodes();
    while (iterator.hasNext()) {
      Node n = iterator.nextNode();
      if (n.isNodeType(Utils.EXO_FORUM_USER_PROFILE)) {
        n.setProperty(Utils.EXO_TOTAL_POST, 0);
        n.setProperty(Utils.EXO_TOTAL_TOPIC, 0);
        n.setProperty(Utils.EXO_MODERATE_CATEGORY, new String[] {});
        n.setProperty(Utils.EXO_MODERATE_FORUMS, new String[] {});
      }
    }
    node.getSession().save();
  }

  public String ArrayToString(String[] strs) {
    List<String> list = Arrays.asList(strs);
    Collections.sort(list);
    return list.toString().replace("[", "").replace("]", "");
  }

  public UserProfile createdUserProfile(String userName) {
    UserProfile userProfile = new UserProfile();
    userProfile.setUserId(userName);
    userProfile.setUserRole((long) 0);
    userProfile.setUserTitle(Utils.ADMIN);
    userProfile.setScreenName(userName);
    userProfile.setEmail("duytucntt@gmail.com");
    userProfile.setJoinedDate(new Date());
    userProfile.setTimeZone(7.0);
    userProfile.setSignature("signature");
    return userProfile;
  }

  public Post createdPost() {
    Post post = new Post();
    post.setOwner(USER_ROOT);
    post.setCreatedDate(new Date());
    post.setModifiedBy(USER_ROOT);
    post.setModifiedDate(new Date());
    post.setName("SubJect");
    post.setMessage("content description");
    post.setRemoteAddr("192.168.1.11");
    post.setIcon("classNameIcon");
    post.setIsApproved(true);
    post.setIsActiveByTopic(true);
    post.setIsHidden(false);
    post.setIsWaiting(false);
    return post;
  }

  public Topic createdTopic(String owner) {
    Topic topicNew = new Topic();

    topicNew.setOwner(owner);
    topicNew.setTopicName("TestTopic");
    topicNew.setCreatedDate(new Date());
    topicNew.setModifiedBy("root");
    topicNew.setModifiedDate(new Date());
    topicNew.setLastPostBy("root");
    topicNew.setLastPostDate(new Date());
    topicNew.setDescription("Topic description");
    topicNew.setPostCount(0);
    topicNew.setViewCount(0);
    topicNew.setIsNotifyWhenAddPost("");
    topicNew.setIsModeratePost(false);
    topicNew.setIsClosed(false);
    topicNew.setIsLock(false);
    topicNew.setIsWaiting(false);
    topicNew.setIsActive(true);
    topicNew.setIcon("classNameIcon");
    topicNew.setIsApproved(true);
    topicNew.setCanView(new String[] {});
    topicNew.setCanPost(new String[] {});
    return topicNew;
  }
  
  public Topic createTopic(String owner, int index) {
    Topic topic = createdTopic(owner);
    topic.setTopicName("TestTopic " + index);
    return topic;

  }

  public Forum createdForum() {
    Forum forum = new Forum();
    forum.setOwner("root");
    forum.setForumName("TestForum");
    forum.setForumOrder(1);
    forum.setCreatedDate(new Date());
    forum.setModifiedBy("root");
    forum.setModifiedDate(new Date());
    forum.setLastTopicPath("");
    forum.setDescription("description");
    forum.setPostCount(0);
    forum.setTopicCount(0);

    forum.setNotifyWhenAddTopic(new String[] {});
    forum.setNotifyWhenAddPost(new String[] {});
    forum.setIsModeratePost(false);
    forum.setIsModerateTopic(false);
    forum.setIsClosed(false);
    forum.setIsLock(false);

    forum.setViewer(new String[] {});
    forum.setCreateTopicRole(new String[] {""});
    forum.setModerators(new String[] {});
    return forum;
  }

  public Category createCategory(String id) {
    Category cat = new Category(id);
    cat.setOwner("root");
    cat.setCategoryName("testCategory");
    cat.setCategoryOrder(1);
    cat.setCreatedDate(new Date());
    cat.setDescription("desciption");
    cat.setModifiedBy("root");
    cat.setModifiedDate(new Date());
    return cat;
  }

  public String getId(String type) {
    try {
      return type + IdGenerator.generate();
    } catch (Exception e) {
      return type + String.valueOf(new Random().nextLong());
    }
  }

  protected Tag createTag(String name, String user) {
    Tag tag = new Tag();
    tag.setName(name);
    tag.setId(Utils.TAG + name);
    tag.setUserTag(new String[] { user });
    return tag;
  }

  public ForumAdministration createForumAdministration() {
    ForumAdministration forumAdministration = new ForumAdministration();
    forumAdministration.setForumSortBy("forumName");
    forumAdministration.setForumSortByType("ascending");
    forumAdministration.setTopicSortBy("threadName");
    forumAdministration.setTopicSortByType("ascending");
    forumAdministration.setCensoredKeyword("");
    forumAdministration.setEnableHeaderSubject(false);
    forumAdministration.setHeaderSubject("");
    forumAdministration.setNotifyEmailContent("");
    return forumAdministration;
  }

  public void setMembershipEntry(String group, String membershipType, boolean isNew) {
    MembershipEntry membershipEntry = new MembershipEntry(group, membershipType);
    if (isNew) {
      membershipEntries.clear();
    }
    membershipEntries.add(membershipEntry);
  }

  public void loginUser(String userId) {
    Identity identity = new Identity(userId, membershipEntries);
    ConversationState state = new ConversationState(identity);
    ConversationState.setCurrent(state);
  }


}
