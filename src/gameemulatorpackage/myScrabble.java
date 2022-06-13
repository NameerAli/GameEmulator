package gameemulatorpackage;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jdk.nashorn.internal.ir.VarNode;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class myScrabble extends Application implements HomeScreen {

    HBox layout;
    VBox vbox;
    Label playerTurn = new Label();
    ArrayList<Button> rack = new ArrayList<Button>();
    ArrayList<String> wordBag = new ArrayList<String>(), blankList = new ArrayList<String>();
    Button[][] boardArr = new Button[15][15];
    Stage window, homeWindow;
    ArrayList<ScrabblePlayer> players = new ArrayList<ScrabblePlayer>();
    ArrayList<Integer> scores = new ArrayList<Integer>();
    int numOfPlayerInt, currentPlayer;
    File dict;
    ArrayList<String> dictionary = new ArrayList<String>();
    int totalGameTurns = 0;
    TableView scoreTable = new TableView();
    boolean skipped = false;

    public myScrabble() {
    }

    @Override
    public void start(Stage stage) {
        //Assigning scores to each letter
        for (int s = 65; s <= 90; s++) {
            int score = 0;
            char ch = (char) s;
            if (s == 'A' || s == 'I' || s == 'U' || s == 'S' || s == 'L' || s == 'E' || s == 'O' || s == 'N' || s == 'R' || s == 'T') {
                score = 1;
            }
            if (s == 'D' || s == 'G') {
                score = 2;
            }
            if (s == 'B' || s == 'C' || s == 'M' || s == 'P') {
                score = 3;
            }
            if (s == 'F' || s == 'H' || s == 'V' || s == 'W' || s == 'Y') {
                score = 4;

            }
            if (s == 'K') {
                score = 5;
            }
            if (s == 'J' || s == 'X') {
                score = 8;
            }
            if (s == 'Q' || s == 'Z') {
                score = 10;
            }
            scores.add(s - 65, score);
        }
        //Transferring dictonary from file to Array
        try {
            dict = new File("dic.txt");
            Scanner myReader = new Scanner(dict);
            while (myReader.hasNext()) {
                String data = myReader.nextLine();
                dictionary.add(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        //TABLE WORK
        TableColumn<String, ScrabblePlayer> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column1.prefWidthProperty().bind(scoreTable.widthProperty().multiply(0.4));
        TableColumn<String, ScrabblePlayer> column2 = new TableColumn<>("Skipped Turn");
        column2.setCellValueFactory(new PropertyValueFactory<>("skipTurn"));
        column2.prefWidthProperty().bind(scoreTable.widthProperty().multiply(0.2));
        TableColumn<String, ScrabblePlayer> column3 = new TableColumn<>("Turns");
        column3.setCellValueFactory(new PropertyValueFactory<>("turns"));
        column3.prefWidthProperty().bind(scoreTable.widthProperty().multiply(0.2));
        TableColumn<String, ScrabblePlayer> column4 = new TableColumn<>("Score");
        column4.setCellValueFactory(new PropertyValueFactory<>("score"));
        column4.prefWidthProperty().bind(scoreTable.widthProperty().multiply(0.2));
        scoreTable.getColumns().addAll(column1, column2, column3, column4);
        scoreTable.setFixedCellSize(25);
        scoreTable.setPrefSize(300, 150);
        scoreTable.setMinSize(150, 25);
        //Initialzing Bag
        for (int i = 97; i < 123; i++) {
            char s = (char) i;
            int numOfPrint = 0;
            if (s == 'a' || s == 'i') {
                numOfPrint = 9;
            }
            if (s == 'e') {
                numOfPrint = 12;
            }
            if (s == 'o') {
                numOfPrint = 8;
            }
            if (s == 'n' || s == 'r' || s == 't') {
                numOfPrint = 6;
            }
            if (s == 'd' || s == 'u' || s == 's' || s == 'l') {
                numOfPrint = 4;
            }
            if (s == 'g') {
                numOfPrint = 3;
            }
            if (s == 'b' || s == 'c' || s == 'm' || s == 'p' || s == 'f' || s == 'h' || s == 'v' || s == 'w' || s == 'y') {
                numOfPrint = 2;
            }
            if (s == 'k' || s == 'j' || s == 'x' || s == 'q' || s == 'z') {
                numOfPrint = 1;
            }
            for (int j = 0; j < numOfPrint; j++) {
                wordBag.add(String.valueOf(s).toUpperCase());
            }
        }
        wordBag.add("*");
        wordBag.add("*");
        //Display Rack
        for (int i = 0; i < 7; i++) {
            Button rect = new Button("-");
            rect.setPrefSize(42, 42);
            rect.setStyle("-fx-background-color: #f5f5dc");
            DropShadow ds = new DropShadow();
            rect.setEffect(ds);
            rack.add(rect);
        }
        //Main Scrabble screen
        window = stage;
        layout = new HBox();
        Label l = new Label("This is Where my game is going to be");
        GridPane board = new GridPane();
        //Adding Scrabble grid
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                Button rec = new Button();
                rec.setPrefSize(45, 45);
                rec.setStyle("-fx-background-color: #c9ff8c");
                rec.setOnMouseEntered(e -> {
                    rec.setStyle("-fx-background-color: #d5ffa6");
                });
                rec.setOnMouseExited(e -> {
                    rec.setStyle("-fx-background-color: #c9ff8c");
                });
                DropShadow ds = new DropShadow();
                rec.setEffect(ds);
                rec.setOnAction(f -> {
                    VBox inputLayout = new VBox();
                    Label directionLabel = new Label("Choose a Direction");
                    ToggleButton downwards = new ToggleButton("DOWNWARDS");
                    ToggleButton rightwards = new ToggleButton("RIGHTWARDS");
                    HBox direction = new HBox();
                    direction.getChildren().addAll(downwards, rightwards);
                    direction.setPadding(new Insets(5));
                    direction.setAlignment(Pos.CENTER);
                    direction.setSpacing(5);
                    Region space = new Region();
                    VBox.setVgrow(space, Priority.ALWAYS);
                    Region space2 = new Region();
                    VBox.setVgrow(space2, Priority.ALWAYS);
                    Region space3 = new Region();
                    VBox.setVgrow(space3, Priority.ALWAYS);
                    Region space4 = new Region();
                    VBox.setVgrow(space4, Priority.ALWAYS);
                    Label wordName = new Label("Enter Word: ");
                    TextField word = new TextField();
                    HBox wordInput = new HBox();
                    wordInput.getChildren().addAll(wordName, word);
                    Button confirm = new Button("CONFIRM");
                    wordInput.setAlignment(Pos.CENTER);
                    confirm.setPrefSize(100, 30);
                    inputLayout.setAlignment(Pos.CENTER);
                    inputLayout.getChildren().addAll(space, directionLabel, direction, space2, wordInput, space3, confirm, space4);
                    Scene inputScene = new Scene(inputLayout, 500, 300);
                    Stage inputStage = new Stage();
                    inputStage.initModality(Modality.APPLICATION_MODAL);
                    inputStage.setScene(inputScene);
                    inputStage.setTitle("Word Input");
                    downwards.setOnAction(o -> {
                        if (rightwards.isSelected()) {
                            rightwards.setSelected(false);
                        }
                    });
                    rightwards.setOnAction(o -> {
                        if (downwards.isSelected()) {
                            downwards.setSelected(false);
                        }
                    });
                    confirm.setOnAction(a -> {
                        boolean conflict = false;
                        ArrayList<String> conflictTile = new ArrayList<String>();
                        ArrayList<String> conflictLetter = new ArrayList<String>();
                        int startX2 = Integer.parseInt(rec.getAccessibleText().substring(0, rec.getAccessibleText().indexOf(",")));
                        int startY2 = Integer.parseInt(rec.getAccessibleText().substring(rec.getAccessibleText().indexOf(",") + 1, rec.getAccessibleText().length()));
                        boolean limit = false;
                        boolean startRightTop = false;
                        boolean endRightTop = false;
                        boolean center = true;
                        if (downwards.isSelected()) {
                            if (startY2 + word.getText().length() > 14) {
                                limit = true;
                            }
                            if (!boardArr[startX2][startY2 - 1].getText().isEmpty()) {
                                startRightTop = true;
                            }
                            if(!boardArr[startX2][word.getText().length()].getText().isEmpty()){
                                endRightTop =true;
                            }
                            if ((!(startY2 <= 7 && (startY2 + word.getText().length()) >= 7)) && totalGameTurns == 0) {
                                center = false;
                            }
                        }
                        if (rightwards.isSelected()) {
                            if (startX2 + word.getText().length() > 14) {
                                limit = true;
                            }
                            if (!boardArr[startX2 - 1][startY2].getText().isEmpty()) {
                                startRightTop = true;
                            }
                            if(!boardArr[word.getText().length()][startY2].getText().isEmpty()){
                                endRightTop =true;
                            }
                            if ((!(startX2 <= 7 && (startX2 + word.getText().length()) >= 7)) && totalGameTurns == 0) {
                                center = false;
                            }
                        }
                        if (!limit && !startRightTop && center) {
                            for (int x = 0; x < word.getText().length(); x++) {
                                if (downwards.isSelected() && totalGameTurns != 0) {
                                    if (!boardArr[startX2][startY2].getText().isEmpty()) {
                                        conflict = true;
                                        conflictTile.add(startX2 + "," + startY2);
                                        conflictLetter.add(String.valueOf(word.getText().charAt(x)));
                                    }
                                    startY2++;
                                }
                                if (rightwards.isSelected() && totalGameTurns != 0) {
                                    if (!boardArr[startX2][startY2].getText().isEmpty()) {
                                        conflict = true;
                                        conflictTile.add(startX2 + "," + startY2);
                                        conflictLetter.add(String.valueOf(word.getText().charAt(x)));
                                    }
                                    startX2++;
                                }
                            }
                            boolean conflictResolve = true;
                            if (conflict) {
                                for (int b = 0; b < conflictLetter.size(); b++) {
                                    String[] conflictTileSplitCopy = conflictTile.get(b).split(",");
                                    int X = Integer.parseInt(conflictTileSplitCopy[0]);
                                    int Y = Integer.parseInt(conflictTileSplitCopy[1]);
                                    if (!boardArr[X][Y].getText().equalsIgnoreCase(conflictLetter.get(b))) {
                                        conflictResolve = false;
                                    }
                                }
                            }
                            if ((!conflictResolve) && (totalGameTurns != 0)) {
                                AlertBoxGame.display("ERROR", "Can Not Use Existing Tiles on Board");
                            } else if (totalGameTurns == 0 || (conflict)) {
                                boolean inDictionary = false;
                                if (downwards.isSelected() && rightwards.isSelected()) {
                                    AlertBoxGame.display("Input Error", "Please choose only one Direction");
                                } else {
                                    if (downwards.isSelected() || rightwards.isSelected()) {
                                        String inputWord = word.getText();
                                        inDictionary = rackCheck(inputWord, conflictLetter);
                                        if (inDictionary) {
                                            //Word is in Dictionary and also in player's Rack
                                            int secondaryScore = 0;
                                            String pos = rec.getAccessibleText();
                                            String[] posSplit = pos.split(",");
                                            String[][] inputWordLoc = new String[inputWord.length()][3];
                                            for (int k = 0; k < inputWordLoc.length; k++) {
                                                inputWordLoc[k][0] = String.valueOf(inputWord.charAt(k));
                                            }

                                            int startSecondaryX = -1, startSecondaryY = -1, endSecondaryX, endSecondaryY;
                                            String secondaryWord = "";
                                            boolean isSecondary = false, secondaryInDictionary = true;
                                            if (downwards.isSelected()) {
                                                for (int w = 0; w < inputWordLoc.length; w++) {
                                                    inputWordLoc[w][1] = posSplit[0];
                                                    inputWordLoc[w][2] = String.valueOf(Integer.parseInt(posSplit[1]) + w);
                                                }
                                                for (int w = 0; w < inputWordLoc.length; w++) {
                                                    isSecondary = false;
                                                    secondaryWord = "";
                                                    startSecondaryX = Integer.parseInt(inputWordLoc[w][1]);
                                                    endSecondaryX = Integer.parseInt(inputWordLoc[w][1]);
                                                    startSecondaryY = Integer.parseInt(inputWordLoc[w][2]);
                                                    endSecondaryY = Integer.parseInt(inputWordLoc[w][2]);
                                                    int count1 = 0, count2 = 0;
                                                    while (startSecondaryX > 0 && (!boardArr[startSecondaryX][startSecondaryY].getText().isEmpty() || (startSecondaryX == Integer.parseInt(inputWordLoc[w][1]) && startSecondaryY == Integer.parseInt(inputWordLoc[w][2]))) && (totalGameTurns != 0)) {
                                                        startSecondaryX--;
                                                        if (count1 > 0) {
                                                            isSecondary = true;
                                                        }
                                                        count1++;
                                                    }
                                                    while (endSecondaryX <= 14 && (!boardArr[endSecondaryX][endSecondaryY].getText().isEmpty() || (endSecondaryX == Integer.parseInt(inputWordLoc[w][1]) && endSecondaryY == Integer.parseInt(inputWordLoc[w][2]))) && (totalGameTurns != 0)) {
                                                        endSecondaryX++;
                                                        if (count2 > 0) {
                                                            isSecondary = true;
                                                        }
                                                        count2++;
                                                    }
                                                    int startSecondaryCopy = startSecondaryX + 1;
                                                    int endSecondaryCopy = endSecondaryX - 1;
                                                    if (isSecondary) {
                                                        for (int u = startSecondaryCopy; u <= endSecondaryCopy; u++) {
                                                            secondaryWord = secondaryWord + boardArr[u][startSecondaryY].getText();
                                                            if (Integer.parseInt(inputWordLoc[w][1]) == u && boardArr[u][startSecondaryY].getText().isEmpty()) {
                                                                secondaryWord = secondaryWord + inputWordLoc[w][0];
                                                            }
                                                        }

                                                    }
                                                    if (!secondaryWord.isEmpty() && !dictionaryCheck(secondaryWord)) {
                                                        secondaryInDictionary = dictionaryCheck(secondaryWord);
                                                        for (int k = 0; k < secondaryWord.length(); k++) {
                                                            for (int q = 0; q < scores.size(); q++) {
                                                                int qwe = q + 65;
                                                                char chq = (char) (qwe);
                                                                String secondaryWordCopy = "";
                                                                for (int u = startSecondaryCopy; u <= endSecondaryCopy; u++) {
                                                                    secondaryWordCopy = secondaryWordCopy + boardArr[u][startSecondaryY].getText();
                                                                }
                                                                if ((secondaryWord.charAt(k)) == chq && secondaryWord.equalsIgnoreCase(secondaryWordCopy)) {
                                                                    secondaryScore = secondaryScore + scores.get(q);
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                            if (rightwards.isSelected()) {
                                                for (int w = 0; w < inputWordLoc.length; w++) {
                                                    inputWordLoc[w][1] = String.valueOf(Integer.parseInt(posSplit[0]) + w);
                                                    inputWordLoc[w][2] = posSplit[1];
                                                }
                                                for (int r = 0; r < inputWordLoc.length; r++) {
                                                    secondaryWord = "";
                                                    isSecondary = false;
                                                    startSecondaryX = Integer.parseInt(inputWordLoc[r][1]);
                                                    endSecondaryX = Integer.parseInt(inputWordLoc[r][1]);
                                                    startSecondaryY = Integer.parseInt(inputWordLoc[r][2]);
                                                    endSecondaryY = Integer.parseInt(inputWordLoc[r][2]);
                                                    int count1 = 0, count2 = 0;
                                                    while (startSecondaryY > 0 && (!boardArr[startSecondaryX][startSecondaryY].getText().isEmpty() || (startSecondaryX == Integer.parseInt(inputWordLoc[r][1]) && startSecondaryY == Integer.parseInt(inputWordLoc[r][2]))) && (totalGameTurns != 0)) {
                                                        startSecondaryY--;
                                                        if (count1 > 0) {
                                                            isSecondary = true;
                                                        }
                                                        count1++;
                                                    }
                                                    while (endSecondaryY <= 14 && (!boardArr[endSecondaryX][endSecondaryY].getText().isEmpty() || (endSecondaryX == Integer.parseInt(inputWordLoc[r][1]) && endSecondaryY == Integer.parseInt(inputWordLoc[r][2]))) && (totalGameTurns != 0)) {
                                                        endSecondaryY++;
                                                        if (count2 > 0) {
                                                            isSecondary = true;
                                                        }
                                                        count2++;
                                                    }
                                                    int startSecondaryCopy = startSecondaryY + 1;
                                                    int endSecondaryCopy = endSecondaryY - 1;
                                                    if (isSecondary) {
                                                        for (int u = startSecondaryY + 1; u <= endSecondaryY - 1; u++) {
                                                            secondaryWord = secondaryWord + boardArr[startSecondaryX][u].getText();
                                                            if (Integer.parseInt(inputWordLoc[r][2]) == u && boardArr[startSecondaryX][u].getText().isEmpty()) {
                                                                secondaryWord = secondaryWord + inputWordLoc[r][0];
                                                            }
                                                        }
                                                    }
                                                    if (!secondaryWord.isEmpty() && !dictionaryCheck(secondaryWord)) {
                                                        secondaryInDictionary = dictionaryCheck(secondaryWord);
                                                        for (int k = 0; k < secondaryWord.length(); k++) {
                                                            for (int q = 0; q < scores.size(); q++) {
                                                                int qwe = q + 65;
                                                                char chq = (char) (qwe);
                                                                String secondaryWordCopy = "";
                                                                for (int u = startSecondaryCopy; u <= endSecondaryCopy; u++) {
                                                                    secondaryWordCopy = secondaryWordCopy + boardArr[startSecondaryX][u].getText();
                                                                }
                                                                if (((secondaryWord.charAt(k)) == chq && (secondaryWord.equalsIgnoreCase(secondaryWordCopy)))) {
                                                                    secondaryScore = secondaryScore + scores.get(q);
                                                                }
                                                            }
                                                        }
                                                        break;
                                                    }
                                                }
                                            }
                                            if (!secondaryInDictionary && isSecondary) {
                                                AlertBoxGame.display("ERROR", "Cannot Place the Words Here");
                                                blankList.clear();

                                            } else {

                                                //IF secondaryInDictionary = false; so dont do aagay ka stuff and give error msg
                                                // printing the character on board
                                                int simpleScore = 0;
                                                int dwsCount = 0;
                                                int twsCount = 0;
                                                inputWord = inputWord.toUpperCase();
                                                int startX = Integer.parseInt(posSplit[0]);
                                                int startY = Integer.parseInt(posSplit[1]);
                                                //getting the color of board
                                                String style = boardArr[startX][startY].getStyle();
                                                String[] styleArr = style.split("#");
                                                //adding scores
                                                for (int k = 0; k < inputWord.length(); k++) {
                                                    style = boardArr[startX][startY].getStyle();
                                                    styleArr = style.split("#");
                                                    String colorCode = styleArr[1];
                                                    for (int q = 0; q < scores.size(); q++) {
                                                        int qwe = q + 65;
                                                        char chq = (char) (qwe);

                                                        if ((inputWord.charAt(k)) == chq) {
                                                            // TLS AND DLS
                                                            if (colorCode.equals("ffa8ae")) {
                                                                simpleScore = simpleScore + 3 * (scores.get(q));
                                                            } else if (colorCode.equals("00ffff")) {
                                                                simpleScore = simpleScore + 2 * (scores.get(q));
                                                            } else if (colorCode.equals("90ee90")) {
                                                                simpleScore = simpleScore + (scores.get(q));
                                                                twsCount++;
                                                            } else if (colorCode.equals("d3d3d3")) {
                                                                dwsCount++;
                                                                simpleScore = simpleScore + (scores.get(q));
                                                            } else {
                                                                simpleScore = simpleScore + scores.get(q);
                                                            }
                                                        }
                                                    }

                                                    boardArr[startX][startY].setText(String.valueOf(inputWord.charAt(k)).toUpperCase());
                                                    boardArr[startX][startY].setFont(new Font(15));
                                                    boardArr[startX][startY].setStyle("-fx-background-color:  #f5f5dc;" + "-fx-border-color: #" + colorCode + ";" + "-fx-border-width: 3 3 3 3;");
                                                    Button bt = boardArr[startX][startY];
                                                    bt.setOnMouseEntered(e -> {
                                                        bt.setStyle("-fx-background-color:  #f9f9eb;" + "-fx-border-color: #" + colorCode + ";" + "-fx-border-width: 3 3 3 3;");
                                                    });
                                                    bt.setOnMouseExited(e -> {
                                                        bt.setStyle("-fx-background-color:  #f5f5dc;" + "-fx-border-color: #" + colorCode + ";" + "-fx-border-width: 3 3 3 3;");
                                                    });
                                                    if (downwards.isSelected()) {
                                                        startY++;
                                                    } else if (rightwards.isSelected()) {
                                                        startX++;
                                                    }
                                                }
                                                // Subtracting blank Letters
                                                for (int h = 0; h < blankList.size(); h++) {
                                                    for (int q = 0; q < scores.size(); q++) {
                                                        int qwe = q + 65;
                                                        char chq = (char) (qwe);
                                                        if (blankList.get(h).toUpperCase().charAt(0) == chq) {
                                                            simpleScore = simpleScore - scores.get(q);
                                                        }
                                                    }
                                                }
                                                // Adding Secondary Words's Score
                                                simpleScore = simpleScore + secondaryScore;
                                                //TWS AND DWS Working
                                                for (int h = 0; h < twsCount; h++) {
                                                    simpleScore = (3 * simpleScore);
                                                }
                                                for (int h = 0; h < dwsCount; h++) {
                                                    simpleScore = (2 * simpleScore);
                                                }
                                                if (totalGameTurns == 0 && inputWord.length() == 7) {
                                                    simpleScore = simpleScore + 50;
                                                }
                                                players.get(currentPlayer).setScore(players.get(currentPlayer).getScore() + simpleScore);
                                                ArrayList<String> rackCheck = new ArrayList<String>();
                                                String[] inputWordSplit;
                                                inputWordSplit = inputWord.split("");
                                                rackCheck = players.get(currentPlayer).getRack();
                                                for (int w = 0; w < inputWordSplit.length; w++) {
                                                    int count = 0, cnt = 0;
                                                    for (int q = 0; q < rackCheck.size(); q++) {
                                                        if ((inputWordSplit[w].equalsIgnoreCase(rackCheck.get(q)))) {
                                                            if (conflict) {
                                                                for (int ij = 0; ij < conflictLetter.size(); ij++) {
                                                                    if (!(conflictLetter.get(ij).equalsIgnoreCase(rackCheck.get(q)))) {
                                                                        if (count == 0) {
                                                                            rackCheck.remove(q);
                                                                        }
                                                                        count++;
                                                                    }
                                                                }
                                                            } else {
                                                                if (cnt == 0) {
                                                                    rackCheck.remove(q);
                                                                    ;
                                                                }
                                                                cnt++;
                                                            }
                                                        }
                                                    }
                                                }
                                                if (!blankList.isEmpty()) {
                                                    for (int ae = 0; ae < blankList.size(); ae++) {
                                                        for (int q = 0; q < rackCheck.size(); q++) {
                                                            if (rackCheck.get(q).equalsIgnoreCase("*")) {
                                                                rackCheck.remove(q);
                                                                break;
                                                            }
                                                        }
                                                    }
                                                    blankList.clear();
                                                }
                                                updateTurn();
                                                inputStage.close();
                                            }
                                        } else {
                                            blankList.clear();
                                        }
                                    } else {
                                        AlertBoxGame.display("Input Error", "Please choose a Direction");
                                        blankList.clear();
                                    }
                                }
                            } else {
                                AlertBoxGame.display("Input Error", "The Words must be connected");
                                blankList.clear();
                            }
                        } else {
                            if (limit) {
                                AlertBoxGame.display("Input Error", "Board Limit Reached");
                            }
                            if (startRightTop||endRightTop) {
                                AlertBoxGame.display("Input Error", "Can Not Place Word Here");
                            }
                            if (!center) {
                                AlertBoxGame.display("Input Error", "Word Should Cross Center");
                            }
                            blankList.clear();
                        }
                    });
                    inputStage.show();

                });
                rec.setFont(new Font(10));
                if ((i == 0 && j == 0) || (i == 0 && j == 7) || (i == 0 && j == 14) || (i == 7 && j == 0) || (i == 7 && j == 14) || (i == 14 && j == 0) || (i == 14 && j == 7) || (i == 14 && j == 14)) {
                    rec.setStyle("-fx-background-color: #ffa8ae");
                    rec.setOnMouseEntered(e -> {
                        rec.setStyle("-fx-background-color: #ffc2c6");
                    });
                    rec.setOnMouseExited(e -> {
                        rec.setStyle("-fx-background-color: #ffa8ae");
                    });
                }
                for (int x = 1; x < 5; x++) {
                    if (i == x && j == x) {
                        rec.setStyle("-fx-background-color: #d3d3d3");
                        rec.setOnMouseEntered(e -> {
                            rec.setStyle("-fx-background-color: #e0e0e0");
                        });
                        rec.setOnMouseExited(e -> {
                            rec.setStyle("-fx-background-color: #d3d3d3");
                        });
                    };
                }
                for (int x = 1, y = 13; x <= 5 && y >= 10; x++, y--) {
                    if (i == x && j == y) {
                        rec.setStyle("-fx-background-color: #d3d3d3");
                        rec.setOnMouseEntered(e -> {
                            rec.setStyle("-fx-background-color: #e0e0e0");
                        });
                        rec.setOnMouseExited(e -> {
                            rec.setStyle("-fx-background-color: #d3d3d3");
                        });
                    }
                }
                for (int x = 13, y = 1; x >= 10 && y <= 5; x--, y++) {
                    if (i == x && j == y) {
                        rec.setStyle("-fx-background-color: #d3d3d3");
                        rec.setOnMouseEntered(e -> {
                            rec.setStyle("-fx-background-color: #e0e0e0");
                        });
                        rec.setOnMouseExited(e -> {
                            rec.setStyle("-fx-background-color: #d3d3d3");
                        });
                    }
                }
                for (int x = 10; x < 14; x++) {
                    if (i == x && j == x) {
                        rec.setStyle("-fx-background-color: #d3d3d3");
                        rec.setOnMouseEntered(e -> {
                            rec.setStyle("-fx-background-color: #e0e0e0");
                        });
                        rec.setOnMouseExited(e -> {
                            rec.setStyle("-fx-background-color: #d3d3d3");
                        });
                    };
                }
                if (i == 7 && j == 7) {
                    rec.setFont(new Font(20));
                    rec.setText("X");
                    rec.setStyle("-fx-background-color: #d3d3d3");
                    rec.setOnMouseEntered(e -> {
                        rec.setStyle("-fx-background-color: #e0e0e0");
                    });
                    rec.setOnMouseExited(e -> {
                        rec.setStyle("-fx-background-color: #d3d3d3");
                    });
                }
                if ((i == 0 && j == 3) || (i == 0 && j == 11) || (i == 2 && j == 6) || (i == 2 && j == 8) || (i == 3 && j == 0) || (i == 3 && j == 7) || (i == 3 && j == 14) || (i == 6 && j == 2) || (i == 6 && j == 6) || (i == 6 && j == 8) || (i == 6 && j == 12) || (i == 7 && j == 3) || (i == 7 && j == 11) || (i == 8 && j == 2) || (i == 8 && j == 6) || (i == 8 && j == 8) || (i == 8 && j == 12) || (i == 11 && j == 0) || (i == 11 && j == 7) || (i == 11 && j == 14) || (i == 12 && j == 6) || (i == 12 && j == 8) || (i == 14 && j == 3) || (i == 14 && j == 11)) {
                    rec.setStyle("-fx-background-color: #00ffff");
                    rec.setOnMouseEntered(e -> {
                        rec.setStyle("-fx-background-color: #89ffff");
                    });
                    rec.setOnMouseExited(e -> {
                        rec.setStyle("-fx-background-color: #00ffff");
                    });
                }
                if ((i == 1 && j == 5) || (i == 1 && j == 9) || (i == 5 && j == 1) || (i == 5 && j == 5) || (i == 5 && j == 9) || (i == 5 && j == 13) || (i == 13 && j == 5) || (i == 13 && j == 9) || (i == 9 && j == 1) || (i == 9 && j == 5) || (i == 9 && j == 9) || (i == 9 && j == 13)) {
                    rec.setStyle("-fx-background-color: #90ee90");
                    rec.setOnMouseEntered(e -> {
                        rec.setStyle("-fx-background-color: #a6f1a6");
                    });
                    rec.setOnMouseExited(e -> {
                        rec.setStyle("-fx-background-color: #90ee90");
                    });
                }
                rec.setAccessibleText(i + "," + j);
                boardArr[i][j] = rec;
                board.add(rec, i, j);
            }
        }
        board.setHgap(0);
        board.setVgap(0);
        vbox = new VBox();
        HBox rackLayout = new HBox();
        for (int i = 0; i < rack.size(); i++) {
            rackLayout.getChildren().add((Button) rack.get(i));
        }
        rackLayout.setSpacing(5);
        rackLayout.setPadding(new Insets(5));
        HBox buttons = new HBox();
        Button shuffle = new Button("SHUFFLE");
        Button quitGame = new Button("QUIT GAME");
        Button skipTurn = new Button("SKIP TURN");
        Button endGame = new Button("ENDGAME");
        endGame.setPrefSize(349, 50);
        endGame.setOnAction(p -> {
            if (totalGameTurns == 0) {
                AlertBoxGame.display("ERROR QUIT", "Please Use QUITGAME");
            } else {
                int highestScore = -100000;
                int highPlayer = 0;
                for (int i = 0; i < players.size(); i++) {
                    int currentPlayerScore = players.get(i).getScore();
                    ArrayList<String> rack = players.get(i).getRack();
                    for (int j = 0; j < players.get(i).getRack().size(); j++) {
                        for (int q = 0; q < scores.size(); q++) {
                            int qwe = q + 65;
                            char chq = (char) (qwe);
                            if (rack.get(j).toUpperCase().charAt(0) == chq) {
                                currentPlayerScore = currentPlayerScore - scores.get(q);
                            }
                        }
                    }
                    players.get(i).setScore(currentPlayerScore);
                    if (currentPlayerScore > highestScore) {
                        highestScore = currentPlayerScore;
                        highPlayer = i;
                    }
                }
                Stage winWindow = new Stage();
                Label winLabel = new Label(players.get(highPlayer).getName() + " WINS");
                Button winCloseButton = new Button("CLOSE");
                winCloseButton.setPrefSize(75, 20);
                winCloseButton.setOnAction(t -> {
                    winWindow.close();
                    exit();
                    start(new Stage());
                });
                VBox winLayout = new VBox(10);
                winLayout.getChildren().addAll(winLabel, winCloseButton);
                winLayout.setAlignment(Pos.CENTER);
                Scene winScene = new Scene(winLayout, 250, 100);
                winWindow.setScene(winScene);
                winWindow.setTitle("Game Finished");
                winWindow.show();
                /*try {
                    PrintWriter output = new PrintWriter(new FileOutputStream(new File("scrabbleScores.csv"), true));
                    for (int j = 0; j < players.size(); j++) {
                        output.println(players.get(j).getName() + "," + players.get(j).getScore() + "," + players.get(j).getDate());
                    }
                    output.close();
                } catch (FileNotFoundException e) {
                    System.out.println("HighSCore File not Found");
                }*/
                Connection con;
                PreparedStatement pst;
                ResultSet rs;
                try {
                    Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                    con = DriverManager.getConnection("jdbc:ucanaccess://src\\highscoredatabase\\ScrabblePlayer.accdb");

                    pst = con.prepareStatement("insert into ScrabblePlayer(FName, Score, SDate)values(?, ?, ?)");
                    for (int i = 0; i < players.size(); i++) {
                        pst.setString(1, players.get(i).getName());
                        pst.setString(2, String.valueOf(players.get(i).getScore()));
                        pst.setString(3, players.get(i).getDate());
                        pst.executeUpdate();
                    }

                    //tableUpdate();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ScrabblePlayer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ScrabblePlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        skipTurn.setPrefSize(113, 50);
        shuffle.setPrefSize(113, 50);
        quitGame.setPrefSize(113, 50);
        buttons.getChildren().addAll(shuffle, skipTurn, quitGame);
        shuffle.setOnAction(e -> {
            ArrayList<String> ar = new ArrayList<String>();
            ar = players.get(currentPlayer).getRack();
            Collections.shuffle(ar);
            updateRack(currentPlayer);
        });
        quitGame.setOnAction(j -> {
            Stage quitWindow = new Stage();
            VBox quitLayout = new VBox();
            Label quitLabel = new Label("Are You Sure?");
            HBox quitButtons = new HBox();
            Button yes = new Button("Yes");
            yes.setPrefSize(75, 30);
            Button goBack = new Button("Go Back");
            goBack.setPrefSize(75, 30);
            yes.setOnAction(o -> {
                quitWindow.close();
                exit();
            });
            goBack.setOnAction(o -> {
                quitWindow.close();
            });
            Region regQuitH1 = new Region();
            HBox.setHgrow(regQuitH1, Priority.ALWAYS);
            Region regQuitH2 = new Region();
            HBox.setHgrow(regQuitH2, Priority.ALWAYS);
            Region regQuitH3 = new Region();
            HBox.setHgrow(regQuitH3, Priority.ALWAYS);
            quitButtons.getChildren().addAll(regQuitH1, goBack, regQuitH2, yes, regQuitH3);
            Region regQuitV1 = new Region();
            VBox.setVgrow(regQuitV1, Priority.ALWAYS);
            Region regQuitV2 = new Region();
            VBox.setVgrow(regQuitV2, Priority.ALWAYS);
            Region regQuitV3 = new Region();
            VBox.setVgrow(regQuitV3, Priority.ALWAYS);
            quitLayout.getChildren().addAll(regQuitV1, quitLabel, regQuitV2, quitButtons, regQuitV3);
            quitLayout.setAlignment(Pos.CENTER);
            Scene quitScene = new Scene(quitLayout, 300, 100);
            quitWindow.initModality(Modality.APPLICATION_MODAL);
            quitWindow.setScene(quitScene);
            quitWindow.showAndWait();
        });
        buttons.setSpacing(5);
        buttons.setPadding(new Insets(5));
        buttons.setAlignment(Pos.CENTER);
        Region reg1 = new Region();
        HBox.setHgrow(reg1, Priority.ALWAYS);
        Region reg2 = new Region();
        HBox.setHgrow(reg2, Priority.ALWAYS);
        Region regV1 = new Region();
        VBox.setVgrow(regV1, Priority.ALWAYS);
        Region regV2 = new Region();
        VBox.setVgrow(regV2, Priority.ALWAYS);
        Region regV3 = new Region();
        VBox.setVgrow(regV3, Priority.ALWAYS);
        Region regV4 = new Region();
        VBox.setVgrow(regV4, Priority.ALWAYS);
        playerTurn.setAlignment(Pos.CENTER);
        rackLayout.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(regV4, scoreTable, regV1, playerTurn, rackLayout, regV2, buttons, endGame, regV3);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(5));
        VBox vBoard = new VBox();
        Region regV11 = new Region();
        VBox.setVgrow(regV11, Priority.ALWAYS);
        Region regV22 = new Region();
        VBox.setVgrow(regV22, Priority.ALWAYS);
        Region regV33 = new Region();
        VBox.setVgrow(regV33, Priority.ALWAYS);
        vBoard.getChildren().addAll(regV11, board, regV22);
        Region regH11 = new Region();
        HBox.setHgrow(regH11, Priority.ALWAYS);
        layout.getChildren().addAll(regH11, vBoard, reg1, vbox, reg2);
        Scene scene = new Scene(layout, 1920, 1080);
        skipTurn.setOnAction(j -> {
            if (players.get(currentPlayer).getSkipTurn() < 2) {
                players.get(currentPlayer).incSkipTurn();
                updateTurn();
            } else {
                Stage alertWindow = new Stage();
                alertWindow.initModality(Modality.APPLICATION_MODAL);
                alertWindow.setTitle("Error Skipping Turns");
                alertWindow.setMinWidth(250);

                Label label1 = new Label();
                label1.setText("Skipping more than 2 turns results in forfeit");
                Button skip = new Button("Forfeit");
                skip.setPrefSize(75, 20);
                skip.setOnAction(qwe -> {
                    skipped = true;
                    alertWindow.close();
                    updateTurn();
                });
                Button closeButton = new Button("CLOSE");
                closeButton.setPrefSize(75, 20);
                closeButton.setOnAction(e -> alertWindow.close());
                HBox skips = new HBox();
                Region regAlert1 = new Region();
                HBox.setHgrow(regAlert1, Priority.ALWAYS);
                Region regAlert2 = new Region();
                HBox.setHgrow(regAlert2, Priority.ALWAYS);
                Region regAlert3 = new Region();
                HBox.setHgrow(regAlert3, Priority.ALWAYS);
                skips.getChildren().addAll(regAlert1, closeButton, regAlert2, skip, regAlert3);
                VBox alertLayout = new VBox(10);
                alertLayout.getChildren().addAll(label1, skips);
                alertLayout.setAlignment(Pos.CENTER);
                Scene alertScene = new Scene(alertLayout, 250, 100);
                alertWindow.setScene(alertScene);
                alertWindow.showAndWait();
            }
        });
        window.setScene(scene);
        window.setTitle("MyScrabble");
        window.setMaximized(true);
        window.show();
        //homewindow popup
        VBox homeLayout = new VBox();
        Button newGame = new Button("New Game");
        newGame.setPrefSize(150, 50);
        Button highScore = new Button("High Scores");
        highScore.setPrefSize(150, 50);
        highScore.setOnAction(k -> highScore());
        Button instructions = new Button("Instructions");
        instructions.setPrefSize(150, 50);
        instructions.setOnAction(i -> Instructions());
        Button exit = new Button("Exit");
        exit.setPrefSize(150, 50);
        homeLayout.getChildren().addAll(newGame, highScore, instructions, exit);
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setSpacing(25);
        Scene homeScene = new Scene(homeLayout, 700, 500);
        homeWindow = new Stage();
        homeWindow.initModality(Modality.APPLICATION_MODAL);
        homeWindow.setScene(homeScene);
        homeWindow.setTitle("Home");
        homeWindow.show();
        ColorAdjust adj = new ColorAdjust(0, -0, -0, 0);
        GaussianBlur blur = new GaussianBlur(5);
        adj.setInput(blur);
        layout.setEffect(adj);
        //button handlers
        exit.setOnAction(e -> {
            exit();
        });
        newGame.setOnAction(e -> {
            newGame();
            homeWindow.close();
        });

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void newGame() {
        //open player window
        ChoiceBox<Integer> numOfPlayer = new ChoiceBox<>();
        numOfPlayer.getItems().addAll(2, 3, 4);
        numOfPlayer.setValue(2);
        numOfPlayerInt = 2;
        HBox playerChoice = new HBox();
        playerChoice.getChildren().addAll(new Label("Number of Players"), numOfPlayer);
        playerChoice.setAlignment(Pos.CENTER_LEFT);
        playerChoice.setSpacing(5);
        HBox player1 = new HBox();
        TextField player1Name = new TextField();
        player1.getChildren().addAll(new Label("Player 1: "), player1Name);
        player1.setAlignment(Pos.CENTER);
        HBox player2 = new HBox();
        TextField player2Name = new TextField();
        player2.getChildren().addAll(new Label("Player 2: "), player2Name);
        player2.setAlignment(Pos.CENTER);
        HBox player3 = new HBox();
        TextField player3Name = new TextField();
        player3.getChildren().addAll(new Label("Player 3: "), player3Name);
        player3.setAlignment(Pos.CENTER);
        HBox player4 = new HBox();
        TextField player4Name = new TextField();
        player4.getChildren().addAll(new Label("Player 4: "), player4Name);
        player4.setAlignment(Pos.CENTER);
        Button next = new Button("Next");
        next.setPrefSize(100, 30);
        Button back = new Button("Back");
        back.setPrefSize(100, 30);

        VBox playerNames = new VBox(player1, player2, player3, player4);
        player1Name.setDisable(false);
        player2Name.setDisable(false);
        player3Name.setDisable(true);
        player4Name.setDisable(true);
        playerNames.setAlignment(Pos.CENTER);
        playerNames.setSpacing(5);
        Region reg1 = new Region();
        HBox.setHgrow(reg1, Priority.ALWAYS);
        Region reg2 = new Region();
        HBox.setHgrow(reg2, Priority.ALWAYS);
        Region reg3 = new Region();
        HBox.setHgrow(reg3, Priority.ALWAYS);
        HBox newGameTopLayout = new HBox();
        newGameTopLayout.getChildren().addAll(reg1, playerChoice, reg2, playerNames, reg3);
        newGameTopLayout.setSpacing(5);
        newGameTopLayout.setAlignment(Pos.CENTER);
        Region reg01 = new Region();
        HBox.setHgrow(reg01, Priority.ALWAYS);
        Region reg02 = new Region();
        HBox.setHgrow(reg02, Priority.ALWAYS);
        Region reg03 = new Region();
        HBox.setHgrow(reg03, Priority.ALWAYS);
        HBox newGameBottomLayout = new HBox();
        newGameBottomLayout.getChildren().addAll(reg01, back, reg02, next, reg03);
        newGameBottomLayout.setSpacing(25);
        newGameBottomLayout.setAlignment(Pos.CENTER);
        newGameBottomLayout.setPadding(new Insets(5));
        VBox newGameLayout = new VBox();
        Region vreg1 = new Region();
        VBox.setVgrow(vreg1, Priority.ALWAYS);
        Region vreg2 = new Region();
        VBox.setVgrow(vreg2, Priority.ALWAYS);
        Region vreg3 = new Region();
        VBox.setVgrow(vreg3, Priority.ALWAYS);
        newGameLayout.getChildren().addAll(vreg1, newGameTopLayout, vreg2, newGameBottomLayout, vreg3);
        numOfPlayer.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                numOfPlayerInt = numOfPlayer.getItems().get((Integer) number2);
                if (numOfPlayerInt == 2) {
                    player1Name.setDisable(false);
                    player2Name.setDisable(false);
                    player3Name.setDisable(true);
                    player4Name.setDisable(true);
                }
                if (numOfPlayerInt == 3) {
                    player1Name.setDisable(false);
                    player2Name.setDisable(false);
                    player3Name.setDisable(false);
                    player4Name.setDisable(true);
                }
                if (numOfPlayerInt == 4) {
                    player1Name.setDisable(false);
                    player2Name.setDisable(false);
                    player3Name.setDisable(false);
                    player4Name.setDisable(false);
                }
            }
        });

        Scene newGameScene = new Scene(newGameLayout, 700, 500);
        Stage newGameWindow = new Stage();
        //homeWindow.setScene(newGameScene);
        newGameWindow.setScene(newGameScene);
        newGameWindow.setTitle("New Game");
        newGameWindow.show();
        back.setOnAction(a -> {
            newGameWindow.close();
            homeWindow.show();
        });
        next.setOnAction(g -> {
            boolean gotNames = false;
            if (player1Name.getText().isEmpty()) {
                player1Name.requestFocus();
                AlertBoxGame.display("Missing Fields", "Please Enter all Player Names");
                gotNames = false;
            } else {
                gotNames = true;
            }
            if (player2Name.getText().isEmpty()) {
                player2Name.requestFocus();
                AlertBoxGame.display("Missing Fields", "Please Enter all Player Names");
                gotNames = false;
            } else {
                gotNames = true;
            }
            if (!player3Name.isDisable()) {
                if (player3Name.getText().isEmpty()) {
                    player3Name.requestFocus();
                    AlertBoxGame.display("Missing Fields", "Please Enter all Player Names");
                    gotNames = false;
                } else {
                    gotNames = true;
                }
            }
            if (!player4Name.isDisable()) {
                if (player4Name.getText().isEmpty()) {
                    player4Name.requestFocus();
                    AlertBoxGame.display("Missing Fields", "Please Enter all Player Names");
                    gotNames = false;
                } else {
                    gotNames = true;
                }
            }
            if (gotNames) {
                if (numOfPlayerInt == 2) {
                    String name = player1Name.getText();
                    players.add(new ScrabblePlayer(name.toUpperCase()));
                    players.add(new ScrabblePlayer(player2Name.getText().toUpperCase()));
                }
                if (numOfPlayerInt == 3) {
                    players.add(new ScrabblePlayer(player1Name.getText().toUpperCase()));
                    players.add(new ScrabblePlayer(player2Name.getText().toUpperCase()));
                    players.add(new ScrabblePlayer(player3Name.getText().toUpperCase()));
                }
                if (numOfPlayerInt == 4) {
                    players.add(new ScrabblePlayer(player1Name.getText().toUpperCase()));
                    players.add(new ScrabblePlayer(player2Name.getText().toUpperCase()));
                    players.add(new ScrabblePlayer(player3Name.getText().toUpperCase()));
                    players.add(new ScrabblePlayer(player4Name.getText().toUpperCase()));
                }
                //Initializing Game Settings
                totalGameTurns = 0;
                currentPlayer = 0;
                playerTurn.setText(players.get(currentPlayer).getName() + "'s TURN");
                //assign racks to each player
                for (int i = 0; i < numOfPlayerInt; i++) {
                    ScrabblePlayer player = players.get(i);
                    for (int k = 0; k < 7; k++) {
                        if (wordBag.size() > 0) {
                            int randNum = (int) (Math.random() * ((wordBag.size() - 1 - 0) + 1)) + 0;
                            player.addToRack(wordBag.get(randNum));
                            wordBag.remove(randNum);
                        } else {
                            System.out.println("Word Bag finished");
                        }
                    }
                }
                updateRack(currentPlayer);
                for (int i = 0; i < players.size(); i++) {
                    scoreTable.getItems().add(players.get(i));
                }
                newGameWindow.close();
                ColorAdjust adj = new ColorAdjust();
                adj.setInput(new GaussianBlur(0));
                layout.setEffect(adj);
            }
        });
    }

    public boolean rackCheck(String inputWord, ArrayList<String> conflictLetters) {
        inputWord = inputWord.toUpperCase();
        boolean inDictionary = false;
        ArrayList<String> rackCheck = new ArrayList<String>();
        String[] arr = inputWord.split("");
        ArrayList<String> inputWordSplit = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            inputWordSplit.add(arr[i]);
        }
        rackCheck = players.get(currentPlayer).getCloneRack();
        //Collections.addAll(inputWordSplit,inputWord.split(""));
        //inputWordSplit = (ArrayList<String>) Arrays.asList(inputWord.split(""));
        //Deleting conflicting letters from inputWordSplit
        for (int i = 0; i < conflictLetters.size(); i++) {
            int count = 0;
            for (int j = 0; j < inputWordSplit.size(); j++) {
                if (inputWordSplit.get(j).equalsIgnoreCase(conflictLetters.get(i))) {
                    if (count == 0) {
                        inputWordSplit.remove(j);
                    }
                    count++;
                }
            }
        }
        boolean letterPresent = false, wordPresent = false;
        //Checking in Rack
        for (int w = 0; w < inputWordSplit.size(); w++) {
            letterPresent = false;
            for (int q = 0; q < rackCheck.size(); q++) {
                if ((inputWordSplit.get(w).equalsIgnoreCase(rackCheck.get(q)))) {
                    letterPresent = true;
                    rackCheck.remove(q);
                    break;
                }
            }
            if (letterPresent) {
                wordPresent = true;
            } else {
                wordPresent = false;
                for (int t = 0; t < rackCheck.size(); t++) {
                    if (rackCheck.get(t).equalsIgnoreCase("*")) {
                        wordPresent = true;
                        blankList.add(inputWordSplit.get(w));
                        rackCheck.remove(t);
                    }
                }
                if (!wordPresent) {
                    break;
                }

            }
        }
        if (!wordPresent) {
            AlertBoxGame.display("Input Error", "Please choose Letters From Your Rack");
            blankList.clear();
        }
        //Checking in Dictionary only if letterPresent is True
        if (wordPresent) {
            if (dictionaryCheck(inputWord)) {
                inDictionary = true;
            } else {
                AlertBoxGame.display("Input Error", "Word Not Present in Dictionary");
                blankList.clear();
            }
        }
        return inDictionary;
    }

    public boolean dictionaryCheck(String inputWord) {
        boolean inDictionary = false;
        ArrayList<String> dictionaryClone = new ArrayList<String>();
        dictionaryClone = (ArrayList<String>) dictionary.clone();
        int c = Collections.binarySearch(dictionaryClone, inputWord.toUpperCase());
        if (c >= 0) {
            inDictionary = true;
        }
        return inDictionary;
    }

    public void updateRack(int playerNumber) {
        for (int i = 0; i < 7; i++) {
            if (i >= players.get(playerNumber).getRack().size()) {
                rack.get(i).setText("");
                rack.get(i).setDisable(true);
            } else {
                if (players.get(playerNumber).getRack().get(i).equalsIgnoreCase("*")) {
                    rack.get(i).setText(" ");
                } else {
                    rack.get(i).setText(players.get(playerNumber).getRack().get(i));
                }
            }
        }
    }

    public void updateTurn() {
        blankList.clear();
        scoreTable.getItems().clear();
        players.get(currentPlayer).incTurns();
        for (int k = 0; k < 7; k++) {
            ScrabblePlayer player = players.get(currentPlayer);
            if (player.getRack().size() >= 7) {
                break;
            }
            int randNum = (int) (Math.random() * ((wordBag.size() - 1 - 0) + 1)) + 0;
            if (wordBag.size() > 0) {
                player.addToRack(wordBag.get(randNum));
                wordBag.remove(randNum);
            }
        }
        int skippedPlayer = currentPlayer;
        currentPlayer++;
        if (currentPlayer > numOfPlayerInt - 1) {
            currentPlayer = 0;
        }
        if (skipped) {
            players.remove(skippedPlayer);
            currentPlayer--;
            numOfPlayerInt--;
            if (numOfPlayerInt <= 1) {
                Stage winWindow = new Stage();
                winWindow.initModality(Modality.APPLICATION_MODAL);
                winWindow.setTitle("WINNER");
                winWindow.setMinWidth(250);
                try {
                    PrintWriter output = new PrintWriter(new FileOutputStream(new File("scores.csv"), true));
                    for (int j = 0; j < players.size(); j++) {
                        output.println(players.get(j).getName() + "," + players.get(j).getScore() + "," + players.get(j).getDate());
                    }
                    output.close();
                } catch (FileNotFoundException e) {
                    System.out.println("HighSCore File not Found");
                }
                Label label2 = new Label();
                label2.setText(players.get(currentPlayer).getName() + " WINS");
                Button closeButton1 = new Button("Press to Close");
                closeButton1.setOnAction(e -> {
                    exit();
                    winWindow.close();
                });
                VBox winLayout = new VBox(10);
                winLayout.getChildren().addAll(label2, closeButton1);
                winLayout.setAlignment(Pos.CENTER);
                Scene winScene = new Scene(winLayout, 250, 100);
                winWindow.setScene(winScene);
                winWindow.showAndWait();
            }
            skipped = false;
        }
        playerTurn.setText(players.get(currentPlayer).getName() + "'s TURN");
        updateRack(currentPlayer);
        boolean check = false;
        for (int k = 0; k < players.size(); k++) {
            if ((players.get(k).getSkipTurn() >= 1) || totalGameTurns == 0) {
                check = true;
            }
        }
        if (!boardArr[7][7].getText().equalsIgnoreCase("X")) {
            totalGameTurns++;
        }
        //updateScore
        //updateTable

        for (int i = 0; i < players.size(); i++) {
            scoreTable.getItems().add(players.get(i));
        }

    }

    @Override
    public void highScore() {
        Stage highScoreWindow = new Stage();
        TableView highScoreTable = new TableView();
        TableColumn<String, ScrabblePlayer> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        //column1.prefWidthProperty().bind(scoreTable.widthProperty().multiply(0.4));
        TableColumn<String, ScrabblePlayer> column2 = new TableColumn<>("Score");
        column2.setCellValueFactory(new PropertyValueFactory<>("score"));
        //column2.prefWidthProperty().bind(scoreTable.widthProperty().multiply(0.2));
        TableColumn<String, ScrabblePlayer> column3 = new TableColumn<>("Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("date"));
        //column3.prefWidthProperty().bind(scoreTable.widthProperty().multiply(0.2));
        highScoreTable.getColumns().addAll(column1, column2, column3);
        /*try {
            File highScore = new File("scrabbleScores.csv");
            Scanner myReader = new Scanner(highScore);
            while (myReader.hasNext()){
                String[] data = myReader.nextLine().split(",");
                ScrabblePlayer player = new ScrabblePlayer(data[0]);
                player.setScore(Integer.parseInt(data[1]));
                player.setDate(data[2]);
                highScoreTable.getItems().addAll(player);
            }
        }catch (FileNotFoundException e){

        }*/
        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://src\\highscoredatabase\\ScrabblePlayer.accdb");
            System.out.println("Connected Successfully");
            //Using SQL SELECT Query
            pst = con.prepareStatement("select * from ScrabblePlayer");
            //Creaing Java ResultSet object
            rs = pst.executeQuery();
            while (rs.next()) {
                String name = rs.getString("FName");
                String score = rs.getString("Score");
                String date = rs.getString("SDate");
                ScrabblePlayer player = new ScrabblePlayer(name);
                player.setScore(Integer.parseInt(score));
                player.setDate(date);
                highScoreTable.getItems().addAll(player);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ScrabblePlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ScrabblePlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        BorderPane highScoreLayout = new BorderPane();
        highScoreLayout.setCenter(highScoreTable);
        Scene highScoreScene = new Scene(highScoreLayout, 280, 500);
        highScoreWindow.setScene(highScoreScene);
        highScoreWindow.setTitle("High Scores");
        highScoreWindow.showAndWait();
    }

    @Override
    public void Instructions() {
        try {

            URI uri = new URI("https://scrabble.hasbro.com/en-us/rules#:~:text=The%20score%20value%20of%20each,of%20a%20blank%20is%20zero.&text=The%20score%20for%20each%20turn,placing%20letters%20on%20Premium%20Squares.");

            java.awt.Desktop.getDesktop().browse(uri);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public void exit() {
        homeWindow.close();
        window.close();
        Gameemulator g1 = new Gameemulator();
        try {
            g1.start(homeWindow);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(myScrabble.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
