@echo off
SET var=Testing\%1
if %1 EQU build (
    SET var=%1
) 
if %1 EQU esm (
    SET var=build-ESM
)
echo D:\%var%\AdventNet_ManageEngine_ServiceDesk_windows_64bit_unpack.zip
start /d D:\Scripts call java FileExtraction D:\%var%\AdventNet_ManageEngine_ServiceDesk_windows_64bit_unpack.zip
timeout 3
call exit