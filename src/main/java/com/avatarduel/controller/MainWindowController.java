/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author indraf
 */
public class MainWindowController {

    @FXML private CardsOnFieldController cardsOnFieldController;
    @FXML private CardsOnFieldController foeCardsOnFieldController;
    @FXML private CardsOnHandController cardsOnHandController;
    @FXML private CardsOnHandController foeCardsOnHandController;
    @FXML private LandPowerController landPowerController;
    @FXML private LandPowerController foeLandPowerController;
    @FXML private PhaseController phaseController;
    @FXML private SingleCardController singleCardController;
    
    @FXML private AnchorPane mainWindow;
    
}
