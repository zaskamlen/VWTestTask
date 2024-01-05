package com.bedwars.game.mysql;

import com.bedwars.game.player.GamePlayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerDatabase extends Database {
    public PlayerDatabase(String url, String user, String password) {
        super(url, user, password);
        createTableIfNotExists();
    }

    private void createTableIfNotExists() {
        try {
            String query = "CREATE TABLE IF NOT EXISTS players (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "username VARCHAR(255) NOT NULL," +
                    "kills INT DEFAULT 0," +
                    "deaths INT DEFAULT 0," +
                    "bedBreak INT DEFAULT 0)";
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(GamePlayer player) {
        try {
            String query = "INSERT IGNORE INTO players (username, kills, deaths, bedBreak) " +
                    "VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
                preparedStatement.setString(1, player.getName());
                preparedStatement.setInt(2, player.getKills());
                preparedStatement.setInt(3, player.getDeaths());
                preparedStatement.setInt(4, player.getBedBreaks());
                preparedStatement.executeUpdate();
            }

            query = "UPDATE players SET " +
                    "kills = kills + ?, " +
                    "deaths = deaths + ?, " +
                    "bedBreak = bedBreak + ? " +
                    "WHERE username = ?";

            try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
                preparedStatement.setInt(1, player.getKills());
                preparedStatement.setInt(2, player.getDeaths());
                preparedStatement.setInt(3, player.getBedBreaks());
                preparedStatement.setString(4, player.getName());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
