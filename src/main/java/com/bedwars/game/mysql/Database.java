package com.bedwars.game.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Database  implements DatabaseManager {

    private final String url;
    private final String user;
    private final String password;
    private Connection connection;

    protected Database(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;

        connect(url, user, password);
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public String getUrl() {
        return url;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }
}
