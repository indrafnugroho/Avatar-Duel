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
import com.avatarduel.card.*;

/**
 *
 * @author indraf
 */
public class Player2FieldController extends PlayerFieldController {
/*    private MainWindowController mainWindowController;
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
    @FXML private Label name;
    @FXML private AnchorPane character;
    @FXML private AnchorPane skill;
    
    public void init(MainWindowController mwc, Player p) {
        System.out.println("Player 2 has been initialized");
        mainWindowController = mwc;
        this.p = p;
        setHP();
        setDeckCapacity();
        setLandPower();
        setName();
        setCardsOnHand();
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
    
    public void setCardsOnHand() {
        for (int i=0; i < this.p.getListOfCardOnHand().size(); i++) {
            VBox card = (VBox) this.cardsOnHand.getChildren().get(i);
            setCard(card, this.p.getListOfCardOnHand().get(i));
        }
    }
    
    public void setCard(VBox v, Card c) {
        Label type = (Label) v.getChildren().get(0);
        Label pow = (Label) v.getChildren().get(1);
        Label atk = (Label) v.getChildren().get(2);
        Label def = (Label) v.getChildren().get(3);
        
        type.setText(c.getType().toString());
        pow.setText(c.getPowAsString());
        atk.setText(c.getAtkAsString());
        def.setText(c.getDefAsString());
    }
    
    public void resetCard(VBox v) {
        Label type = (Label) v.getChildren().get(0);
        Label pow = (Label) v.getChildren().get(1);
        Label atk = (Label) v.getChildren().get(2);
        Label def = (Label) v.getChildren().get(3);
        
        type.setText("");
        pow.setText("");
        atk.setText("");
        def.setText("");
    }
*/
}
