
# Deployment 


# how to go to production


Thank you for clarifying. I'll help you create a complete deployment solution. Let's break this down into steps:

1. First, let's build the Spring Boot JAR file. Add this to your `pom.xml` if not already present:

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <executable>true</executable>
            </configuration>
        </plugin>
    </plugins>
</build>
```

Then run this in your backend project directory:
```bash
./mvnw clean package
```
This will create a JAR file in the `target` folder.

2. For the Next.js frontend, you'll need to build it for production:
```bash
npm run build
```

3. Let me create a Windows batch file that will:
- Start the Spring Boot backend
- Start the Next.js frontend in production mode
- Open the browser
- Provide an easy way to stop both servers



```sh
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
start /B java -jar backend.jar > backend.log 2>&1
timeout /t 5

:: Start Next.js frontend
echo Starting Next.js frontend...
start /B npm run start > frontend.log 2>&1
timeout /t 5

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

```

Here's how to prepare everything for your client:

1. Create a deployment folder with this structure:
```
deployment/
├── backend.jar        # Your Spring Boot JAR
├── frontend/          # Next.js built files
│   ├── .next/
│   ├── node_modules/
│   ├── package.json
│   └── ...
├── start-servers.bat  # The batch script above
├── backend.log        # Will be created when running
└── frontend.log      # Will be created when running
```

2. Steps to prepare the deployment:
   ```bash
   # In your backend directory
	# change the java version 
   export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
   ./mvnw clean package
   cp target/your-app-name.jar ../deployment/backend.jar

   # In your frontend directory
   npm run build
   cp -r .next package.json package-lock.json node_modules ../deployment/frontend/
   ```

3. Copy the batch script I provided into `start-servers.bat`

key points
1. database password should be localhost
2. java 21 should be the system version
3. node should be installed.
4. mysql should be installed.
5. printer initial configs
6.


Instructions for your client:
1. Install prerequisites:
    - Java 21 (JRE is sufficient)
    - Node.js 20.18.0
    - MySQL (as you mentioned it's required)
2. Double-click `start-servers.bat`
3. Choose option 1 to start servers
4. Choose option 2 to stop servers

The script will:
- Show a menu for starting/stopping servers
- Log all output to separate files for troubleshooting
- Automatically open the browser when servers start
- Properly handle process management

Would you like me to:
1. Add any additional features to the batch script?
2. Provide a setup script for installing prerequisites?
3. Add more detailed error handling or logging?



