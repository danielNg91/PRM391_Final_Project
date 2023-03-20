package com.ngoctin.intuition.IntuitionStore.repositories.category;

import com.ngoctin.intuition.IntuitionStore.entities.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>,MyCategoryRepository {
}
