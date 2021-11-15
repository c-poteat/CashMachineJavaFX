package rocks.zipcode.atm;

import javafx.stage.StageStyle;
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

    private TextField field1 = new TextField();
    private TextField field2 = new TextField();
    private TextField field3 = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {

        VBox vbox = new VBox(10);
        vbox.setPrefSize(500, 600);

        TextArea areaInfo = new TextArea();

        field1.setPromptText("Enter Account ID");
        field2.setPromptText("Enter Deposit Amount");
        field3.setPromptText("Enter Withdraw Amount");
        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field1.getText());
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {

            int amount = Integer.parseInt(field2.getText());
            cashMachine.deposit((float) amount);

            areaInfo.setText(cashMachine.toString());

        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            Float amount = Float.parseFloat(field3.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnClear = new Button("Clear");
        btnClear.setOnAction(e -> {

            cashMachine.exit();
            areaInfo.setText(cashMachine.toString());
        });


        FlowPane flowpane = new FlowPane();
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnClear);
        vbox.getChildren().addAll(field1,field2,field3, flowpane, areaInfo);
        return vbox;

    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.setTitle("Cash Machine");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.initStyle(StageStyle.DECORATED);
            stage.show();
        }

    public static void main(String[] args) {
        launch(args);
    }


}


