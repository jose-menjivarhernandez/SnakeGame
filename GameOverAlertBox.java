package userInterface;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
		try {
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setMinWidth(250);
		MainMenu2 mainmenu = new MainMenu2();		

		Button closeButton = new Button ("Quit Game");
		Button mainMenuButton = new Button("Return to Main Menu");
		//Adding the buttons to the button HBox
		HBox buttonCollection = new HBox(10, mainMenuButton, closeButton);
		
		//We set the buttons on action specifically to fulfill their immediate duties
		mainMenuButton.setOnAction(e->{
			window.close();
			mainmenu.stage1 = mainmenu.displayMainMenu();
			mainmenu.stage1.show();
		});
		closeButton.setOnAction(e -> {
			window.close();
			Platform.exit();
		});
		//Creating add name and score for highscore fileIO
		Label promptName = new Label ("Please Enter Your Name: ");
		TextField gettingName = new TextField();
		
		//Adding to HBOX
		VBox namingCollection = new VBox(promptName, gettingName);
		namingCollection.setAlignment(Pos.BOTTOM_CENTER);
		buttonCollection.getChildren().addAll(namingCollection);
		
		
		//Putting info into text file
		String filename = "highscore.txt";
		File highscore = new File(filename);
		if (highscore.exists()) {
		}
		
		gettingName.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER) {
		try {
			String name = gettingName.getText();
			FileWriter fwriter = new FileWriter (filename, true);
			BufferedWriter buffwriter = new BufferedWriter(fwriter);
			buffwriter.write(name +" : " + Integer.toString(mainmenu.newDriver.getCounter()));
			buffwriter.newLine();
			buffwriter.close();
		}catch(FileNotFoundException fnfe) {}
		catch (IOException ioe) {}	
		
			}
		});
		
		
		//Creating scene for high score to be put
		Label announcetitle = new Label ("HIGHSCORES");
		announcetitle.setAlignment(Pos.CENTER);
		VBox highscoreBox = new VBox(announcetitle);
		
		
		//Getting information from text file
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader buffreader = new BufferedReader(freader);
			while(buffreader.readLine()!= null || buffreader.readLine() == "\n") {
				highscoreBox.getChildren().add(new Label (buffreader.readLine()));
			}
			buffreader.close();
		}catch (FileNotFoundException fnfe) {}
		catch (IOException ioe) {}
		
		//Setting elements in window
		BorderPane borderPane = new BorderPane(); 
	
		
		buttonCollection.setAlignment(Pos.CENTER);
		borderPane.setCenter(buttonCollection);
		borderPane.setBottom(highscoreBox);
		//Adding the borderPane to the scene

		Scene scene = new Scene(borderPane, 900,650);
	
		window.setScene(scene);
		window.setTitle("Snoke Game Over!");
		window.show();
		return window;
		
	}catch (IllegalStateException ise) {

		window.setMinWidth(250);
		MainMenu2 mainmenu = new MainMenu2();		

		Button closeButton = new Button ("Quit Game");
		Button mainMenuButton = new Button("Return to Main Menu");
		
		//Adding the buttons to the button HBox
		HBox buttonCollection = new HBox(10, mainMenuButton, closeButton);
	
		//We set the buttons on action specifically to fulfill their immediate duties
		mainMenuButton.setOnAction(e->{
			window.close();
			mainmenu.stage1 = mainmenu.displayMainMenu();
			mainmenu.stage1.show();
		});
		closeButton.setOnAction(e -> {
			window.close();
			Platform.exit();
		});
		//Creating add name and score for highscore option
		Label promptName = new Label ("Please Enter Your Name: ");
		TextField gettingName = new TextField();
		
		//Adding to HBOX
		VBox namingCollection = new VBox(promptName, gettingName);
		namingCollection.setAlignment(Pos.BOTTOM_CENTER);
		buttonCollection.getChildren().addAll(namingCollection);
		
		
		//Putting info into text file
		String filename = "highscore.txt";
		File highscore = new File(filename);
		if (highscore.exists()) {
		}
		
		gettingName.setOnKeyPressed(e->{
			if(e.getCode() == KeyCode.ENTER) {
		try {
			String name = gettingName.getText();
			FileWriter fwriter = new FileWriter (filename, true);
			BufferedWriter buffwriter = new BufferedWriter(fwriter);
			buffwriter.write(name +" : " + Integer.toString(mainmenu.newDriver.getCounter()));
			buffwriter.newLine();
			buffwriter.close();
		}catch(FileNotFoundException fnfe) {}
		catch (IOException ioe) {}	
		
			}
		});
		
		
		//Creating scene for high score to be put
		Label announcetitle = new Label ("HIGHSCORES");
		announcetitle.setAlignment(Pos.CENTER);
		VBox highscoreBox = new VBox(announcetitle);
		
		
		//Getting information from text file
		try {
			FileReader freader = new FileReader(filename);
			BufferedReader buffreader = new BufferedReader(freader);
			while(buffreader.readLine()!= null || buffreader.readLine() == "\n") {
				highscoreBox.getChildren().add(new Label (buffreader.readLine()));
			}
			buffreader.close();
		}catch (FileNotFoundException fnfe) {}
		catch (IOException ioe) {}
		
		//Setting elements in window
		BorderPane borderPane = new BorderPane(); 
	

		buttonCollection.setAlignment(Pos.CENTER);

		borderPane.setCenter(buttonCollection);
		borderPane.setBottom(highscoreBox);
		//Adding the borderPane to the scene

		Scene scene = new Scene(borderPane, 900,650);
	
		window.setScene(scene);
		window.setTitle("Snoke Game Over!");
		window.show();
		return window;
		
	}
	}
	
}
