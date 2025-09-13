# 💰 Expense Tracker App

A beautiful Java desktop application for tracking personal expenses and income with **Indian Rupees (₹)** support and modern GUI built using Java Swing.

![Java](https://img.shields.io/badge/Java-11+-orange)
![Swing](https://img.shields.io/badge/GUI-Java%20Swing-blue)
![Currency](https://img.shields.io/badge/Currency-Indian%20Rupees-green)
![License](https://img.shields.io/badge/License-MIT-yellow)

## ✨ Features

### 🎨 **Beautiful UI**
- **Modern Design**: Clean, professional interface with beautiful color scheme
- **Multiple Versions**: Choose from Basic, Modern, or Ultra Modern UI
- **Responsive Layout**: Adapts to different window sizes
- **Interactive Elements**: Hover effects and smooth animations

### 💰 **Financial Management**
- **Indian Rupees Support**: All amounts displayed in ₹
- **Real-time Balance**: Instant balance calculation
- **Income & Expense Tracking**: Separate tracking with color coding
- **Transaction Categories**: Food, Rent, Salary, Bonus, and more
- **Date Picker**: Easy date selection for transactions

### 📊 **Transaction Management**
- **Add Transactions**: Simple form with validation
- **Edit/Delete**: Full CRUD operations
- **Transaction History**: Beautiful card-based display
- **Search & Filter**: Easy transaction management

### 📈 **Reports & Analytics**
- **Multiple Formats**: CSV, PDF, XLSX, TXT export
- **Visual Statistics**: Color-coded income vs expenses
- **Transaction Count**: Track total transactions

## 🚀 Quick Start

### Prerequisites
- **Java 11 or higher** ([Download here](https://adoptium.net/))
- **Windows, macOS, or Linux**

### Installation & Run

#### Method 1: Download & Run (Easiest)
```bash
# 1. Download this repository
git clone https://github.com/YOUR_USERNAME/expense-tracker-app.git

# 2. Navigate to project folder
cd expense-tracker-app

# 3. Run the beautiful version
.\run-beautiful.bat
```

#### Method 2: Using Maven
```bash
# Clone and run
git clone https://github.com/YOUR_USERNAME/expense-tracker-app.git
cd expense-tracker-app
mvn clean compile exec:java -Dexec.mainClass="com.expensetracker.view.BeautifulExpenseTracker"
```

## 🎯 Available Versions

| Version | Description | Run Command |
|---------|-------------|-------------|
| **Beautiful** | Modern UI with enhanced styling | `.\run-beautiful.bat` |
| **Working** | Basic functional version | `.\run-working.bat` |
| **Ultra Modern** | Professional tabbed interface | `.\run-ultra-modern.bat` |
| **All Versions** | Choose which version to run | `.\run-all-versions.bat` |

## 📱 Screenshots

### Beautiful UI Version
- **Dashboard**: Real-time balance and statistics
- **Add Transaction**: Clean form with validation
- **Transaction History**: Card-based transaction display

### Features in Action
- 💰 **Balance Tracking**: ₹0.00 → ₹1,25,000.00
- 📈 **Income Management**: Salary, Bonus, Freelance
- 📉 **Expense Tracking**: Food, Rent, Transportation
- 🎨 **Beautiful Interface**: Modern, user-friendly design

## 🛠️ Technical Details

### Architecture
- **MVC Pattern**: Clean separation of concerns
- **Java Swing**: Modern GUI framework
- **SQLite Database**: Local data storage
- **JDBC**: Database connectivity

### Project Structure
```
src/main/java/com/expensetracker/
├── view/                    # GUI Components
│   ├── BeautifulExpenseTracker.java
│   ├── WorkingExpenseTracker.java
│   └── UltraModernExpenseTracker.java
├── model/                   # Data Models
├── controller/              # Business Logic
├── database/               # Database Layer
└── util/                   # Utilities
```

### Dependencies
- **SQLite JDBC**: Database connectivity
- **iText**: PDF report generation
- **Apache POI**: Excel report generation

## 🌍 Internationalization

Currently supports:
- **Indian Rupees (₹)** - Primary currency
- **English** - Interface language

Planned:
- Multiple currency support
- Localization for different regions

## 🤝 Contributing

We welcome contributions! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/amazing-feature`
3. **Commit** your changes: `git commit -m 'Add amazing feature'`
4. **Push** to the branch: `git push origin feature/amazing-feature`
5. **Open** a Pull Request

### Development Setup
```bash
# Clone repository
git clone https://github.com/YOUR_USERNAME/expense-tracker-app.git

# Install dependencies
mvn clean install

# Run in development mode
mvn exec:java -Dexec.mainClass="com.expensetracker.view.BeautifulExpenseTracker"
```

## 📋 Roadmap

### Version 1.1
- [ ] Multiple currency support
- [ ] Data export improvements
- [ ] Theme customization
- [ ] Keyboard shortcuts

### Version 1.2
- [ ] Mobile app version
- [ ] Cloud sync
- [ ] Advanced reporting
- [ ] Budget management

### Version 2.0
- [ ] Web application
- [ ] User authentication
- [ ] Multi-user support
- [ ] API integration

## 🐛 Troubleshooting

### Common Issues

#### "Java not found" Error
```bash
# Install Java 11+ from https://adoptium.net/
# Verify installation: java -version
```

#### "Maven not found" Error
```bash
# Use the provided batch files (no Maven needed)
# Or install Maven from https://maven.apache.org/
```

#### App won't start
1. Check Java version: `java -version`
2. Ensure Java 11+ is installed
3. Try running: `java -cp target/classes com.expensetracker.view.BeautifulExpenseTracker`

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@yourusername](https://github.com/yourusername)
- Email: your.email@example.com
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/yourprofile)

## 🙏 Acknowledgments

- Java Swing community for excellent documentation
- Open source libraries (SQLite, iText, Apache POI)
- Contributors and users who provide feedback

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/YOUR_USERNAME/expense-tracker-app/issues)
- **Discussions**: [GitHub Discussions](https://github.com/YOUR_USERNAME/expense-tracker-app/discussions)
- **Email**: your.email@example.com

---

**Made with ❤️ in India 🇮🇳**

*Track your finances beautifully with Indian Rupees support!*

## Features

### 🔐 Authentication System
- User registration and login
- Secure password storage
- Session management

### 💰 Transaction Management
- Add income and expense transactions
- Categorize transactions (Food, Rent, Salary, etc.)
- Edit and delete existing transactions
- Date picker for transaction dates
- Optional descriptions for transactions

### 📊 Dashboard
- Real-time balance calculation
- Total income and expense summaries
- Transaction history with card-based layout
- User-friendly interface with emojis

### 📈 Report Generation
- Export transactions to multiple formats:
  - CSV (Comma Separated Values)
  - PDF (Portable Document Format)
  - XLSX (Excel format)
  - TXT (Plain text)
- Choose save location for reports

### 🏗️ Architecture
- **MVC Pattern**: Clean separation of concerns
- **Database**: SQLite for local data storage
- **GUI**: Java Swing with modern styling
- **Reports**: iText for PDF, Apache POI for Excel

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Installation & Setup

1. **Clone or download the project**
   ```bash
   git clone <repository-url>
   cd expense-tracker
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn exec:java -Dexec.mainClass="com.expensetracker.ExpenseTrackerApp"
   ```

   Or build and run the JAR:
   ```bash
   mvn clean package
   java -jar target/expense-tracker-1.0.0.jar
   ```

## Database Schema

The application uses SQLite with the following tables:

### Users Table
```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);
```

### Transactions Table
```sql
CREATE TABLE transactions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id INTEGER NOT NULL,
    amount REAL NOT NULL,
    type TEXT NOT NULL,
    category TEXT NOT NULL,
    date TEXT NOT NULL,
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
```

## Project Structure

```
src/
├── main/
│   └── java/
│       └── com/
│           └── expensetracker/
│               ├── ExpenseTrackerApp.java          # Main application entry point
│               ├── controller/                     # Controllers (MVC)
│               │   ├── AuthController.java
│               │   ├── TransactionController.java
│               │   └── ReportController.java
│               ├── database/                       # Database layer
│               │   └── DatabaseManager.java
│               ├── model/                          # Data models
│               │   ├── User.java
│               │   └── Transaction.java
│               ├── util/                           # Utility classes
│               │   └── ReportGenerator.java
│               └── view/                           # GUI components
│                   ├── LoginFrame.java
│                   ├── MainFrame.java
│                   ├── AddTransactionDialog.java
│                   ├── EditTransactionDialog.java
│                   └── ReportDialog.java
```

## Usage

### Getting Started
1. Launch the application
2. Register a new account or login with existing credentials
3. Start adding your income and expense transactions

### Adding Transactions
1. Click "Add Transaction" button
2. Fill in the amount, select type (Income/Expense)
3. Choose appropriate category
4. Set the date (defaults to today)
5. Add optional description
6. Click Save

### Managing Transactions
- View all transactions in the main dashboard
- Click "Edit" to modify existing transactions
- Click "Delete" to remove transactions
- Balance updates automatically

### Generating Reports
1. Click "Generate Report" button
2. Select desired format (CSV, PDF, XLSX, TXT)
3. Choose save directory
4. Click "Generate Report"

## Dependencies

- **SQLite JDBC**: Database connectivity
- **iText**: PDF report generation
- **Apache POI**: Excel report generation
- **Java Swing**: GUI framework

## Error Handling

The application includes comprehensive error handling for:
- Invalid user input (negative amounts, empty fields)
- Database connection issues
- File I/O errors during report generation
- User authentication failures

## Security Notes

- Passwords are stored in plain text (for simplicity)
- In production, implement proper password hashing
- Database file is stored locally in the application directory

## Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure SQLite JDBC driver is in classpath
   - Check file permissions for database creation

2. **Report Generation Fails**
   - Verify write permissions for selected directory
   - Ensure all required dependencies are installed

3. **GUI Not Displaying Properly**
   - Check Java version compatibility
   - Try different look and feel settings

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## License

This project is open source and available under the MIT License.

## Support

For issues and questions, please create an issue in the repository or contact the development team.
