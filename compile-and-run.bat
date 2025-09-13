@echo off
echo Expense Tracker App - Compilation and Run Script
echo ================================================
echo.

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 11 or higher and try again
    echo.
    echo Download Java from: https://adoptium.net/
    pause
    exit /b 1
)

echo Java found! Checking version...
java -version
echo.

REM Create directories
if not exist "target\classes" mkdir target\classes
if not exist "lib" mkdir lib

echo.
echo IMPORTANT: This script requires you to download the following JAR files:
echo 1. SQLite JDBC Driver: sqlite-jdbc-3.44.1.0.jar
echo 2. iText PDF Library: itextpdf-5.5.13.3.jar  
echo 3. Apache POI Library: poi-ooxml-5.2.4.jar
echo.
echo Please download these files and place them in the 'lib' folder.
echo.
echo Download links:
echo - SQLite: https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc/3.44.1.0
echo - iText: https://mvnrepository.com/artifact/com.itextpdf/itextpdf/5.5.13.3
echo - POI: https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml/5.2.4
echo.

REM Check if required JAR files exist
if not exist "lib\sqlite-jdbc-3.44.1.0.jar" (
    echo Error: sqlite-jdbc-3.44.1.0.jar not found in lib folder
    echo Please download and place it in the lib folder
    pause
    exit /b 1
)

if not exist "lib\itextpdf-5.5.13.3.jar" (
    echo Error: itextpdf-5.5.13.3.jar not found in lib folder
    echo Please download and place it in the lib folder
    pause
    exit /b 1
)

if not exist "lib\poi-ooxml-5.2.4.jar" (
    echo Error: poi-ooxml-5.2.4.jar not found in lib folder
    echo Please download and place it in the lib folder
    pause
    exit /b 1
)

echo All required JAR files found!
echo.

REM Compile Java files
echo Compiling Java source files...
javac -cp "lib\*" -d target\classes src\main\java\com\expensetracker\**\*.java

if %errorlevel% neq 0 (
    echo.
    echo Compilation failed! Please check the error messages above.
    pause
    exit /b 1
)

echo Compilation successful!
echo.

REM Run the application
echo Starting Expense Tracker App...
echo.
java -cp "target\classes;lib\*" com.expensetracker.ExpenseTrackerApp

if %errorlevel% neq 0 (
    echo.
    echo Error occurred while running the application
    pause
)
