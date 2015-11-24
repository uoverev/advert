@echo off
cd ..
mvn clean package -Dmaven.test.skip=true -P dev
echo [INFO] Maven Package (Development) Success.
pause