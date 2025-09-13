package com.expensetracker.view;

import com.expensetracker.controller.AuthController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Login frame for user authentication
 */
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private AuthController authController;
    
    public LoginFrame() {
        this.authController = new AuthController();
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setTitle("Expense Tracker - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
        
        // Style the buttons
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        
        registerButton.setBackground(new Color(40, 167, 69));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Title panel
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Expense Tracker", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 123, 255));
        titlePanel.add(titleLabel);
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);
        
        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(buttonPanel, gbc);
        
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
    }
    
    private void setupEventHandlers() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (authController.login(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new MainFrame(authController.getCurrentUser()).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                if (authController.register(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Registration successful! You can now login.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Username already exists or registration failed", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
