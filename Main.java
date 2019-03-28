package userInterface;
import controller.Driver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import logic.*;
import controller.*;
public class Main extends Application{
	MainMenu2 mainmenu = new MainMenu2();
	GameOverAlertBox boxy = new GameOverAlertBox();
	Driver newDriver = new Driver();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage = mainmenu.displayMainMenu();
		primaryStage.show();
	}
	public static void main (String [] args) {
		launch(args);
	}	
}