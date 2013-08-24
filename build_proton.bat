@echo off
echo >>>>>>>> Proton v0.1.0 <<<<<<<<
echo >>>>>>>> Build started <<<<<<<<
xcopy ..\..\forge\mcp\src ..\..\forge\mcp\src-backup /E /I /Q
echo Minecraft source backed up.
xcopy source ..\..\forge\mcp\src\minecraft /E /Q
xcopy ..\novamenu\source ..\..\forge\mcp\src\minecraft /E /Q
echo Copied Proton source.
cd ..\..\forge\mcp
call recompile.bat < nul
call reobfuscate_srg.bat < nul
echo Compile phase complete.
cd ..\..\projects\proton
xcopy resources ..\..\forge\mcp\reobf\minecraft /E /Q
cd ..\novamenu
xcopy resources\assets ..\..\forge\mcp\reobf\minecraft\assets /E /Q
echo Copied Proton resources.
cd ..\..\forge\mcp
rmdir /S /Q src
xcopy src-backup src /E /I /Q
rmdir /S /Q src-backup
cd reobf\minecraft
jar cmf META-INF\MANIFEST.MF Proton0.1.0.jar com assets mcmod.info remap.csv proton_at.cfg
echo >>>>>>> Build complete. Outputted to mcp\reobf\minecraft\Proton0.1.0.jar <<<<<<<
pause