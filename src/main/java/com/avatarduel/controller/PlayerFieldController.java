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
    protected MainWindowController mainWindowController;
    protected Player p;
    protected Player enemy;
    protected int turn;
    public boolean isLandSummoned;
    public boolean isCharSelected;
    public boolean isSkillSelected;
    public Card selectedChar;
    public Card selectedSkill;
    public Card enemyCard;
    
    @FXML protected VBox details;
    @FXML protected Label hp;
    @FXML protected AnchorPane deck;
    @FXML protected Label deckCapacity;
    @FXML protected AnchorPane cardsOnHand;
    @FXML protected HBox landPower;
    @FXML protected Label air;
    @FXML protected Label fire;
    @FXML protected Label earth;
    @FXML protected Label water;
    @FXML protected Label energy;
    @FXML protected AnchorPane cardsOnField;
    @FXML protected AnchorPane player1Field;
    @FXML protected Label name;
    @FXML protected AnchorPane character;
    @FXML protected AnchorPane skill;
    @FXML protected Button attackPlayer;
    @FXML protected AnchorPane cardButtons;
    @FXML protected Button throwCardButton;
    @FXML protected Button cancelCardButton;
    @FXML protected Button charAttackBtn;
    @FXML protected Button charRotateBtn;
    @FXML protected Button skillAttachBtn;
    
    /**
     * Initialize the player controller.
     * @param mwc the parent of this controller
     * @param p the player to be controlled.
     * @param enemy the enemy player.
     * @param turnNumber turn number. value 1 or 2.
     */
    public void init(MainWindowController mwc, Player p, Player enemy, int turn) {
        mainWindowController = mwc;
        this.p = p;
        this.enemy = enemy;
        this.turn = turn;
        isLandSummoned = false;
        isCharSelected = false;
        isSkillSelected = false;
        refreshPlayer();
        this.name.setText(this.p.getName());
        System.out.println("Player " + this.p.getName() + " has been initialized");
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
    
    /**
     * Set hand visibility.
     * @param v if true, make the hand cards visible, and do otherwise if false.
     */
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
        for (int i=0; i < 10; i++) {
            AnchorPane card = (AnchorPane) this.cardsOnHand.getChildren().get(i);
            resetCard(card);
        }
        for (int i=0; i < this.p.getListOfCardOnHand().size(); i++) {
            AnchorPane card = (AnchorPane) this.cardsOnHand.getChildren().get(i);
            setCard(card, this.p.getListOfCardOnHand().get(i), "default");
        }
        for (int i=0; i < 6; i++) {
            AnchorPane card1 = (AnchorPane) this.character.getChildren().get(i);
            AnchorPane card2 = (AnchorPane) this.skill.getChildren().get(i);
            resetCard(card1);
            resetCard(card2);
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
    
    @FXML protected void cardOnHandClicked(ActionEvent e) {
        if (mainWindowController.getTurn() == this.turn) {
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
                        refreshPlayer();
                    }
                } else if (card.getType() == CardType.CHARACTER) {
                    if (mainWindowController.getCurrPhase().equals("main")) {
                        Character child = (Character) card;
                        if (p.putCharacterOnTable(child, Position.ATTACK)) {
                            refreshPlayer();
                        }
                    }
                } else {
                    if (mainWindowController.getCurrPhase().equals("main") && selectedSkill == null) {
                        setCard(cardGUI, card, "highlight");
                        selectedSkill = card;
                        cardButtons.setVisible(true);
                        skillAttachBtn.setVisible(true);
                    }
                }
            }
        }
    }
    
    @FXML protected void hoverToCard(Event e) {
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
    
    @FXML protected void hoverFromCard(Event e) {
        this.mainWindowController.resetCardDetails();
    }

    @FXML protected void attackPlayerButtonClicked(ActionEvent event) {
        if (mainWindowController.getTurn() == 2) {
            if (p.getListOfCharacterOnTable().isEmpty()) {
                //ATTACK
            }
        }
    }

    @FXML protected void charAttackClicked(ActionEvent event) {
    }

    @FXML protected void charRotateClicked(ActionEvent event) {
    }

    @FXML protected void skillAttachClicked(ActionEvent e) {
        if (selectedSkill != null && selectedChar != null) {
            Character ch = (Character) selectedChar;
            if (p.putSkillOnTable(selectedSkill, ch)) {
                if (selectedSkill.getType() == CardType.DESTROY) {
                    p.useSkill(p, selectedSkill);
                } else {
                    p.useSkill(selectedSkill);
                    AnchorPane card1 = (AnchorPane) character.getChildren().get(p.getListOfCharacterOnTable().indexOf(ch));
                    AnchorPane card2 = (AnchorPane) skill.getChildren().get(p.getListOfSkillOnTable().indexOf(selectedSkill));
                    setCard(card1, selectedChar, "default");
                    setCard(card2, selectedSkill, "default");
                }
                selectedSkill = null;
                selectedChar = null;
                refreshPlayer();
                skillAttachBtn.setVisible(false);
                cardButtons.setVisible(false);
            }
        } else if (enemyCard != null && enemyCard.getType() != CardType.CHARACTER && selectedSkill != null) {
            
        }
    }

    @FXML protected void throwCardButtonClicked(ActionEvent event) {
        if (mainWindowController.getCurrPhase().equals("main")) {
            if (selectedChar != null) {
                if (p.getListOfCharacterOnTable().contains(selectedChar)) {
                    p.removeCharacterFromTable(p, (Character) selectedChar);
                    selectedChar = null;
                    cardButtons.setVisible(false);
                    refreshPlayer();
                }
            } else if (selectedSkill != null) {
                if (p.getListOfSkillOnTable().contains(selectedSkill)) {
                    p.removeSkillFromTable(p, selectedSkill);
                    selectedSkill = null;
                    cardButtons.setVisible(false);
                    refreshPlayer();
                }
            }
        }
    }

    @FXML protected void cancelCardButtonClicked(ActionEvent event) {
        if (selectedChar != null) {
            Character ch = (Character) selectedChar;
            if (p.getListOfCharacterOnTable().contains(ch)) {
                AnchorPane card = (AnchorPane) character.getChildren().get(p.getListOfCharacterOnTable().indexOf(ch));
                setCard(card, selectedChar, "default");
                selectedChar = null;
                charAttackBtn.setVisible(false);
                charRotateBtn.setVisible(false);
                cardButtons.setVisible(false);
            }
        }
        
        if (selectedSkill != null) {
            if (p.getListOfSkillOnTable().contains(selectedSkill)) {
                AnchorPane card = (AnchorPane) skill.getChildren().get(p.getListOfSkillOnTable().indexOf(selectedSkill));
                setCard(card, selectedSkill, "default");
                selectedSkill = null;
                skillAttachBtn.setVisible(false);
                cardButtons.setVisible(false);
            } else {
                AnchorPane card = (AnchorPane) cardsOnHand.getChildren().get(p.getListOfCardOnHand().indexOf(selectedSkill));
                setCard(card, selectedSkill, "default");
                selectedSkill = null;
                skillAttachBtn.setVisible(false);
                cardButtons.setVisible(false);
            }
        }
    }

    @FXML protected void charCardClicked(ActionEvent e) {
        if (mainWindowController.getCurrPhase().equals("main") && selectedChar == null) {
            Node button = (Node) e.getSource();
            AnchorPane cardGUI = (AnchorPane) button.getParent();
            int idxCard = character.getChildren().indexOf(cardGUI);
            Card card = p.getListOfCharacterOnTable().get(idxCard);
            selectedChar = card;
            setCard(cardGUI, card, "highlight");
            if (selectedSkill == null) {
                cardButtons.setVisible(true);
                charAttackBtn.setVisible(true);
                charRotateBtn.setVisible(true);
            }
        } else if (mainWindowController.getCurrPhase().equals("battle") && selectedChar == null){
                
        }
    }

    @FXML protected void skillCardClicked(ActionEvent e) {
        if (mainWindowController.getCurrPhase().equals("main") && selectedSkill == null) {
            Node button = (Node) e.getSource();
            AnchorPane cardGUI = (AnchorPane) button.getParent();
            int idxCard = skill.getChildren().indexOf(cardGUI);
            Card card = p.getListOfSkillOnTable().get(idxCard);
            selectedSkill = card;
            setCard(cardGUI, card, "highlight");
            cardButtons.setVisible(true);
        }
    }
}
