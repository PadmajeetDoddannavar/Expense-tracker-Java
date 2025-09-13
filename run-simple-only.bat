@echo off
echo Starting Simple Expense Tracker App...
echo.

REM Set Java path
set JAVA_PATH="C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\java.exe"
set JAVAC_PATH="C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\javac.exe"

REM Create directories
if not exist "target\classes" mkdir target\classes

echo Compiling Simple Expense Tracker...
%JAVAC_PATH% -d target\classes -encoding UTF-8 src\main\java\com\expensetracker\SimpleExpenseTrackerApp.java

if %errorlevel% neq 0 (
    echo.
    echo Compilation failed! Please check the error messages above.
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Starting Simple Expense Tracker App...
echo.

REM Run the application
%JAVA_PATH% -cp target\classes com.expensetracker.SimpleExpenseTrackerApp

if %errorlevel% neq 0 (
    echo.
    echo Error occurred while running the application
    pause
)
