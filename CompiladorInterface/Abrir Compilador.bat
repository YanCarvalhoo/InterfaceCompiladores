@echo off
cd /d "%~dp0"

where javaw >nul 2>nul
if %errorlevel%==0 (
    start "" javaw -jar CompiladorInterface.jar
    exit /b
)

where java >nul 2>nul
if %errorlevel%==0 (
    java -jar CompiladorInterface.jar
    exit /b
)

echo ============================================================
echo  O Java nao foi encontrado neste computador.
echo.
echo  Instale o Java (JDK 17 ou mais novo) e tente de novo:
echo  https://adoptium.net
echo.
echo  Na instalacao, deixe marcada a opcao "Add to PATH".
echo ============================================================
pause
