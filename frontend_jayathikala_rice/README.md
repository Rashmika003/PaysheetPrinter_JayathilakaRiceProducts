
# example bash script for run both servers

@echo off
setlocal

:: Set paths
set SPRING_BOOT_JAR=path\to\your\springboot-app.jar
set NEXTJS_DIR=path\to\your\nextjs\project

:: Start Spring Boot
start "Spring Boot App" java -jar %SPRING_BOOT_JAR%

:: Wait for a moment to ensure Spring Boot has started
timeout /t 10

:: Navigate to Next.js directory
cd /d %NEXTJS_DIR%

:: Start Next.js
start "Next.js App" cmd /c npm run dev

echo Both applications have been started!
echo Press any key to stop both applications...
pause > nul

:: Kill both processes
taskkill /FI "WINDOWTITLE eq Spring Boot App*" /F
taskkill /FI "WINDOWTITLE eq Next.js App*" /F

echo Applications have been stopped.
pause