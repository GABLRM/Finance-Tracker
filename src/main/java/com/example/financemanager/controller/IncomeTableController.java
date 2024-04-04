package com.example.financemanager.controller;

import com.example.financemanager.model.Income;
import com.example.financemanager.utils.ExpenseAndIncomeDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import org.slf4j.Logger;

import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class IncomeTableController {
    private static final Logger log = getLogger(ExpenseTableController.class);

    @FXML
    private TableView<Income> incomeTable;

    private final ObservableList<Income> items = FXCollections.observableArrayList();

    public void initialize() {
        items.addAll(ExpenseAndIncomeDAO.getAllIncomes());
        incomeTable.setItems(items);
    }

    public void addIncome(ActionEvent event) {
        Dialog<Income> addPersonDialog = new IncomeDialog();
        Optional<Income> result = addPersonDialog.showAndWait();
        result.ifPresent(items::add);

        log.debug(result.toString());

        event.consume();
    }
}
