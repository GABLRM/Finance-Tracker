package com.example.financemanager.utils;

import com.example.financemanager.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpenseDAO {
    public static void addExpense(String date, float housing, float food, float goingOut, float transportation, float travel, float tax, float other) {
        String sql = "INSERT INTO expense(date, housing, food, goingOut, transportation, travel, tax, other) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection connection = Database.connect();
             PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql)) {
            preparedStatement.setString(1, date);
            preparedStatement.setFloat(2, housing);
            preparedStatement.setFloat(3, food);
            preparedStatement.setFloat(4, goingOut);
            preparedStatement.setFloat(5, transportation);
            preparedStatement.setFloat(6, travel);
            preparedStatement.setFloat(7, tax);
            preparedStatement.setFloat(8, other);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Expense> getAllExpenses() {
        String sql = "SELECT * FROM expense";
        List<Expense> expenses = new ArrayList<>();
        try (Connection connection = Database.connect();
             PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                expenses.add(new Expense(
                        LocalDate.parse(rs.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        rs.getFloat("housing"),
                        rs.getFloat("food"),
                        rs.getFloat("goingOut"),
                        rs.getFloat("transportation"),
                        rs.getFloat("travel"),
                        rs.getFloat("tax"),
                        rs.getFloat("other")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
}
