@echo off
echo ========================================
echo    Expense Tracker - UI Showcase
echo ========================================
echo.
echo Choose which version to run:
echo.
echo 1. Working Version (Basic)
echo 2. Modern Version (Enhanced UI)
echo 3. Ultra Modern Version (Professional)
echo 4. Run All Versions
echo.
set /p choice="Enter your choice (1-4): "

if "%choice%"=="1" (
    echo.
    echo Starting Working Version...
    call run-working.bat
) else if "%choice%"=="2" (
    echo.
    echo Starting Modern Version...
    call run-modern.bat
) else if "%choice%"=="3" (
    echo.
    echo Starting Ultra Modern Version...
    call run-ultra-modern.bat
) else if "%choice%"=="4" (
    echo.
    echo Starting all versions...
    echo.
    echo 1. Working Version:
    start "Working Version" cmd /c run-working.bat
    timeout /t 3 /nobreak >nul
    echo.
    echo 2. Modern Version:
    start "Modern Version" cmd /c run-modern.bat
    timeout /t 3 /nobreak >nul
    echo.
    echo 3. Ultra Modern Version:
    start "Ultra Modern Version" cmd /c run-ultra-modern.bat
) else (
    echo Invalid choice. Please run the script again.
    pause
)
