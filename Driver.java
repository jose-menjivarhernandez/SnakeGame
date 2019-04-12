package controller;
import logic.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import userInterface.GameOverAlertBox;
/** 
 * The Driver class is the general creator of the Pane that will, in the Main Menu, 
 * be used to begin the scene that runs a time-line. This time-line drives the changes in the 
 * specific screen in order for the snake to move, and the berry to re-spawn
 * The instance variables Direction, which are enumerations, control where the snake is moving towards.
 * NODE_SIZE is the length and height in pixels of the block that make up the snake. 
 * BOARD_WIDTH is the width of the board in terms of specific node-sizes 
 * BOARD_HEIGHT is the height of the board in terms of specific node-sizes 
 * duration is the time in between time-line cycles, or how often the scene changes 
 * the type String is used to determine what maze to be used and implemented. 
 * The observable list of nodes is what makes up the body of the snake. 
 * the integer counter keeps the score when a berry is eaten
 * The boolean "iterating" is used to determine whether the time-line is playing
 * The boolean "moved" is used to determine whether there has been a change of direction or not 
 * The instances of the classes Maze1 and Maze2 are used to potentially add the barriers onto
 * the specified scene. 
*/
public class Driver{
	public enum Direction {
	        UP, DOWN, LEFT, RIGHT
	}
	   
    public final int NODE_SIZE = 10;
    public final int BOARD_WIDTH = 70 * NODE_SIZE;
    public final int BOARD_HEIGHT = 65 * NODE_SIZE;
    //Challenge mode
    public boolean challenge;
    public double duration = 0.1;
    public String type = null;
   
    public int counter;
    public String name;
    public boolean moved = false;
    public boolean iterating = false;
    public Direction direction = Direction.RIGHT;    
    ObservableList<Node> actualSnake;
    Timeline timeline;
    Maze1 maze1 = new Maze1();
    Maze2 maze2 = new Maze2();
    
    /**
     *  The getDuration method
     * @return returns the durations of an instance of class Driver
     */
    public double getDuration() {
    	return duration;
    }
    /**
     * The setDuration method sets the duration for an instance of Driver
     * @param newDuration is the parameter to set the duration as
     */
    public void setDuration (double newDuration) {
    	duration = newDuration;
    }
    /**
     * The setType method set the type of maze for an instance of Driver
     * @param newType is a String to set the type as. 
     */
    public void setType(String newType) {
    	type = newType;
    }
    /**
     * The getType method 
     * @return returns the type of maze for an instance of Driver
     */
    public String getType() {
    	return type;
    }
    /**
     * The getCounter method returns counter, used for keeping score, of an instance of Driver
     * @return returns the integer counter
     */
    public int getCounter() {
    	return counter;
    }
    /**
     * determines if they will enter challenge mode or not for the given map and difficulty
     * @param returns whether they said yes or no to challenge move
     */
    public void setChallenge(boolean choice) {
    	challenge = choice;
    }
    /**
     * The createAct method is pivotal to the class Driver. It drives the creation of the specific 
     * pane, which is later going to be depicted in a scene.
     * @param tline is the specific time-line that will be running in the KeyFrame
     * @param duration is the time between iterations of different KeyFrames
     * @param type is the specific type of maze or board desired, which will later on create the barriers and mazes
     * @return the pane that switches frames at a duration when depicted on a scene.
     */
     public Parent createAct(Timeline tline, double duration, String type) {
    	Pane root = new Pane();
        root.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);
      
        //setting the class variables as parameters entered
        this.duration = duration;
        this.type = type;
       
        //Creating the snake's body, which is a group being added into the actualSnake(observable list)
        Group actualSnakeBody = new Group();
        actualSnake = actualSnakeBody.getChildren();
       
        //Creating a berry at a random spot in the screen (a square)
        Rectangle berry = new Rectangle(NODE_SIZE, NODE_SIZE);
        berry.setFill(Color.BLUE);
        berry.setTranslateX((int)(Math.random() * (BOARD_WIDTH-NODE_SIZE)) / NODE_SIZE * NODE_SIZE);
        berry.setTranslateY((int)(Math.random() * (BOARD_HEIGHT-NODE_SIZE)) / NODE_SIZE * NODE_SIZE);
       
        //Setting up the re-iterating KeyFrame
        KeyFrame frame = new KeyFrame(Duration.seconds(duration), event -> {
            if (!iterating)
                return;
            //boolean shouldTakeAway checks whether the snake's head should be taken  away, as the movement of the snake
            //solely depends on taking away its head node, and setting the tail to the start of the Observable List 
            boolean shouldTakeAway = actualSnake.size() > 1;
          //depending on shouldTakeAway, the tail of the snake is the last or the only node in the list
            Node tail = shouldTakeAway ? actualSnake.remove(actualSnake.size()-1) : actualSnake.get(0);

            double tailX = tail.getTranslateX();
            double tailY = tail.getTranslateY();

          //Depending on the Direction, translating where the snake is going at that iteration
            switch (direction) {
                case UP:
                    tail.setTranslateX(actualSnake.get(0).getTranslateX());
                    tail.setTranslateY(actualSnake.get(0).getTranslateY() - NODE_SIZE);
                    break;
                case DOWN:
                    tail.setTranslateX(actualSnake.get(0).getTranslateX());
                    tail.setTranslateY(actualSnake.get(0).getTranslateY() + NODE_SIZE);
                    break;
                case LEFT:
                    tail.setTranslateX(actualSnake.get(0).getTranslateX() - NODE_SIZE);
                    tail.setTranslateY(actualSnake.get(0).getTranslateY());
                    break;
                case RIGHT:
                    tail.setTranslateX(actualSnake.get(0).getTranslateX() + NODE_SIZE);
                    tail.setTranslateY(actualSnake.get(0).getTranslateY());
                    break;
			default:
				break;
            }

            moved = true;
            //If statement adds the tail to the head if the snake was longer than one Node
            if (shouldTakeAway)
                actualSnake.add(0, tail);

            // collision detection
            for (Node rect : actualSnake) {
                if (rect != tail && tail.getTranslateX() == rect.getTranslateX()
                        && tail.getTranslateY() == rect.getTranslateY()) {
                    endGame(tline);
                    break;
                }
            }
          //Side collision detection
            if (tail.getTranslateX() < 0 || tail.getTranslateX() >= BOARD_WIDTH
                    || tail.getTranslateY() < 0 || tail.getTranslateY() >= BOARD_HEIGHT) {
                endGame(tline);
            }
            

            
            
            //eating detection
            if (tail.getTranslateX() == berry.getTranslateX()
                    && tail.getTranslateY() == berry.getTranslateY()) {
            	counter ++;
            	
            	int randX = (int)(Math.random() * (BOARD_WIDTH-NODE_SIZE)) / NODE_SIZE * NODE_SIZE;
            	int randY = (int)(Math.random() * (BOARD_HEIGHT-NODE_SIZE)) / NODE_SIZE * NODE_SIZE;
            	//placing berry in a valid place for maze 2
            	if(type == "m2") {
            		System.out.println(randX+ "&" + randY);
            		while((!isValidPlacementMaze2X(randX)) && (!isValidPlacementMaze2Y(randY))) {
            			randX = (int)(Math.random() * (BOARD_WIDTH-NODE_SIZE)) / NODE_SIZE * NODE_SIZE;
                    	randY = (int)(Math.random() * (BOARD_HEIGHT-NODE_SIZE)) / NODE_SIZE * NODE_SIZE;
            		}
            		berry.setTranslateX(randX);
            		berry.setTranslateY(randY);
            	//placing berry in a valid place for maze 1
            	}else if (type == "m1") {
            		System.out.println(randX +"&"+ randY);
            		while((!isValidPlacementMaze1X(randX)) && (!isValidPlacementMaze1Y(randY))) {
            			randX = (int)(Math.random() * (BOARD_WIDTH-NODE_SIZE)) / NODE_SIZE * NODE_SIZE;
            			randY = (int)(Math.random() * (BOARD_WIDTH-NODE_SIZE)) / NODE_SIZE * NODE_SIZE;
            		}
            		berry.setTranslateX(randX);
            		berry.setTranslateY(randY);
            	//placing berry
            	}else {
            		berry.setTranslateX(randX);
            		berry.setTranslateY(randY);
            	}
            	
            	//Setting a new node where the tail was
                Rectangle rect = new Rectangle(NODE_SIZE, NODE_SIZE);
                rect.setTranslateX(tailX);
                rect.setTranslateY(tailY);

                actualSnake.add(rect);
            }
            //This if statement creates barriers where the player dies if Maze1 is chosen 
            if (type == "m1") {
            	if (tail.getTranslateX() >= NODE_SIZE*40 && tail.getTranslateX() <=  NODE_SIZE*40 + ((NODE_SIZE*29))
        				&& tail.getTranslateY() >= NODE_SIZE*25 && tail.getTranslateY() <= (NODE_SIZE*25+((NODE_SIZE)))) {
        				 
        			endGame(tline);
        		}
        		
        		if (tail.getTranslateX() >= NODE_SIZE*40 && tail.getTranslateX() <=  NODE_SIZE*40 + ((NODE_SIZE))
        				&& tail.getTranslateY() >= NODE_SIZE*45 && tail.getTranslateY() <= (NODE_SIZE*45+((NODE_SIZE*20)))) {
        				 
        			endGame(tline);
        		}
        		
        		if (tail.getTranslateX() >= NODE_SIZE*60 && tail.getTranslateX() <=  NODE_SIZE*60 + ((NODE_SIZE))
        				&& tail.getTranslateY() >= NODE_SIZE*25 && tail.getTranslateY() <= (NODE_SIZE*25+((NODE_SIZE*29)))) {
        				 
        			endGame(tline);
        		}
        		
        		if (tail.getTranslateX() >= NODE_SIZE*30 && tail.getTranslateX() <=  NODE_SIZE*30 + ((NODE_SIZE*19))
        				&& tail.getTranslateY() >= NODE_SIZE*15 && tail.getTranslateY() <= (NODE_SIZE*15+((NODE_SIZE)))) {
        				 
        			endGame(tline);
        		}
        		
        		if (tail.getTranslateX() >= NODE_SIZE*20 && tail.getTranslateX() <=  NODE_SIZE*20 + ((NODE_SIZE*29))
        				&& tail.getTranslateY() >= NODE_SIZE*35 && tail.getTranslateY() <= (NODE_SIZE*35+((NODE_SIZE)))) {
        				 
        			endGame(tline);
        		}
        		if (tail.getTranslateX() >= NODE_SIZE*10 && tail.getTranslateX() <=  NODE_SIZE*10 + ((NODE_SIZE))
        				&& tail.getTranslateY() >= NODE_SIZE*0 && tail.getTranslateY() <= (NODE_SIZE*29)) {
        				 
        			endGame(tline);
        		}
            }
            //This if statement creates barriers where the player dies if Maze2 is chosen 
            else if (type == "m2") {
            	//Maze 2 barrier 1
        		if (tail.getTranslateX() >=0 && tail.getTranslateX() <= NODE_SIZE*42
        				&& tail.getTranslateY() >= NODE_SIZE*20 && tail.getTranslateY() <= (NODE_SIZE*20+((NODE_SIZE)))) {
        			endGame(tline);}
        		//Maze 2 barrier2
        		if (tail.getTranslateX() >= NODE_SIZE*40 && tail.getTranslateX() <= NODE_SIZE*40+ ((NODE_SIZE))
        				&& tail.getTranslateY() >= NODE_SIZE*10 && tail.getTranslateY() <= (NODE_SIZE*10+((NODE_SIZE*10)))) {
        			endGame(tline);}
        		//Maze 2 barrier 3
        		if (tail.getTranslateX() >= NODE_SIZE*54 && tail.getTranslateX() <= NODE_SIZE*54+ ((NODE_SIZE))
        				&& tail.getTranslateY() >= NODE_SIZE*10 && tail.getTranslateY() <= (NODE_SIZE*10+((NODE_SIZE*39)))) {
        			endGame(tline);}
        		//Maze 2 barrier 4
        		if (tail.getTranslateX() >= NODE_SIZE*20 && tail.getTranslateX() <= NODE_SIZE*20+ ((NODE_SIZE*34))
        				&& tail.getTranslateY() >= NODE_SIZE*10 && tail.getTranslateY() <= (NODE_SIZE*10+((NODE_SIZE)))) {
        			endGame(tline);}
        		// Maze 2 barrier 5 
        		if (tail.getTranslateX() >= NODE_SIZE*54 && tail.getTranslateX() <= NODE_SIZE*54+ ((NODE_SIZE*9))
        				&& tail.getTranslateY() >= NODE_SIZE*36 && tail.getTranslateY() <= (NODE_SIZE*36+((NODE_SIZE)))) {
        			endGame(tline);}
        		//Maze 2 barrier 6
        		if (tail.getTranslateX() >= NODE_SIZE*60 && tail.getTranslateX() <= NODE_SIZE*60+ ((NODE_SIZE*10))
        				&& tail.getTranslateY() >= NODE_SIZE*18 && tail.getTranslateY() <= (NODE_SIZE*18+((NODE_SIZE)))) {
        			endGame(tline);}
        		//Maze 2 barrier 7
        		if (tail.getTranslateX() >= NODE_SIZE*10 && tail.getTranslateX() <= NODE_SIZE*10+ ((NODE_SIZE*20))
        				&& tail.getTranslateY() >= NODE_SIZE*50 && tail.getTranslateY() <= (NODE_SIZE*50+((NODE_SIZE)))) {
        			endGame(tline);}
        		// Maze 2 barrier 8
        		if (tail.getTranslateX() >= NODE_SIZE*30 && tail.getTranslateX() <= NODE_SIZE*30+ ((NODE_SIZE))
        				&& tail.getTranslateY() >= NODE_SIZE*38 && tail.getTranslateY() <= (NODE_SIZE*38+((NODE_SIZE*20)))) {
        			endGame(tline);}
        		
        		// Maze 2 barrier 9
        		if (tail.getTranslateX() >= NODE_SIZE*20 && tail.getTranslateX() <= NODE_SIZE*20+ ((NODE_SIZE*24))
        				&& tail.getTranslateY() >= NODE_SIZE*30 && tail.getTranslateY() <= (NODE_SIZE*30+(NODE_SIZE))) {
        			endGame(tline);}
        		// Maze 2 barrier 10
        		if (tail.getTranslateX() >= NODE_SIZE*44 && tail.getTranslateX() <= NODE_SIZE*44+ ((NODE_SIZE))
        				&& tail.getTranslateY() >= NODE_SIZE*30 && tail.getTranslateY() <= (NODE_SIZE*30+((NODE_SIZE*28)))) {
        			endGame(tline);}
        		//Maze 2 barrier 11
        		if (tail.getTranslateX() >= NODE_SIZE*44 && tail.getTranslateX() <= NODE_SIZE*44+ ((NODE_SIZE*16))
        				&& tail.getTranslateY() >= NODE_SIZE*58 && tail.getTranslateY() <= (NODE_SIZE*58 +((NODE_SIZE)))) {
        			endGame(tline);}
            }
        });
        
        tline.getKeyFrames().add(frame);
        tline.setCycleCount(Animation.INDEFINITE);
        
        
        if (type == "classic") {
        	root.getChildren().addAll(berry, actualSnakeBody);
	        if(challenge && duration == 0.1) {
	        	tline.setCycleCount(600) ;
	        	tline.setOnFinished(e->{
	        		endGame(tline);
	        	});
	 
	        }else if(challenge && duration == 0.075) {
	        	tline.setCycleCount(800);
	        	tline.setOnFinished(e->{
	        		endGame(tline);
	        	});
	        }else if(challenge && duration == 0.05) {
	        	tline.setCycleCount(1200);
	        	tline.setOnFinished(e->{
	        		endGame(tline);
	        	});
	        }
        	return root;
	        }
        //This if statement creates the visible barriers where the player dies if Maze1 is chosen
        else if(type == "m1") {
        	root.getChildren().addAll(berry, actualSnakeBody, maze1.addBarrier1(),maze1.addBarrier2(),
    				maze1.addBarrier3(), maze1.addBarrier4(), maze1.addBarrier5(), maze1.addBarrier6());
	        if(challenge && duration == 0.1) {
	        	tline.setCycleCount(600) ;
	        	tline.setOnFinished(e->{
	        		endGame(tline);
	        	});
	        }else if(challenge && duration == 0.075) {
	        	tline.setCycleCount(800);
	        	tline.setOnFinished(e->{
	        		endGame(tline);
	        	});
	        }else if(challenge && duration == 0.05) {
	        	tline.setCycleCount(1200);
	        	tline.setOnFinished(e->{
	        		endGame(tline);
	        	});
	        }        	
    		return root;
        }
        //This if statement creates the visible barriers where the player dies if Maze2 is chosen
        else  {
        	root.getChildren().addAll(berry, actualSnakeBody, maze2.addBlock1(), maze2.addBlock2(), maze2.addBlock3(),
    				maze2.addBlock4(),maze2.addBlock5(),maze2.addBlock6(),maze2.addBlock7(),maze2.addBlock8(),
    				maze2.addBlock9(),maze2.addBlock10(),maze2.addBlock11());
	        if(challenge && duration == 0.1) {
	        	tline.setCycleCount(600) ;
	        	tline.setOnFinished(e->{
	        		endGame(tline);
	        	});
	        }else if(challenge && duration == 0.075) {
	        	tline.setCycleCount(800);
	        	tline.setOnFinished(e->{
	        		endGame(tline);
	        	});
	        }else if(challenge && duration == 0.05) {
	        	tline.setCycleCount(1200);
	        	tline.setOnFinished(e->{
	        		endGame(tline);
	        	});
	        }
    		return root;
        }
    }
     /**
      * This method finishes the game when there is a death and begins it immediately
      * @param tline determines in which specific Key Frame time-line a game will be started 
      */
    public void restartGame(Timeline tline) {
        endGame(tline);
        beginGame(tline);
    }

    /**
     * This method makes iterating false and stops the specified time-line
     * @param tline is the time-line to be stopped by method
     */
    
    public void endGame(Timeline tline) {
        iterating = false;
        tline.stop();
        actualSnake.clear();

        GameOverAlertBox.displayEndGame();  
    }
    /**
     * This method begins the game, initially adding the the head of the snake onto the Observable List
     * @param tline is the time-line to be started
     */
    public void beginGame(Timeline tline) {
        Rectangle head = new Rectangle(NODE_SIZE, NODE_SIZE);
        actualSnake.add(head);
        tline.play();
        iterating = true;
    }
    /**
     * 
     * @return the time-line in an instance of Driver
     */
    public Timeline getTimeLine() {
    	return timeline;
    }

    /** 
     * checks if the berry can be placed at this x coordinate
     * @param x coordinate for berry 
     * @return if valid placement or not
     */
    public boolean isValidPlacementMaze2X(int xcoord) {
		if (xcoord >= 0 && xcoord<=  NODE_SIZE*42)  {
			return false;
		}
		if (xcoord >= NODE_SIZE*40 && xcoord<=  NODE_SIZE*40 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*54 && xcoord<=  NODE_SIZE*54 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*20 && xcoord<=  NODE_SIZE*20 + ((NODE_SIZE*34)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*54 && xcoord<=  NODE_SIZE*54 + ((xcoord*10)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*60 && xcoord<=  NODE_SIZE*60 + ((NODE_SIZE*10)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*10 && xcoord<=  NODE_SIZE*10 + ((NODE_SIZE*20)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*30 && xcoord<=  NODE_SIZE*30 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*20 && xcoord<=  NODE_SIZE*20 + ((NODE_SIZE*24)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*44 && xcoord<=  NODE_SIZE*44 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*44 && xcoord<=  NODE_SIZE*44 + ((NODE_SIZE*16)) ){
			return false;
		}if (xcoord >= 650) {
			return false;
		}
		return true;
    }
    /**
     * checks if berry can be placed at this ycoordinate
     * @param ycoord where berry would be placed on y-axis
     * @return if valid placement or not
     */
    public boolean isValidPlacementMaze2Y(int ycoord) {
    	if (ycoord >= NODE_SIZE*20 && ycoord<=  NODE_SIZE*20 + ((NODE_SIZE*2)) ) {
			return false;
		}
		if (ycoord >= NODE_SIZE*10 && ycoord<=  NODE_SIZE*10 + ((NODE_SIZE*10)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*10 && ycoord<=  NODE_SIZE*10 + ((NODE_SIZE*40)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*10 && ycoord<=  NODE_SIZE*10 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*36 && ycoord<=  NODE_SIZE*36 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*18 && ycoord<=  NODE_SIZE*18 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*50 && ycoord<=  NODE_SIZE*50 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*38 && ycoord<=  NODE_SIZE*38 + ((NODE_SIZE*20)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*30 && ycoord<=  NODE_SIZE*30 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*30 && ycoord<=  NODE_SIZE*30+ ((NODE_SIZE*28)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*58 && ycoord<=  NODE_SIZE*58 + ((NODE_SIZE*2)) ){
			return false;
		}   	
    	if (ycoord>= 650) {
    		return false;
    	}
    	return true;
    }
	/**
	 * checking if berry placement is valid at this x coordinate
	 * @param xcoord where berry would be placed
	 * @return if valid placement or not
	 */
    public boolean isValidPlacementMaze1X(int xcoord) {
    	if (xcoord >= NODE_SIZE*40 && xcoord<=  NODE_SIZE*40 + ((NODE_SIZE*30)) ) {
    		return false;
    	}
		if (xcoord >= NODE_SIZE*40 && xcoord<=  NODE_SIZE*40 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*60 && xcoord<=  NODE_SIZE*60 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*30 && xcoord<=  NODE_SIZE*30 + ((NODE_SIZE*20)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*20 && xcoord<=  NODE_SIZE*20 + ((NODE_SIZE*40)) ){
			return false;
		}
		if (xcoord >= NODE_SIZE*10 && xcoord<=  NODE_SIZE*10 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (xcoord >= 650) {
			return false;
		}
    	return true;
    }
    /**
     * checking if berry can be placed at this ycoord
     * @param ycoord where berry would be placed
     * @return if berry can be placed or not
     */
    public boolean isValidPlacementMaze1Y(int ycoord) {
    	if (ycoord >= NODE_SIZE*25 && ycoord<=  NODE_SIZE*25 + ((NODE_SIZE*2)) ) {
    		return false;
    	}
		if (ycoord >= NODE_SIZE*45 && ycoord<=  NODE_SIZE*45 + ((NODE_SIZE*20)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*25 && ycoord<=  NODE_SIZE*25 + ((NODE_SIZE*30)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*15 && ycoord<=  NODE_SIZE*15 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (ycoord >= NODE_SIZE*35 && ycoord<=  NODE_SIZE*35 + ((NODE_SIZE*2)) ){
			return false;
		}
		if (ycoord >= 0 && ycoord<= NODE_SIZE*30 ){
			return false;
		}
		if (ycoord>= 650) {
			return false;
		}
	
    	return true;
    }
}

