package com.hpe.service;

public class GreetingService {

	private Message message;

	public GreetingService() {
	}

	public GreetingService(Message message) {
		this.message = message;
	}

	// writable property called "message" (mutator)
	public void setMessage(Message message) {
		System.out.println("setMessage called");
		this.message = message;
	}

	// readable property called "message" (accessor)
	public Message getMessage() {
		return message;
	}

	public void greet() {
		System.out.println(this.message.getFrom() + " - " + this.message.getText());
	}
}
