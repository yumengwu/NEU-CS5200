package edu.northeastern.cs5200.controllers.hello;

public class HelloObject {
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String msg) {
		this.message = msg;
	}
	public HelloObject(String msg) {
		this.message = msg;
	}
	public HelloObject() {
	}
}
