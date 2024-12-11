package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:users.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection()) {
            String createUsersTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "username TEXT NOT NULL, "
                    + "password TEXT NOT NULL)";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createUsersTableSQL);
            }

            String createBmiTableSQL = "CREATE TABLE IF NOT EXISTS bmi_results ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "email TEXT NOT NULL, "
                    + "weight REAL NOT NULL, "
                    + "height REAL NOT NULL, "
                    + "bmi REAL NOT NULL, "
                    + "date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(createBmiTableSQL);
            }

            String checkUserSQL = "SELECT * FROM users WHERE username = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkUserSQL)) {
                ps.setString(1, "admin");
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    String insertUserSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
                    try (PreparedStatement insertPS = conn.prepareStatement(insertUserSQL)) {
                        insertPS.setString(1, "admin");
                        insertPS.setString(2, "admin");
                        insertPS.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveBMIResult(String email, double weight, double height, double bmi) {
        String insertBMIResultSQL = "INSERT INTO bmi_results (email, weight, height, bmi) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(insertBMIResultSQL)) {
            ps.setString(1, email);
            ps.setDouble(2, weight);
            ps.setDouble(3, height);
            ps.setDouble(4, bmi);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean registerUser(String email, String password) {
        String checkUserSQL = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(checkUserSQL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return false;
            }

            String insertUserSQL = "INSERT INTO users (username, password) VALUES (?, ?)";
            try (PreparedStatement insertPS = conn.prepareStatement(insertUserSQL)) {
                insertPS.setString(1, email);
                insertPS.setString(2, password);
                insertPS.executeUpdate();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean verifyUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<BMIHistoryEntry> getBMIHistory(String email) {
        List<BMIHistoryEntry> history = new ArrayList<>(); // Using java.util.List
        String query = "SELECT weight, height, bmi, date FROM bmi_results WHERE email = ? ORDER BY date DESC";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                double weight = rs.getDouble("weight");
                double height = rs.getDouble("height");
                double bmi = rs.getDouble("bmi");
                bmi = Math.round(bmi * 100.0) / 100.0;
                Date date = rs.getDate("date");

                BMIHistoryEntry entry = new BMIHistoryEntry(weight, height, bmi, date);
                history.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}
