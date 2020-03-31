package com.avatarduel.model;

class Card{
	private int id;
	private String name;
	private Element element;
	private String description;
	private String imagepath;

	public Card(CardBuilder builder){
		this.id = builder.id;
		this.element = builder.element;
		this.description = builder.description;
		this.imagepath = builder.imagepath;
	}
}