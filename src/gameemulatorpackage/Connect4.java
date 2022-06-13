/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameemulatorpackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import static javafx.application.Platform.exit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JTable;

public class Connect4 extends Application implements HomeScreen {

    private static final int TileSize = 120;
    private static final int Columns = 7;
    private static final int Rows = 6;

    private boolean redMove = true;
    private Disc[][] grid = new Disc[Columns][Rows];

    private Pane discRoot = new Pane();
    Stage stage;
    PlayerConnect4 Player1;
    PlayerConnect4 Player2;
    int turnCount = 0;
    Label label3;
    ArrayList<PlayerConnect4> Player = new ArrayList<>();
    Stage newGameStage;
    HBox Game1;
    Gameemulator g1 = new Gameemulator();
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    Stage homeWindow;
    Scene insScene;

    private Parent createContent() {
        Pane root = new Pane();
        root.getChildren().add(discRoot);
        Shape gridShape = makeGrid();
        root.getChildren().add(gridShape);
        root.getChildren().addAll(makeColumns());
        return root;
    }

    private Shape makeGrid() {
        Shape shape = new Rectangle((Columns + 1) * TileSize, (Rows + 1) * TileSize); //Making the rectangular grid
        for (int j = 0; j < Rows; j++) //Setting up a loop for making the circles
        {
            for (int i = 0; i < Columns; i++) {
                Circle circle = new Circle(TileSize / 2); //The circle size
                circle.setCenterX(TileSize / 2); //Makes the center of the circles from the top left
                circle.setCenterY(TileSize / 2);
                circle.setTranslateX(i * (TileSize + 5) + (TileSize / 3)); //'j' represents the column number, (TileSize + 5) represents the distance between the cirles, (TileSize / 4) represents the starting distance from the left corner 
                circle.setTranslateY(j * (TileSize + 5) + (TileSize / 3));
                shape = Shape.subtract(shape, circle); //Carves out an empty space from the grid in the shape of a circle 
            }
        }

        Light.Distant light = new Light.Distant(); //The lighting effects
        light.setAzimuth(45.0);
        light.setElevation(30.0);
        Lighting lighting = new Lighting();
        lighting.setLight(light);
        lighting.setSurfaceScale(5.0);

        shape.setFill(Color.BROWN); //The color of the grid
        shape.setEffect(lighting); //Adding the lighting effect to the grid
        return shape;
    }

    private List<Rectangle> makeColumns() {
        List<Rectangle> list = new ArrayList<>();
        for (int i = 0; i < Columns; i++) {
            Rectangle rect = new Rectangle(TileSize, (Rows + 1) * TileSize); //Making multiple rectanlges which will act as visual indicators for disc placement
            rect.setTranslateX(i * (TileSize + 5) + (TileSize / 3));
            rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.275))); //Making it so that when mouse hovers over a column, that column lights up indicating the position of the dropped disc
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            final int column = i;
            rect.setOnMouseClicked(e -> {
                placeDisc(new Disc(redMove), column);
                turnCount++;
                if (turnCount == 42) {
                    gameOver();
                }
                label3.setText("    TurnNumber: " + String.valueOf(turnCount));
            });
            list.add(rect);
        }
        return list;
    }

    private void placeDisc(Disc disc, int column) {
        int row = Rows - 1; //Everything from here till the "grid[column][row] checks whether there is an empty space
        do {
            if (!getDisc(column, row).isPresent()) {
                break;
            }
            row--;
        } while (row >= 0);
        if (row < 0) {
            return;
        }

        grid[column][row] = disc;
        discRoot.getChildren().add(disc);
        disc.setTranslateX((column * (TileSize + 5) + (TileSize / 3)));
        final int currentRow = row;

        TranslateTransition animation = new TranslateTransition(Duration.seconds(0.3), disc);
        animation.setToY((row * (TileSize + 5) + (TileSize / 3)));
        animation.setOnFinished(e -> {
            if (gameEnded(column, currentRow)) {
                gameOver();
            }
            redMove = !redMove;
        });
        animation.play();
    }

    private boolean gameEnded(int column, int row) {
        List<Point2D> vertical = IntStream.rangeClosed(row - 3, row + 3).mapToObj(r -> new Point2D(column, r)).collect(Collectors.toList());
        List<Point2D> horizontal = IntStream.rangeClosed(column - 3, column + 3).mapToObj(c -> new Point2D(c, row)).collect(Collectors.toList());

        Point2D topLeft = new Point2D(column - 3, row - 3);
        List<Point2D> diagonal1 = IntStream.rangeClosed(0, 6).mapToObj(i -> topLeft.add(i, i)).collect(Collectors.toList());

        Point2D botLeft = new Point2D(column - 3, row + 3);
        List<Point2D> diagonal2 = IntStream.rangeClosed(0, 6).mapToObj(i -> botLeft.add(i, -i)).collect(Collectors.toList());
        return checkRange(vertical) || checkRange(horizontal) || checkRange(diagonal1) || checkRange(diagonal2);
    }

    private boolean checkRange(List<Point2D> points) {
        int chain = 0;
        for (Point2D p : points) {
            int column = (int) p.getX();
            int row = (int) p.getY();

            Disc disc = getDisc(column, row).orElse(new Disc(!redMove)); //checks whether there is a winning combination for a color, which in case there is no combination, the check fails and thus resets check
            if (disc.red == redMove) {
                chain++;
                if (chain == 4) {
                    return true;
                }
            } else {
                chain = 0;
            }
        }
        return false;
    }

    private void gameOver() {
        System.out.println("Winner: " + (redMove ? "Red" : "Yellow"));
        Stage window = new Stage();
        //Blocking other events or windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("GameOver");
        window.setMaxWidth(350);
        Label label = new Label("Player " + (redMove ? Player1.getName().toUpperCase() : Player2.getName().toUpperCase()) + " is the Winner.\n \nPlay Again?");
        if (turnCount == 42) {
            label.setText("Game Draw");
            int n = Player.size() - 1;
            Player.get(n - 1).setDraw(Player.get(n - 1).getDraw() + 1);
            Player.get(n).setDraw(Player.get(n).getDraw() + 1);
        } else if (redMove) {
            int n = Player.size() - 1;
            Player.get(n - 1).setScore(Player.get(n - 1).getScore() + 1);
            Player.get(n).setLoss(Player.get(n).getLoss() + 1);
        } else {
            int n = Player.size() - 1;
            Player.get(n).setScore(Player.get(n).getScore() + 1);
            Player.get(n - 1).setLoss(Player.get(n - 1).getLoss() + 1);
        }
        VBox topMsg = new VBox();
        topMsg.setAlignment(Pos.CENTER);
        topMsg.getChildren().add(label);

        Button button1 = new Button("Yes");
        button1.setPrefSize(75, 30);
        Button button2 = new Button("No");
        button2.setPrefSize(75, 30);
        button1.setOnAction(e -> {
            //ScoreUpdater();
            window.close();
            stage.close();
            discRoot.getChildren().clear();
            grid = new Disc[Columns][Rows];
            newGame();
            turnCount = 0;
            /*Region reg01 = new Region();
                HBox.setHgrow(reg01, Priority.ALWAYS);
                Region reg02 = new Region();
                HBox.setHgrow(reg02, Priority.ALWAYS);
                stage.close();
                newGame();
                Game1 = new HBox();
                Game1.getChildren().addAll(reg01, createContent(), reg02);
                grid = new Disc[Columns][Rows];
                turnCount = 0;
                window.close(); */
        });
        button2.setOnAction(e -> {
            ScoreUpdater();
            window.close();
            stage.close();
            discRoot.getChildren().clear();
            grid = new Disc[Columns][Rows];
            turnCount = 0;
            homeWindow.show();
            redMove = true;
        });
        HBox botOpt = new HBox(50);
        botOpt.setAlignment(Pos.CENTER);
        botOpt.getChildren().addAll(button1, button2);

        BorderPane layout = new BorderPane();
        layout.setCenter(topMsg);
        layout.setBottom(botOpt);
        layout.setPadding(new Insets(20, 20, 20, 20));

        Scene scene = new Scene(layout, 350, 150);
        window.setScene(scene);
        window.show();
    }

    public void ScoreUpdater() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://src\\highscoredatabase\\Connect4Player.accdb");

            pst = con.prepareStatement("insert into Connect4Player(Name, Wins, Losses, Draws)values(?, ?, ?, ?)");
            int n = Player.size() - 1;
            pst.setString(1, Player.get(n - 1).getName());
            pst.setString(2, String.valueOf(Player.get(n - 1).getScore()));
            pst.setString(3, String.valueOf(Player.get(n - 1).getLoss()));
            pst.setString(4, String.valueOf(Player.get(n - 1).getDraw()));
            pst.executeUpdate();
            pst.setString(1, Player.get(n).getName());
            pst.setString(2, String.valueOf(Player.get(n).getScore()));
            pst.setString(3, String.valueOf(Player.get(n).getLoss()));
            pst.setString(4, String.valueOf(Player.get(n).getDraw()));
            pst.executeUpdate();
            //tableUpdate();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlayerConnect4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerConnect4.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Optional<Disc> getDisc(int column, int row) {
        if (column < 0 || column >= Columns || row < 0 || row >= Rows) {
            return Optional.empty();
        }
        return Optional.ofNullable(grid[column][row]);
    }

    @Override
    public void highScore() {
        Stage highScoreStage = new Stage();
        TableView highScoreTable = new TableView();
        TableColumn<String, PlayerConnect4> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<String, PlayerConnect4> column2 = new TableColumn<>("Wins");
        column2.setCellValueFactory(new PropertyValueFactory<>("score"));
        TableColumn<String, PlayerConnect4> column3 = new TableColumn<>("Losses");
        column3.setCellValueFactory(new PropertyValueFactory<>("loss"));
        TableColumn<String, PlayerConnect4> column4 = new TableColumn<>("Draws");
        column4.setCellValueFactory(new PropertyValueFactory<>("draw"));
        highScoreTable.getColumns().addAll(column1, column2, column3, column4);
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://src\\highscoredatabase\\Connect4Player.accdb");
            System.out.println("Database Connected Successfully for Connect 4");
            //Using SQL SELECT Query
            pst = con.prepareStatement("select * from Connect4Player");
            //Creaing Java ResultSet object
            rs = pst.executeQuery();
            while (rs.next()) {
                String Name = rs.getString("Name");
                String Wins = rs.getString("Wins");
                String Losses = rs.getString("Losses");
                String Draws = rs.getString("Draws");
                //Printing Results
                PlayerConnect4 player = new PlayerConnect4(Name);
                player.setScore(Integer.parseInt(Wins));
                player.setLoss(Integer.parseInt(Losses));
                player.setDraw(Integer.parseInt(Draws));
                highScoreTable.getItems().addAll(player);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PlayerConnect4.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlayerConnect4.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene highScoreScene = new Scene(highScoreTable, 750, 500);
        highScoreStage.setScene(highScoreScene);
        highScoreStage.setTitle("HighScores");
        highScoreStage.show();
    }

    @Override
    public void Instructions() {
        Stage insStage = new Stage();
        VBox insWindow = new VBox();
        Label insLabel = new Label();
        Region reg01 = new Region();
        VBox.setVgrow(reg01, Priority.ALWAYS);
        Region reg02 = new Region();
        VBox.setVgrow(reg02, Priority.ALWAYS);
        Region reg03 = new Region();
        VBox.setVgrow(reg03, Priority.ALWAYS);
        insLabel.setText("-To play Connect 4, try to get 4 of your color checkers in a row horizontally, vertically, or diagonally \n before your opponent.\n \n-When it's your turn, drop one of your checkers into one of the slots at the top of the plastic grid.\n \n-Then, let your opponent take their turn.\n \n-The First one to get 4 checkers in a row Wins the game.");
        Button back = new Button("Back");
        back.setPrefSize(150, 50);
        insWindow.getChildren().addAll(reg01, insLabel, reg02, back, reg03);
        insWindow.setAlignment(Pos.TOP_CENTER);
        insScene = new Scene(insWindow, 700, 300);
        back.setOnAction(e -> {
            insStage.close();
            homeWindow.show();
        });
        homeWindow.close();
        insStage.setScene(insScene);
        insStage.setTitle("Instructions");
        insStage.show();
    }

    @Override
    public void exit() {
        boolean ans = ConfirmationBox.display("Exit Game", "Are You Sure You Want To Exit?");
        if (ans) {
            try {
                g1.start(newGameStage);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Connect4.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        homeWindow.close();
    }

    public static class Disc extends Circle {

        private final boolean red;

        public Disc(boolean red) {
            super(TileSize / 2, red ? Color.RED : Color.YELLOW); //Checks whether the Disc color is red or yellow 
            this.red = red;

            setCenterX(TileSize / 2);
            setCenterY(TileSize / 2);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        /* stage.setScene(new Scene(createContent()));
        stage.show(); */
        homeWindow = stage;
        VBox homeLayout = new VBox();
        Button newGame = new Button("New Game");
        newGame.setPrefSize(150, 50);
        Button highScore = new Button("High Scores");
        highScore.setPrefSize(150, 50);
        Button instructions = new Button("Instructions");
        instructions.setPrefSize(150, 50);
        Button exit = new Button("Exit");
        exit.setPrefSize(150, 50);
        homeLayout.getChildren().addAll(newGame, highScore, instructions, exit);
        homeLayout.setAlignment(Pos.CENTER);
        homeLayout.setSpacing(25);
        Scene homeScene = new Scene(homeLayout, 700, 500);

        newGameStage = new Stage();
        VBox newGameMenu1 = new VBox();
        newGameMenu1.setAlignment(Pos.CENTER);
        Region reg01 = new Region();
        VBox.setVgrow(reg01, Priority.ALWAYS);
        Region reg02 = new Region();
        VBox.setVgrow(reg02, Priority.ALWAYS);
        Region reg03 = new Region();
        VBox.setVgrow(reg03, Priority.ALWAYS);
        HBox newGameMenu2 = new HBox(40);
        newGameMenu2.setAlignment(Pos.CENTER);
        GridPane newGameMenu = new GridPane();
        newGameMenu.setAlignment(Pos.CENTER);
        newGameMenu.setVgap(12);
        newGameMenu.setHgap(12);
        Label playerLabel1 = new Label("Player 1:");
        GridPane.setConstraints(playerLabel1, 0, 0);
        Label playerLabel2 = new Label("Player 2:");
        GridPane.setConstraints(playerLabel2, 0, 1);
        TextField playerName1 = new TextField();
        GridPane.setConstraints(playerName1, 1, 0);
        TextField playerName2 = new TextField();
        GridPane.setConstraints(playerName2, 1, 1);
        Button newGameButton = new Button("Continue");
        newGameButton.setPrefSize(100, 30);
        Button newGameBackButton = new Button("Back");
        newGameBackButton.setPrefSize(100, 30);
        newGameMenu2.getChildren().addAll(newGameBackButton, newGameButton);
        newGameMenu.getChildren().addAll(playerLabel1, playerLabel2, playerName1, playerName2);
        newGameMenu1.getChildren().addAll(reg01, newGameMenu, reg02, newGameMenu2, reg03);
        Scene newGameScene = new Scene(newGameMenu1, 320, 130);

        newGameButton.setOnAction(e -> {
            homeWindow.close();
            if (!playerName1.getText().isEmpty() && !playerName2.getText().isEmpty()) {
                Player1 = new PlayerConnect4(playerName1.getText());
                Player2 = new PlayerConnect4(playerName2.getText());
                Player.add(Player1);
                Player.add(Player2);
                newGame();
                newGameStage.close();
            } else {
                AlertBoxGame.display("Error", "Please Enter Name");
            }
        });
        newGameBackButton.setOnAction(e -> {
            homeWindow.setTitle("Home");
            homeWindow.setScene(homeScene);
            newGameStage.close();
            homeWindow.show();
        });
        instructions.setOnAction(e -> {
            Instructions();
        });
        newGame.setOnAction(e -> {
            newGameStage.setScene(newGameScene);
            newGameStage.setTitle("Player Select");
            homeWindow.close();
            newGameStage.show();
        });
        homeWindow.setOnCloseRequest(e -> {
            e.consume();
            boolean ans = ConfirmationBox.display("Exit Game", "Are You Sure You Want To Exit?\n \nAny Progress Made Will Be Lost");
            if (ans) {
                homeWindow.close();
            }
        });
        newGameStage.setOnCloseRequest(e -> {
            e.consume();
            boolean ans = ConfirmationBox.display("Exit Game", "Are You Sure You Want To Exit?\n \nAny Progress Made Will Be Lost");
            if (ans) {
                newGameStage.close();
            }
        });
        exit.setOnAction(e -> {
            exit();
        });
        highScore.setOnAction(e -> {
            highScore();
        });
        homeWindow.setScene(homeScene);
        homeWindow.setTitle("Home");
        homeWindow.show();
    }

    public void newGame() {
        stage = new Stage();
        HBox Game = new HBox(10);
        Game.setAlignment(Pos.CENTER);
        Game1 = new HBox();
        Game1.setAlignment(Pos.CENTER);
        VBox Game2 = new VBox(40);
        Game.setAlignment(Pos.CENTER);
        Region reg01 = new Region();
        Region reg02 = new Region();
        Region reg03 = new Region();
        Region reg04 = new Region();
        Region reg05 = new Region();
        Region reg06 = new Region();
        HBox.setHgrow(reg01, Priority.ALWAYS);
        HBox.setHgrow(reg02, Priority.ALWAYS);
        HBox.setHgrow(reg03, Priority.ALWAYS);
        HBox.setHgrow(reg04, Priority.ALWAYS);
        VBox.setVgrow(reg05, Priority.ALWAYS);
        VBox.setVgrow(reg06, Priority.ALWAYS);
        Label label1 = new Label("   Player1: " + Player1.getName().toUpperCase());
        Label label2 = new Label("   Player2: " + Player2.getName().toUpperCase());
        label3 = new Label("    TurnNumber: " + turnCount);
        label1.setPrefSize(150, 50);
        label2.setPrefSize(150, 50);
        label3.setPrefSize(150, 50);
        label1.setStyle("-fx-background-color:BLACK");
        label1.setTextFill(Color.WHITE);
        label2.setStyle("-fx-background-color:BLACK");
        label2.setTextFill(Color.WHITE);
        label3.setStyle("-fx-background-color:BLACK");
        label3.setTextFill(Color.WHITE);
        Game2.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
        Game.getChildren().addAll(reg01, label1, reg02, label3, reg03, label2, reg04);
        Game1.getChildren().addAll(reg05, createContent(), reg06);
        Game2.getChildren().addAll(Game1, Game);
        stage.setScene(new Scene(Game2, 1150, 925));
        stage.show();
        stage.setOnCloseRequest(e -> {
            e.consume();
            boolean ans = ConfirmationBox.display("Exit Game", "Are You Sure You Want To Exit?\n \nAny Progress Made Will Be Lost");
            if (ans) {
                stage.close();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
