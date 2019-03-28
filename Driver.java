package controller;
import logic.*;
import userInterface.*;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritablePixelFormat;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import userInterface.GameOverAlertBox;


public class Driver{
	   public enum Direction {
	        UP, DOWN, LEFT, RIGHT
	    }
	   
    public final int NODE_SIZE = 10;
    public final int BOARD_WIDTH = 70 * NODE_SIZE;
    public final int BOARD_HEIGHT = 65 * NODE_SIZE;
    
    public double duration = 0.1;
    public String type = null;
   
    public int counter;

    public boolean moved = false;
    public boolean iterating = false;
    public Direction direction = Direction.RIGHT;    
    ObservableList<Node> actualSnake;
    Timeline timeline;
    Maze1 maze1 = new Maze1();
    Maze2 maze2 = new Maze2();
    
    public double getDuration() {
    	return duration;
    }
    public void setDuration (double newDuration) {
    	duration = newDuration;
    }
    public void setType(String newType) {
    	type = newType;
    }
    public String getType() {
    	return type;
    }
    
    public int getCounter() {
    	return counter;
    }



     public Parent createAct(Timeline tline, double duration, String type) {
    	Pane root = new Pane();
        root.setPrefSize(BOARD_WIDTH, BOARD_HEIGHT);
        
        this.duration = duration;
        this.type = type;

        Group actualSnakeBody = new Group();
        actualSnake = actualSnakeBody.getChildren();

        Rectangle berry = new Rectangle(NODE_SIZE, NODE_SIZE);
        berry.setFill(Color.BLUE);
        berry.setTranslateX((int)(Math.random() * (BOARD_WIDTH-NODE_SIZE)) / NODE_SIZE * NODE_SIZE);
        berry.setTranslateY((int)(Math.random() * (BOARD_HEIGHT-NODE_SIZE)) / NODE_SIZE * NODE_SIZE);

        KeyFrame frame = new KeyFrame(Duration.seconds(duration), event -> {
            if (!iterating)
                return;

            boolean shouldTakeAway = actualSnake.size() > 1;

            Node tail = shouldTakeAway ? actualSnake.remove(actualSnake.size()-1) : actualSnake.get(0);

            double tailX = tail.getTranslateX();
            double tailY = tail.getTranslateY();


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

            if (tail.getTranslateX() < 0 || tail.getTranslateX() >= BOARD_WIDTH
                    || tail.getTranslateY() < 0 || tail.getTranslateY() >= BOARD_HEIGHT) {
                endGame(tline);
            }

            if (tail.getTranslateX() == berry.getTranslateX()
                    && tail.getTranslateY() == berry.getTranslateY()) {
            	counter ++;
            	
            	int randX = (int)(Math.random() * (BOARD_WIDTH-NODE_SIZE)) / NODE_SIZE * NODE_SIZE;
            	int randY = (int)(Math.random() * (BOARD_HEIGHT-NODE_SIZE)) / NODE_SIZE * NODE_SIZE;
            
            	berry.setTranslateX(randX);
            	berry.setTranslateY(randY);
            	

                Rectangle rect = new Rectangle(NODE_SIZE, NODE_SIZE);
                rect.setTranslateX(tailX);
                rect.setTranslateY(tailY);

                actualSnake.add(rect);
            }
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
        tline.setCycleCount(tline.INDEFINITE);
        
        if (type == "classic") {
        	root.getChildren().addAll(berry, actualSnakeBody);
	        return root;
	        }
        
        else if(type == "m1") {
        	root.getChildren().addAll(berry, actualSnakeBody, maze1.addBarrier1(),maze1.addBarrier2(),
    				maze1.addBarrier3(), maze1.addBarrier4(), maze1.addBarrier5(), maze1.addBarrier6());
    		return root;
        }
        else  {
        	root.getChildren().addAll(berry, actualSnakeBody, maze2.addBlock1(), maze2.addBlock2(), maze2.addBlock3(),
    				maze2.addBlock4(),maze2.addBlock5(),maze2.addBlock6(),maze2.addBlock7(),maze2.addBlock8(),
    				maze2.addBlock9(),maze2.addBlock10(),maze2.addBlock11());
    		return root;
        }
    }
     
    public void restartGame(Timeline tline) {
        endGame(tline);
        beginGame(tline);
    }

    public void endGame(Timeline tline) {
        iterating = false;
        tline.stop();
        actualSnake.clear();
        GameOverAlertBox.displayEndGame();  
    }

    public void beginGame(Timeline tline) {
    	
        Rectangle head = new Rectangle(NODE_SIZE, NODE_SIZE);
        actualSnake.add(head);
        tline.play();
        iterating = true;
   
    }
    public Timeline getTimeLine() {
    	return timeline;
    }



		
}

