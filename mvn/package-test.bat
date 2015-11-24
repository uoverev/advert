@echo off
cd ..
mvn clean package -Dmaven.test.skip=true -P test
echo [INFO] Maven Package (Test) Success.
pause