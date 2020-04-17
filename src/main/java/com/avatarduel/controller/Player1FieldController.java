/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author indraf
 */
public class Player1FieldController {
    private MainWindowController mainWindowController;
    
    @FXML
    private VBox details;
    @FXML
    private Label hp;
    @FXML
    private AnchorPane deck;
    @FXML
    private Label deckCapacity;
    @FXML
    private AnchorPane cardsOnHand;
    @FXML
    private HBox landPower;
    @FXML
    private Label air;
    @FXML
    private Label fire;
    @FXML
    private Label earth;
    @FXML
    private Label water;
    @FXML
    private Label energy;
    @FXML
    private AnchorPane cardsOnField;
    @FXML
    private AnchorPane player1Field;
    @FXML
    private Label name;
    @FXML
    private AnchorPane character;
    @FXML
    private AnchorPane skill;
    
    public void init(MainWindowController mwc) {
        System.out.println("Player 1 has been initialized");
        mainWindowController = mwc;
    }
}
