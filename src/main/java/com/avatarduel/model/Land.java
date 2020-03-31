package com.avatarduel.model;

public class Land extends Card{
  public Land() {
    this.name = "";
    this.description = "";
    this.element = Element.AIR;
  }

  public Land(String name, String description, Element element) {
    this.name = name;
    this.description = description;
    this.element = element;
  }
}