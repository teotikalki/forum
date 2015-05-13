/*
 * Copyright (C) 2003-2013 eXo Platform SAS.
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
package org.exoplatform.forum.service.updater;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;

import org.exoplatform.commons.upgrade.UpgradeProductPlugin;
import org.exoplatform.commons.version.util.VersionComparator;
import org.exoplatform.container.xml.InitParams;
import org.exoplatform.forum.common.CommonUtils;
import org.exoplatform.forum.common.jcr.KSDataLocation;
import org.exoplatform.forum.common.jcr.PropertyReader;
import org.exoplatform.forum.service.Utils;
import org.exoplatform.management.annotations.Managed;
import org.exoplatform.management.annotations.ManagedDescription;
import org.exoplatform.management.jmx.annotations.NameTemplate;
import org.exoplatform.management.jmx.annotations.Property;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.jcr.impl.core.query.QueryImpl;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;


@Managed
@NameTemplate( { @Property(key = "product.group.id", value = "org.exoplatform.forum") })
@ManagedDescription("Plugin that allows to migration number post count of forum from 4.1-M1, 4.1.0 to 4.2.0")
public class ForumPostCountUpdaterPlugin extends UpgradeProductPlugin {
  private static Log LOG      = ExoLogger.getLogger(ForumPostCountUpdaterPlugin.class);
  
  public ForumPostCountUpdaterPlugin(InitParams initParams) {
    super(initParams);
  }

  @Override
  public void processUpgrade(String oldVersion, String newVersion) {
    long start = System.currentTimeMillis();
    try {
      upgradeForumPostCount();
      LOG.info(String.format("Successfully to migrate forum post count from %s to %s", oldVersion, newVersion));
    } catch (Exception e) {
      LOG.warn(String.format("Failed to migrate forum from %s to %s", oldVersion, newVersion), e);
    } finally {
      LOG.info(String.format("Done to migrated forum post count on time: %s(ms)", (System.currentTimeMillis() -start)));
    }
  }

  private void upgradeForumPostCount() throws Exception {
    KSDataLocation dataLocator = CommonUtils.getComponent(KSDataLocation.class);
    SessionProvider sProvider = CommonUtils.createSystemProvider();
    Session session = dataLocator.getSessionManager().getSession(sProvider);
    String strQuery = new StringBuilder("SELECT ").append(Utils.EXO_POST_COUNT).append(" FROM ").append(Utils.EXO_FORUM).toString();
    QueryManager qm = session.getWorkspace().getQueryManager();
    QueryImpl query = (QueryImpl) qm.createQuery(strQuery, Query.SQL);
    NodeIterator forumNodes = query.execute().getNodes();
    while (forumNodes.hasNext()) {
      Node forumNode = forumNodes.nextNode();
      updatePostCount(forumNode, qm);
    }
  }

  private void updatePostCount(Node forumNode, QueryManager qm) {
    try {
      StringBuilder strQuery = jcrPathLikeAndNotLikeTopic(forumNode.getPath());
      strQuery.append(" AND ").append(Utils.EXO_IS_ACTIVE).append("='true'")
              .append(" AND ").append(Utils.EXO_IS_APPROVED).append("='true'")
              .append(" AND ").append(Utils.EXO_IS_WAITING).append("='false'");
      //
      QueryImpl query = (QueryImpl) qm.createQuery(strQuery.toString(), Query.SQL);
      NodeIterator topicNodes = query.execute().getNodes();

      long postCount = 0;
      while (topicNodes.hasNext()) {
        // topic post
        ++postCount;
        // posts on a topic
        postCount += new PropertyReader(topicNodes.nextNode()).l(Utils.EXO_POST_COUNT, 0l);
      }
      //
      forumNode.setProperty(Utils.EXO_POST_COUNT, postCount);
      //
      forumNode.getSession().save();
    } catch (Exception e) {
      LOG.warn(String.format("Upgrade the forum %s is unsuccessful", forumNode.toString()), e);
    }
  }

  @Override
  public boolean shouldProceedToUpgrade(String newVersion, String previousVersion) {
    return VersionComparator.isAfter(newVersion, previousVersion);
  }

  private StringBuilder jcrPathLikeAndNotLikeTopic(String fullPath) {
    StringBuilder sqlQuery = new StringBuilder("SELECT ").append(Utils.EXO_POST_COUNT).append(" FROM ").append(Utils.EXO_TOPIC)
        .append(" WHERE (").append("jcr:path LIKE '").append(fullPath)
        .append("/%' AND NOT jcr:path LIKE '").append(fullPath).append("/%/%'").append(")");
    return sqlQuery;
  }
}
