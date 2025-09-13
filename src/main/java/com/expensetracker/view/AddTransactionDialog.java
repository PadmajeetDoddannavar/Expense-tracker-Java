package com.expensetracker.view;

import com.expensetracker.model.Transaction;
import com.expensetracker.model.User;
import com.expensetracker.controller.TransactionController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

/**
 * Dialog for adding new transactions
 */
public class AddTransactionDialog extends JDialog {
    private User currentUser;
    private TransactionController transactionController;
    private MainFrame parentFrame;
    
    private JTextField amountField;
    private JRadioButton incomeRadio;
    private JRadioButton expenseRadio;
    private JComboBox<String> categoryCombo;
    private JSpinner dateSpinner;
    private JTextArea descriptionArea;
    private JButton saveButton;
    private JButton cancelButton;
    
    private String[] incomeCategories = {"Salary", "Bonus", "Freelance", "Investment", "Other"};
    private String[] expenseCategories = {"Food", "Rent", "Transportation", "Entertainment", "Healthcare", "Utilities", "Other"};
    
    public AddTransactionDialog(MainFrame parent, User user, TransactionController controller) {
        super(parent, "Add Transaction", true);
        this.parentFrame = parent;
        this.currentUser = user;
        this.transactionController = controller;
        
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setSize(400, 500);
        setLocationRelativeTo(parentFrame);
        
        amountField = new JTextField(10);
        incomeRadio = new JRadioButton("Income 💵");
        expenseRadio = new JRadioButton("Expense 💸");
        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(incomeRadio);
        typeGroup.add(expenseRadio);
        expenseRadio.setSelected(true); // Default to expense
        
        categoryCombo = new JComboBox<>(expenseCategories);
        categoryCombo.setEditable(false);
        
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        dateSpinner.setValue(java.sql.Date.valueOf(LocalDate.now()));
        
        descriptionArea = new JTextArea(3, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        
        saveButton = new JButton("Save");
        saveButton.setBackground(new Color(40, 167, 69));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(108, 117, 125));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Amount
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Amount ($):"), gbc);
        gbc.gridx = 1;
        formPanel.add(amountField, gbc);
        
        // Type
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Type:"), gbc);
        gbc.gridx = 1;
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.add(incomeRadio);
        typePanel.add(expenseRadio);
        formPanel.add(typePanel, gbc);
        
        // Category
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        formPanel.add(categoryCombo, gbc);
        
        // Date
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Date:"), gbc);
        gbc.gridx = 1;
        formPanel.add(dateSpinner, gbc);
        
        // Description
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        formPanel.add(descriptionArea, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        // Update categories when type changes
        incomeRadio.addActionListener(e -> updateCategories());
        expenseRadio.addActionListener(e -> updateCategories());
        
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    Transaction transaction = createTransaction();
                    if (transactionController.addTransaction(transaction)) {
                        JOptionPane.showMessageDialog(AddTransactionDialog.this, "Transaction added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        parentFrame.refreshData();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(AddTransactionDialog.this, "Failed to add transaction", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        
        cancelButton.addActionListener(e -> dispose());
    }
    
    private void updateCategories() {
        if (incomeRadio.isSelected()) {
            categoryCombo.setModel(new DefaultComboBoxModel<>(incomeCategories));
        } else {
            categoryCombo.setModel(new DefaultComboBoxModel<>(expenseCategories));
        }
    }
    
    private boolean validateInput() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!incomeRadio.isSelected() && !expenseRadio.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please select a transaction type", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private Transaction createTransaction() {
        double amount = Double.parseDouble(amountField.getText().trim());
        String type = incomeRadio.isSelected() ? "Income" : "Expense";
        String category = (String) categoryCombo.getSelectedItem();
        LocalDate date = ((java.sql.Date) dateSpinner.getValue()).toLocalDate();
        String description = descriptionArea.getText().trim();
        
        return new Transaction(currentUser.getId(), amount, type, category, date, description);
    }
}
