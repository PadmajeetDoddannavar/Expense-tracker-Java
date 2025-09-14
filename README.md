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

