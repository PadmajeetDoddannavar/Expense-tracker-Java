package com.expensetracker.view;

import com.expensetracker.model.User;
import com.expensetracker.model.Transaction;
import com.expensetracker.controller.TransactionController;
import com.expensetracker.controller.ReportController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Main frame of the application showing user dashboard and transaction management
 */
public class MainFrame extends JFrame {
    private User currentUser;
    private TransactionController transactionController;
    private ReportController reportController;
    
    private JLabel welcomeLabel;
    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JPanel transactionPanel;
    private JButton addTransactionButton;
    private JButton generateReportButton;
    private JButton logoutButton;
    
    public MainFrame(User user) {
        this.currentUser = user;
        this.transactionController = new TransactionController();
        this.reportController = new ReportController();
        
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        refreshData();
    }
    
    private void initializeComponents() {
        setTitle("Expense Tracker - Main");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        welcomeLabel = new JLabel("Welcome, " + currentUser.getUsername() + "! 👋");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        balanceLabel = new JLabel("Balance: $0.00 💵");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setForeground(new Color(0, 123, 255));
        
        incomeLabel = new JLabel("Total Income: $0.00 📈");
        incomeLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        incomeLabel.setForeground(new Color(40, 167, 69));
        
        expenseLabel = new JLabel("Total Expenses: $0.00 📉");
        expenseLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        expenseLabel.setForeground(new Color(220, 53, 69));
        
        addTransactionButton = new JButton("Add Transaction 🔲");
        addTransactionButton.setBackground(new Color(0, 123, 255));
        addTransactionButton.setForeground(Color.WHITE);
        addTransactionButton.setFocusPainted(false);
        
        generateReportButton = new JButton("Generate Report 📊");
        generateReportButton.setBackground(new Color(108, 117, 125));
        generateReportButton.setForeground(Color.WHITE);
        generateReportButton.setFocusPainted(false);
        
        logoutButton = new JButton("Logout 🔄");
        logoutButton.setBackground(new Color(220, 53, 69));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        
        transactionPanel = new JPanel();
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
        transactionPanel.setBorder(BorderFactory.createTitledBorder("Transaction History"));
        
        JScrollPane scrollPane = new JScrollPane(transactionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Top panel with user info and balance
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.setBackground(new Color(248, 249, 250));
        
        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userInfoPanel.add(welcomeLabel);
        userInfoPanel.setBackground(new Color(248, 249, 250));
        
        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        balancePanel.add(balanceLabel);
        balancePanel.add(incomeLabel);
        balancePanel.add(expenseLabel);
        balancePanel.setBackground(new Color(248, 249, 250));
        
        topPanel.add(userInfoPanel, BorderLayout.WEST);
        topPanel.add(balancePanel, BorderLayout.EAST);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addTransactionButton);
        buttonPanel.add(generateReportButton);
        buttonPanel.add(logoutButton);
        
        // Transaction panel
        JPanel transactionContainer = new JPanel(new BorderLayout());
        transactionContainer.add(new JScrollPane(transactionPanel), BorderLayout.CENTER);
        
        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(transactionContainer, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        addTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddTransactionDialog(MainFrame.this, currentUser, transactionController).setVisible(true);
            }
        });
        
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportDialog(MainFrame.this, currentUser, reportController).setVisible(true);
            }
        });
        
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginFrame().setVisible(true);
                }
            }
        });
    }
    
    public void refreshData() {
        updateBalance();
        updateTransactionHistory();
    }
    
    private void updateBalance() {
        double totalIncome = transactionController.getTotalIncome(currentUser.getId());
        double totalExpenses = transactionController.getTotalExpenses(currentUser.getId());
        double balance = totalIncome - totalExpenses;
        
        balanceLabel.setText(String.format("Balance: $%.2f 💵", balance));
        incomeLabel.setText(String.format("Total Income: $%.2f 📈", totalIncome));
        expenseLabel.setText(String.format("Total Expenses: $%.2f 📉", totalExpenses));
    }
    
    private void updateTransactionHistory() {
        transactionPanel.removeAll();
        
        List<Transaction> transactions = transactionController.getTransactions(currentUser.getId());
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
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        // Transaction details
        JPanel detailsPanel = new JPanel(new GridLayout(2, 2));
        detailsPanel.add(new JLabel("Amount: $" + String.format("%.2f", transaction.getAmount())));
        detailsPanel.add(new JLabel("Type: " + transaction.getType()));
        detailsPanel.add(new JLabel("Category: " + transaction.getCategory()));
        detailsPanel.add(new JLabel("Date: " + transaction.getDate().toString()));
        
        if (transaction.getDescription() != null && !transaction.getDescription().isEmpty()) {
            detailsPanel.add(new JLabel("Description: " + transaction.getDescription()));
        }
        
        // Action buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton editButton = new JButton("Edit 📝");
        JButton deleteButton = new JButton("Delete 🗑️");
        
        editButton.setBackground(new Color(255, 193, 7));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        
        deleteButton.setBackground(new Color(220, 53, 69));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        
        editButton.addActionListener(e -> {
            new EditTransactionDialog(MainFrame.this, transaction, transactionController).setVisible(true);
        });
        
        deleteButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure you want to delete this transaction?", "Delete Transaction", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                if (transactionController.deleteTransaction(transaction.getId())) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Transaction deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    refreshData();
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Failed to delete transaction", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        card.add(detailsPanel, BorderLayout.CENTER);
        card.add(buttonPanel, BorderLayout.EAST);
        
        return card;
    }
}
