package com.expensetracker.util;

import com.expensetracker.model.Transaction;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Utility class for generating reports in different formats
 */
public class ReportGenerator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    public boolean generateReport(List<Transaction> transactions, String format, String directory) throws Exception {
        String fileName = "expense_report_" + System.currentTimeMillis();
        
        switch (format.toUpperCase()) {
            case "CSV":
                return generateCSVReport(transactions, directory, fileName + ".csv");
            case "PDF":
                return generatePDFReport(transactions, directory, fileName + ".pdf");
            case "XLSX":
                return generateXLSXReport(transactions, directory, fileName + ".xlsx");
            case "TXT":
                return generateTXTReport(transactions, directory, fileName + ".txt");
            default:
                throw new IllegalArgumentException("Unsupported format: " + format);
        }
    }
    
    private boolean generateCSVReport(List<Transaction> transactions, String directory, String fileName) {
        try (FileWriter writer = new FileWriter(new File(directory, fileName))) {
            // Write header
            writer.append("ID,Amount,Type,Category,Date,Description\n");
            
            // Write data
            for (Transaction transaction : transactions) {
                writer.append(String.valueOf(transaction.getId())).append(",");
                writer.append(String.valueOf(transaction.getAmount())).append(",");
                writer.append(transaction.getType()).append(",");
                writer.append(transaction.getCategory()).append(",");
                writer.append(transaction.getDate().format(DATE_FORMATTER)).append(",");
                writer.append(transaction.getDescription() != null ? transaction.getDescription() : "").append("\n");
            }
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean generatePDFReport(List<Transaction> transactions, String directory, String fileName) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(new File(directory, fileName)));
            document.open();
            
            // Add title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
            Paragraph title = new Paragraph("Expense Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));
            
            // Create table
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            
            // Add headers
            table.addCell("Amount");
            table.addCell("Type");
            table.addCell("Category");
            table.addCell("Date");
            table.addCell("Description");
            table.addCell("ID");
            
            // Add data
            for (Transaction transaction : transactions) {
                table.addCell(String.valueOf(transaction.getAmount()));
                table.addCell(transaction.getType());
                table.addCell(transaction.getCategory());
                table.addCell(transaction.getDate().format(DATE_FORMATTER));
                table.addCell(transaction.getDescription() != null ? transaction.getDescription() : "");
                table.addCell(String.valueOf(transaction.getId()));
            }
            
            document.add(table);
            document.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean generateXLSXReport(List<Transaction> transactions, String directory, String fileName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Expense Report");
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Amount", "Type", "Category", "Date", "Description", "ID"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }
            
            // Create data rows
            int rowNum = 1;
            for (Transaction transaction : transactions) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(transaction.getAmount());
                row.createCell(1).setCellValue(transaction.getType());
                row.createCell(2).setCellValue(transaction.getCategory());
                row.createCell(3).setCellValue(transaction.getDate().format(DATE_FORMATTER));
                row.createCell(4).setCellValue(transaction.getDescription() != null ? transaction.getDescription() : "");
                row.createCell(5).setCellValue(transaction.getId());
            }
            
            // Auto-size columns
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }
            
            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(new File(directory, fileName))) {
                workbook.write(fileOut);
            }
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private boolean generateTXTReport(List<Transaction> transactions, String directory, String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(new File(directory, fileName)))) {
            writer.println("EXPENSE REPORT");
            writer.println("==============");
            writer.println();
            
            for (Transaction transaction : transactions) {
                writer.println("Amount: $" + String.format("%.2f", transaction.getAmount()));
                writer.println("Type: " + transaction.getType());
                writer.println("Category: " + transaction.getCategory());
                writer.println("Date: " + transaction.getDate().format(DATE_FORMATTER));
                writer.println("Description: " + (transaction.getDescription() != null ? transaction.getDescription() : ""));
                writer.println("ID: " + transaction.getId());
                writer.println("----------------------------------------");
            }
            
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
