package userInterface;
import javafx.animation.Timeline;
import javafx.application.Application;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import logic.*;
import controller.*;
//Add this as a setOnAction to button in main/ when we detect game is over

public class GameOverAlertBox extends MainMenu2 {
	
	static Stage window = new Stage();
	
	public static Stage displayEndGame () {

		window.initModality(Modality.APPLICATION_MODAL);
		

		//*****ARBITRARY NUMBER RN*****
		window.setMinWidth(250);
		MainMenu2 mainmenu = new MainMenu2();		
		//Need getScore and getTime methods
		Label label = new Label("Your Score was: " + mainmenu.newDriver.getCounter());
		label.setFont(Font.font("Verdana",50));


		//Adding the top labels to the HBox
		VBox topLabelling = new VBox(50);
		topLabelling.getChildren().addAll(label);
		Button closeButton = new Button ("Quit Game");
		Button mainMenuButton = new Button("Return to Main Menu");
		//Adding the buttons to the button HBox
		HBox buttonCollection = new HBox(10, mainMenuButton, closeButton);
	
		mainMenuButton.setOnAction(e->{
			window.close();
			mainmenu.stage1 = mainmenu.displayMainMenu();
			mainmenu.stage1.show();
		});
		closeButton.setOnAction(e -> {
			window.close();
			Platform.exit();
		});

		//Setting elements in window
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