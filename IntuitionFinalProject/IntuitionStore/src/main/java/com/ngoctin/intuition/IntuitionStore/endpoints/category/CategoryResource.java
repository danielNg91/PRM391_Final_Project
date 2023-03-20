package com.ngoctin.intuition.IntuitionStore.endpoints.category;

import com.ngoctin.intuition.IntuitionStore.entities.category.Category;
import com.ngoctin.intuition.IntuitionStore.models.category.CategoryResponse;
import com.ngoctin.intuition.IntuitionStore.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
@RequiredArgsConstructor
public class CategoryResource {

    private final CategoryService categoryService;

    @PostMapping(path = "/createCategory/{categoryName}")
    ResponseEntity<?> createCategory(@PathVariable(name = "categoryName") String categoryName){
        if(categoryService.createCategory(categoryName)){
            return ResponseEntity.status(HttpStatus.OK).body("Create Categoty Successfully !");
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Create Categoty Failed !");
    }
    @GetMapping("/searchByLikeName/{name}")
    public ResponseEntity<?>  searchByLikeName(@PathVariable("name") String name){
        List<Category> categoryList= categoryService.searchByLikeName(name);
        if(categoryList != null){
            return ResponseEntity.status(HttpStatus.OK).body(categoryList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found !");
    }
}
