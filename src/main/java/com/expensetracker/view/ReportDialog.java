package com.expensetracker.view;

import com.expensetracker.model.User;
import com.expensetracker.controller.ReportController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Dialog for generating reports in different formats
 */
public class ReportDialog extends JDialog {
    private User currentUser;
    private ReportController reportController;
    
    private JRadioButton csvRadio;
    private JRadioButton pdfRadio;
    private JRadioButton xlsxRadio;
    private JRadioButton txtRadio;
    private JButton generateButton;
    private JButton cancelButton;
    private JTextField filePathField;
    private JButton browseButton;
    
    public ReportDialog(MainFrame parent, User user, ReportController controller) {
        super(parent, "Generate Report", true);
        this.currentUser = user;
        this.reportController = controller;
        
        initializeComponents();
        setupLayout();
        setupEventHandlers();
    }
    
    private void initializeComponents() {
        setSize(500, 400);
        setLocationRelativeTo(getParent());
        
        csvRadio = new JRadioButton("CSV Format");
        pdfRadio = new JRadioButton("PDF Format");
        xlsxRadio = new JRadioButton("Excel Format (XLSX)");
        txtRadio = new JRadioButton("Text Format");
        
        ButtonGroup formatGroup = new ButtonGroup();
        formatGroup.add(csvRadio);
        formatGroup.add(pdfRadio);
        formatGroup.add(xlsxRadio);
        formatGroup.add(txtRadio);
        csvRadio.setSelected(true); // Default to CSV
        
        filePathField = new JTextField(30);
        filePathField.setEditable(false);
        
        browseButton = new JButton("Browse");
        browseButton.setBackground(new Color(0, 123, 255));
        browseButton.setForeground(Color.WHITE);
        browseButton.setFocusPainted(false);
        
        generateButton = new JButton("Generate Report");
        generateButton.setBackground(new Color(40, 167, 69));
        generateButton.setForeground(Color.WHITE);
        generateButton.setFocusPainted(false);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBackground(new Color(108, 117, 125));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
    }
    
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Format selection
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Select Report Format:"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(csvRadio, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(pdfRadio, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(xlsxRadio, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(txtRadio, gbc);
        
        // File path selection
        gbc.gridx = 0; gbc.gridy = 5;
        mainPanel.add(new JLabel("Save to:"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        JPanel pathPanel = new JPanel(new BorderLayout());
        pathPanel.add(filePathField, BorderLayout.CENTER);
        pathPanel.add(browseButton, BorderLayout.EAST);
        mainPanel.add(pathPanel, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(generateButton);
        buttonPanel.add(cancelButton);
        
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                
                int result = fileChooser.showOpenDialog(ReportDialog.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedDirectory = fileChooser.getSelectedFile();
                    filePathField.setText(selectedDirectory.getAbsolutePath());
                }
            }
        });
        
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filePathField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(ReportDialog.this, "Please select a directory to save the report", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String format = getSelectedFormat();
                String directory = filePathField.getText().trim();
                
                try {
                    boolean success = reportController.generateReport(currentUser.getId(), format, directory);
                    if (success) {
                        JOptionPane.showMessageDialog(ReportDialog.this, "Report generated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(ReportDialog.this, "Failed to generate report", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ReportDialog.this, "Error generating report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        cancelButton.addActionListener(e -> dispose());
    }
    
    private String getSelectedFormat() {
        if (csvRadio.isSelected()) return "CSV";
        if (pdfRadio.isSelected()) return "PDF";
        if (xlsxRadio.isSelected()) return "XLSX";
        if (txtRadio.isSelected()) return "TXT";
        return "CSV"; // Default
    }
}
