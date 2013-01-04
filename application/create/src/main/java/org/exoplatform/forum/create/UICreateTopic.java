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
package org.exoplatform.forum.create;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.forum.common.CommonUtils;
import org.exoplatform.forum.common.webui.BaseUIForm;
import org.exoplatform.portal.application.PortalRequestContext;
import org.exoplatform.portal.webui.util.Util;
import org.exoplatform.webui.application.WebuiRequestContext;
import org.exoplatform.webui.config.annotation.ComponentConfig;
import org.exoplatform.webui.config.annotation.EventConfig;
import org.exoplatform.webui.core.UIComponent;
import org.exoplatform.webui.core.lifecycle.UIFormLifecycle;
import org.exoplatform.webui.core.model.SelectItemOption;
import org.exoplatform.webui.event.Event;
import org.exoplatform.webui.event.Event.Phase;
import org.exoplatform.webui.event.EventListener;
import org.exoplatform.webui.form.UIFormSelectBox;

/**
 * Created by The eXo Platform SAS
 * Author : Vu Duy Tu
 *          tuvd@exoplatform.com
 * Jan 2, 2013  
 */

@ComponentConfig(lifecycle = UIFormLifecycle.class, 
  template = "classpath:groovy/webui/forum/create/UICreate.gtmpl", 
  events = {
    @EventConfig(listeners = UICreateTopic.NextActionListener.class, phase = Phase.DECODE),
    @EventConfig(listeners = UICreateForm.CancelActionListener.class, phase = Phase.DECODE) 
  }
)
public class UICreateTopic extends UICreateForm {

  
  public UICreateTopic() {

  }
  
  static public class NextActionListener extends EventListener<UICreateTopic> {

    public void execute(Event<UICreateTopic> event) throws Exception {
      UICreateTopic createTopic = event.getSource();
      nextAction(createTopic, ACTION_TYPE.CREATE_TOPIC, event.getRequestContext());
    }
  }

}
