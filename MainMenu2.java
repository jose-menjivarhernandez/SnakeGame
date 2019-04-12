package userInterface;
import controller.Driver;
import controller.Driver.Direction;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
	
	Driver newDriver = new Driver();
	Label score = new Label();

	public Stage stage1 = new Stage();
	
	
	public class ControlClass implements EventHandler<KeyEvent>{

		
		/**
		 * 
		 * The inner EventHandling class "ControlClass" handles the controls on keys pressed.
		 * Immediately after event, Control Class switches directions with a switch statement
		 * this gives order to our case switcher in the method createAct in newDriver
		 *
		 */
		@Override
		public void handle(KeyEvent event) {
			score.setText("Score: " + newDriver.getCounter());
			switch(event.getCode()) {
			case W:
				if (newDriver.direction != Direction.DOWN)
					newDriver.direction = Direction.UP;
				break;
			case A:
				if (newDriver.direction != Direction.RIGHT)
					newDriver.direction = Direction.LEFT;
				break;
			case S:
				if (newDriver.direction != Direction.UP)
					newDriver.direction = Direction.DOWN;
				break;
			case D:
				if (newDriver.direction != Direction.LEFT)
					newDriver.direction = Direction.RIGHT;
				break;
			default:
				newDriver.direction = Direction.RIGHT;
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
		VBox labelling = new VBox(10);
		
		
		Label label1 = new Label("Welcome to the Snoke Game!");
		label1.setFont(Font.font("Verdana", 50));
		Label label2 = new Label ("Please select your difficulty:");
		label2.setFont(Font.font("Verdana",50));
		
		labelling.getChildren().addAll(label1, label2);
		
		//Creating VBox and adding created difficulty buttons to it
		VBox buttonHolder = new VBox(50);
		
		Button easyButton = new Button ("Easy");
		easyButton.setPrefSize(200, 100);
		
		Button easyChallengeButton = new Button ("Easy Challenge Mode");
		easyChallengeButton.setPrefSize(200,100);
		
		Button mediumButton = new Button ("Medium");
		mediumButton.setPrefSize(200,100);

		Button mediumChallengeButton = new Button ("Medium Challenge Mode");
		mediumChallengeButton.setPrefSize(200,100);
		
		Button hardButton = new Button ("Hard");
		hardButton.setPrefSize(200, 100);
		
		Button hardChallengeButton = new Button ("Hard Challenge Mode");
		hardChallengeButton.setPrefSize(200,100);
		
		buttonHolder.getChildren().addAll(easyButton,easyChallengeButton, mediumButton,mediumChallengeButton, hardButton, hardChallengeButton);


		//Setting first screen elements in window
		labelling.setAlignment(Pos.CENTER);
		buttonHolder.setAlignment(Pos.CENTER);
		Button ReturnButton = new Button ("Return");
		ReturnButton.setOnAction(e->{
		stage1.close();
		stage1 = displayMainMenu();
		stage1.show();
		});
		
		
		//****Contents for Scene 1 are here
		BorderPane mainMenu = new BorderPane();
		mainMenu.setTop(labelling);
		mainMenu.setCenter(buttonHolder);
		Scene scene1 = new Scene(mainMenu, 900, 650);
		stage1.setScene(scene1);
		
		//*Contents for Easy selection are here 
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
		
		Timeline timeline = new Timeline();
		BorderPane generalPane= new BorderPane();
		//Set "Return" and "Quit" buttons
		Button QuitButton = new Button("Quit");
		ReturnButton.setPrefSize(75, 75);
		QuitButton.setPrefSize(75, 75);
		//Challenge Label
		Label funMessage = new Label ("Have Fun!");
		
		//Add both buttons and timer to the scene
		
		VBox sideButtons = new VBox(funMessage, ReturnButton, QuitButton, score);
		sideButtons.setAlignment(Pos.CENTER);
		generalPane.setCenter(sideButtons);

	    QuitButton.setOnAction(e->stage1.close());
		
		//These follwoing lines of code dictate the specific actions of every specific
	    //button in the different menus shown stages in the GUI
		easyButton.setOnAction(e->{
			stage1.setScene(scene2);
			newDriver.setDuration(0.1);
		});
		easyChallengeButton.setOnAction(e->{
			stage1.setScene(scene2);
			newDriver.setDuration(0.1);
			newDriver.setChallenge(true);
			funMessage.setText("You have 1 minute");
		});
		mediumButton.setOnAction(e->{
			stage1.setScene(scene2);

			newDriver.setDuration(0.075);
		});
		mediumChallengeButton.setOnAction(e->{
			stage1.setScene(scene2);
			newDriver.setDuration(0.075);
			newDriver.setChallenge(true);
			funMessage.setText("You have 1 minute");
		});
		hardButton.setOnAction(e->{
			stage1.setScene(scene2);

			newDriver.setDuration(0.05);
			
		});
		hardChallengeButton.setOnAction(e->{
			stage1.setScene(scene2);
			newDriver.setDuration(0.05);
			newDriver.setChallenge(true);
			funMessage.setText("You have 1 minute");
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

