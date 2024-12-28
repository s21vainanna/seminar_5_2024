package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class h2 {

	public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:h2:mem:testdb", "sa", "");  // Replace with correct credentials
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
