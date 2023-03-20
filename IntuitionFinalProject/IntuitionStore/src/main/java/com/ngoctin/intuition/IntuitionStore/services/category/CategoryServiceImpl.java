package com.ngoctin.intuition.IntuitionStore.services.category;

import com.ngoctin.intuition.IntuitionStore.entities.category.Category;
import com.ngoctin.intuition.IntuitionStore.models.category.CategoryResponse;
import com.ngoctin.intuition.IntuitionStore.repositories.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public boolean createCategory(String categoryName) {
        try {
            if(categoryRepository.createCategory(categoryName)){
                return true;
            }
            return false;
        }catch (Exception exception){
            log.error("Exception : " + exception.getMessage());
            return false;
        }
    }

    @Override
    public List<Category> searchByLikeName(String name) {
        try {
            return categoryRepository.searchByLikeName(name);
        } catch (Exception exception) {
            return null;
        }
    }
}
