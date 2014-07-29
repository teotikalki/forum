package org.exoplatform.forum.service.impl.model;

import java.util.List;

import org.exoplatform.forum.service.Category;
import org.exoplatform.forum.service.DataStorage;
import org.exoplatform.forum.service.filter.model.CategoryFilter;

public class CategoryListAccess extends AbstractListAccess<Category> {
  
  private DataStorage storage;
  private CategoryFilter filter;
  
  public CategoryListAccess(DataStorage storage, CategoryFilter filter) {
    this.storage = storage;
    this.filter = filter;
  }
  
  public Category[] load(int offset, int limit) throws Exception, IllegalArgumentException {
    List<Category> categories = storage.getCategories(filter, offset, limit);
    return categories.toArray(new Category[categories.size()]);
  }

  public int getSize() throws Exception {
    return storage.getCategoriesCount(filter);
  }

  public Category[] load(int pageSelect) throws Exception, IllegalArgumentException {
    int offset = getOffset(pageSelect);
    int limit = getPageSize();
    setCurrentPage(pageSelect);
    return load(offset, limit);
  }

}
