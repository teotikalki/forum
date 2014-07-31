package org.exoplatform.forum.service.impl.model;

import java.util.List;

import org.exoplatform.forum.service.DataStorage;
import org.exoplatform.forum.service.Forum;
import org.exoplatform.forum.service.filter.model.ForumFilter;

public class ForumListAccess extends AbstractListAccess<Forum>  {
  
  private DataStorage storage;
  private ForumFilter filter;

  public ForumListAccess(DataStorage storage, ForumFilter filter) {
    this.storage = storage;
    this.filter = filter;
  }
  
  @Override
  public Forum[] load(int offset, int limit) throws Exception, IllegalArgumentException {
    List<Forum> forums = storage.getForums(filter, offset, limit);
    return forums.toArray(new Forum[forums.size()]);
  }

  @Override
  public int getSize() throws Exception {
    return storage.getForumsCount(filter);
  }

  @Override
  public Forum[] load(int pageSelect) throws Exception, IllegalArgumentException {
    // TODO Auto-generated method stub
    return null;
  }

}
