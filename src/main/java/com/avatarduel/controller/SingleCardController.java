/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    
    private Card card;
    
    public void init(MainWindowController mwc, Card card) {
        System.out.println("Single Card has been initialized");
        mainWindowController = mwc;
        this.card = card;
    }
    
    public void setCard(Card c) {
        this.card = c;
    }
    
    public void showCardDetails() {
        
    }
}
