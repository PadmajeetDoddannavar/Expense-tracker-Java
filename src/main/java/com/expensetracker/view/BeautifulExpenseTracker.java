package com.expensetracker.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Beautiful Expense Tracker with modern UI
 */
public class BeautifulExpenseTracker extends JFrame {
    private List<Transaction> transactions = new ArrayList<>();
    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JPanel transactionPanel;
    private JTextField amountField;
    private JComboBox<String> typeCombo;
    private JComboBox<String> categoryCombo;
    private JTextField descriptionField;
    private JSpinner dateSpinner;
    
    // Beautiful color scheme
    private final Color PRIMARY_COLOR = new Color(52, 152, 219);
    private final Color SUCCESS_COLOR = new Color(46, 204, 113);
    private final Color DANGER_COLOR = new Color(231, 76, 60);
    private final Color WARNING_COLOR = new Color(241, 196, 15);
    private final Color LIGHT_GRAY = new Color(236, 240, 241);
    private final Color DARK_GRAY = new Color(52, 73, 94);
    private final Color WHITE = Color.WHITE;
    
    public BeautifulExpenseTracker() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        updateDisplay();
    }
    
    private void initializeComponents() {
        setTitle("💎 Beautiful Expense Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setResizable(true);
        
        // Initialize labels with beautiful styling
        balanceLabel = createStyledLabel("₹0.00", PRIMARY_COLOR, 28, Font.BOLD);
        incomeLabel = createStyledLabel("₹0.00", SUCCESS_COLOR, 18, Font.BOLD);
        expenseLabel = createStyledLabel("₹0.00", DANGER_COLOR, 18, Font.BOLD);
        
        // Initialize input components
        amountField = createStyledTextField(12);
        amountField.setToolTipText("Enter amount (e.g., 1000.50)");
        
        typeCombo = createStyledComboBox(new String[]{"Income", "Expense"});
        typeCombo.setToolTipText("Select transaction type");
        
        categoryCombo = createStyledComboBox(new String[]{"Food", "Rent", "Transportation", "Entertainment", "Salary", "Bonus", "Other"});
        categoryCombo.setToolTipText("Select category");
        
        descriptionField = createStyledTextField(20);
        descriptionField.setToolTipText("Enter description (optional)");
        
        // Date spinner
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
        dateSpinner.setValue(java.util.Date.from(LocalDate.now().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
        dateSpinner.setBorder(new LineBorder(LIGHT_GRAY, 1, true));
        dateSpinner.setPreferredSize(new Dimension(120, 35));
        
        // Transaction panel
        transactionPanel = new JPanel();
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
        transactionPanel.setBackground(WHITE);
        transactionPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    private JLabel createStyledLabel(String text, Color color, int size, int style) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", style, size));
        label.setForeground(color);
        return label;
    }
    
    private JTextField createStyledTextField(int columns) {
        JTextField field = new JTextField(columns);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        field.setBorder(new LineBorder(LIGHT_GRAY, 1, true));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 35));
        return field;
    }
    
    private JComboBox<String> createStyledComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        combo.setBorder(new LineBorder(LIGHT_GRAY, 1, true));
        combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, 35));
        return combo;
    }
    
    private JButton createStyledButton(String text, Color bgColor, Color fgColor, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setPreferredSize(new Dimension(width, height));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        getContentPane().setBackground(LIGHT_GRAY);
        
        // Header panel
        JPanel headerPanel = createHeaderPanel();
        
        // Stats panel
        JPanel statsPanel = createStatsPanel();
        
        // Input panel
        JPanel inputPanel = createInputPanel();
        
        // Transaction panel
        JScrollPane scrollPane = new JScrollPane(transactionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.getViewport().setBackground(WHITE);
        
        add(headerPanel, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
        add(scrollPane, BorderLayout.EAST);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PRIMARY_COLOR);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        JLabel titleLabel = new JLabel("💎 Beautiful Expense Tracker");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(WHITE);
        
        JLabel subtitleLabel = new JLabel("Track your finances with style and elegance");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(WHITE);
        
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(PRIMARY_COLOR);
        titlePanel.add(titleLabel);
        
        JPanel subtitlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        subtitlePanel.setBackground(PRIMARY_COLOR);
        subtitlePanel.add(subtitleLabel);
        
        panel.add(titlePanel, BorderLayout.WEST);
        panel.add(subtitlePanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createStatsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 0));
        panel.setBackground(LIGHT_GRAY);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Balance card
        JPanel balanceCard = createStatCard("💰 Balance", balanceLabel, PRIMARY_COLOR);
        
        // Income card
        JPanel incomeCard = createStatCard("📈 Income", incomeLabel, SUCCESS_COLOR);
        
        // Expense card
        JPanel expenseCard = createStatCard("📉 Expenses", expenseLabel, DANGER_COLOR);
        
        panel.add(balanceCard);
        panel.add(incomeCard);
        panel.add(expenseCard);
        
        return panel;
    }
    
    private JPanel createStatCard(String title, JLabel valueLabel, Color accentColor) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(WHITE);
        card.setBorder(new LineBorder(accentColor, 2, true));
        card.setPreferredSize(new Dimension(200, 120));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(DARK_GRAY);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(valueLabel, BorderLayout.CENTER);
        
        card.add(contentPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(WHITE);
        panel.setBorder(new EmptyBorder(20, 30, 20, 30));
        
        // Input form
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        formPanel.setBackground(WHITE);
        
        // Amount input
        JPanel amountPanel = createInputGroup("Amount (₹)", amountField);
        formPanel.add(amountPanel);
        
        // Type selection
        JPanel typePanel = createInputGroup("Type", typeCombo);
        formPanel.add(typePanel);
        
        // Category selection
        JPanel categoryPanel = createInputGroup("Category", categoryCombo);
        formPanel.add(categoryPanel);
        
        // Date selection
        JPanel datePanel = createInputGroup("Date", dateSpinner);
        formPanel.add(datePanel);
        
        // Description input
        JPanel descPanel = createInputGroup("Description", descriptionField);
        formPanel.add(descPanel);
        
        // Add button
        JButton addButton = createStyledButton("➕ Add Transaction", SUCCESS_COLOR, WHITE, 150, 40);
        addButton.addActionListener(e -> addTransaction());
        formPanel.add(addButton);
        
        // Clear button
        JButton clearButton = createStyledButton("🗑️ Clear", WARNING_COLOR, WHITE, 100, 40);
        clearButton.addActionListener(e -> clearForm());
        formPanel.add(clearButton);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createInputGroup(String label, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(WHITE);
        
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelComponent.setForeground(DARK_GRAY);
        labelComponent.setBorder(new EmptyBorder(0, 0, 5, 0));
        
        panel.add(labelComponent, BorderLayout.NORTH);
        panel.add(component, BorderLayout.CENTER);
        
        return panel;
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
                showError("Amount must be greater than 0");
                return;
            }
            
            String type = (String) typeCombo.getSelectedItem();
            String category = (String) categoryCombo.getSelectedItem();
            String description = descriptionField.getText().trim();
            LocalDate date = ((java.util.Date) dateSpinner.getValue()).toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            
            Transaction transaction = new Transaction(amount, type, category, date, description);
            transactions.add(transaction);
            
            clearForm();
            updateDisplay();
            showSuccess("Transaction added successfully!");
            
        } catch (NumberFormatException e) {
            showError("Please enter a valid amount");
        }
    }
    
    private void clearForm() {
        amountField.setText("");
        descriptionField.setText("");
        dateSpinner.setValue(java.util.Date.from(LocalDate.now().atStartOfDay(java.time.ZoneId.systemDefault()).toInstant()));
    }
    
    private void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
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
        
        balanceLabel.setText(String.format("₹%.2f", balance));
        incomeLabel.setText(String.format("₹%.2f", totalIncome));
        expenseLabel.setText(String.format("₹%.2f", totalExpenses));
        
        // Update transaction panel
        transactionPanel.removeAll();
        
        if (transactions.isEmpty()) {
            JLabel emptyLabel = new JLabel("No transactions yet. Add your first transaction above!");
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            emptyLabel.setForeground(DARK_GRAY);
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            emptyLabel.setBorder(new EmptyBorder(50, 20, 50, 20));
            transactionPanel.add(emptyLabel);
        } else {
            for (Transaction transaction : transactions) {
                JPanel transactionCard = createTransactionCard(transaction);
                transactionPanel.add(transactionCard);
                transactionPanel.add(Box.createVerticalStrut(10));
            }
        }
        
        transactionPanel.revalidate();
        transactionPanel.repaint();
    }
    
    private JPanel createTransactionCard(Transaction transaction) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(WHITE);
        card.setBorder(new LineBorder(LIGHT_GRAY, 1, true));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        
        // Left panel with transaction details
        JPanel detailsPanel = new JPanel(new GridLayout(2, 3, 10, 5));
        detailsPanel.setBackground(WHITE);
        detailsPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        
        // Amount with color coding
        JLabel amountLabel = new JLabel("₹" + String.format("%.2f", transaction.amount));
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        amountLabel.setForeground("Income".equals(transaction.type) ? SUCCESS_COLOR : DANGER_COLOR);
        detailsPanel.add(amountLabel);
        
        // Type
        JLabel typeLabel = new JLabel(transaction.type);
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        typeLabel.setForeground(DARK_GRAY);
        detailsPanel.add(typeLabel);
        
        // Category
        JLabel categoryLabel = new JLabel(transaction.category);
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        categoryLabel.setForeground(DARK_GRAY);
        detailsPanel.add(categoryLabel);
        
        // Date
        JLabel dateLabel = new JLabel(transaction.date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        dateLabel.setForeground(DARK_GRAY);
        detailsPanel.add(dateLabel);
        
        // Description
        JLabel descLabel = new JLabel(transaction.description.isEmpty() ? "No description" : transaction.description);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descLabel.setForeground(DARK_GRAY);
        detailsPanel.add(descLabel);
        
        // Right panel with action button
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(WHITE);
        actionPanel.setBorder(new EmptyBorder(10, 10, 10, 20));
        
        JButton deleteButton = createStyledButton("🗑️", DANGER_COLOR, WHITE, 40, 30);
        deleteButton.setToolTipText("Delete transaction");
        deleteButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this transaction?", 
                "Delete Transaction", 
                JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                transactions.remove(transaction);
                updateDisplay();
            }
        });
        actionPanel.add(deleteButton);
        
        card.add(detailsPanel, BorderLayout.CENTER);
        card.add(actionPanel, BorderLayout.EAST);
        
        return card;
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BeautifulExpenseTracker().setVisible(true);
        });
    }
    
    // Transaction class
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
