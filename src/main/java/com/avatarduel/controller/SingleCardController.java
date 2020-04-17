/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import com.avatarduel.card.*;

/**
 *
 * @author indraf
 */
public class SingleCardController {
    private MainWindowController mainWindowController;
    
    @FXML
    private Label name;
    @FXML
    private ImageView element;
    @FXML
    private Label effect;
    @FXML
    private Label type;
    @FXML
    private ImageView picture;
    @FXML
    private Label description;
    @FXML
    private Label status;
    @FXML
    private AnchorPane singleCard;
     
    private Card card;
    
    public void init(MainWindowController mwc, Card card) {
        System.out.println("Single Card has been initialized");
        mainWindowController = mwc;
        this.card = card;
        showCardDetails();
    }
    
    public void setCard(Card c) {
        this.card = c;
    }
    
    public void showCardDetails() {
        setName();
        setElement();
        setEffect();
        setType();
        setPicture();
        setDescription();
        setStatus();
    }
    
    public void setName() {
        this.name.setText(this.card.getName());
    }
    
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
    
    public void setEffect() {
//        TO-DO
        this.effect.setText("");
    }
    
    public void setType() {
        this.type.setText(this.card.getType().name());
    }
    
    public void setPicture() {
        Image img = new Image(this.card.getPath());
        this.picture.setImage(img);
    }
    
    public void setDescription() {
        this.description.setText(this.card.getDescription());
    }
    
    public void setStatus() {
        String stat = this.card.getStatsAsString();
        this.status.setText(stat);
    }
}