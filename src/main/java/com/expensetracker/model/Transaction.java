package com.expensetracker.model;

import java.time.LocalDate;

/**
 * Transaction model class representing a financial transaction
 */
public class Transaction {
    private int id;
    private int userId;
    private double amount;
    private String type; // "Income" or "Expense"
    private String category;
    private LocalDate date;
    private String description;
    
    public Transaction() {}
    
    public Transaction(int userId, double amount, String type, String category, LocalDate date, String description) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
        this.description = description;
    }
    
    public Transaction(int id, int userId, double amount, String type, String category, LocalDate date, String description) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.date = date;
        this.description = description;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
