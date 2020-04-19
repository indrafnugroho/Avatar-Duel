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
import javafx.scene.transform.Rotate;

import com.avatarduel.player.*;
import com.avatarduel.card.*;
import com.avatarduel.card.Character;

public class PlayerFieldController {
    protected MainWindowController mainWindowController;
    protected Player p;
    protected Player enemy;
    protected int turn;
    public boolean isLandSummoned;
    public Card selectedChar;
    public Card selectedSkill;
    public Card selectedSkillOnTable;
    
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
    @FXML protected AnchorPane cardButtons;
    @FXML protected Button throwCardButton;
    @FXML protected Button cancelCardButton;
    @FXML protected Button charRotateBtn;
    @FXML protected Button skillAttachBtn;
    
    /**
     * Initialize the player controller.
     * @param mwc the parent of this controller
     * @param p the player to be controlled.
     * @param enemy the enemy player.
     * @param turn, turn player to be controlled
     */
    public void init(MainWindowController mwc, Player p, Player enemy, int turn) {
        mainWindowController = mwc;
        this.p = p;
        this.enemy = enemy;
        this.turn = turn;
        isLandSummoned = false;
        refreshPlayer();
        this.name.setText(this.p.getName());
        System.out.println("Player " + this.p.getName() + " has been initialized");
    }
    
    /**
     * Set hand card visibility.
     * @param s, either "visible" or "invisible"
     */
    public void setCharRotateBtn(String s) {
        if (s.equals("visible")) charRotateBtn.setVisible(true);
        else charRotateBtn.setVisible(false);
    }
    
    /**
     * Set hand card visibility.
     * @param s, either "visible" or "invisible"
     */
    public void setSkillAttachBtn(String s) {
        if (s.equals("visible")) skillAttachBtn.setVisible(true);
        else skillAttachBtn.setVisible(false);
    }
    
    /**
     * Set hand card visibility.
     * @param s, either "visible" or "invisible"
     */
    public void setCardsOnHand(String s) {
        if (s.equals("visible")) {
            cardsOnHand.setVisible(true);
        } else cardsOnHand.setVisible(false);
    }
    
    /**
     * refresh player to get updated look on GUI
     */
    public void refreshPlayer() {
        if (this.mainWindowController.getCurrPhase().equals("win") && this.p.getLifePoint() > 0) {
            this.hp.setText("WIN");
        
        } else {
            this.hp.setText(this.p.getLifePoint() + " HP");
        }
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
    
    /**
     * set the look of card
     * @param a, container card in GUI
     * @param c, card to be set
     * @param s, border of the card, either "highlight" or "default"
     */
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
        if (c.getState() != null) {
            if (c.getState().getPosition() == Position.DEFENSE) {
                Rotate r = new Rotate();
                r.setAngle(90);
                a.setRotate(90);
            }
        }
    }
    
    /**
     * reset the look of card
     * @param a, card container in the GUI
     */
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
        a.setRotate(0);
    }
   
    /**
     * Handler for mouse click on hand card event.
     * @param e ActionEvent the source event.
     */
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
                } else if (card.getType() == CardType.DESTROY) {
                    if (mainWindowController.getCurrPhase().equals("main")) {
                        setCard(cardGUI, card, "highlight");
                        selectedSkill = card;
                        cardButtons.setVisible(true);
                        throwCardButton.setVisible(false);
                        mainWindowController.setCurrCardEvent("destroy-card");
                    }
                } else {
                    if (mainWindowController.getCurrPhase().equals("main") && selectedSkill == null) {
                        setCard(cardGUI, card, "highlight");
                        selectedSkill = card;
                        cardButtons.setVisible(true);
                        skillAttachBtn.setVisible(true);
                        throwCardButton.setVisible(false);
                    }
                }
            }
        }
    }
    
    /**
     * handler for hovered card
     * @param e, event source
     */
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
    
    /**
     * handler for hovered-from-card
     * @param e, event source
     */
    @FXML protected void hoverFromCard(Event e) {
        this.mainWindowController.resetCardDetails();
    }

    /**
     * handler for clicked rotate card button
     * @param event, event source
     */
    @FXML protected void charRotateClicked(ActionEvent event) {
        if (mainWindowController.getCurrPhase().equals("main") && mainWindowController.getTurn() == this.turn && selectedChar != null) {
            selectedChar.getState().rotate();
            selectedChar = null;
            cardButtons.setVisible(false);
            refreshPlayer();
        } 
    }
    
    /**
    * handler for clicked attach skill button
    * @param e, event source
    */
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
        }
    }
    
    /**
     * handler for clicked throw card button
     * @param event, event source
     */
    @FXML protected void throwCardButtonClicked(ActionEvent event) {
        if (mainWindowController.getCurrPhase().equals("main")) {
            if (selectedChar != null) {
                if (p.getListOfCharacterOnTable().contains((Character)selectedChar)) {
                    p.removeCharacterFromTable(p, (Character) selectedChar);
                    selectedChar = null;
                    cardButtons.setVisible(false);
                    refreshPlayer();
                }
            } else if (selectedSkillOnTable != null) {
                if (p.getListOfSkillOnTable().contains(selectedSkillOnTable)) {
                    p.removeSkillFromTable(p, selectedSkillOnTable);
                    selectedSkillOnTable = null;
                    cardButtons.setVisible(false);
                    refreshPlayer();
                }
            }
        }
    }
    
    /**
     * Handler for clicked cancel card button
     * @param event, event source
     */
    @FXML protected void cancelCardButtonClicked(ActionEvent event) {
        if (selectedChar != null) {
            Character ch = (Character) selectedChar;
            if (p.getListOfCharacterOnTable().contains(ch)) {
                AnchorPane card = (AnchorPane) character.getChildren().get(p.getListOfCharacterOnTable().indexOf(ch));
                setCard(card, selectedChar, "default");
                selectedChar = null;
                charRotateBtn.setVisible(false);
                cardButtons.setVisible(false);
            }
            mainWindowController.setCurrCardEvent("");
        }
        
        if (selectedSkill != null) {
                AnchorPane card = (AnchorPane) cardsOnHand.getChildren().get(p.getListOfCardOnHand().indexOf(selectedSkill));
                setCard(card, selectedSkill, "default");
                selectedSkill = null;
                skillAttachBtn.setVisible(false);
                cardButtons.setVisible(false);
        }
        if (selectedSkillOnTable != null) {
                AnchorPane card = (AnchorPane) skill.getChildren().get(p.getListOfSkillOnTable().indexOf(selectedSkillOnTable));
                setCard(card, selectedSkillOnTable, "default");
                selectedSkillOnTable = null;
                skillAttachBtn.setVisible(false);
                cardButtons.setVisible(false);
        }
    }
    
    /**
     * Select card to be attacked
     * @param card, attacked card
     */
    public void onAttackCard(com.avatarduel.card.Character card) {
        this.p.attack(this.enemy, (com.avatarduel.card.Character) selectedChar, card);
        selectedChar = null;
        this.mainWindowController.refreshPlayers();
        cardButtons.setVisible(false);
    }
    
    /**
     * Select card to be destroyed
     * @param card, destroyed card
     */
    public void onDestroyCard(com.avatarduel.card.Character card) {
        if (p.putSkillOnTable(selectedSkill, card)) {
            if (selectedSkill.getType() == CardType.DESTROY) {
                p.useSkill(this.enemy, selectedSkill);
            }
        }
        selectedSkill = null;
        this.mainWindowController.refreshPlayers();
        cardButtons.setVisible(false);
    }

    /**
     * Handler for clicked character card on field
     * @param e, event source
     */
    @FXML protected void charCardClicked(ActionEvent e) {
        if (selectedChar == null) {
            String currPhase = mainWindowController.getCurrPhase();
            Node button = (Node) e.getSource();
            AnchorPane cardGUI = (AnchorPane) button.getParent();
            int idxCard = character.getChildren().indexOf(cardGUI);
            com.avatarduel.card.Character card = p.getListOfCharacterOnTable().get(idxCard);
            if (mainWindowController.getTurn() == this.turn) {
                // Select main 
                if (currPhase.equals("main")) {
                    setCard(cardGUI, card, "highlight");
                    selectedChar = card;
                    cardButtons.setVisible(true);
                    throwCardButton.setVisible(true);
                    charRotateBtn.setVisible(true);
                } else if (currPhase.equals("battle") && card.getState().getPosition() == Position.ATTACK && !card.getHasAttacked()) {
                // Select card used to attack
                    setCard(cardGUI, card, "highlight");
                    selectedChar = card;
                    if (this.enemy.getListOfCharacterOnTable().size() == 0) {
                        // If enemy do not have any character on field, can attack directly.
                        //charAttackBtn.setVisible(true);
                        this.p.attack(this.enemy, (com.avatarduel.card.Character) selectedChar);
                        mainWindowController.refreshPlayers();
                        selectedChar = null;
                    } else {
                        this.mainWindowController.setCurrCardEvent("card-attack");
                        throwCardButton.setVisible(false);
                        charRotateBtn.setVisible(false);
                        cardButtons.setVisible(true); 
                    }
                }
            } else {
                if (currPhase.equals("main") && this.mainWindowController.getCurrCardEvent().equals("destroy-card")) {
                    System.out.println("destroy");
                    this.mainWindowController.onDestroyTargetSelected(this, card);
                    this.mainWindowController.setCurrCardEvent("");
                }
                else if (currPhase.equals("battle") && this.mainWindowController.getCurrCardEvent().equals("card-attack")) {
                    System.out.println("attack");
                    this.mainWindowController.onAttackTargetSelected(this, card);
                    this.mainWindowController.setCurrCardEvent("");
                }
            }
        }
        this.mainWindowController.checkPlayersHP();
    }
    
    /**
     * Handler for clicked skill card on field
     * @param e, event source
     */
    @FXML protected void skillCardClicked(ActionEvent e) {
        if (mainWindowController.getCurrPhase().equals("main") && selectedSkillOnTable == null) {
            Node button = (Node) e.getSource();
            AnchorPane cardGUI = (AnchorPane) button.getParent();
            int idxCard = skill.getChildren().indexOf(cardGUI);
            Card card = p.getListOfSkillOnTable().get(idxCard);
            selectedSkillOnTable = card;
            setCard(cardGUI, card, "highlight");
            cardButtons.setVisible(true);
        }
    }
}
