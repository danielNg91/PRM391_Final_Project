package com.ngoctin.intuition.IntuitionStore.repositories.category;

import com.ngoctin.intuition.IntuitionStore.entities.category.Category;
import com.ngoctin.intuition.IntuitionStore.models.category.CategoryResponse;

import java.util.List;

public interface MyCategoryRepository {
    boolean createCategory(String categoryName);
    List<Category> searchByLikeName(String name);
}
