package rocks.zipcode.atm;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.StageStyle;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;



/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {
        Button btnSubmit;
        Button btnDeposit;
        Button btnWithdraw;
        Button btnClear;
        Button btnExit;
        Float OVERDRAFT_LIMIT = Float.valueOf(100);
//        Button btnAllAccounts;

private TextField accountId = new TextField();
private TextField deposit = new TextField();
private TextField withdraw = new TextField();
private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {

        withdraw.setMaxWidth(180);
        withdraw.setTranslateX(575);
        withdraw.setTranslateY(5);
        deposit.setMaxWidth(175);
        deposit.setTranslateX(300);
        deposit.setTranslateY(40);
        accountId.setTranslateX(50);
        accountId.setTranslateY(75);
        accountId.setMaxWidth(175);

        accountId.setStyle("-fx-text-inner-color: Green;");
        accountId.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        deposit.setStyle("-fx-text-inner-color: Green;");
        deposit.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        withdraw.setStyle("-fx-text-inner-color: Green;");
        withdraw.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        VBox vbox = new VBox(10);
        vbox.setPrefSize(500, 500);

        TextArea areaInfo = new TextArea();
        areaInfo.setMaxWidth(500);
        areaInfo.setTranslateX(140);
        areaInfo.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        areaInfo.setStyle("-fx-text-inner-color: Green;");
        accountId.setPromptText("Enter Account ID");
        deposit.setPromptText("Enter Deposit Amount");
        withdraw.setPromptText("Enter Withdraw Amount");


        areaInfo.setPrefHeight(200);
        areaInfo.setPrefWidth(200);

        btnSubmit = new Button("Login/Submit");
        btnSubmit.setTranslateX(5);
        btnSubmit.setTranslateY(250);
        btnSubmit.setStyle("-fx-font: 15 arial; -fx-base: #0A8B54;");

        btnSubmit.setOnAction(e -> {
            btnDeposit.setVisible(true);
            btnWithdraw.setVisible(true);
            btnClear.setVisible(true);
            btnExit.setVisible(true);
            //             btnAllAccounts.setVisible(true);
            int id = Integer.parseInt(accountId.getText());
            cashMachine.login(id);
            areaInfo.setText(cashMachine.toString());
        });
        btnDeposit = new Button("Deposit");
        btnDeposit.setTranslateX(-100);
        btnDeposit.setTranslateY(300);
        btnDeposit.setStyle("-fx-font: 15 arial; -fx-base: #0A8B54;");
        btnDeposit.setOnAction(e -> {
            Float amount = Float.parseFloat(deposit.getText());
            if (amount < 0) {
                areaInfo.setText("Input cannot be negative. Please try again.");
            } else {
                cashMachine.deposit(amount);
                areaInfo.setText(cashMachine.toString());
            }
        });
        btnWithdraw = new Button("Withdraw");
        btnWithdraw.setTranslateX(-175);
        btnWithdraw.setTranslateY(350);
        btnWithdraw.setStyle("-fx-font: 15 arial; -fx-base: #0A8B54;");
        btnWithdraw.setOnAction(e -> {
            Float amount = Float.parseFloat(withdraw.getText());
            if (amount < OVERDRAFT_LIMIT) {
                areaInfo.setText("Sorry, this withdrawal would exceed the overdraft limit");
            }
            if (amount < 0) {
                areaInfo.setText("Sorry, you can not withdraw a negative amount");
            } else {
                cashMachine.withdraw(amount);
                areaInfo.setText(cashMachine.toString());
            }
        });
        btnClear = new Button("Clear");
        btnClear.setTranslateX(85);
        btnClear.setTranslateY(250);
        btnClear.setStyle("-fx-font: 15 arial; -fx-base: #0A8B54;");
        btnClear.setOnAction(e -> {
            accountId.clear();
            deposit.clear();
            withdraw.clear();
            cashMachine.exit();
            areaInfo.setText(cashMachine.toString());
        });
        btnExit = new Button("Exit");
        btnExit.setTranslateX(35);
        btnExit.setTranslateY(300);
        btnExit.setStyle("-fx-font: 15 arial; -fx-base: #0A8B54;");
        btnExit.setOnAction(e -> {
            System.exit(0);
            areaInfo.setText(cashMachine.toString());

        });

//                btnAllAccounts = new Button("All Accounts");
//                btnAllAccounts.setTranslateX(300);
//                btnAllAccounts.setTranslateY(250);
//                btnAllAccounts.setStyle("-fx-font: 15 arial; -fx-base: #0A8B54;");
//                btnAllAccounts.setOnAction(e -> {

//                        areaInfo.setText(cashMachine.toString());
//                });

        FlowPane flowpane = new FlowPane();
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnClear);
        flowpane.getChildren().add(btnExit);
//                flowpane.getChildren().add(btnAllAccounts);
        vbox.getChildren().addAll(accountId, deposit, withdraw, flowpane, areaInfo);
        return vbox;


    }

@Override
public void start(Stage stage) throws Exception {
    stage.setTitle("Welcome to ZipCloud Bank");
    stage.setWidth(800);
    stage.setHeight(600);
    stage.initStyle(StageStyle.DECORATED);
        stage.setScene(new Scene(createContent()));
        stage.show();

        btnDeposit.setVisible(false);
        btnWithdraw.setVisible(false);
        btnClear.setVisible(false);
        btnExit.setVisible(false);
//        btnAllAccounts.setVisible(false);

 }

public static void main(String[] args) {
        launch(args);
        }
}



