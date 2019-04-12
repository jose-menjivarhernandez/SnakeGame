package userInterface;


/**
 * The class Main primarily serves as a starter and runner for the GUI and 
 * uses the method displayMainMenu of an instance of Main Menu to run everything 
 * within the GUI
 * 
 */
import javafx.application.Application;
import javafx.stage.Stage;
public class Main extends Application{
	MainMenu2 mainmenu = new MainMenu2();
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage = mainmenu.displayMainMenu();
		primaryStage.show();
	}
	public static void main (String [] args) {
		launch(args);
	}	
}
