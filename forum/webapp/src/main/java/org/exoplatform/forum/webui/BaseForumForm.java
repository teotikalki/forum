/*
 * Copyright (C) 2003-2009 eXo Platform SAS.
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
package org.exoplatform.forum.webui;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.forum.ForumUtils;
import org.exoplatform.forum.common.CommonUtils;
import org.exoplatform.forum.common.webui.BaseUIForm;
import org.exoplatform.forum.common.webui.UIGroupSelector;
import org.exoplatform.forum.common.webui.UIPopupContainer;
import org.exoplatform.forum.common.webui.UIUserSelect;
import org.exoplatform.forum.service.ForumService;
import org.exoplatform.forum.service.UserProfile;
import org.exoplatform.forum.service.Watch;
import org.exoplatform.web.application.RequestContext;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.UIPopupWindow;

/**
 * Base class for UIForm used in forum application.
 * Provides convenience methods to access the service
 * @author <a href="mailto:patrice.lamarque@exoplatform.com">Patrice Lamarque</a>
 * @version $Revision$
 */
public class BaseForumForm extends BaseUIForm {

  private ForumService forumService;

  public UserProfile   userProfile = null;

  public List<Watch>   listWatches = new ArrayList<Watch>();

  private boolean isAddColonInLabel = false;
  /**
   * Get a reference to the forum service
   * @return
   */
  protected ForumService getForumService() {
    if (forumService == null) {
      forumService = (ForumService) ExoContainerContext.getCurrentContainer().getComponentInstanceOfType(ForumService.class);
    }
    return forumService;
  }
  @Override
  public void processRender(WebuiRequestContext context) throws Exception {
    getUserProfile();
    ForumUtils.addScripts(null, null, "eXo.forum.UIForumPortlet.initTooltip('" + getId() + "');");
    if (getTemplate() != null)
    {
       super.processRender(context);
       return;
    }
    Writer writer = context.getWriter();
    writer.append("<div class='UIForm ").append(getId()).append("'>");
    begin();
    renderChildren(context);
    end();
    writer.append("</div>");
  }

  /**
   * Set forum service (used by unit tests)
   * @param forumService
   */
  protected void setForumService(ForumService forumService) {
    this.forumService = forumService;
  }

  public UISliderControl getUISliderControl(String name) {
     return findComponentById(name);
  }
  
  @Override
  public String getLabel(ResourceBundle res, String id) {
    String label = super.getLabel(res, id);
    if(isAddColonInLabel) {
      return String.format("%s%s", label, CommonUtils.COLON);
    }
    return label;
  }

  public boolean isAddColonInLabel() {
    return isAddColonInLabel;
  }

  public void setAddColonInLabel(boolean isAddColonInLabel) {
    this.isAddColonInLabel = isAddColonInLabel;
  }

  public UserProfile getUserProfile() {
    if (userProfile == null) {
      try {
        userProfile = getAncestorOfType(UIForumPortlet.class).getUserProfile();
      } catch (Exception e) {
        userProfile = new UserProfile();
      }
    }
    return userProfile;
  }

  protected void setListWatches() throws Exception {
    listWatches = getForumService().getWatchByUser(getUserProfile().getUserId());
  }

  protected boolean isWatching(String path) throws Exception {
    for (Watch watch : listWatches) {
      if (path.equals(watch.getNodePath()) && watch.isAddWatchByEmail())
        return true;
    }
    return false;
  }

  protected String getEmailWatching(String path) throws Exception {
    for (Watch watch : listWatches) {
      try {
        if (watch.getNodePath().endsWith(path))
          return watch.getEmail();
      } catch (Exception e) {
        log.debug("Failed to check email watching.");
      }
    }
    return ForumUtils.EMPTY_STR;
  }

  protected String getScreenName(String userName) throws Exception {
    return getForumService().getScreenName(userName);
  }

  protected String getShortScreenName(String screenName) throws Exception {
    if (screenName != null && screenName.length() > 17 && !screenName.trim().contains(" ")) {
      boolean isDelted = false;
      if (screenName.indexOf("<s>") >= 0) {
        screenName = screenName.replaceAll("<s>", ForumUtils.EMPTY_STR).replaceAll("</s>", ForumUtils.EMPTY_STR);
        isDelted = true;
      }
      screenName = (new StringBuilder().append("<span title=\"").append(screenName).append("\">").append(((isDelted) ? "<s>" : ForumUtils.EMPTY_STR)).append(ForumUtils.getSubString(screenName, 15)).append(((isDelted) ? "</s></span>" : "</span>"))).toString();
    }
    return screenName;
  }

  protected <T extends UIComponent> T openPopup(Class<T> componentType, String popupId, int width, int height) throws Exception {
    UIForumPortlet forumPortlet = getAncestorOfType(UIForumPortlet.class);
    return openPopup(forumPortlet, componentType, popupId, width, height);
  }

  protected <T extends UIComponent> T openPopup(Class<T> componentType, int width, int height) throws Exception {
    UIForumPortlet forumPortlet = getAncestorOfType(UIForumPortlet.class);
    return openPopup(forumPortlet, componentType, width, height);
  }

  protected <T extends UIComponent> T openPopup(Class<T> componentType, int width) throws Exception {
    return openPopup(componentType, width, 0);
  }

  protected <T extends UIComponent> T openPopup(Class<T> componentType, String popupId, int width) throws Exception {
    return openPopup(componentType, popupId, width, 0);
  }

  protected boolean addWatch(String path, UserProfile userProfile) {
    List<String> values = new ArrayList<String>();
    try {
      values.add(userProfile.getEmail());
      getForumService().addWatch(1, path, values, userProfile.getUserId());
      setListWatches();
      info("UIAddWatchingForm.msg.successfully", false);
      return true;
    } catch (Exception e) {
      warning("UIAddWatchingForm.msg.fall", false);
      return false;
    }
  }

  protected boolean unWatch(String path, UserProfile userProfile) {
    try {
      getForumService().removeWatch(1, path, userProfile.getUserId() + ForumUtils.SLASH + getEmailWatching(path));
      setListWatches();
      info("UIAddWatchingForm.msg.UnWatchSuccessfully", false);
      return true;
    } catch (Exception e) {
      warning("UIAddWatchingForm.msg.UnWatchfall", false);
      log.debug("Failed to add watch.");
      return false;
    }
  }

  protected static void closePopupWindow(UIPopupWindow popupWindow) {
    popupWindow.setUIComponent(null);
    popupWindow.setShow(false);
    popupWindow.setRendered(false);
    WebuiRequestContext context = RequestContext.getCurrentInstance();
    context.addUIComponentToUpdateByAjax(popupWindow.getParent());
  }

  protected void showUIUserSelect(UIPopupContainer uiPopupContainer, String popupWinDowId, String id) throws Exception {
    UIGroupSelector uiGroupSelector = uiPopupContainer.findFirstComponentOfType(UIGroupSelector.class);
    if (uiGroupSelector != null) {
      UIPopupWindow popupWindow = uiGroupSelector.getAncestorOfType(UIPopupWindow.class);
      closePopupWindow(popupWindow);
    }
    UIPopupWindow uiPopupWindow = uiPopupContainer.getChildById(popupWinDowId);
    if (uiPopupWindow == null)
      uiPopupWindow = uiPopupContainer.addChild(UIPopupWindow.class, popupWinDowId, popupWinDowId);
    UIUserSelect uiUserSelector = uiPopupContainer.createUIComponent(UIUserSelect.class, null, "UIUserSelector");
    uiUserSelector.setShowSearch(true);
    uiUserSelector.setShowSearchUser(true);
    uiUserSelector.setShowSearchGroup(false);
    uiUserSelector.setPermisionType(id);
    uiUserSelector.setSpaceGroupId(getAncestorOfType(UIForumPortlet.class).getSpaceGroupId());
    uiPopupWindow.setUIComponent(uiUserSelector);
    uiPopupWindow.setShow(true);
    uiPopupWindow.setWindowSize(740, 400);
    uiPopupWindow.setRendered(true);
    uiPopupContainer.setRendered(true);
  }

}
