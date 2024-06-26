package com.example.financemanager.controller;

import com.example.financemanager.FinanceTrackerApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.slf4j.LoggerFactory.getLogger;

public class ExpenseController implements Initializable {

    private static final Logger log = getLogger(ExpenseController.class);

    @FXML
    private VBox root;

    @FXML
    private HeaderController headerController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        headerController.getViewValue().addListener((observable, oldValue, newValue) -> {
            String fileName = "";
            try {
                if (Objects.equals(newValue, "Graphics")) {
                    fileName = "expense-graph.fxml";
                } else if (Objects.equals(newValue, "Expenses")) {
                    fileName = "expense-table.fxml";
                } else if (Objects.equals(newValue, "Incomes")) {
                    fileName = "income-table.fxml";
                }
                Node component = FXMLLoader.load(Objects.requireNonNull(FinanceTrackerApplication.class.getResource(fileName)));
                root.getChildren().removeLast();
                root.getChildren().add(component);
            } catch (IOException e) {
                log.error("Unable to load " + fileName);
            }
        });
    }
}
