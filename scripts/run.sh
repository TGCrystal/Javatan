#!/bin/bash

cd ..
cd src
export PATH_TO_FX=../javafx-sdk-11.0.2/lib
javadoc --module-path $PATH_TO_FX --add-modules javafx.controls -author -version -d ../docs *.java
javac --module-path $PATH_TO_FX --add-modules javafx.controls -d . *.java
java app.UnitTests
java --module-path $PATH_TO_FX --add-modules javafx.controls app.Simulation
cd ..
cd scripts

