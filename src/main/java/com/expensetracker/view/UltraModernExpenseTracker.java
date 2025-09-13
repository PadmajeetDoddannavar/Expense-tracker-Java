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
 * Ultra Modern Expense Tracker with advanced UI features
 */
public class UltraModernExpenseTracker extends JFrame {
    private List<Transaction> transactions = new ArrayList<>();
    private JLabel balanceLabel;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JLabel transactionCountLabel;
    private JPanel transactionPanel;
    private JTextField amountField;
    private JComboBox<String> typeCombo;
    private JComboBox<String> categoryCombo;
    private JTextField descriptionField;
    private JSpinner dateSpinner;
    private JTabbedPane tabbedPane;
    
    // Modern color palette
    private final Color PRIMARY_COLOR = new Color(74, 144, 226);
    private final Color PRIMARY_DARK = new Color(52, 152, 219);
    private final Color SUCCESS_COLOR = new Color(39, 174, 96);
    private final Color DANGER_COLOR = new Color(231, 76, 60);
    private final Color WARNING_COLOR = new Color(241, 196, 15);
    private final Color INFO_COLOR = new Color(52, 152, 219);
    private final Color LIGHT_GRAY = new Color(248, 249, 250);
    private final Color MEDIUM_GRAY = new Color(189, 195, 199);
    private final Color DARK_GRAY = new Color(52, 73, 94);
    private final Color WHITE = Color.WHITE;
    private final Color BLACK = Color.BLACK;
    
    public UltraModernExpenseTracker() {
        initializeComponents();
        setupLayout();
        setupEventHandlers();
        updateDisplay();
        setLookAndFeel();
    }
    
    private void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initializeComponents() {
        setTitle("💎 Ultra Modern Expense Tracker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(true);
        setMinimumSize(new Dimension(1000, 700));
        
        // Set application icon
        setIconImage(createModernIcon());
        
        // Initialize labels with modern styling
        balanceLabel = createStyledLabel("₹0.00", PRIMARY_COLOR, 32, Font.BOLD);
        incomeLabel = createStyledLabel("₹0.00", SUCCESS_COLOR, 18, Font.BOLD);
        expenseLabel = createStyledLabel("₹0.00", DANGER_COLOR, 18, Font.BOLD);
        transactionCountLabel = createStyledLabel("0 Transactions", INFO_COLOR, 14, Font.PLAIN);
        
        // Initialize input components
        amountField = createModernTextField(15);
        amountField.setToolTipText("Enter amount (e.g., 1000.50)");
        
        typeCombo = createModernComboBox(new String[]{"Income", "Expense"});
        typeCombo.setToolTipText("Select transaction type");
        
        categoryCombo = createModernComboBox(new String[]{"Food", "Rent", "Transportation", "Entertainment", "Salary", "Bonus", "Other"});
        categoryCombo.setToolTipText("Select category");
        
        descriptionField = createModernTextField(25);
        descriptionField.setToolTipText("Enter description (optional)");
        
        // Date spinner
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "MMM dd, yyyy"));
        dateSpinner.setValue(java.sql.Date.valueOf(LocalDate.now()));
        dateSpinner.setBorder(new LineBorder(MEDIUM_GRAY, 1, true));
        dateSpinner.setPreferredSize(new Dimension(150, 40));
        
        // Tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        // Transaction panel
        transactionPanel = new JPanel();
        transactionPanel.setLayout(new BoxLayout(transactionPanel, BoxLayout.Y_AXIS));
        transactionPanel.setBackground(WHITE);
        transactionPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
    }
    
    private JLabel createStyledLabel(String text, Color color, int size, int style) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", style, size));
        label.setForeground(color);
        return label;
    }
    
    private JTextField createModernTextField(int columns) {
        JTextField field = new JTextField(columns);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(new LineBorder(MEDIUM_GRAY, 1, true));
        field.setPreferredSize(new Dimension(field.getPreferredSize().width, 40));
        field.setBackground(WHITE);
        
        // Add focus effect
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(new LineBorder(PRIMARY_COLOR, 2, true));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(new LineBorder(MEDIUM_GRAY, 1, true));
            }
        });
        
        return field;
    }
    
    private JComboBox<String> createModernComboBox(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        combo.setBorder(new LineBorder(MEDIUM_GRAY, 1, true));
        combo.setPreferredSize(new Dimension(combo.getPreferredSize().width, 40));
        combo.setBackground(WHITE);
        
        // Add focus effect
        combo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combo.setBorder(new LineBorder(PRIMARY_COLOR, 2, true));
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                combo.setBorder(new LineBorder(MEDIUM_GRAY, 1, true));
            }
        });
        
        return combo;
    }
    
    private JButton createModernButton(String text, Color bgColor, Color fgColor, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setPreferredSize(new Dimension(width, height));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        // Add modern hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker());
                button.setBorder(new LineBorder(bgColor.darker(), 2, true));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
                button.setBorder(new EmptyBorder(10, 20, 10, 20));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.darker().darker());
            }
        });
        
        return button;
    }
    
    private Image createModernIcon() {
        BufferedImage icon = new BufferedImage(48, 48, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = icon.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Create gradient background
        GradientPaint gradient = new GradientPaint(0, 0, PRIMARY_COLOR, 48, 48, PRIMARY_DARK);
        g2d.setPaint(gradient);
        g2d.fillRoundRect(4, 4, 40, 40, 20, 20);
        
        // Add dollar sign
        g2d.setColor(WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.drawString("$", 16, 32);
        
        g2d.dispose();
        return icon;
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        getContentPane().setBackground(LIGHT_GRAY);
        
        // Header panel
        JPanel headerPanel = createModernHeaderPanel();
        
        // Main content area
        JPanel mainPanel = createMainPanel();
        
        add(headerPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private JPanel createModernHeaderPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(PRIMARY_COLOR);
        panel.setBorder(new EmptyBorder(25, 40, 25, 40));
        
        // Title section
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        titlePanel.setBackground(PRIMARY_COLOR);
        
        JLabel titleLabel = new JLabel("💎 Ultra Modern Expense Tracker");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(WHITE);
        
        JLabel subtitleLabel = new JLabel("Professional financial management made simple");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitleLabel.setForeground(WHITE);
        
        JPanel titleContainer = new JPanel(new BorderLayout());
        titleContainer.setBackground(PRIMARY_COLOR);
        titleContainer.add(titleLabel, BorderLayout.NORTH);
        titleContainer.add(subtitleLabel, BorderLayout.SOUTH);
        
        titlePanel.add(titleContainer);
        
        // Stats section
        JPanel statsPanel = createHeaderStatsPanel();
        
        panel.add(titlePanel, BorderLayout.WEST);
        panel.add(statsPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createHeaderStatsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 0));
        panel.setBackground(PRIMARY_COLOR);
        
        // Balance
        JPanel balancePanel = new JPanel(new BorderLayout());
        balancePanel.setBackground(PRIMARY_COLOR);
        JLabel balanceTitle = new JLabel("BALANCE");
        balanceTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        balanceTitle.setForeground(WHITE);
        balanceTitle.setHorizontalAlignment(SwingConstants.CENTER);
        balancePanel.add(balanceTitle, BorderLayout.NORTH);
        balancePanel.add(balanceLabel, BorderLayout.CENTER);
        
        // Transaction count
        JPanel countPanel = new JPanel(new BorderLayout());
        countPanel.setBackground(PRIMARY_COLOR);
        JLabel countTitle = new JLabel("TRANSACTIONS");
        countTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));
        countTitle.setForeground(WHITE);
        countTitle.setHorizontalAlignment(SwingConstants.CENTER);
        countPanel.add(countTitle, BorderLayout.NORTH);
        countPanel.add(transactionCountLabel, BorderLayout.CENTER);
        
        panel.add(balancePanel);
        panel.add(countPanel);
        
        return panel;
    }
    
    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_GRAY);
        panel.setBorder(new EmptyBorder(20, 40, 20, 40));
        
        // Stats cards
        JPanel statsPanel = createStatsCardsPanel();
        
        // Tabbed content
        tabbedPane.addTab("📊 Dashboard", createDashboardPanel());
        tabbedPane.addTab("➕ Add Transaction", createAddTransactionPanel());
        tabbedPane.addTab("📋 Transaction History", createTransactionHistoryPanel());
        
        panel.add(statsPanel, BorderLayout.NORTH);
        panel.add(tabbedPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createStatsCardsPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 20, 0));
        panel.setBackground(LIGHT_GRAY);
        panel.setBorder(new EmptyBorder(0, 0, 20, 0));
        
        // Income card
        JPanel incomeCard = createModernStatCard("📈 INCOME", incomeLabel, SUCCESS_COLOR, "Total money earned");
        
        // Expense card
        JPanel expenseCard = createModernStatCard("📉 EXPENSES", expenseLabel, DANGER_COLOR, "Total money spent");
        
        // Balance card
        JPanel balanceCard = createModernStatCard("💰 BALANCE", balanceLabel, PRIMARY_COLOR, "Current balance");
        
        panel.add(incomeCard);
        panel.add(expenseCard);
        panel.add(balanceCard);
        
        return panel;
    }
    
    private JPanel createModernStatCard(String title, JLabel valueLabel, Color accentColor, String description) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(WHITE);
        card.setBorder(new LineBorder(accentColor, 3, true));
        card.setPreferredSize(new Dimension(250, 120));
        
        // Header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(accentColor);
        headerPanel.setBorder(new EmptyBorder(10, 15, 10, 15));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        titleLabel.setForeground(WHITE);
        headerPanel.add(titleLabel);
        
        // Content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(WHITE);
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        descLabel.setForeground(MEDIUM_GRAY);
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        contentPanel.add(valueLabel, BorderLayout.CENTER);
        contentPanel.add(descLabel, BorderLayout.SOUTH);
        
        card.add(headerPanel, BorderLayout.NORTH);
        card.add(contentPanel, BorderLayout.CENTER);
        
        return card;
    }
    
    private JPanel createDashboardPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(WHITE);
        panel.setBorder(new LineBorder(MEDIUM_GRAY, 1, true));
        
        JLabel welcomeLabel = new JLabel("Welcome to your financial dashboard!");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        welcomeLabel.setForeground(DARK_GRAY);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBorder(new EmptyBorder(50, 20, 20, 20));
        
        JLabel instructionLabel = new JLabel("Use the tabs above to add transactions or view your history");
        instructionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        instructionLabel.setForeground(MEDIUM_GRAY);
        instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        instructionLabel.setBorder(new EmptyBorder(0, 20, 50, 20));
        
        panel.add(welcomeLabel, BorderLayout.CENTER);
        panel.add(instructionLabel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createAddTransactionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(WHITE);
        panel.setBorder(new LineBorder(MEDIUM_GRAY, 1, true));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(WHITE);
        formPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Amount
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(createInputGroup("Amount (₹)", amountField), gbc);
        
        // Type
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(createInputGroup("Type", typeCombo), gbc);
        
        // Category
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(createInputGroup("Category", categoryCombo), gbc);
        
        // Date
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(createInputGroup("Date", dateSpinner), gbc);
        
        // Description
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(createInputGroup("Description", descriptionField), gbc);
        
        // Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(WHITE);
        
        JButton addButton = createModernButton("➕ Add Transaction", SUCCESS_COLOR, WHITE, 180, 50);
        addButton.addActionListener(e -> addTransaction());
        
        JButton clearButton = createModernButton("🗑️ Clear Form", WARNING_COLOR, WHITE, 150, 50);
        clearButton.addActionListener(e -> clearForm());
        
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        
        formPanel.add(buttonPanel, gbc);
        
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createTransactionHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(WHITE);
        panel.setBorder(new LineBorder(MEDIUM_GRAY, 1, true));
        
        JScrollPane scrollPane = new JScrollPane(transactionPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        scrollPane.getViewport().setBackground(WHITE);
        
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createInputGroup(String label, JComponent component) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(WHITE);
        
        JLabel labelComponent = new JLabel(label);
        labelComponent.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelComponent.setForeground(DARK_GRAY);
        labelComponent.setBorder(new EmptyBorder(0, 0, 8, 0));
        
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
            LocalDate date = ((java.sql.Date) dateSpinner.getValue()).toLocalDate();
            
            Transaction transaction = new Transaction(amount, type, category, date, description);
            transactions.add(transaction);
            
            clearForm();
            updateDisplay();
            showSuccess("Transaction added successfully!");
            
            // Switch to transaction history tab
            tabbedPane.setSelectedIndex(2);
            
        } catch (NumberFormatException e) {
            showError("Please enter a valid amount");
        }
    }
    
    private void clearForm() {
        amountField.setText("");
        descriptionField.setText("");
        dateSpinner.setValue(java.sql.Date.valueOf(LocalDate.now()));
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
        transactionCountLabel.setText(transactions.size() + " Transactions");
        
        // Update transaction panel
        transactionPanel.removeAll();
        
        if (transactions.isEmpty()) {
            JLabel emptyLabel = new JLabel("No transactions yet. Add your first transaction above!");
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            emptyLabel.setForeground(MEDIUM_GRAY);
            emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
            emptyLabel.setBorder(new EmptyBorder(100, 20, 100, 20));
            transactionPanel.add(emptyLabel);
        } else {
            for (Transaction transaction : transactions) {
                JPanel transactionCard = createModernTransactionCard(transaction);
                transactionPanel.add(transactionCard);
                transactionPanel.add(Box.createVerticalStrut(15));
            }
        }
        
        transactionPanel.revalidate();
        transactionPanel.repaint();
    }
    
    private JPanel createModernTransactionCard(Transaction transaction) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(WHITE);
        card.setBorder(new LineBorder(MEDIUM_GRAY, 1, true));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        
        // Left panel with transaction details
        JPanel detailsPanel = new JPanel(new GridLayout(2, 4, 15, 8));
        detailsPanel.setBackground(WHITE);
        detailsPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        
        // Amount with color coding and icon
        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        amountPanel.setBackground(WHITE);
        JLabel amountIcon = new JLabel("Income".equals(transaction.type) ? "📈" : "📉");
        amountIcon.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JLabel amountLabel = new JLabel("₹" + String.format("%.2f", transaction.amount));
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        amountLabel.setForeground("Income".equals(transaction.type) ? SUCCESS_COLOR : DANGER_COLOR);
        amountPanel.add(amountIcon);
        amountPanel.add(amountLabel);
        detailsPanel.add(amountPanel);
        
        // Type
        JLabel typeLabel = new JLabel(transaction.type);
        typeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        typeLabel.setForeground(DARK_GRAY);
        detailsPanel.add(typeLabel);
        
        // Category
        JLabel categoryLabel = new JLabel(transaction.category);
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        categoryLabel.setForeground(DARK_GRAY);
        detailsPanel.add(categoryLabel);
        
        // Date
        JLabel dateLabel = new JLabel(transaction.date.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        dateLabel.setForeground(MEDIUM_GRAY);
        detailsPanel.add(dateLabel);
        
        // Description
        JLabel descLabel = new JLabel(transaction.description.isEmpty() ? "No description" : transaction.description);
        descLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        descLabel.setForeground(MEDIUM_GRAY);
        detailsPanel.add(descLabel);
        
        // Empty cells for alignment
        detailsPanel.add(new JLabel(""));
        detailsPanel.add(new JLabel(""));
        detailsPanel.add(new JLabel(""));
        
        // Right panel with action button
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setBackground(WHITE);
        actionPanel.setBorder(new EmptyBorder(15, 15, 15, 25));
        
        JButton deleteButton = createModernButton("🗑️ Delete", DANGER_COLOR, WHITE, 100, 35);
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
            new UltraModernExpenseTracker().setVisible(true);
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
