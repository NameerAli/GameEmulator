/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameemulatorpackage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author rhs-9
 */
public class ConfirmationBox {
    static boolean answer;
    public static boolean display(String title, String message)
    {
        Stage window = new Stage();
        
        //Blocking other events or windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMaxWidth(350);
        
        Label label = new Label(message);
        VBox topMsg = new VBox();
        topMsg.setAlignment(Pos.CENTER);
        topMsg.getChildren().add(label);
        
        Button button1 = new Button("Yes");
        button1.setPrefSize(75, 30);
        Button button2 = new Button("No");
        button2.setPrefSize(75, 30);
        button1.setOnAction(e -> {
            answer = true;
            window.close();
        });
        button2.setOnAction(e -> {
            answer = false;
            window.close();
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
        window.showAndWait();
        return answer;
    }
}
