@echo off
title Jayathilaka Rice Products Server Controller
color 0A

:: Create a file to store the process IDs
if not exist "pids.txt" type nul > pids.txt

:MENU
cls
echo ===============================
echo   Server Controller Menu
echo ===============================
echo 1. Start Servers
echo 2. Stop Servers
echo 3. Exit
echo.
set /p choice="Enter your choice (1-3): "

if "%choice%"=="1" goto START
if "%choice%"=="2" goto STOP
if "%choice%"=="3" goto END
goto MENU

:START
echo Starting servers...

:: Start Spring Boot backend
echo Starting Spring Boot backend...
start /B java -jar JayathilakaPosBackedWithPrinter-0.0.1-SNAPSHOT.jar > backend.log 2>&1
timeout /t 5

:: Start Next.js frontend
echo Starting Next.js frontend...
cd frontend_jayathikala_rice
start /B npm run start > frontend.log 2>&1
timeout /t 5
cd ..

:: Open browser
echo Opening browser...
start http://localhost:3000

echo.
echo Servers are running!
echo - Backend: http://localhost:8085
echo - Frontend: http://localhost:3000
echo.
echo Check backend.log and frontend.log for any errors.
echo.
pause
goto MENU

:STOP
echo Stopping servers...

:: Kill Java process (Spring Boot)
for /f "tokens=2" %%a in ('tasklist ^| findstr "java.exe"') do (
    taskkill /PID %%a /F
)

:: Kill Node process (Next.js)
for /f "tokens=2" %%a in ('tasklist ^| findstr "node.exe"') do (
    taskkill /PID %%a /F
)

echo Servers stopped successfully!
pause
goto MENU

:END
echo Goodbye!
timeout /t 2
exit
