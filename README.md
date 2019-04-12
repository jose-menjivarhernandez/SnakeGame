# SnakeGame
The packages containing the code you need to use in order to compile for the final project submission are under the Demo3Project branch. There is the ControllerPackage, LogicPackage, and userInterface Package. In order to compile, you will download the classes found in the respective packages. To run the console version, you will run the main found in SnakeConsoleController class found within the controller package, in order to run the GUI version of the game, using JavaFX, run the main found in the main class within the userInterface package.The JUnit test classes can be found in the LogicPackage, which you will need to ensure you have added JUnit4 as a library to this project. If running the JUnits in the console you need to put junit-4.12.jar and hamcrest-core-1.3.jar, which can be found on the D2L into the folder where the project is located,and use the following code to compile: javac -cp .:junit-4.12.jar:hamcrest-core-1.3.jar *.java and the following code to run the tests: java -cp .:junit-4.12.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore

Citations:

Professor Nathaly Verwaal, Computer Science 219, Introduction for Multidisciplinary Studies II :theories used in the implementation and creating of our console game

https://github.com/andreirichkov/console-snake/tree/master/src/application Andrei Richkov : creator of a console snake game, for which there was inspiration

Taneli Armanto: Creator of the original retro game Snake, while working for Nokia

https://www.youtube.com/watch?v=nK6l1uVlunc Almas Baimagamebetov : Lecturer in game development. Used for implementation of moving the snake
