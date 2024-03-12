package com.example.financemanager;

import com.example.financemanager.utils.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class FinanceTrackerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        if (!Database.isOK()) {
            System.exit(1);
        }
        FXMLLoader fxmlLoader = new FXMLLoader(FinanceTrackerApplication.class.getResource("expense-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Finance Tracker");
        stage.getIcons().add(new Image(String.valueOf(FinanceTrackerApplication.class.getResource("assets/launcher.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}