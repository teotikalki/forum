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

public class PollEntity extends AbstractEntity {

  public PollEntity(Poll poll) {
    setProperty("id", poll.getId());
    setProperty("name", poll.getQuestion());
    setProperty("owner", poll.getOwner());
    setProperty("createdDate", RestUtils.formatDateToISO8601(poll.getCreatedDate()));
    setProperty("updatedDate", RestUtils.formatDateToISO8601(poll.getModifiedDate()));
    //
    setProperty("title", poll.getQuestion());
    setProperty("timeout", String.valueOf(poll.getTimeOut()));
    //
    setProperty("closed", String.valueOf(poll.getIsClosed()));
    setProperty("againVote", String.valueOf(poll.getIsAgainVote()));
    setProperty("multiCheck", String.valueOf(poll.getIsMultiCheck()));
    //
    setProperty("options", poll.getOption());
    setProperty("vote", poll.getVote());
  }

  public void setTopic(BaseEntity topic) {
    setProperty("topic", topic);
  }

  public void setTitle(String title) {
    setProperty("title", title);
  }

  public void setTimeout(String timeout) {
    setProperty("timeout", timeout);
  }

  public void setClosed(String closed) {
    setProperty("closed", closed);
  }

  public void setAgainVote(String againVote) {
    setProperty("againVote", againVote);
  }

  public void setMultiCheck(String multiCheck) {
    setProperty("multiCheck", multiCheck);
  }

  public void setOptions(String[] options) {
    setProperty("options", options);
  }

  public void setVote(String[] vote) {
    setProperty("vote", vote);
  }

  public void setUsersVotes(String[] usersVotes) {
    setProperty("usersVotes", usersVotes);
  }
}
