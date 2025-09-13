package com.expensetracker.controller;

import com.expensetracker.model.Transaction;
import com.expensetracker.database.DatabaseManager;
import com.expensetracker.util.ReportGenerator;
import java.util.List;

/**
 * Controller for handling report generation
 */
public class ReportController {
    private DatabaseManager databaseManager;
    private ReportGenerator reportGenerator;
    
    public ReportController() {
        this.databaseManager = DatabaseManager.getInstance();
        this.reportGenerator = new ReportGenerator();
    }
    
    public boolean generateReport(int userId, String format, String directory) throws Exception {
        List<Transaction> transactions = databaseManager.getTransactions(userId);
        return reportGenerator.generateReport(transactions, format, directory);
    }
}
