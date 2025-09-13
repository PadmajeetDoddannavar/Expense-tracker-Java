package com.expensetracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple Expense Tracker App without external dependencies
 */
public class SimpleExpenseTrackerApp extends JFrame {
    private List<Transaction> transactions = new ArrayList<>();
    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JPanel transactionPanel;
    private JTextField amountField;
    private JComboBox<String> typeCombo;
    private JComboBox<String> categoryCombo;
    private JTextField descriptionField;
    
    public SimpleExpenseTrackerApp() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        updateDisplay();
    }
    
    private void initializeComponents() {
        setTitle("Expense Tracker App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        balanceLabel = new JLabel("Balance: $0.00");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setForeground(Color.BLUE);
        
        incomeLabel = new JLabel("Total Income: $0.00");
        incomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        incomeLabel.setForeground(Color.GREEN);
        
        expenseLabel = new JLabel("Total Expenses: $0.00");
        expenseLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        expenseLabel.setForeground(Color.RED);
        
        amountField = new JTextField(10);
        typeCombo = new JComboBox<>(new String[]{"Income", "Expense"});
        categoryCombo = new JComboBox<>(new String[]{"Food", "Rent", "Transportation", "Entertainment", "Salary", "Bonus", "Other"});
        descriptionField = new JTextField(20);
        
        transactionPanel = new JPanel();
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
        transactionPanel.setBorder(BorderFactory.createTitledBorder("Transaction History"));
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Top panel with balance info
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(balanceLabel);
        topPanel.add(incomeLabel);
        topPanel.add(expenseLabel);
        
        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("Type:"));
        inputPanel.add(typeCombo);
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryCombo);
        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionField);
        
        JButton addButton = new JButton("Add Transaction");
        addButton.setBackground(Color.BLUE);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> addTransaction());
        inputPanel.add(addButton);
        
        // Transaction panel
        JScrollPane scrollPane = new JScrollPane(transactionPanel);
        
        add(topPanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        typeCombo.addActionListener(e -> updateCategories());
    }
    
    private void updateCategories() {
        String type = (String) typeCombo.getSelectedItem();
        if ("Income".equals(type)) {
            categoryCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Salary", "Bonus", "Freelance", "Investment", "Other"}));
        } else {
            categoryCombo.setModel(new DefaultComboBoxModel<>(new String[]{"Food", "Rent", "Transportation", "Entertainment", "Healthcare", "Utilities", "Other"}));
        }
    }
    
    private void addTransaction() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String type = (String) typeCombo.getSelectedItem();
            String category = (String) categoryCombo.getSelectedItem();
            String description = descriptionField.getText().trim();
            
            Transaction transaction = new Transaction(amount, type, category, LocalDate.now(), description);
            transactions.add(transaction);
            
            // Clear fields
            amountField.setText("");
            descriptionField.setText("");
            
            updateDisplay();
            JOptionPane.showMessageDialog(this, "Transaction added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateDisplay() {
        double totalIncome = transactions.stream()
            .filter(t -> "Income".equals(t.type))
            .mapToDouble(t -> t.amount)
            .sum();
            
        double totalExpenses = transactions.stream()
            .filter(t -> "Expense".equals(t.type))
            .mapToDouble(t -> t.amount)
            .sum();
            
        double balance = totalIncome - totalExpenses;
        
        balanceLabel.setText(String.format("Balance: $%.2f", balance));
        incomeLabel.setText(String.format("Total Income: $%.2f", totalIncome));
        expenseLabel.setText(String.format("Total Expenses: $%.2f", totalExpenses));
        
        // Update transaction panel
        transactionPanel.removeAll();
        for (Transaction transaction : transactions) {
            JPanel transactionCard = createTransactionCard(transaction);
            transactionPanel.add(transactionCard);
        }
        transactionPanel.revalidate();
        transactionPanel.repaint();
    }
    
    private JPanel createTransactionCard(Transaction transaction) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        
        JPanel detailsPanel = new JPanel(new GridLayout(2, 3));
        detailsPanel.add(new JLabel("Amount: $" + String.format("%.2f", transaction.amount)));
        detailsPanel.add(new JLabel("Type: " + transaction.type));
        detailsPanel.add(new JLabel("Category: " + transaction.category));
        detailsPanel.add(new JLabel("Date: " + transaction.date.toString()));
        detailsPanel.add(new JLabel("Description: " + transaction.description));
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.setBackground(Color.RED);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> {
            transactions.remove(transaction);
            updateDisplay();
        });
        
        card.add(detailsPanel, BorderLayout.CENTER);
        card.add(deleteButton, BorderLayout.EAST);
        
        return card;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new SimpleExpenseTrackerApp().setVisible(true);
        });
    }
    
    // Simple Transaction class
    private static class Transaction {
        double amount;
        String type;
        String category;
        LocalDate date;
        String description;
        
        Transaction(double amount, String type, String category, LocalDate date, String description) {
            this.amount = amount;
            this.type = type;
            this.category = category;
            this.date = date;
            this.description = description;
        }
    }
}
