package com.example.we_sport.DAO;

import com.example.we_sport.DatabaseConnector;
import com.example.we_sport.Entity.Entraineur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntraineurDAO {

    public void addEntraineur(Entraineur entraineur) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO entraineurs (nom, prenom, email, motDePasse, telephone, specialite) VALUES (?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, entraineur.getNom());
            preparedStatement.setString(2, entraineur.getPrenom());
            preparedStatement.setString(3, entraineur.getEmail());
            preparedStatement.setString(4, entraineur.getMotDePasse());
            preparedStatement.setString(5, entraineur.getTelephone());
            preparedStatement.setString(6, entraineur.getSpecialite());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Entraineur> getAllEntraineurs() {
        List<Entraineur> entraineurs = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entraineurs")) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Entraineur entraineur = new Entraineur(
                        resultSet.getInt("entraineurID"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("motDePasse"),
                        resultSet.getString("telephone"),
                        resultSet.getString("specialite"),
                        resultSet.getBoolean("verified")
                );
                entraineurs.add(entraineur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entraineurs;
    }

    public void updateEntraineur(Entraineur entraineur) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE entraineurs SET nom=?, prenom=?, email=?, motDePasse=?, telephone=?, specialite=? WHERE entraineurID=?")) {

            preparedStatement.setString(1, entraineur.getNom());
            preparedStatement.setString(2, entraineur.getPrenom());
            preparedStatement.setString(3, entraineur.getEmail());
            preparedStatement.setString(4, entraineur.getMotDePasse());
            preparedStatement.setString(5, entraineur.getTelephone());
            preparedStatement.setString(6, entraineur.getSpecialite());
            preparedStatement.setInt(7, entraineur.getEntraineurID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteEntraineur(int entraineurID) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM entraineurs WHERE entraineurID=?")) {

            preparedStatement.setInt(1, entraineurID);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entraineur> getEntraineursBySpeciality(String speciality) {

        List<Entraineur> entraineurs = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entraineurs WHERE specialite = ?")) {

            preparedStatement.setString(1, speciality);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Entraineur entraineur = mapResultSetToEntraineur(resultSet);
                entraineurs.add(entraineur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entraineurs;
    }
    private Entraineur mapResultSetToEntraineur(ResultSet resultSet) throws SQLException {
        int entraineurID = resultSet.getInt("entraineurID");
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        String email = resultSet.getString("email");
        String motDePasse = resultSet.getString("motDePasse");
        String telephone = resultSet.getString("telephone");
        String specialite = resultSet.getString("specialite");

        return new Entraineur(entraineurID, nom, prenom, email, motDePasse, telephone, specialite);
    }
    public List<Entraineur> getEntraineursByName(String name) {

        List<Entraineur> entraineurs = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM entraineurs WHERE nom LIKE ?")) {

            preparedStatement.setString(1, "%" + name + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Entraineur entraineur = mapResultSetToEntraineur(resultSet);
                entraineurs.add(entraineur);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entraineurs;
    }

    public void updateVerificationStatus(int entraineurID, boolean newVerificationStatus) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE entraineurs SET verified=? WHERE entraineurID=?")) {

            preparedStatement.setBoolean(1, newVerificationStatus);
            preparedStatement.setInt(2, entraineurID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
