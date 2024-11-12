@echo off
title Jayathilaka Rice Products Server Installer...
color 0A

echo Installing required libraries. this will take almost 500mb.

cd frontend_jayathikala_rice
npm install

echo Building the Server... 
npm run build 

echo Installed. You can close the window..

timeout /t 5
exit