package com.example.web_demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseObject {
	@Schema(description = "Status", example = "ok")
	private String status;
	@Schema(description = "Sessage", example = "successfully")
    private String message;
	@Schema(description = "Count", example = "0")
    private int count;
	@Schema(description = "Data", example = "empty")
    private Object data;
    
    public ResponseObject() {}

    public ResponseObject(String status, String message, int count, Object data) {
        this.status = status;
        this.message = message;
        this.count = count;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
    	this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
