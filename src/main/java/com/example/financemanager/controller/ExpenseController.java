package com.example.financemanager.controller;

import com.example.financemanager.model.Expense;
import com.example.financemanager.utils.ExpenseDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class ExpenseController {
    private static final Logger log = getLogger(ExpenseController.class);
    @FXML
    private TableView<Expense> expenseTable;

    private final ObservableList<Expense> items = FXCollections.observableArrayList();

    public void initialize() {
        items.addAll(
                ExpenseDAO.getAllExpenses()
        );
        expenseTable.setItems(items);
    }

    public void addExpense(ActionEvent event) {
        Dialog<Expense> addPersonDialog = new ExpenseDialog();
        Optional<Expense> result = addPersonDialog.showAndWait();
        result.ifPresent(items::add);

        System.out.println(result);
        log.debug(result.toString());
        event.consume();
    }

}