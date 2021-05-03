cd ..
export PATH_TO_FX=javafx-sdk-11.0.2/lib
javac --module-path $PATH_TO_FX --add-modules javafx.controls -encoding UTF-8 src/edu/rpi/cs/csci4960/s21/javatan/*.java
javac --module-path $PATH_TO_FX --add-modules javafx.controls -encoding UTF-8 -cp lib/junit-4.13.jar:src:. tests/edu/rpi/cs/csci4960/s21/javatan/*.java
javadoc --module-path $PATH_TO_FX --add-modules javafx.controls edu.rpi.cs.csci4960.s21.javatan -encoding UTF-8 -cp src:. -d docs
cd scripts