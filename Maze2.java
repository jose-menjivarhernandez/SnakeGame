package logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * The class Maze2 creates a series of rectangles that are later displayed onto a specific pane
 * This pane is later put into a dynamic KeyFrame and depicted in a scene in the GUI.
 */

public class Maze2{
	int NODE_SIZE = 10;
	 int BOARD_WIDTH = 70 * NODE_SIZE;
	 int BOARD_HEIGHT = 65 * NODE_SIZE;
	
	public  Rectangle addBlock1() {
		Rectangle rec1 = new Rectangle(0, NODE_SIZE *20,NODE_SIZE * 42, NODE_SIZE * 2);
		rec1.setFill(Color.CHOCOLATE);
		return rec1;
	}

	public  Rectangle addBlock2() {
		Rectangle rec2 = new Rectangle(NODE_SIZE*40, NODE_SIZE*10, NODE_SIZE * 2, NODE_SIZE * 10);
		rec2.setFill(Color.CHOCOLATE);
		return rec2;
	}

	public  Rectangle addBlock3() {
		Rectangle rec3 = new Rectangle(NODE_SIZE*54, NODE_SIZE*10, NODE_SIZE * 2, NODE_SIZE * 40);
		rec3.setFill(Color.CHOCOLATE);
		return rec3;
	}

	public  Rectangle addBlock4() {
		Rectangle rec4 = new Rectangle(NODE_SIZE*20, NODE_SIZE*10,NODE_SIZE * 34, NODE_SIZE * 2);
		rec4.setFill(Color.CHOCOLATE);
		return rec4;
	}
		
	public  Rectangle addBlock5() {
		Rectangle rec5 = new Rectangle(NODE_SIZE*54, NODE_SIZE*36, NODE_SIZE * 10, NODE_SIZE * 2);
		rec5.setFill(Color.CHOCOLATE);
		return rec5;
	}
	
	public  Rectangle addBlock6() {
		Rectangle rec6 = new Rectangle(NODE_SIZE*60, NODE_SIZE*18, NODE_SIZE * 10, NODE_SIZE * 2);
		rec6.setFill(Color.CHOCOLATE);
		return rec6;
	}
	
	public  Rectangle addBlock7() {
		Rectangle rec7 = new Rectangle(NODE_SIZE*10, NODE_SIZE*50, NODE_SIZE * 20, NODE_SIZE * 2);
		rec7.setFill(Color.CHOCOLATE);
		return rec7;
	}
	
	public  Rectangle addBlock8() {
		Rectangle rec8 = new Rectangle(NODE_SIZE*30, NODE_SIZE*38, NODE_SIZE * 2, NODE_SIZE * 20);
		rec8.setFill(Color.CHOCOLATE);
		return rec8;
	}
	
	public  Rectangle addBlock9() {
		Rectangle rec9 = new Rectangle(NODE_SIZE* 20, NODE_SIZE*30, NODE_SIZE * 24, NODE_SIZE * 2);
		rec9.setFill(Color.CHOCOLATE);
		return rec9;
	}
	
	public  Rectangle addBlock10() {
		Rectangle rec10 = new Rectangle(NODE_SIZE*44, NODE_SIZE* 30,NODE_SIZE * 2, NODE_SIZE * 28);
		rec10.setFill(Color.CHOCOLATE);
		return rec10;
	}
	
	public  Rectangle addBlock11() {
		Rectangle rec11 = new Rectangle(NODE_SIZE*44, NODE_SIZE*58, NODE_SIZE * 16, NODE_SIZE * 2);
		rec11.setFill(Color.CHOCOLATE);
		return rec11;
	}
}

