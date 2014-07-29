package org.exoplatform.forum.service.cache.model.key;

import org.exoplatform.forum.common.cache.model.ScopeCacheKey;
import org.exoplatform.forum.service.filter.model.CategoryFilter;

public class CategoryListKey extends ScopeCacheKey {

  private final String foo;
  private CategoryFilter filter;
  private int offset;
  private int limit;

  public CategoryListKey(String foo) {
    this.foo = foo;
  }
  
  public CategoryListKey(CategoryFilter filter, int offset, int limit) {
    this.foo = "";
    this.filter = filter;
    this.offset = offset;
    this.limit = limit;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CategoryListKey)) return false;
    if (!super.equals(o)) return false;

    CategoryListKey that = (CategoryListKey) o;

    if (limit != that.limit) return false;
    if (offset != that.offset) return false;
    
    if (foo != null ? !foo.equals(that.foo) : that.foo != null) return false;

    return true;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (foo != null ? foo.hashCode() : 0);
    result = 31 * result + (filter != null && filter.getCategoryName() != null ? filter.getCategoryName().hashCode() : 0);
    result = 31 * result + offset;
    result = 31 * result + limit;
    return result;
  }

}
