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

  protected HrefLink topic;
  protected String title;
  protected String timeout;
  protected String closed;
  protected String againVote;
  protected String multiCheck;
  protected String[] options;
  protected String[] vote;

  protected HrefLink[] usersVotes;
  
  public PollJson(Poll poll) {
    this.id = poll.getId();
    this.name = poll.getQuestion();
    this.owner = poll.getOwner();
    this.createdDate = RestUtils.formatDateToISO8601(poll.getCreatedDate());
    this.updatedDate = RestUtils.formatDateToISO8601(poll.getModifiedDate());
    //
    this.title = poll.getQuestion();
    this.timeout = String.valueOf(poll.getTimeOut());
    //
    this.closed = String.valueOf(poll.getIsClosed());
    this.againVote = String.valueOf(poll.getIsAgainVote());
    this.multiCheck = String.valueOf(poll.getIsMultiCheck());
    //
    this.options = poll.getOption();
    this.vote = poll.getVote();
  }
}
