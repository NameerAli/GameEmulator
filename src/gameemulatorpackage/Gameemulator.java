package gameemulatorpackage;

import java.awt.Color;
import java.io.FileInputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.*;
import javafx.scene.Group;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Gameemulator extends Application {

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {

        tictactoemenu t1 = new tictactoemenu();
        Connect4 c1 = new Connect4();
        myScrabble s1 = new myScrabble();
        ////////Emulator logo/////////////////
        // create a input stream 
        FileInputStream in = new FileInputStream("images\\1.png");
        // create a image 
        Image i4 = new Image(in);
        // create a image View 
        ImageView iw4 = new ImageView(i4);

        /////////////////Connect4 Icon///////////////////////
        // create a input stream 
        FileInputStream input2 = new FileInputStream("images\\p.jpeg");
        // create a image 
        Image i2 = new Image(input2);
        // create a image View 
        ImageView iw2 = new ImageView(i2);

        /////////////TicTactoe logo//////////////////////
        // create a input stream 
        FileInputStream input = new FileInputStream("images\\ppp.png");
        // create a image 
        Image i = new Image(input);
        // create a image View 
        ImageView iw = new ImageView(i);

        //////////////////////Scramble logo/////////////////////////////
        // create a input stream 
        FileInputStream input1 = new FileInputStream("images\\pp.jpeg");
        // create a image 
        Image i1 = new Image(input1);
        // create a image View 
        ImageView iw1 = new ImageView(i1);

        Button b1 = new Button("", iw2);
        b1.setMaxSize(400, 300);
        b1.setOnAction(e -> {
            try {
                c1.start(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Gameemulator.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.stop();
            } catch (Exception ex) {
                Logger.getLogger(Gameemulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button b2 = new Button("", iw1);
        b2.setMaxSize(100, 300);
        b2.setOnAction(e -> {
            s1.start(primaryStage);
            try {
                this.stop();
            } catch (Exception ex) {
                Logger.getLogger(Gameemulator.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Button b3 = new Button("", iw);
        b3.setMaxSize(100, 300);
        b3.setOnAction(e -> {
            t1.setVisible(true);
            t1.setTitle("TicTacToe");
        });

        VBox buttonBar = new VBox(50, iw4, b1, b2, b3);

        Button exit = new Button("EXIT");
        exit.setMaxSize(800, 200);
        exit.setOnAction(e -> System.exit(0));

        ToolBar toolBar = new ToolBar();
        Button about = new Button("About");
        about.setOnAction(e -> about());
        toolBar.getItems().add(about);

        buttonBar.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();

        root.setCenter(buttonBar);

        root.setBottom(exit);
        root.setTop(toolBar);

        // create a background fill 
        BackgroundFill background_fill = new BackgroundFill(javafx.scene.paint.Color.DARKCYAN, CornerRadii.EMPTY, Insets.EMPTY);

        // create Background 
        Background background = new Background(background_fill);

        // set background 
        buttonBar.setBackground(background);
        toolBar.setBackground(background);
        Scene scene = new Scene(root, 500, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Emulator");
        primaryStage.show();

    }
    
    public void about(){
        Stage insStage = new Stage();
        VBox insWindow = new VBox(1);
        Label aboutLabel = new Label();
        Label aboutHeading = new Label();
        Label aboutVersion = new Label();
        Label lines = new Label();
        Label lines2 = new Label();
        Region reg01 = new Region();
        VBox.setVgrow(reg01, Priority.ALWAYS);
        Region reg02 = new Region();
        VBox.setVgrow(reg02, Priority.ALWAYS);
        Region reg03 = new Region();
        VBox.setVgrow(reg03, Priority.ALWAYS);
        Region reg04 = new Region();
        VBox.setVgrow(reg04, Priority.ALWAYS);
        Region reg05 = new Region();
        VBox.setVgrow(reg05, Priority.ALWAYS);
        aboutLabel.setText(" After numerious weeks of studies and experiments, ChadLads launched a Gaming Emulator consisting\n of Three Classic Games\n \n The classical game, Connect4 was developed by HamzaHussain\n \n The word-search game Scrabble was developed by SyedAhris\n \n Finally the oldest game TicTacToe, also called X-O was developed by NameerAli");
        aboutHeading.setText("About The Developers");
        aboutVersion.setText("                                         2020 Version : 1.00\nDeveloped by: HamzaHussain, NameerAli and SyedAhris from Team ChadLads\n                 Copyright ChadLads Co., Ltd. 2020. All rights reserved");
        lines.setText("-----------------------------------------------------------------------------------------------------------");
        lines2.setText("-----------------------------------------------------------------------------------------------------------");
        Button back = new Button("Back");
        back.setPrefSize(150, 50);
        insWindow.getChildren().addAll(reg01, aboutHeading, reg02, aboutLabel, reg03, lines, aboutVersion, lines2, reg04, back, reg05);
        insWindow.setAlignment(Pos.TOP_CENTER);
        Scene insScene = new Scene(insWindow, 1050, 650);
        back.setOnAction(e -> {
            insStage.close();
            
        });
        
        insStage.setScene(insScene);
        insStage.setTitle("About");
        insStage.show();
        aboutLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 18));
        aboutHeading.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 33));
        aboutVersion.setFont(Font.font(18));
        lines.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
        lines2.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 20));
    }
    

    public static void main(String[] args) {
        launch(args);
    }

}
        