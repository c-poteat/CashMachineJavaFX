package rocks.zipcode.atm;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PasswordLoginScreen extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        TextField textField = new TextField();
        textField.setPromptText("Enter a password");
        PasswordField pwdField = new PasswordField();
        Button button = new Button("Submit");
        button.setTranslateX(250);
        button.setTranslateY(75);

        Label passwordLabel = new Label("Password: ");

        Text text = new Text("");
        Font font = Font.font("georgia", FontWeight.BOLD, FontPosture.REGULAR, 14);
        text.setFont(font);
        text.setTranslateX(15);
        text.setTranslateY(125);
        text.setFill(Color.RED);

        button.setOnAction(e -> {

            String pwd = pwdField.getText();

            if (pwd.equals("cdp123")) {
                CashMachineApp cashMachineApp = new CashMachineApp();
                try {

                    cashMachineApp.start(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                text.setText("Wrong password try again");
            }
        });
        HBox box = new HBox(5);
        box.setPadding(new Insets(25, 5, 5, 50));
        box.getChildren().addAll(passwordLabel, pwdField);
        Group root = new Group(box, button, text);


        Scene scene = new Scene(root, 595, 150, Color.GHOSTWHITE);

        stage.setTitle("ZipCloud Bank Login Screen");
        stage.setScene(scene);
        stage.show();
    }
}

