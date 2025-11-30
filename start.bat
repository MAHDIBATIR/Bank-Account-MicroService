@echo off
echo Demarrage du Micro-service...
cd /d "%~dp0"
java -jar target\Micro-service-0.0.1-SNAPSHOT.jar
pause

