@echo off

REM
REM Convenient script to create and configure eclipse workspace
REM

call mvn eclipse:configure-workspace


rem configure xml, html, and javascript 
call mvn install -Pconfigure-workspace -N
