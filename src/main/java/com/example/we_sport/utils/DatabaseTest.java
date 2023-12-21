package com.example.we_sport.utils;

import com.example.we_sport.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnector.getConnection()) {
            if (connection != null) {
                System.out.println("Connected to the database!");

                // Example: Query all entraineurs
                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery("SELECT * FROM entraineurs")) {

                    while (resultSet.next()) {
                        int entraineurID = resultSet.getInt("entraineurID");
                        String nom = resultSet.getString("nom");
                        String prenom = resultSet.getString("prenom");
                        System.out.println("Entraineur ID: " + entraineurID + ", Name: " + nom + " " + prenom);
                    }
                }

            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
