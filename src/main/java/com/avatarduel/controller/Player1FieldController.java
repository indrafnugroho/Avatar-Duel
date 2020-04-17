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

import com.avatarduel.player.*;

/**
 *
 * @author indraf
 */
public class Player1FieldController {
    private MainWindowController mainWindowController;
    private Player p;
    
    @FXML private VBox details;
    @FXML private Label hp;
    @FXML private AnchorPane deck;
    @FXML private Label deckCapacity;
    @FXML private AnchorPane cardsOnHand;
    @FXML private HBox landPower;
    @FXML private Label air;
    @FXML private Label fire;
    @FXML private Label earth;
    @FXML private Label water;
    @FXML private Label energy;
    @FXML private AnchorPane cardsOnField;
    @FXML private AnchorPane player1Field;
    @FXML private Label name;
    @FXML private AnchorPane character;
    @FXML private AnchorPane skill;
    
    public void init(MainWindowController mwc, Player p) {
        System.out.println("Player 1 has been initialized");
        mainWindowController = mwc;
        this.p = p;
        setHP();
        setDeckCapacity();
        setLandPower();
        setName();
    }
    
    public void setHP () {
        this.hp.setText(this.p.getLifePoint() + " HP");
    }
    
    public void setDeckCapacity() {
        this.deckCapacity.setText(this.p.getDeck().size() + " / 50");
    }
    
    public void setLandPower() {
        this.air.setText(this.p.getStatus().airToString());
        this.fire.setText(this.p.getStatus().fireToString());
        this.earth.setText(this.p.getStatus().earthToString());
        this.water.setText(this.p.getStatus().waterToString());
        this.energy.setText(this.p.getStatus().energyToString());
    }
    
    public void setName() {
        this.name.setText(this.p.getName());
    }
}
