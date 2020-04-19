package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import com.avatarduel.card.*;
import com.avatarduel.card.Character;
import com.avatarduel.util.CSVReader;
import com.avatarduel.player.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class MainWindowController {
    @FXML private Player1FieldController player1FieldController;
    @FXML private Player2FieldController player2FieldController;
    @FXML private SingleCardController singleCardController;
    @FXML private VBox phase;
    @FXML private AnchorPane mainWindow;
    @FXML private Label draw;
    @FXML private Label main;
    @FXML private Label battle;
    @FXML private Label end;
    @FXML private Button nextButton;
    
    private Player p1;
    private Player p2;
    private static final String LAND_CSV_FILE_PATH = "../card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "../card/data/character.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "../card/data/skill_aura.csv";
    private List<Land> landList;
    private List<Character> characterList;
    private List<Aura> auraList;
    private String currPhase;
    private int turn;
    private String currCardEvent;

    /**
     * Load cards
     */
    public void loadCards() throws IOException, URISyntaxException {
        File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
        File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
        File skillCSVFile = new File(getClass().getResource(SKILL_AURA_CSV_FILE_PATH).toURI());

        CSVReader landReader = new CSVReader(landCSVFile, "\t");
        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
        CSVReader skillReader = new CSVReader(skillCSVFile, "\t");

        landReader.setSkipHeader(true);
        characterReader.setSkipHeader(true);
        skillReader.setSkipHeader(true);

        List<String[]> landRows = landReader.read();
        List<String[]> characterRows = characterReader.read();
        List<String[]> skillRows = skillReader.read();

        this.landList = new ArrayList<Land>();
        this.characterList = new ArrayList<Character>();
        this.auraList = new ArrayList<Aura>();

        for (String[] row : landRows) {
            Land l = new CardBuilder(CardType.LAND)
                .setId(Integer.parseInt(row[0]))
                .setName(row[1])
                .setElement(Element.valueOf(row[2]))
                .setDescription(row[3])
                .setImagePath(row[4])
                .buildLand();
            this.landList.add(l);
        }

        for (String[] row : characterRows) {
            Character c = new CardBuilder(CardType.CHARACTER)
                .setId(Integer.parseInt(row[0]))
                .setName(row[1])
                .setElement(Element.valueOf(row[2]))
                .setDescription(row[3])
                .setImagePath(row[4])
                .setAttack(Integer.parseInt(row[5]))
                .setDefense(Integer.parseInt((row[6])))
                .setPower(Integer.parseInt(row[7]))
                .buildCharacter();
            this.characterList.add(c);
        }

        for (String[] row : skillRows) {
            Aura a = new CardBuilder(CardType.AURA)
                .setId(Integer.parseInt(row[0]))
                .setName(row[1])
                .setElement(Element.valueOf(row[2]))
                .setDescription(row[3])
                .setImagePath(row[4])
                .setPower(Integer.parseInt(row[5]))
                .setAttack(Integer.parseInt(row[6]))
                .setDefense(Integer.parseInt((row[7])))
                .buildAura();
            this.auraList.add(a);
        }
    }
    
    /**
     * Initialize Main Window Controller
     */
    @FXML private void initialize() throws IOException, URISyntaxException {
        try {
            System.out.println("Game has started");
            String background = MainWindowController.class.getResource("../background/background.jpg").toExternalForm();
            mainWindow.setStyle("-fx-background-image: url('" + background + "'); " +
                          "-fx-background-position: center center; ");
            currPhase = "draw";
            currCardEvent = "";
            turn = 1;
            loadCards();
            this.p1 = new Player("Qila", this.landList, this.characterList, this.auraList);
            this.p2 = new Player("Hojun", this.landList, this.characterList, this.auraList);
            this.p1.drawCardFromDeck();
            player2FieldController.setCardsOnHand("invisible");
            player1FieldController.init(this, this.p1, this.p2, 1);
            player2FieldController.init(this, this.p2, this.p1, 2);
            singleCardController.init(this, this.auraList.get(0));
        }
        catch (IOException | URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
    
    /**
     * Get current phase
     */
    public String getCurrPhase() {
        return currPhase;
    }

    /**
     * Get current card event
     */
    public String getCurrCardEvent() {
        return this.currCardEvent;
    }
    
    /**
     * Set current card event
     * @param event, user-defined event
     */
    public void setCurrCardEvent(String event) {
        this.currCardEvent = event;
    } 
    
    /**
     * Get turn
     */
    public int getTurn() {
        return turn;
    }
    
    /**
     * Show card details
     * @param c, card that want the details to be shown
     */
    public void showCardDetails(Card c) {
        this.singleCardController.setCard(c);
        this.singleCardController.showCardDetails();
    }
    
    /**
     * Reset card details
     */
    public void resetCardDetails() {
        this.singleCardController.resetCard();
    }

    /**
     * Refresh players
     */
    public void refreshPlayers() {
        this.player1FieldController.refreshPlayer();
        this.player2FieldController.refreshPlayer();
    }

    /**
     * Select attack target
     * @param pfc, the Player Field Controller
     * @param card, target card
     */
    public void onAttackTargetSelected(PlayerFieldController pfc, com.avatarduel.card.Character card) {
        if (pfc == player1FieldController) {
            player2FieldController.onAttackCard(card);
        } else if (pfc == player2FieldController) {
            player1FieldController.onAttackCard(card);
        } 
    }
    /**
     * Select destroy target
     * @param pfc, the Player Field Controller
     * @param card, target card
     */
    
    public void onDestroyTargetSelected(PlayerFieldController pfc, com.avatarduel.card.Character card) {
        if (pfc == player1FieldController) {
            player2FieldController.onDestroyCard(card);
        } else if (pfc == player2FieldController) {
            player1FieldController.onDestroyCard(card);
        } 
    }

    /**
     * Check player's HP
     */
    public void checkPlayersHP() {
        if  (this.p1.getLifePoint() <= 0) {
            System.out.println(this.p2.getName() + "Wins!");
            this.currPhase = "win";
        } else if  (this.p2.getLifePoint() <= 0) {
            System.out.println(this.p1.getName() + "Wins!");
            this.currPhase = "win";
        }
    }

    /**
     * Next Button Clicked
     * @param event, what ActionEvent that set
     */
    @FXML private void nextButtonClicked(ActionEvent event) {
        if (this.currPhase.equals("draw")) {
            this.draw.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
            this.main.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: yellow;");
            this.currPhase = "main";
        } else if (currPhase.equals("main")) {
            this.main.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
            this.battle.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: yellow;");
            this.currPhase = "battle";
            if (turn == 2) player1FieldController.setAttackPlayerButton("visible");
//            else player2FieldController.setAttackPlayerButton("visible");
        } else if (currPhase.equals("battle")) {
            this.battle.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
            this.end.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: yellow;");
            this.currPhase = "end";
            if (turn == 2) player1FieldController.setAttackPlayerButton("invisible");
//            else player2FieldController.setAttackPlayerButton("invisible");
        } else if (currPhase.equals("end")) {
            this.end.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid;");
            this.draw.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-style: solid; -fx-background-color: yellow;");
            this.currPhase = "draw";
            if (turn == 1) {
                turn = 2;
                this.p2.initializeTurn();
                this.p2.drawCardFromDeck();
                p2.getStatus().reset();
                this.player2FieldController.refreshPlayer();
                this.player2FieldController.isLandSummoned = false;
                player2FieldController.setCardsOnHand("visible");
                player1FieldController.setCardsOnHand("invisible");
                this.nextButton.setLayoutY(125.0);
            }
            else {
                turn = 1;
                this.p1.initializeTurn();
                this.p1.drawCardFromDeck();
                p1.getStatus().reset();
                this.player1FieldController.refreshPlayer();
                this.player1FieldController.isLandSummoned = false;
                player1FieldController.setCardsOnHand("visible");
                player2FieldController.setCardsOnHand("invisible");
                this.nextButton.setLayoutY(550.0);
            }
        }
    }
}
