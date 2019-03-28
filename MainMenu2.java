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


public class MainMenu2  {
	
	Driver newDriver = new Driver();
	
	boolean easyClick = false;
	boolean mediumClick = false;
	boolean hardClick = false; 
	boolean classicClicked = false;
	boolean maze1Clicked = false;
	boolean maze2Clicked = false;
	Label score = new Label();

	public Stage stage1 = new Stage();
	
	
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
		//Add both buttons and timer to the scene
		
		VBox sideButtons = new VBox(ReturnButton, QuitButton, score);
		sideButtons.setAlignment(Pos.CENTER);
		generalPane.setCenter(sideButtons);

	    QuitButton.setOnAction(e->stage1.close());
		
		
		easyButton.setOnAction(e->{
			stage1.setScene(scene2);
			easyClick = true;
			newDriver.setDuration(0.1);
		});

		mediumButton.setOnAction(e->{
			stage1.setScene(scene2);
			mediumClick = true;
			newDriver.setDuration(0.075);
		});

		hardButton.setOnAction(e->{
			stage1.setScene(scene2);
			hardClick = true;
			newDriver.setDuration(0.05);
			
		});
		
		classic.setOnAction(e->{
			classicClicked = true;
			newDriver.setType("classic");
			generalPane.setLeft(newDriver.createAct(timeline, newDriver.getDuration(),newDriver.getType()));
			Scene finalScene = new Scene(generalPane,900,650);
			stage1.setScene(finalScene);
			newDriver.beginGame(timeline);
			finalScene.setOnKeyPressed(new ControlClass());
		});

		maze1.setOnAction(e->{
			maze1Clicked = true;
			newDriver.setType("m1");
			generalPane.setLeft(newDriver.createAct(timeline, newDriver.getDuration(),newDriver.getType()));
			Scene finalScene = new Scene(generalPane,900,650);
			stage1.setScene(finalScene);
			newDriver.beginGame(timeline);
			finalScene.setOnKeyPressed(new ControlClass());
			
		});

		maze2.setOnAction(e->{
			maze2Clicked = true;
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

