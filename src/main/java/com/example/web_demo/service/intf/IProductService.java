package com.example.web_demo.service.intf;

import org.springframework.http.ResponseEntity;

import com.example.web_demo.model.Product;
import com.example.web_demo.model.ResponseObject;

public interface IProductService {
	public ResponseEntity<ResponseObject> createProduct(Product product);
	public ResponseEntity<ResponseObject> updateProduct(long id, Product newProduct);
	public ResponseEntity<ResponseObject> deleteProduct(long id);
	public ResponseEntity<ResponseObject> getDetailProduct(long id);
	public ResponseEntity<ResponseObject> getAllProduct();
}
