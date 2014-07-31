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
package org.exoplatform.forum.service.rest.model;

import org.exoplatform.forum.service.rest.RestUtils;
import org.exoplatform.poll.service.Poll;

public class PollJson extends AbstractJson {
  private static final long serialVersionUID = 1L;

  public PollJson(Poll poll) {
    put("id", poll.getId());
    put("name", poll.getQuestion());
    put("owner", poll.getOwner());
    put("createdDate", RestUtils.formatDateToISO8601(poll.getCreatedDate()));
    put("updatedDate", RestUtils.formatDateToISO8601(poll.getModifiedDate()));
    //
    put("title", poll.getQuestion());
    put("timeout", String.valueOf(poll.getTimeOut()));
    //
    put("closed", String.valueOf(poll.getIsClosed()));
    put("againVote", String.valueOf(poll.getIsAgainVote()));
    put("multiCheck", String.valueOf(poll.getIsMultiCheck()));
    //
    put("options", poll.getOption());
    put("vote", poll.getVote());
  }

  public void setTopic(HrefLink topic) {
    put("topic", topic);
  }

  public void setTitle(String title) {
    put("title", title);
  }

  public void setTimeout(String timeout) {
    put("timeout", timeout);
  }

  public void setClosed(String closed) {
    put("closed", closed);
  }

  public void setAgainVote(String againVote) {
    put("againVote", againVote);
  }

  public void setMultiCheck(String multiCheck) {
    put("multiCheck", multiCheck);
  }

  public void setOptions(String[] options) {
    put("options", options);
  }

  public void setVote(String[] vote) {
    put("vote", vote);
  }

  public void setUsersVotes(String[] usersVotes) {
    put("usersVotes", usersVotes);
  }
}
