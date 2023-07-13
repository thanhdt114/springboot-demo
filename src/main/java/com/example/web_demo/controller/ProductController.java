package com.example.web_demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.web_demo.model.Product;
import com.example.web_demo.model.ResponseObject;
import com.example.web_demo.service.intf.IProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping(path = "/api/v1/products")
@Tag(name = "Products", description = "APIs for managing products")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @GetMapping("/list")
    @Operation(summary = "Get all product")
    @ApiResponses(value = {
        @ApiResponse(
    		responseCode = "200", 
    		description = "status: ok",
            content = @Content(
        		mediaType = "application/json",
        		schema = @Schema(implementation = Product.class)
        	)
    	)
    })
    ResponseEntity<ResponseObject> getAllProducts() {
        return iProductService.getAllProduct();
    }
    
    @GetMapping("/product/{id}")
    @Operation(summary = "Get detail product")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
    	return iProductService.getDetailProduct(id);
    }
    
    @PostMapping("/create")
    @Operation(summary = "insert new Product with POST method")
    @ApiResponses(value = {
        @ApiResponse(
    		responseCode = "200", 
    		description = "status: ok",
            content = @Content(
        		mediaType = "application/json",
        		schema = @Schema(implementation = Product.class)
        	)
    	)
    })
    ResponseEntity<ResponseObject> createProduct(@RequestBody Product newProduct) {
        return iProductService.createProduct(newProduct);
    }
    
    @PutMapping("/update/{id}")
    @Operation(summary = "Update product")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
    	return iProductService.updateProduct(id, newProduct);
    }
    
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete product")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
    	return iProductService.deleteProduct(id);
    }
    
    @GetMapping("/test")
    @Operation(summary = "Test API")
    @ApiResponses(value = {
        @ApiResponse(
    		responseCode = "200", 
    		description = "status: ok",
            content = @Content(
        		mediaType = "application/json",
        		schema = @Schema(implementation = ResponseObject.class)
        	)
    	)
    })
    ResponseEntity<ResponseObject> testAPI() {
    	return ResponseEntity.status(HttpStatus.OK).body(
    		new ResponseObjectController().success("successfully", 0, "empty")
    	);
    }
}

