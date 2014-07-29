package org.exoplatform.forum.service.rest;

import java.net.URI;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.RuntimeDelegate;

import org.exoplatform.commons.utils.CommonsUtils;
import org.exoplatform.forum.common.CommonUtils;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.services.rest.impl.RuntimeDelegateImpl;
import org.exoplatform.services.security.ConversationState;
import org.exoplatform.services.security.Identity;
import org.exoplatform.services.security.IdentityConstants;

public abstract class AbstractForumRestServiceImpl {

  protected static final CacheControl cc;
  static {
    RuntimeDelegate.setInstance(new RuntimeDelegateImpl());
    cc = new CacheControl();
    cc.setNoCache(true);
    cc.setNoStore(true);
  }

  protected String getUserId(SecurityContext sc, UriInfo uriInfo) {
    try {
      String userId = getUserId();
      return (CommonUtils.isEmpty(userId)) ? sc.getUserPrincipal().getName() : userId;
    } catch (NullPointerException e) {
      return getViewerId(uriInfo);
    } catch (Exception e) {
      return null;
    }
  }
  
  private String getUserId() {
    try {
      ConversationState conversionState = ConversationState.getCurrent();
      Identity identity = conversionState.getIdentity();
      String userId = identity.getUserId();
      return (CommonUtils.isEmpty(userId) || IdentityConstants.ANONIM.equals(userId)) ? null : userId;
    } catch (Exception e) {
      return null;
    }
  }

  private String getViewerId(UriInfo uriInfo) {
    URI uri = uriInfo.getRequestUri();
    String requestString = uri.getQuery();
    if (requestString == null) {
      return null;
    }
    String[] queryParts = requestString.split("&");
    for (String queryPart : queryParts) {
      if (queryPart.startsWith("opensocial_viewer_id")) {
        return queryPart.substring(queryPart.indexOf("=") + 1, queryPart.length());
      }
    }
    return null;
  }

  protected ForumService getForumService() {
    return CommonsUtils.getService(ForumService.class);
  }
}
