package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.event.Event;

import com.avatarduel.player.*;
import com.avatarduel.card.*;
import com.avatarduel.card.Character;

public class PlayerFieldController {
    private MainWindowController mainWindowController;
    private Player p;
    public boolean isLandSummoned;
    
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
    @FXML private Button attack;
   
    /**
     * Initialize the player controller.
     * @param mwc the parent of this controller
     * @param p the player to be controlled.
     */
    public void init(MainWindowController mwc, Player p) {
        System.out.println("Player " + this.p.getName() + "has been initialized");
        mainWindowController = mwc;
        this.p = p;
        isLandSummoned = false;
        refreshPlayer();
        this.name.setText(this.p.getName());
    }
    
    /**
     * Set hand visibility.
     * @param v if true, make the hand cards visible, and do otherwise if false.
     */
    public void setHandVisibility(boolean v) {
        this.cardsOnHand.setVisible(true);
    }
    
    /**
     * Refresh the information displayed.
     */
    public void refreshPlayer() {
        this.hp.setText(this.p.getLifePoint() + " HP");
        this.deckCapacity.setText(this.p.getDeck().size() + " / 50");
        this.air.setText(this.p.getStatus().airToString());
        this.fire.setText(this.p.getStatus().fireToString());
        this.earth.setText(this.p.getStatus().earthToString());
        this.water.setText(this.p.getStatus().waterToString());
        this.energy.setText(this.p.getStatus().energyToString());
        for (int i=0; i < this.p.getListOfCardOnHand().size(); i++) {
            AnchorPane card = (AnchorPane) this.cardsOnHand.getChildren().get(i);
            setCard(card, this.p.getListOfCardOnHand().get(i));
        }
        for (int i=0; i < this.p.getListOfCharacterOnTable().size(); i++) {
            AnchorPane card = (AnchorPane) this.character.getChildren().get(i);
            setCard(card, this.p.getListOfCharacterOnTable().get(i));
        }
        for (int i=0; i < this.p.getListOfSkillOnTable().size(); i++) {
            AnchorPane card = (AnchorPane) this.skill.getChildren().get(i);
            setCard(card, this.p.getListOfSkillOnTable().get(i));
        }
    }
    
    @FXML private void cardOnHandClicked(ActionEvent e) {
        if (mainWindowController.getTurn() == 1) {
            Node button = (Node) e.getSource();
            AnchorPane cardGUI = (AnchorPane) button.getParent();
            int idxCard = cardsOnHand.getChildren().indexOf(cardGUI);
            if (idxCard < p.getListOfCardOnHand().size()) {
                Card card = p.getListOfCardOnHand().get(idxCard);
                if (card.getType() == CardType.LAND) {
                    if (!isLandSummoned && mainWindowController.getCurrPhase().equals("main")) {
                        p.getStatus().addStatus(card.getElement());
                        p.getListOfCardOnHand().remove(card);
                        isLandSummoned = true;
                        resetCard((AnchorPane) cardsOnHand.getChildren().get(p.getListOfCardOnHand().size()));
                        refreshPlayer();
                    }
                } else if (card.getType() == CardType.CHARACTER) {
                    if (mainWindowController.getCurrPhase().equals("main")) {
                        Character child = (Character) card;
                        boolean success = false;
                        if (child.getElement() == Element.AIR) {
                            success = p.getStatus().useAir(child.getPower());
                        } else if (child.getElement() == Element.WATER) {
                            success = p.getStatus().useWater(child.getPower());
                        } else if (child.getElement() == Element.FIRE) {
                            success = p.getStatus().useFire(child.getPower());
                        } else if (child.getElement() == Element.EARTH) {
                            success = p.getStatus().useEarth(child.getPower());
                        } else if (child.getElement() == Element.ENERGY) {
                            success = p.getStatus().useEnergy(child.getPower());
                        }
                        if (success) {
                            p.getListOfCharacterOnTable().add(child);
                            p.getListOfCardOnHand().remove(card);
                            resetCard((AnchorPane) cardsOnHand.getChildren().get(p.getListOfCardOnHand().size()));
                            refreshPlayer();
                        }
                    }
                } else {
                    if (mainWindowController.getCurrPhase().equals("main")) {
                        boolean success = false;
                        if (card.getElement() == Element.AIR) {
                            success = p.getStatus().useAir(card.getPower());
                        } else if (card.getElement() == Element.WATER) {
                            success = p.getStatus().useWater(card.getPower());
                        } else if (card.getElement() == Element.FIRE) {
                            success = p.getStatus().useFire(card.getPower());
                        } else if (card.getElement() == Element.EARTH) {
                            success = p.getStatus().useEarth(card.getPower());
                        } else if (card.getElement() == Element.ENERGY) {
                            success = p.getStatus().useEnergy(card.getPower());
                        }
                        if (success) {
                            p.getListOfSkillOnTable().add(card);
                            p.getListOfCardOnHand().remove(card);
                            resetCard((AnchorPane) cardsOnHand.getChildren().get(p.getListOfCardOnHand().size()));
                            refreshPlayer();
                        }
                    }
                }
            }
        }
    }
    
    public void setCard(AnchorPane a, Card c) {
        Label type = (Label) a.getChildren().get(0);
        Label pow = (Label) a.getChildren().get(1);
        Label atk = (Label) a.getChildren().get(2);
        Label def = (Label) a.getChildren().get(3);
        
        type.setText(c.getType().toString());
        pow.setText(c.getPowAsString());
        atk.setText(c.getAtkAsString());
        def.setText(c.getDefAsString());
        switch (c.getElement()) {
            case AIR:
                a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: orange;");
                break;
            case WATER:
                a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: lightblue;");
                break;
            case FIRE:
                a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: red;");
                break;
            case EARTH:
                a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: lightgreen;");
                break;
            case ENERGY:
                a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: white;");
                break;
        }
    }
    
    @FXML private void hoverToCard(Event e) {
        Node button = (Node) e.getSource();
        AnchorPane cardGUI = (AnchorPane) button.getParent();
        if (cardGUI.getParent().equals(this.cardsOnHand)) {
            int idxCard = this.cardsOnHand.getChildren().indexOf(cardGUI);
            if (idxCard < this.p.getListOfCardOnHand().size()) {
                this.mainWindowController.showCardDetails(this.p.getListOfCardOnHand().get(idxCard));
            }
        } else if (cardGUI.getParent().equals(this.character)) {
            int idxCard = this.character.getChildren().indexOf(cardGUI);
            if (idxCard < this.p.getListOfCharacterOnTable().size()) {
                this.mainWindowController.showCardDetails(this.p.getListOfCharacterOnTable().get(idxCard));
            }
        } else if (cardGUI.getParent().equals(this.skill)) {
            int idxCard = this.skill.getChildren().indexOf(cardGUI);
            if (idxCard < this.p.getListOfSkillOnTable().size()) {
                this.mainWindowController.showCardDetails(this.p.getListOfSkillOnTable().get(idxCard));
            }
        }
    }
    
    @FXML private void hoverFromCard(Event e) {
        this.mainWindowController.resetCardDetails();
    }
    
    public void resetCard(AnchorPane a) {
        Label type = (Label) a.getChildren().get(0);
        Label pow = (Label) a.getChildren().get(1);
        Label atk = (Label) a.getChildren().get(2);
        Label def = (Label) a.getChildren().get(3);
        
        type.setText("");
        pow.setText("");
        atk.setText("");
        def.setText("");
        a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
    }

    @FXML private void attackButtonClicked(ActionEvent event) {
        if (mainWindowController.getTurn() == 2) {
            if (p.getListOfCharacterOnTable().size() == 0) {
                //ATTACK
            }
        }
    }
}
