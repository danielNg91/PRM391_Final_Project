package com.ngoctin.intuition.IntuitionStore.services.category;

import com.ngoctin.intuition.IntuitionStore.entities.category.Category;
import com.ngoctin.intuition.IntuitionStore.models.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    boolean createCategory(String categoryName);
    List<Category> searchByLikeName(String name);
}
