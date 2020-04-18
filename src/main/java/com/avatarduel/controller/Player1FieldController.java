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

public class Player1FieldController {
    private MainWindowController mainWindowController;
    private Player p;
    public boolean isLandSummoned;
    public boolean isCharSelected;
    public boolean isSkillSelected;
    public Card selectedChar;
    public Card selectedSkill;
    public Card enemyCard;
    
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
    @FXML private Button attackPlayer;
    @FXML private AnchorPane cardButtons;
    @FXML private Button throwCardButton;
    @FXML private Button cancelCardButton;
    @FXML private Button charAttackBtn;
    @FXML private Button charRotateBtn;
    @FXML private Button skillAttachBtn;
    
    public void init(MainWindowController mwc, Player p) {
        System.out.println("Player 1 has been initialized");
        mainWindowController = mwc;
        this.p = p;
        isLandSummoned = false;
        isCharSelected = false;
        isSkillSelected = false;
        refreshPlayer();
        this.name.setText(this.p.getName());
    }
    
    public void setAttackPlayerButton(String s) {
        if (s.equals("visible")) attackPlayer.setVisible(true);
        else attackPlayer.setVisible(false);
    }
    
    public void setCharATKBtn(String s) {
        if (s.equals("visible")) charAttackBtn.setVisible(true);
        else charAttackBtn.setVisible(false);
    }
    
    public void setCharRotateBtn(String s) {
        if (s.equals("visible")) charRotateBtn.setVisible(true);
        else charRotateBtn.setVisible(false);
    }
    
    public void setSkillAttachBtn(String s) {
        if (s.equals("visible")) skillAttachBtn.setVisible(true);
        else skillAttachBtn.setVisible(false);
    }
    
    public void setCardsOnHand(String s) {
        if (s.equals("visible")) {
            cardsOnHand.setVisible(true);
        } else cardsOnHand.setVisible(false);
    }
    
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
            setCard(card, this.p.getListOfCardOnHand().get(i), "default");
        }
        for (int i=0; i < this.p.getListOfCharacterOnTable().size(); i++) {
            AnchorPane card = (AnchorPane) this.character.getChildren().get(i);
            setCard(card, this.p.getListOfCharacterOnTable().get(i), "default");
        }
        for (int i=0; i < this.p.getListOfSkillOnTable().size(); i++) {
            AnchorPane card = (AnchorPane) this.skill.getChildren().get(i);
            setCard(card, this.p.getListOfSkillOnTable().get(i), "default");
        }
    }
    
    public void setCard(AnchorPane a, Card c, String s) {
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
                if (s.equals("highlight")) a.setStyle("-fx-border-color: yellow; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: orange;");
                else a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: orange;");
                break;
            case WATER:
                if (s.equals("highlight")) a.setStyle("-fx-border-color: yellow; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: lightblue;");
                else a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: lightblue;");
                break;
            case FIRE:
                if (s.equals("highlight")) a.setStyle("-fx-border-color: yellow; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: red;");
                else a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: red;");
                break;
            case EARTH:
                if (s.equals("highlight")) a.setStyle("-fx-border-color: yellow; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: lightgreen;");
                else a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: lightgreen;");
                break;
            case ENERGY:
                if (s.equals("highlight")) a.setStyle("-fx-border-color: yellow; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: white;");
                else a.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: white;");
                break;
        }
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
    
    @FXML private void cardOnHandClicked(ActionEvent e) {
        if (mainWindowController.getTurn() == 1) {
            Node button = (Node) e.getSource();
            AnchorPane cardGUI = (AnchorPane) button.getParent();
            int idxCard = cardsOnHand.getChildren().indexOf(cardGUI);
            if (idxCard < p.getListOfCardOnHand().size()) {
                Card card = p.getListOfCardOnHand().get(idxCard);
                if (card.getType() == CardType.LAND) {
                    if (!isLandSummoned && mainWindowController.getCurrPhase().equals("main")) {
                        Land l = (Land) card;
                        p.useLand(l);
                        isLandSummoned = true;
                        resetCard((AnchorPane) cardsOnHand.getChildren().get(p.getListOfCardOnHand().size()));
                        refreshPlayer();
                    }
                } else if (card.getType() == CardType.CHARACTER) {
                    if (mainWindowController.getCurrPhase().equals("main")) {
                        Character child = (Character) card;
                        if (p.putCharacterOnTable(child, Position.ATTACK)) {
                            resetCard((AnchorPane) cardsOnHand.getChildren().get(p.getListOfCardOnHand().size()));
                            refreshPlayer();
                        }
                    }
                } else {
                    if (mainWindowController.getCurrPhase().equals("main")) {
                        setCard(cardGUI, card, "highlight");
                        selectedSkill = card;
                        cardButtons.setVisible(true);
                        skillAttachBtn.setVisible(true);
                    }
                }
            }
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

    @FXML private void attackPlayerButtonClicked(ActionEvent event) {
        if (mainWindowController.getTurn() == 2) {
            if (p.getListOfCharacterOnTable().isEmpty()) {
                //ATTACK
            }
        }
    }

    @FXML private void charAttackClicked(ActionEvent event) {
    }

    @FXML private void charRotateClicked(ActionEvent event) {
    }

    @FXML private void skillAttachClicked(ActionEvent e) {
        if (selectedSkill != null && selectedChar != null) {
            Character ch = (Character) selectedChar;
            if (p.putSkillOnTable(selectedSkill, ch)) {
                if (selectedSkill.getType() == CardType.DESTROY) p.useSkill(p, selectedSkill);
                else p.useSkill(selectedSkill);
                
                AnchorPane card1 = (AnchorPane) character.getChildren().get(p.getListOfCharacterOnTable().indexOf(ch));
                AnchorPane card2 = (AnchorPane) skill.getChildren().get(p.getListOfSkillOnTable().indexOf(selectedSkill));
                setCard(card1, selectedChar, "default");
                setCard(card2, selectedSkill, "default");
                resetCard((AnchorPane) cardsOnHand.getChildren().get(p.getListOfCardOnHand().size()));
                refreshPlayer();
                skillAttachBtn.setVisible(false);
                cardButtons.setVisible(false);
            }
        } else if (enemyCard != null && enemyCard.getType() != CardType.CHARACTER && selectedSkill != null) {
            
        }
    }

    @FXML private void throwCardButtonClicked(ActionEvent event) {
    }

    @FXML private void cancelCardButtonClicked(ActionEvent event) {
    }

    @FXML private void charCardClicked(ActionEvent e) {
        if (mainWindowController.getCurrPhase().equals("main")) {
            if (selectedSkill != null) {
                Node button = (Node) e.getSource();
                AnchorPane cardGUI = (AnchorPane) button.getParent();
                int idxCard = character.getChildren().indexOf(cardGUI);
                if (idxCard < p.getListOfCharacterOnTable().size()) {
                    Card card = p.getListOfCardOnHand().get(idxCard);
                    selectedChar = card;
                    setCard(cardGUI, card, "highlight");
                } else {
                    
                }
            }
        } else {
                
        }
    }

    @FXML private void skillCardClicked(ActionEvent event) {
    }
}