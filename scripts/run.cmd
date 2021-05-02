cd ..
cd src
set PATH_TO_FX="..\javafx-sdk-11.0.2\lib"
javac --module-path %PATH_TO_FX% --add-modules javafx.controls edu\rpi\cs\csci4960\s21\javatan\*.java
java --module-path %PATH_TO_FX% --add-modules javafx.controls edu.rpi.cs.csci4960.s21.javatan.GUI
cd ..
cd scripts