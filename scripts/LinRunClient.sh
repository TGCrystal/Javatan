cd ../
set PATH_TO_FX="javafx-sdk-11.0.2/lib"
java --module-path %PATH_TO_FX% --add-modules javafx.controls -cp src:. edu.rpi.cs.csci4960.s21.javatan.GUI
cd scripts