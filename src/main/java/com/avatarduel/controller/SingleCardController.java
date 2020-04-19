package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import com.avatarduel.card.*;

public class SingleCardController {
    private MainWindowController mainWindowController;
    
    @FXML private Label name;
    @FXML private ImageView element;
    @FXML private Label effect;
    @FXML private Label type;
    @FXML private ImageView picture;
    @FXML private Label description;
    @FXML private Label status;
    @FXML private AnchorPane singleCard;
     
    private Card card;
    
    public void init(MainWindowController mwc, Card card) {
        System.out.println("Single Card has been initialized");
        mainWindowController = mwc;
        this.card = card;
        singleCard.setVisible(false);
    }
    
    /**
     * Set card 
     * @param s, card that want to set
     */
    public void setCard(Card c) {
        this.card = c;
    }
    
    /**
     * Show card details
     */
    public void showCardDetails() {
        setName();
        setElement();
        setEffect();
        setType();
        setPicture();
        setDescription();
        setStatus();
        setColor();
        singleCard.setVisible(true);
    }
    
    /**
     * Set name
     */
    public void setName() {
        this.name.setText(this.card.getName());
    }
    
    /**
     * Set element
     */
    public void setElement() {
        switch(this.card.getElement()) {
            case AIR:
                Image img = new Image("com/avatarduel/card/image/element/air.png");
                this.element.setImage(img);
                break;
            case WATER:
                Image img2 = new Image("com/avatarduel/card/image/element/water.png");
                this.element.setImage(img2);
                break;
            case EARTH:
                Image img3 = new Image("com/avatarduel/card/image/element/earth.png");
                this.element.setImage(img3);
                break;
            case FIRE:
                Image img4 = new Image("com/avatarduel/card/image/element/fire.png");
                this.element.setImage(img4);
                break;
            case ENERGY:
                Image img5 = new Image("com/avatarduel/card/image/element/energy.png");
                this.element.setImage(img5);
                break;
        }
    }
    
    /**
     * Set card effect
     */
    public void setEffect() {
        if (card.getType() != CardType.CHARACTER && card.getType() != CardType.LAND) {
            effect.setText(card.getType().name());
        }
    }
    
    /**
     * Set card type
     */
    public void setType() {
        if (card.getType() != CardType.AURA && card.getType() != CardType.POWERUP && card.getType() != CardType.DESTROY) {
            this.type.setText(this.card.getType().name());
        } else this.type.setText("SKILL");
    }
    
    /**
     * Set card picture
     */
    public void setPicture() {
        Image img = new Image(this.card.getPath());
        this.picture.setImage(img);
    }
    
    /**
     * Set card description
     */
    public void setDescription() {
        this.description.setText(this.card.getDescription());
    }
    
    /**
     * Set card status
     */
    public void setStatus() {
        String stat = this.card.getStatsAsString();
        this.status.setText(stat);
    }
    
    /**
     * Set card color
     */
    public void setColor() {
        switch (card.getElement()) {
            case AIR:
                singleCard.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: orange;");
                break;
            case WATER:
                singleCard.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: lightblue;");
                break;
            case FIRE:
                singleCard.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: red;");
                break;
            case EARTH:
                singleCard.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: lightgreen;");
                break;
            case ENERGY:
                singleCard.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: white;");
                break;
        }
    }
    
    /**
     * Reset all card's attribute
     */
    public void resetCard() {
        this.name.setText("");
        this.element.setImage(null);
        this.effect.setText("");
        this.type.setText("");
        this.picture.setImage(null);
        this.description.setText("");
        this.status.setText("");
        singleCard.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
        singleCard.setVisible(false);
    }
}