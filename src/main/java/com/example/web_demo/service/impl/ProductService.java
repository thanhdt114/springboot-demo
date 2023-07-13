package com.example.web_demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.web_demo.controller.ResponseObjectController;
import com.example.web_demo.model.Product;
import com.example.web_demo.model.ResponseObject;
import com.example.web_demo.repository.ProductRepository;
import com.example.web_demo.service.intf.IProductService;

@Service
public class ProductService implements IProductService{
	@Autowired
    private ProductRepository productRepository;
	
	@Override
	public ResponseEntity<ResponseObject> createProduct(Product newProduct) {
		List<Product> foundProducts = productRepository.findByProductName(newProduct.getProductName().trim());
		
        if(foundProducts.size() > 0) {
        	return ResponseEntity.status(HttpStatus.OK).body(
                	new ResponseObjectController().failed("Add product successfully", 0 , "")
                );
        } else {
        	return ResponseEntity.status(HttpStatus.OK).body(
                	new ResponseObjectController().success("Product is already", foundProducts.size(), productRepository.save(newProduct))
                );
        }
	}

	@Override
	public ResponseEntity<ResponseObject> updateProduct(long id, Product newProduct) {
		Product updatedProduct = productRepository.findById(id)
				.map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear(newProduct.getYear());
                    product.setPrice(newProduct.getPrice());
                    return productRepository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return productRepository.save(newProduct);
                });
		
		return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObjectController().success("Update Product successfully", 1, updatedProduct)
        );
	}

	@Override
	public ResponseEntity<ResponseObject> deleteProduct(long id) {
		boolean exists = productRepository.existsById(id);
		if(exists) {
			return ResponseEntity.status(HttpStatus.OK).body(
	                new ResponseObjectController().success("Delete product successfully", 1, "")
	            );
        } else {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObjectController().failed("Cannot find product to delete", 0, "")
                );
        }
	}

	@Override
	public ResponseEntity<ResponseObject> getDetailProduct(long id) {
		Optional<Product> foundProduct = productRepository.findById(id);
		return foundProduct.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObjectController().success("Query product successfully", 1, foundProduct)
                        //you can replace "ok" with your defined "error code"
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObjectController().failed("Cannot find product with id = " + id, 0, "")
                );
	}

	@Override
	public ResponseEntity<ResponseObject> getAllProduct() {
		List<Product> foundProducts = productRepository.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(
            	new ResponseObjectController().success("Get all products", foundProducts.size() ,foundProducts)
            );
	}

}
