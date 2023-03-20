package com.ngoctin.intuition.IntuitionStore.endpoints.product;

import com.ngoctin.intuition.IntuitionStore.entities.product.Product;
import com.ngoctin.intuition.IntuitionStore.models.product.CreateProductRequest;
import com.ngoctin.intuition.IntuitionStore.models.product.ProductResponse;
import com.ngoctin.intuition.IntuitionStore.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductResource{

    private final ProductService productService;

    @PostMapping(path = "/createProduct")
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRequest createProductRequest){
        if(productService.createProduct(createProductRequest.getProduct(), createProductRequest.getCateID())){
            return ResponseEntity.status(HttpStatus.OK).body("Create Product Successfully !");
        }
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).body("Create Product Failed !");
    }

    @GetMapping(path = "/getAllProducts")
    public ResponseEntity<?> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        if(products != null && products.size() != 0){
            List<ProductResponse> response = new ArrayList<>();
            products.forEach(product -> {
                int id = Integer.parseInt(product.getId()+"");
                response.add(new ProductResponse(
                        id,
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getDescription(),
                        product.getUrl()));
            });
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Get All Products Failed !");
    }

    @GetMapping(path = "/getProducts/{cateID}")
    public ResponseEntity<?> getproductsByCateID( @PathVariable("cateID") int cateID){
        List<Product> products = productService.getProductsByCateID(cateID);
        if(products != null && products.size() != 0){
            List<ProductResponse> response = new ArrayList<>();
            products.forEach(product -> {
                int id = Integer.parseInt(product.getId()+"");
                response.add(new ProductResponse(
                        id,
                        product.getName(),
                        product.getPrice(),
                        product.getQuantity(),
                        product.getDescription(),
                        product.getUrl()));
            });
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Get All Products Failed !");
    }

    @GetMapping(path = "/findByName/{productName}")
    ResponseEntity<?> createCategory(@PathVariable(name = "productName") String productName){
        ProductResponse response = productService.getProductByName(productName);
        if(response != null){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productName + " is not found !");
    }

    @GetMapping("/searchByLikeName/{name}")
    public ResponseEntity<?>  searchByLikeName(@PathVariable("name") String name){
        List<Product> products= productService.searchByLikeName(name);
        if(products != null){
            return ResponseEntity.status(HttpStatus.OK).body(products);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found !");
    }

    @GetMapping(path = "/getProductByID/{productID}")
    public ResponseEntity<?> getProductByID(@PathVariable("productID")int id){
        ProductResponse productResponse = productService.getProductByID(id);
        if(productResponse != null){
            return ResponseEntity.status(HttpStatus.OK).body(productResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found !");

    }



}
