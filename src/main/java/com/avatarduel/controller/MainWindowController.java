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

//import com.avatarduel.player.*;
import com.avatarduel.card.*;
import com.avatarduel.card.Character;
import com.avatarduel.util.CSVReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

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
    
//    private Player p1;
//    private Player p2;
    
    private static final String LAND_CSV_FILE_PATH = "../card/data/land.csv";
    private static final String CHARACTER_CSV_FILE_PATH = "../card/data/character.csv";
    private static final String SKILL_AURA_CSV_FILE_PATH = "../card/data/skill_aura.csv";
    private List<Land> landList;
    private List<Character> characterList;
    private List<Aura> auraList;

    public void loadCards() throws IOException, URISyntaxException {
        File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
        File characterCSVFile = new File(getClass().getResource(CHARACTER_CSV_FILE_PATH).toURI());
        File skillCSVFile = new File(getClass().getResource(SKILL_AURA_CSV_FILE_PATH).toURI());
        System.out.println("check1");

        CSVReader landReader = new CSVReader(landCSVFile, "\t");
        CSVReader characterReader = new CSVReader(characterCSVFile, "\t");
        CSVReader skillReader = new CSVReader(skillCSVFile, "\t");
        System.out.println("check2");

        landReader.setSkipHeader(true);
        characterReader.setSkipHeader(true);
        skillReader.setSkipHeader(true);
        System.out.println("check3");

        List<String[]> landRows = landReader.read();
        List<String[]> characterRows = characterReader.read();
        List<String[]> skillRows = skillReader.read();
        System.out.println("check4");

        this.landList = new ArrayList<Land>();
        this.characterList = new ArrayList<Character>();
        this.auraList = new ArrayList<Aura>();
        System.out.println("check5");

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
        System.out.println("check6");

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
        System.out.println("check7");

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
        System.out.println("check8");
    }
    
    @FXML private void initialize() throws IOException, URISyntaxException {
        try {
            System.out.println("Game has started");
            player1FieldController.init(this);
            player2FieldController.init(this);
            loadCards();
            singleCardController.init(this, this.characterList.get(0));
        }
        catch (IOException | URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
}
