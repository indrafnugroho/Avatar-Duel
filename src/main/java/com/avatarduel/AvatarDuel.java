package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import com.avatarduel.card.*;
//import com.avatarduel.player.*;
//import com.avatarduel.controller.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
  private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
  private List<Land> landList;
  private List<com.avatarduel.card.Character> characterList;
  private List<Aura> auraList;

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
    this.characterList = new ArrayList<com.avatarduel.card.Character>();
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
      com.avatarduel.card.Character c = new CardBuilder(CardType.CHARACTER)
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

  @Override
  public void start(Stage stage) throws IOException {   
    try {
        this.loadCards();
        Parent windows = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("com/avatarduel/fxml/MainWindow.fxml")));
        Scene scene = new Scene(windows);
        stage.setTitle("Avatar Duel");
        stage.setScene(scene);
        stage.show();
    } catch (IOException | URISyntaxException e) {
        throw new IllegalStateException(e);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}