@ECHO OFF
SET var=Testing\%1
if %1 EQU build (
    SET var=%1
) 
if %1 EQU esm (
    SET var=build-ESM
)
echo D:\%var%\AdventNet\ME\ServiceDesk\logs
call code D:\%var%\AdventNet\ME\ServiceDesk\logs
timeout 3
call exit