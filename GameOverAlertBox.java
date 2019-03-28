package userInterface;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import logic.*;
import controller.*;

/**
 * 
 * The purpose of this class is to create an Alert Box the first time an individual has lost
 * in order to know what actions to be taken by the game, whether to try another game mode or
 * to quit the game 
 *
 */
public class GameOverAlertBox extends MainMenu2 {
	
	static Stage window = new Stage();
	
	/**
	 * 
	 * The method displayEndGame simply displays the Stage in which a final score for the 
	 * loser player is displayed and and options for next steps are presented
	 */
	
	public static Stage displayEndGame () {

		window.initModality(Modality.APPLICATION_MODAL);
		MainMenu2 mainmenu = new MainMenu2();		
		window.setMinWidth(250);
		
		//Creates the top label which displays the final obtained score
		Label label = new Label("Your Score was: " + newDriver.getCounter());
		label.setFont(Font.font("Verdana",50));


		//Adding the top labels to the HBox
		VBox topLabelling = new VBox(50);
		topLabelling.getChildren().addAll(label);
		Button closeButton = new Button ("Quit Game");
		Button mainMenuButton = new Button("Return to Main Menu");
		//Adding the buttons to the button HBox
		HBox buttonCollection = new HBox(10, mainMenuButton, closeButton);
		
		//We set the buttons on action specifically to fulfill their immediate duties. 
		mainMenuButton.setOnAction(e->{
			window.close();
			mainmenu.stage1 = mainmenu.displayMainMenu();
			mainmenu.stage1.show();
		});
		
		closeButton.setOnAction(e -> {
			window.close();
			Platform.exit();
		});

		//Setting elements in window in appropriate alignment
		BorderPane borderPane = new BorderPane(); 
	
		topLabelling.setAlignment(Pos.CENTER);
		buttonCollection.setAlignment(Pos.CENTER);
		borderPane.setTop(topLabelling);
		borderPane.setCenter(buttonCollection);
		
		//Adding the borderPane to the scene

		Scene scene = new Scene(borderPane, 900,650);
	
		window.setScene(scene);
		window.setTitle("Snoke Game Over!");
		window.show();
		return window;
		
	}

	
}
