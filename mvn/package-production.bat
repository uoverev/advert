@echo off
cd ..
mvn clean package -Dmaven.test.skip=true -P prod
echo [INFO] Maven Package (Production) Success.
pause