@echo off
title Jayathilaka Rice Products Server Installer
color 0A

echo Installing required libraries for the frontend...
cd frontend_jayathikala_rice
call npm install
if %errorlevel% neq 0 goto error

echo Building the frontend server...
call npm run build
if %errorlevel% neq 0 goto error

echo Installation and build completed successfully!
echo You can now close this window.
timeout /t 5 > nul
exit

:error
echo An error occurred during the installation or build process.
echo Please check the log files for more information.
pause
exit