package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import com.avatarduel.model.*;
import com.avatarduel.model.Character;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  private static final String CHARACTER_CSV_FILE_PATH = "card/data/character.csv";
  private static final String SKILL_AURA_CSV_FILE_PATH = "card/data/skill_aura.csv";
  private List<Land> landList;
  private List<com.avatarduel.model.Character> characterList;
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
    this.characterList = new ArrayList<com.avatarduel.model.Character>();
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

  @Override
  public void start(Stage stage) {
    Text text = new Text();
    text.setText("Loading...");
    text.setX(50);
    text.setY(50);

    Group root = new Group();
    root.getChildren().add(text);

    Scene scene = new Scene(root, 1280, 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    try {
      this.loadCards();
      text.setText("Avatar Duel!");
    } catch (Exception e) {
      text.setText("Failed to load cards: " + e);
    }
  }

  public static void main(String[] args) {
    launch();
  }
}