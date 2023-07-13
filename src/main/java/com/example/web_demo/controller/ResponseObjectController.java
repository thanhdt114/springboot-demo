package com.example.web_demo.controller;

import com.example.web_demo.model.ResponseObject;

public class ResponseObjectController {
	public ResponseObject success(String message, int count, Object data) {
		return new ResponseObject("ok", message, count, data);
	}
	
	public ResponseObject failed(String message, int count, Object data) {
		return new ResponseObject("failed", message, count, data);
	}
}
