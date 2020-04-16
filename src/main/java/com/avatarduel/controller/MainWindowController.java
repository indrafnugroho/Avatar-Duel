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

import com.avatarduel.player.*;
import com.avatarduel.card.*;

/**
 *
 * @author indraf
 */
public class MainWindowController {
    @FXML private Player1FieldController player1FieldController;
    @FXML private Player2FieldController player2FieldController;
    @FXML private SingleCardController singleCardController;
    @FXML private VBox phase;
    @FXML private Label drawPhase;
    @FXML private Label mainPhase;
    @FXML private Label battlePhase;
    @FXML private Label endPhase;
    @FXML
    private AnchorPane mainWindow;
    
    private Player p1;
    private Player p2;
    
    private void initialize() {
        System.out.println("Game has started");
//        this.p1 = new Player();
        player1FieldController.init(this);
        player2FieldController.init(this);
        singleCardController.init(this, p1.getListOfCardOnTable().get(0));
    }
}
