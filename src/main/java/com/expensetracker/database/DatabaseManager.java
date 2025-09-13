package com.expensetracker.database;

import com.expensetracker.model.User;
import com.expensetracker.model.Transaction;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Database manager class handling all database operations
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:expense_tracker.db";
    private static DatabaseManager instance;
    private Connection connection;
    
    private DatabaseManager() {
        initializeDatabase();
    }
    
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    private void initializeDatabase() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            createTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void createTables() throws SQLException {
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                username TEXT UNIQUE NOT NULL,
                password TEXT NOT NULL
            )
        """;
        
        String createTransactionsTable = """
            CREATE TABLE IF NOT EXISTS transactions (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                user_id INTEGER NOT NULL,
                amount REAL NOT NULL,
                type TEXT NOT NULL,
                category TEXT NOT NULL,
                date TEXT NOT NULL,
                description TEXT,
                FOREIGN KEY (user_id) REFERENCES users (id)
            )
        """;
        
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createTransactionsTable);
        }
    }
    
    // User operations
    public boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public User loginUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Transaction operations
    public boolean addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (user_id, amount, type, category, date, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, transaction.getUserId());
            pstmt.setDouble(2, transaction.getAmount());
            pstmt.setString(3, transaction.getType());
            pstmt.setString(4, transaction.getCategory());
            pstmt.setString(5, transaction.getDate().toString());
            pstmt.setString(6, transaction.getDescription());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Transaction> getTransactions(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY date DESC";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(
                    rs.getInt("id"),
                    rs.getInt("user_id"),
                    rs.getDouble("amount"),
                    rs.getString("type"),
                    rs.getString("category"),
                    LocalDate.parse(rs.getString("date")),
                    rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    
    public boolean updateTransaction(Transaction transaction) {
        String sql = "UPDATE transactions SET amount = ?, type = ?, category = ?, date = ?, description = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setDouble(1, transaction.getAmount());
            pstmt.setString(2, transaction.getType());
            pstmt.setString(3, transaction.getCategory());
            pstmt.setString(4, transaction.getDate().toString());
            pstmt.setString(5, transaction.getDescription());
            pstmt.setInt(6, transaction.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteTransaction(int transactionId) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, transactionId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public double getTotalIncome(int userId) {
        String sql = "SELECT SUM(amount) FROM transactions WHERE user_id = ? AND type = 'Income'";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    public double getTotalExpenses(int userId) {
        String sql = "SELECT SUM(amount) FROM transactions WHERE user_id = ? AND type = 'Expense'";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
