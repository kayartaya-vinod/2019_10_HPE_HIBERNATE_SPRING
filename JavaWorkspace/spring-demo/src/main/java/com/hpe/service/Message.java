package com.hpe.service;

public class Message {
	private String text;
	private String from;

	public Message() {
	}

	public Message(String text, String from) {
		super();
		this.text = text;
		this.from = from;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

}
