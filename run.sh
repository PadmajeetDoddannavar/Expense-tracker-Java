#!/bin/bash

echo "Starting Expense Tracker App..."
echo

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Error: Maven is not installed or not in PATH"
    echo "Please install Maven and try again"
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed or not in PATH"
    echo "Please install Java 11 or higher and try again"
    exit 1
fi

echo "Building and running the application..."
echo

# Clean, compile, and run
mvn clean compile exec:java -Dexec.mainClass="com.expensetracker.ExpenseTrackerApp"

if [ $? -ne 0 ]; then
    echo
    echo "Error occurred while running the application"
    read -p "Press any key to continue..."
fi
