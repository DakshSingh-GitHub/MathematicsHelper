cd .\bin\
jar cf mathematics.jar .
Move-Item -Path "mathematics.jar" -Destination "../" -Force
cd ../
