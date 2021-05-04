## Project Structure
* The `javafx-sdk-11.0.2/` folder holds the JavaFX library we use
* The `src/` folder holds our source code
* The `tests/` folder contains all our tests
* The `lib/` folder contains the other libraries we used
* The `scripts/` folder contains all the scripts we use to build, run, and test the application on Windows and Linux
* The `presentation/` folder contains the slides used in the presentation

## How to Use
To build the application, open a terminal window and `cd` into the `scripts/` folder. From there, run the applicable script for your OS (`WinBuild.bat` on Windows and `LinBuild.sh` on linux). NOTE: On linux, you will need to replace the `javafx-sdk-11.0.2` folder with the release for linux here: https://gluonhq.com/download/javafx-11-0-2-sdk-linux/. 
To run the application client, run either `WinRunClient.bat` or `LinRunClient.sh` according to your OS.
To run the unit tests, run either `WinTest.bat` or `LinTest.sh`