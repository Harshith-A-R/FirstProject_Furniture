package com.furnitureshop.Furniture.Response;



public class ServiceResponse {
	
	
	private String message;
	private Object object;
	private String status;
	
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ServiceResponse [message=" + message + ", object=" + object + ", status=" + status + "]";
	}
	
	
	
	
	
	

}
