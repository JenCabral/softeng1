@echo off

call SetClasspath

REM The following lines will restore the original database from the original script
REM The sample project doesn't require this, but yours might

REM cd database
REM call RestoreDB.bat
REM cd ..

REM To get console-mode results from unit test execution, use the following instead:

REM java junit.textui.TestRunner comp3350.tests.RunIntegrationTests

@echo on

java junit.swingui.TestRunner comp3350.tests.RunIntegrationTests
