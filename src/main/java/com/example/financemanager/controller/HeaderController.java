package com.example.financemanager.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuItem;

public class HeaderController {

    private final StringProperty viewValue = new SimpleStringProperty("Table");

    public StringProperty getViewValue() {
        return viewValue;
    }

    public void setView(ActionEvent event) {
        viewValue.set(((MenuItem) event.getSource()).getText());
        event.consume();
    }
}
