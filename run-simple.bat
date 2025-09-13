@echo off
echo Starting Expense Tracker App (Simple Version)...
echo.

REM Set Java path
set JAVA_PATH="C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\java.exe"
set JAVAC_PATH="C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\javac.exe"

REM Create directories
if not exist "target\classes" mkdir target\classes

echo Compiling Java source files...
%JAVAC_PATH% -d target\classes -encoding UTF-8 src\main\java\com\expensetracker\ExpenseTrackerApp.java src\main\java\com\expensetracker\controller\*.java src\main\java\com\expensetracker\database\*.java src\main\java\com\expensetracker\model\*.java src\main\java\com\expensetracker\util\*.java src\main\java\com\expensetracker\view\*.java

if %errorlevel% neq 0 (
    echo.
    echo Compilation failed! Please check the error messages above.
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Starting Expense Tracker App...
echo.

REM Run the application
%JAVA_PATH% -cp target\classes com.expensetracker.ExpenseTrackerApp

if %errorlevel% neq 0 (
    echo.
    echo Error occurred while running the application
    pause
)
