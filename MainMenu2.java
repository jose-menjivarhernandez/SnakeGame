package userInterface;
import logic.*; 
import userInterface.*;
import controller.Driver;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The class MainMenu2 (as a derivative of initial main menu in past demo) provides the user 
 * with a menu graphical interface so as to choose the difficulty, and board desired. Immediately
 * after these aspects are chosen, a specific scene is opened onto the stage, using
 * the createAct method from the class Driver
 * We used an instance of Driver "newDriver" in order to access the creation of a dynamic pane
 * We instantiated our label "score" in order for the inner EventHandling class to update it.
 * Our Stage will be what is ultimately depicted through the Graphical User Interface.
 */

public class MainMenu2  {
	
	static Driver newDriver = new Driver();
	Label score = new Label();

	public Stage stage1 = new Stage();
	
	/**
	 * 
	 * The inner EventHandling class "ControlClass" handles the controls on keys pressed.
	 * Immediately after event, Control Class switches directions with a switch statement
	 * this gives order to our case switcher in the method createAct in newDriver
	 *
	 */
	public class ControlClass implements EventHandler<KeyEvent>{

		
		@Override
		public void handle(KeyEvent event) {
			score.setText("Score: " + newDriver.getCounter());
			switch(event.getCode()) {
			case W:
				if (newDriver.direction != newDriver.direction.DOWN)
					newDriver.direction = newDriver.direction.UP;
				break;
			case A:
				if (newDriver.direction != newDriver.direction.RIGHT)
					newDriver.direction = newDriver.direction.LEFT;
				break;
			case S:
				if (newDriver.direction != newDriver.direction.UP)
					newDriver.direction = newDriver.direction.DOWN;
				break;
			case D:
				if (newDriver.direction != newDriver.direction.LEFT)
					newDriver.direction = newDriver.direction.RIGHT;
				break;
			default:
				newDriver.direction = newDriver.direction.RIGHT;
				break;
			}
		}

		
	}
	/**
	 * The DisplayMainMenu method displays all visuals in the GUI
	 * @return the Stage with the scenes holding specifications for customized game plays 
	 */

	public Stage displayMainMenu() {
		//Creating HBox and adding created labels to it
		VBox labelling = new VBox(100);
		
		
		Label label1 = new Label("Welcome to the Snoke Game!");
		label1.setFont(Font.font("Verdana", 50));
		Label label2 = new Label ("Please select your difficulty:");
		label2.setFont(Font.font("Verdana",50));
		
		labelling.getChildren().addAll(label1, label2);
		
		//Creating VBox and adding created difficulty buttons to it
		VBox buttonHolder = new VBox(50);
		
		Button easyButton = new Button ("Easy");
		easyButton.setPrefSize(200, 100);

		Button mediumButton = new Button ("Medium");
		mediumButton.setPrefSize(200,100);

		
		Button hardButton = new Button ("Hard");
		hardButton.setPrefSize(200, 100);
		
		buttonHolder.getChildren().addAll(easyButton, mediumButton, hardButton);
		

		//Setting first screen elements in window
		labelling.setAlignment(Pos.CENTER);
		buttonHolder.setAlignment(Pos.CENTER);
		
		
		//Contents for Scene 1 are here
		BorderPane mainMenu = new BorderPane();
		mainMenu.setTop(labelling);
		mainMenu.setCenter(buttonHolder);
		Scene scene1 = new Scene(mainMenu, 900, 650);
		stage1.setScene(scene1);
		
		//Contents for Board Maze selection are here 
		BorderPane selectionMenu = new BorderPane();		
		VBox selectionHolder = new VBox(50);
		
		//Create 3 buttons for board options
		Label selecting = new Label ("Please select your board");
		selecting.setFont(Font.font("Verdana", 50));
		Button classic = new Button ("Classic");
		classic.setPrefSize(200, 100);
		Button maze1 = new Button ("Maze 1");
		maze1.setPrefSize(200, 100);
		Button maze2 = new Button ("Maze 2"); 
		maze2.setPrefSize(200, 100);
		
		//Place all 3 buttons at the center of the window
		selectionHolder.getChildren().addAll(selecting, classic, maze1, maze2);
		selectionHolder.setAlignment(Pos.CENTER);
		selectionMenu.setCenter(selectionHolder);

		Scene scene2 = new Scene(selectionMenu, 900, 650);
		
		//Add both buttons and timer to the scene
		Timeline timeline = new Timeline();
		BorderPane generalPane= new BorderPane();
		
		//Set "Return" and "Quit" buttons
		//These buttons are immediately created with their specified 
		
		Button ReturnButton = new Button ("Return");
		ReturnButton.setOnAction(e->{
			stage1.close();
			timeline.stop();
			stage1 = displayMainMenu();
			stage1.show();
		});
		
		Button QuitButton = new Button("Quit");
		ReturnButton.setPrefSize(75, 75);
		QuitButton.setPrefSize(75, 75);
		
		VBox sideButtons = new VBox(ReturnButton, QuitButton, score);
		sideButtons.setAlignment(Pos.CENTER);
		generalPane.setCenter(sideButtons);

	    QuitButton.setOnAction(e->
	    {stage1.close();
	    timeline.stop();
	    	}
	    );
		
		//These following lines of code dictate the specific actions of every specific 
	   //button in the different menus shown stages in the GUI
		easyButton.setOnAction(e->{
			stage1.setScene(scene2);
			newDriver.setDuration(0.1);
		});

		mediumButton.setOnAction(e->{
			stage1.setScene(scene2);
		
			newDriver.setDuration(0.075);
		});

		hardButton.setOnAction(e->{
			stage1.setScene(scene2);
			
			newDriver.setDuration(0.05);
			
		});
		
		//The next few setOnAction statements are setting the buttons for pressing events
		//that are immediately going to create a dynamic scene using the time-line, 
		//duration, and type of Driver class to depict in the scene.
		classic.setOnAction(e->{
			
			newDriver.setType("classic");
			generalPane.setLeft(newDriver.createAct(timeline, newDriver.getDuration(),newDriver.getType()));
			Scene finalScene = new Scene(generalPane,900,650);
			stage1.setScene(finalScene);
			newDriver.beginGame(timeline);
			finalScene.setOnKeyPressed(new ControlClass());
		});

		maze1.setOnAction(e->{
			
			newDriver.setType("m1");
			generalPane.setLeft(newDriver.createAct(timeline, newDriver.getDuration(),newDriver.getType()));
			Scene finalScene = new Scene(generalPane,900,650);
			stage1.setScene(finalScene);
			newDriver.beginGame(timeline);
			finalScene.setOnKeyPressed(new ControlClass());
			
		});

		maze2.setOnAction(e->{
			
			newDriver.setType("m2");
			generalPane.setLeft(newDriver.createAct(timeline, newDriver.getDuration(),newDriver.getType()));
			Scene finalScene = new Scene(generalPane,900,650);
			stage1.setScene(finalScene);
			newDriver.beginGame(timeline);
			finalScene.setOnKeyPressed(new ControlClass());
			
		});
		
		
		
		
	return stage1;
		
	}
	
	
	
}


