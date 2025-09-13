package com.expensetracker.controller;

import com.expensetracker.model.User;
import com.expensetracker.database.DatabaseManager;

/**
 * Controller for handling authentication operations
 */
public class AuthController {
    private DatabaseManager databaseManager;
    private User currentUser;
    
    public AuthController() {
        this.databaseManager = DatabaseManager.getInstance();
    }
    
    public boolean login(String username, String password) {
        User user = databaseManager.loginUser(username, password);
        if (user != null) {
            this.currentUser = user;
            return true;
        }
        return false;
    }
    
    public boolean register(String username, String password) {
        return databaseManager.registerUser(username, password);
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void logout() {
        this.currentUser = null;
    }
}
