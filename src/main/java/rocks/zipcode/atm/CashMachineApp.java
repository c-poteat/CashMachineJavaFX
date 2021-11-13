package rocks.zipcode.atm;

import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {
    Button btnLogin;
    Button btnSubmit;
    Button btnDeposit;
    Button btnWithdraw;
    int password;


    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private void disableButtons() {
        btnDeposit.setVisible(false);
        btnSubmit.setVisible(false);
        btnWithdraw.setVisible(false);
        disableButtons();
    }

    private Parent createContent() {

        VBox vbox = new VBox(20);
        vbox.setPrefSize(500, 450);

        TextArea areaInfo = new TextArea();
        areaInfo.setPrefHeight(75);
        areaInfo.setPrefWidth(100);
        btnLogin = new Button("Please Login");

        btnLogin.setTranslateX(10);
        btnLogin.setTranslateY(50);
        btnLogin.setStyle("-fx-font: 20 arial; -fx-base: #db6204;");
        btnLogin.setOnAction(e -> {

                password = Integer.parseInt(field.getText());
                password = 1007;
                cashMachine.login(password);
               areaInfo.setText(cashMachine.toString());

            });


        btnSubmit = new Button("Set Account ID");
        btnSubmit.setTranslateX(30);
        btnSubmit.setTranslateY(200);
        btnSubmit.setStyle("-fx-font: 20 arial; -fx-base: #db6204;");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);

           areaInfo.setText(cashMachine.toString());
        });

        btnDeposit = new Button("Deposit");
        btnDeposit.setTranslateX(50);
        btnDeposit.setTranslateY(200);
        btnDeposit.setStyle("-fx-font: 20 arial; -fx-base: #db6204;");
        btnDeposit.setOnAction(e -> {
           Float amount = Float.parseFloat(field.getText());
            cashMachine.deposit(amount);

           areaInfo.setText(cashMachine.toString());
        });

        btnWithdraw = new Button("Withdraw");
        btnWithdraw.setTranslateX(25);
        btnWithdraw.setTranslateY(163);
        btnWithdraw.setStyle("-fx-font: 20 arial; -fx-base: #db6204;");
        btnWithdraw.setOnAction(e -> {
            Alert overdrawn = new Alert(Alert.AlertType.NONE);
            Float amount = Float.parseFloat(field.getText());
            cashMachine.withdraw(amount);
            if(amount < 0) {
                // set alert type
                overdrawn.setAlertType(Alert.AlertType.INFORMATION);

                // set content text
                overdrawn.setContentText("Cannot draw a negative amount");

                // show the dialog
                overdrawn.show();

            }

           areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Reset");
        btnExit.setTranslateX(60);
        btnExit.setTranslateY(220);
        btnExit.setStyle("-fx-font: 20 arial; -fx-base: #db6204;");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());
        });

        FlowPane flowpane = new FlowPane();

        flowpane.getChildren().add(btnLogin);
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }




    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
