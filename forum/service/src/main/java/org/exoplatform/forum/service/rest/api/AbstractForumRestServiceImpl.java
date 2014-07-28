package org.exoplatform.forum.service.rest.api;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.ext.RuntimeDelegate;

import org.exoplatform.services.rest.impl.RuntimeDelegateImpl;

public abstract class AbstractForumRestServiceImpl {

  protected static final CacheControl         cc;
  static {
    RuntimeDelegate.setInstance(new RuntimeDelegateImpl());
    cc = new CacheControl();
    cc.setNoCache(true);
    cc.setNoStore(true);
  }
}
