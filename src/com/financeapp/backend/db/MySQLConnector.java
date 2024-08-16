package com.financeapp.backend.db;

import com.financeapp.backend.data.User;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;

public class MySQLConnector {
    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;

    static {
        try (InputStream input = MySQLConnector.class.getClassLoader().getResourceAsStream("com/financeapp/backend/resources/config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            prop.load(input);
            DB_URL = prop.getProperty("db.url");
            DB_USERNAME = prop.getProperty("db.username");
            DB_PASSWORD = prop.getProperty("db.password");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static User validateLogin(String username, String password) {
        String query = SQLStatementFactory.constructUsernameStatement();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String storedHashedPassword = resultSet.getString("password");

                    if (PasswordUtils.checkIfPasswordMatches(password, storedHashedPassword)) {
                        int userId = resultSet.getInt("id");
                        BigDecimal balance = resultSet.getBigDecimal("balance");
                        return new User(userId, username, password, balance);
                    }
                    else {
                        System.out.println("Password does not match.");
                    }
                }
                else {
                    System.out.println("No user found with the given username and password.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return null;
    }

    public static boolean registerNewUser(String username, String password) throws SQLException
    {
        String hashedPassword = PasswordUtils.hashPassword(password);

        /* true - success, false - failure */
        if (!checkIfUserExists(username))
        {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                SQLStatementFactory.insertUserIntoDatabase()
            );

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);

            preparedStatement.executeUpdate();

            return true;
        }

        return false;
    }

    private static boolean checkIfUserExists(String username) throws SQLException
    {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            SQLStatementFactory.constructUsernameStatement()
        );

        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    private static Connection getConnection() throws SQLException {
        if (DB_URL == null || DB_USERNAME == null || DB_PASSWORD == null) {
            throw new SQLException("Database configuration is not set");
        }
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
