@echo off
echo ========================================
echo    Push Expense Tracker to GitHub
echo ========================================
echo.

REM Check if git is installed
git --version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Git is not installed or not in PATH
    echo Please install Git from: https://git-scm.com/downloads
    pause
    exit /b 1
)

echo Git found! Initializing repository...
echo.

REM Initialize git if not already done
if not exist ".git" (
    echo Initializing git repository...
    git init
    echo.
)

REM Add all files
echo Adding files to git...
git add .

REM Check if there are changes to commit
git diff --cached --quiet
if %errorlevel% equ 0 (
    echo No changes to commit.
    echo.
) else (
    echo Committing changes...
    git commit -m "Update Expense Tracker with Indian Rupees support and enhanced UI"
    echo.
)

echo ========================================
echo    GitHub Setup Instructions
echo ========================================
echo.
echo 1. Go to https://github.com
echo 2. Click "New Repository"
echo 3. Name: expense-tracker-app
echo 4. Description: "Beautiful Java desktop app for expense tracking with Indian Rupees"
echo 5. Make it Public
echo 6. Don't initialize with README
echo 7. Click "Create Repository"
echo.
echo 8. Copy the repository URL (e.g., https://github.com/YOUR_USERNAME/expense-tracker-app.git)
echo 9. Run these commands:
echo.
echo    git branch -M main
echo    git remote add origin YOUR_REPOSITORY_URL
echo    git push -u origin main
echo.
echo ========================================
echo    Current Repository Status
echo ========================================
echo.

REM Show current status
git status

echo.
echo Ready to push to GitHub!
echo Follow the instructions above to complete the setup.
echo.
pause
