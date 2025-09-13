package com.expensetracker.controller;

import com.expensetracker.model.Transaction;
import com.expensetracker.database.DatabaseManager;
import java.util.List;

/**
 * Controller for handling transaction operations
 */
public class TransactionController {
    private DatabaseManager databaseManager;
    
    public TransactionController() {
        this.databaseManager = DatabaseManager.getInstance();
    }
    
    public boolean addTransaction(Transaction transaction) {
        return databaseManager.addTransaction(transaction);
    }
    
    public List<Transaction> getTransactions(int userId) {
        return databaseManager.getTransactions(userId);
    }
    
    public boolean updateTransaction(Transaction transaction) {
        return databaseManager.updateTransaction(transaction);
    }
    
    public boolean deleteTransaction(int transactionId) {
        return databaseManager.deleteTransaction(transactionId);
    }
    
    public double getTotalIncome(int userId) {
        return databaseManager.getTotalIncome(userId);
    }
    
    public double getTotalExpenses(int userId) {
        return databaseManager.getTotalExpenses(userId);
    }
}
