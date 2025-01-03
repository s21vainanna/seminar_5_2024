package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnecter {
    public static void main(String[] args) {
        // Replace with your database details
        String jdbcURL = "jdbc:mysql://localhost:3306/Groceries";
        String dbUser = "root";
        String dbPassword = "trakikaki32";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword)) {
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
