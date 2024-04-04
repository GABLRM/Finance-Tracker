package com.example.financemanager.utils;

import com.example.financemanager.model.Expense;
import com.example.financemanager.model.Income;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExpenseAndIncomeDAO {

    private static final Logger log = LoggerFactory.getLogger(ExpenseAndIncomeDAO.class);


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
            log.error("Could not add Expense to database", e);
        }
    }

    public static List<Expense> getAllExpenses() {
        String sql = "SELECT * FROM expense";
        List<Expense> expenses = new ArrayList<>();
        try (Connection connection = Database.connect();
             PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
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
            log.error("Could not load Expenses from database", e);
        }
        return expenses;
    }


    public static List<Expense> findLastExpensesEndingAtCurrentMonth(int numberOfLine, LocalDate currentMonth) {
        String query = "SELECT * FROM expense WHERE date <= '" + currentMonth.format(DateTimeFormatter.ofPattern("MMM yy")) + "' ORDER BY date DESC LIMIT " + numberOfLine;

        List<Expense> lastExpenses = new ArrayList<>();

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                lastExpenses.add(new Expense(
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
            log.error("Could not load Expenses from database", e);
        }
        return lastExpenses;
    }

    public static void addIncome(String date, Float salary, Float helps, Float autoBusiness, Float passives, Float other) {
        String query = "INSERT INTO income(date, salary, helps, autoBusiness, passives, other) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = Database.connect()) {
            assert connection != null;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, date);
            statement.setFloat(2, salary);
            statement.setFloat(3, helps);
            statement.setFloat(4, autoBusiness);
            statement.setFloat(5, passives);
            statement.setFloat(6, other);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Income> getAllIncomes() {
        String query = "SELECT * FROM income";

        List<Income> incomeArray = new ArrayList<>();

        try (Connection connection = Database.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                incomeArray.add(new Income(
                        LocalDate.parse(rs.getString("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        rs.getFloat("salary"),
                        rs.getFloat("helps"),
                        rs.getFloat("autoBusiness"),
                        rs.getFloat("passives"),
                        rs.getFloat("other")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return incomeArray;
    }
}