@ECHO OFF
SET var=Testing\%1
if %1 EQU build (
    SET var=%1
) 
if %1 EQU esm (
    SET var=build-ESM
)
xcopy /s /y D:\%var%\developmode D:\%var%\AdventNet\ME\ServiceDesk\webapps\ROOT

start /d D:\Scripts call java FileReplace D:\%var%\AdventNet\ME\ServiceDesk\

timeout 3
exit