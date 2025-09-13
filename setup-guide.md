# Expense Tracker App - Setup Guide

## Prerequisites Installation

### 1. Install Java Development Kit (JDK) 11 or higher

**For Windows:**
1. Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
2. Run the installer and follow the instructions
3. Add Java to your system PATH:
   - Open System Properties → Advanced → Environment Variables
   - Add `JAVA_HOME` variable pointing to your JDK installation
   - Add `%JAVA_HOME%\bin` to your PATH variable

**For macOS:**
```bash
# Using Homebrew
brew install openjdk@11

# Or download from Oracle/OpenJDK website
```

**For Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-11-jdk
```

### 2. Install Maven (Optional but Recommended)

**For Windows:**
1. Download Maven from [Apache Maven](https://maven.apache.org/download.cgi)
2. Extract to a folder (e.g., `C:\Program Files\Apache\maven`)
3. Add Maven to your system PATH:
   - Add `MAVEN_HOME` variable pointing to your Maven installation
   - Add `%MAVEN_HOME%\bin` to your PATH variable

**For macOS:**
```bash
# Using Homebrew
brew install maven
```

**For Linux (Ubuntu/Debian):**
```bash
sudo apt install maven
```

## Running the Application

### Method 1: Using Maven (Recommended)

If you have Maven installed:

```bash
# Navigate to the project directory
cd "C:\Users\Appu Raj\Desktop\Expense Tracker"

# Clean and compile
mvn clean compile

# Run the application
mvn exec:java -Dexec.mainClass="com.expensetracker.ExpenseTrackerApp"
```

### Method 2: Using Java directly

If you don't have Maven, you can run the application directly:

1. **Download required JAR files:**
   - SQLite JDBC: `sqlite-jdbc-3.44.1.0.jar`
   - iText PDF: `itextpdf-5.5.13.3.jar`
   - Apache POI: `poi-ooxml-5.2.4.jar` and its dependencies

2. **Compile the Java files:**
   ```bash
   javac -cp "path/to/sqlite-jdbc.jar;path/to/itextpdf.jar;path/to/poi-ooxml.jar" -d target/classes src/main/java/com/expensetracker/**/*.java
   ```

3. **Run the application:**
   ```bash
   java -cp "target/classes;path/to/sqlite-jdbc.jar;path/to/itextpdf.jar;path/to/poi-ooxml.jar" com.expensetracker.ExpenseTrackerApp
   ```

### Method 3: Using the provided batch file

**For Windows:**
```bash
# Double-click run.bat or run from command prompt
run.bat
```

**For Linux/macOS:**
```bash
# Make executable and run
chmod +x run.sh
./run.sh
```

## Troubleshooting

### Common Issues:

1. **"mvn is not recognized"**
   - Maven is not installed or not in PATH
   - Install Maven following the instructions above

2. **"java is not recognized"**
   - Java is not installed or not in PATH
   - Install JDK 11+ and add to PATH

3. **ClassNotFoundException**
   - Required JAR files are missing
   - Download dependencies manually or use Maven

4. **Database connection error**
   - Check file permissions in the application directory
   - Ensure SQLite JDBC driver is available

5. **Report generation fails**
   - Verify write permissions for the selected directory
   - Ensure all report generation dependencies are available

## Project Structure

```
Expense Tracker/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── expensetracker/
│                   ├── ExpenseTrackerApp.java
│                   ├── controller/
│                   ├── database/
│                   ├── model/
│                   ├── util/
│                   └── view/
├── pom.xml
├── README.md
├── run.bat (Windows)
├── run.sh (Linux/macOS)
└── setup-guide.md
```

## Features Overview

- ✅ User authentication (login/register)
- ✅ Add/Edit/Delete transactions
- ✅ Income and expense tracking
- ✅ Category management
- ✅ Balance calculation
- ✅ Report generation (CSV, PDF, XLSX, TXT)
- ✅ Modern GUI with Java Swing
- ✅ SQLite database
- ✅ MVC architecture

## Support

If you encounter any issues:
1. Check this setup guide
2. Verify all prerequisites are installed
3. Check the console output for error messages
4. Ensure all dependencies are available
