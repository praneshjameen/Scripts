@ECHO OFF
SET portno=%1
if [%1] == [] ( SET portno=65432)
echo portno : %portno%
start /d D:\build\AdventNet\ME\ServiceDesk\pgsql\bin call psql.exe -h 127.0.0.1 -U postgres -p %portno% -d servicedesk
timeout 3
call exit