/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author indraf
 */
public class Player1FieldController {
    private MainWindowController mainWindowController;
    
    @FXML
    private VBox p1COH1;
    @FXML
    private Label p1COHType1;
    @FXML
    private Label p1COHPower1;
    @FXML
    private Label p1COHAttack1;
    @FXML
    private Label p1COHDefense1;
    @FXML
    private VBox p1COH2;
    @FXML
    private Label p1COHType2;
    @FXML
    private Label p1COHPower2;
    @FXML
    private Label p1COHAttack2;
    @FXML
    private Label p1COHDefense2;
    @FXML
    private VBox p1COH3;
    @FXML
    private Label p1COHType3;
    @FXML
    private Label p1COHPower3;
    @FXML
    private Label p1COHAttack3;
    @FXML
    private Label p1COHDefense3;
    @FXML
    private VBox p1COH4;
    @FXML
    private Label p1COHType4;
    @FXML
    private Label p1COHPower4;
    @FXML
    private Label p1COHAttack4;
    @FXML
    private Label p1COHDefense4;
    @FXML
    private VBox p1COH5;
    @FXML
    private Label p1COHType5;
    @FXML
    private Label p1COHPower5;
    @FXML
    private Label p1COHAttack5;
    @FXML
    private Label p1COHDefense5;
    @FXML
    private VBox p1COH6;
    @FXML
    private Label p1COHType6;
    @FXML
    private Label p1COHPower6;
    @FXML
    private Label p1COHAttack6;
    @FXML
    private Label p1COHDefense6;
    @FXML
    private VBox p1COH7;
    @FXML
    private Label p1COHType7;
    @FXML
    private Label p1COHPower7;
    @FXML
    private Label p1COHAttack7;
    @FXML
    private Label p1COHDefense7;
    @FXML
    private VBox p1COH8;
    @FXML
    private Label p1COHType8;
    @FXML
    private Label p1COHPower8;
    @FXML
    private Label p1COHAttack8;
    @FXML
    private Label p1COHDefense8;
    @FXML
    private VBox p1COH9;
    @FXML
    private Label p1COHType9;
    @FXML
    private Label p1COHPower9;
    @FXML
    private Label p1COHAttack9;
    @FXML
    private Label p1COHDefense9;
    @FXML
    private VBox p1COH10;
    @FXML
    private Label p1COHType10;
    @FXML
    private Label p1COHPower10;
    @FXML
    private Label p1COHAttack10;
    @FXML
    private Label p1COHDefense10;
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
    private VBox landPower;
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
    
    public void init(MainWindowController mwc) {
        System.out.println("Player 1 has been initialized");
        mainWindowController = mwc;
    }
}
