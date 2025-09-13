@echo off
echo Starting Expense Tracker App with Java 17...
echo.

REM Set Java path
set JAVA_PATH="C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\java.exe"
set JAVAC_PATH="C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot\bin\javac.exe"

REM Create directories
if not exist "target\classes" mkdir target\classes
if not exist "lib" mkdir lib

echo Checking for required JAR files...
echo.

REM Check if required JAR files exist
if not exist "lib\sqlite-jdbc-3.44.1.0.jar" (
    echo Downloading SQLite JDBC driver...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.44.1.0/sqlite-jdbc-3.44.1.0.jar' -OutFile 'lib\sqlite-jdbc-3.44.1.0.jar'"
)

if not exist "lib\itextpdf-5.5.13.3.jar" (
    echo Downloading iText PDF library...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/itextpdf/itextpdf/5.5.13.3/itextpdf-5.5.13.3.jar' -OutFile 'lib\itextpdf-5.5.13.3.jar'"
)

if not exist "lib\poi-ooxml-5.2.4.jar" (
    echo Downloading Apache POI library...
    powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/apache/poi/poi-ooxml/5.2.4/poi-ooxml-5.2.4.jar' -OutFile 'lib\poi-ooxml-5.2.4.jar'"
)

echo.
echo Compiling Java source files...
%JAVAC_PATH% -cp "lib\*" -d target\classes -encoding UTF-8 src\main\java\com\expensetracker\ExpenseTrackerApp.java src\main\java\com\expensetracker\controller\*.java src\main\java\com\expensetracker\database\*.java src\main\java\com\expensetracker\model\*.java src\main\java\com\expensetracker\util\*.java src\main\java\com\expensetracker\view\*.java

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
%JAVA_PATH% -cp "target\classes;lib\*" com.expensetracker.ExpenseTrackerApp

if %errorlevel% neq 0 (
    echo.
    echo Error occurred while running the application
    pause
)
