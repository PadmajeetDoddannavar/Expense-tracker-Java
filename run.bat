@echo off
echo Starting Expense Tracker App...
echo.

REM Check if Maven is installed
mvn --version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Maven is not installed or not in PATH
    echo Please install Maven and try again
    pause
    exit /b 1
)

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 11 or higher and try again
    pause
    exit /b 1
)

echo Building and running the application...
echo.

REM Clean, compile, and run
mvn clean compile exec:java -Dexec.mainClass="com.expensetracker.ExpenseTrackerApp"

if %errorlevel% neq 0 (
    echo.
    echo Error occurred while running the application
    pause
)
