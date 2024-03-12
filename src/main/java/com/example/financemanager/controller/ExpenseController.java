package com.example.financemanager.controller;

import com.example.financemanager.model.Expense;
import com.example.financemanager.utils.ExpenseDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;

import java.time.LocalDate;
import java.util.Optional;

public class ExpenseController {
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
        event.consume();
    }

}