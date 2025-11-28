:'
# --- Inicio de la parte Unix/Linux (.sh) ---
echo "Sistema operativo detectado: Unix/Linux"
echo "Abriendo Telnet en localhost:5000..."
telnet localhost 5000
exit
# --- Fin de la parte Unix/Linux (.sh) ---
'

:: --- Inicio de la parte Windows (.bat) ---
@echo off
rem Detectar si estamos en Windows
ver >nul 2>&1
if %errorlevel%==0 (
    echo Sistema operativo detectado: Windows
    echo Abriendo Telnet en localhost:5000...
    start cmd /k "telnet localhost 5000"
    exit /b
)
:: --- Fin de la parte Windows (.bat) ---
