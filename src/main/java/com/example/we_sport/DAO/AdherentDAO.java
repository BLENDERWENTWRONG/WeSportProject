package com.example.we_sport.DAO;

import com.example.we_sport.DatabaseConnector;
import com.example.we_sport.Entity.Adherent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class AdherentDAO {

    // Method to insert a new adherent into the database
    public void addAdherent(Adherent adherent) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO adherents (nom, prenom, email, motDePasse, telephone) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, adherent.getNom());
            preparedStatement.setString(2, adherent.getPrenom());
            preparedStatement.setString(3, adherent.getEmail());
            preparedStatement.setString(4, adherent.getMotDePasse());
            preparedStatement.setString(5, adherent.getTelephone());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all adherents from the database
    public List<Adherent> getAllAdherents() {
        List<Adherent> adherents = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM adherents")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Adherent adherent = new Adherent(
                        resultSet.getInt("adherentID"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("telephone")
                );
                adherents.add(adherent);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return adherents;
    }

    // Method to update an existing adherent in the database
    public void updateAdherent(Adherent adherent) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE adherents SET nom=?, prenom=?, email=?, motDePasse=?, telephone=? WHERE adherentID=?")) {

            preparedStatement.setString(1, adherent.getNom());
            preparedStatement.setString(2, adherent.getPrenom());
            preparedStatement.setString(3, adherent.getEmail());
            preparedStatement.setString(4, adherent.getMotDePasse());
            preparedStatement.setString(5, adherent.getTelephone());
            preparedStatement.setInt(6, adherent.getAdherentID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an adherent from the database
    public void deleteAdherent(int adherentID) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM adherents WHERE adherentID=?")) {

            preparedStatement.setInt(1, adherentID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public double calculateAverageAgeOfAdherents() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT birthdate FROM adherents");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            LocalDate currentDate = LocalDate.now();
            int totalAge = 0;
            int adherentCount = 0;

            while (resultSet.next()) {
                LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
                int age = Period.between(birthdate, currentDate).getYears();

                totalAge += age;
                adherentCount++;
            }

            if (adherentCount > 0) {
                return (double) totalAge / adherentCount;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    public double getAdherentData() {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT birthdate FROM adherents");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            LocalDate currentDate = LocalDate.now();
            int totalAge = 0;
            int adherentCount = 0;

            while (resultSet.next()) {
                LocalDate birthdate = resultSet.getDate("birthdate").toLocalDate();
                int age = Period.between(birthdate, currentDate).getYears();

                totalAge += age;
                adherentCount++;
            }

            if (adherentCount > 0) {
                return (double) totalAge / adherentCount;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
}
