package logic;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Maze1 {
	int NODE_SIZE = 10;
	int BOARD_WIDTH = 70 * NODE_SIZE;
	int BOARD_HEIGHT = 65 * NODE_SIZE;

	public Rectangle addBarrier1() {
		Rectangle rect1 = new Rectangle(NODE_SIZE * 40, NODE_SIZE * 25,NODE_SIZE * 30, NODE_SIZE * 2);
		rect1.setFill(Color.CHOCOLATE);
		
		return rect1;
	}

	public Rectangle addBarrier2() {
		Rectangle rect2 = new Rectangle(NODE_SIZE * 40,NODE_SIZE * 45,NODE_SIZE * 2, NODE_SIZE * 20);
		rect2.setFill(Color.CHOCOLATE);
		return rect2;
	}

	public Rectangle addBarrier3() {
		Rectangle rect3 = new Rectangle(NODE_SIZE * 60,NODE_SIZE * 25,NODE_SIZE * 2, NODE_SIZE * 30);
		rect3.setFill(Color.CHOCOLATE);
		
		return rect3;
	}

	public Rectangle addBarrier4() {
		Rectangle rect4 = new Rectangle(NODE_SIZE * 30,NODE_SIZE * 15,NODE_SIZE * 20, NODE_SIZE * 2);
		rect4.setFill(Color.CHOCOLATE);
		
		return rect4;
	}
		
	public Rectangle addBarrier5() {
		Rectangle rect5 = new Rectangle(NODE_SIZE * 20,NODE_SIZE * 35,NODE_SIZE * 40, NODE_SIZE * 2);
		rect5.setFill(Color.CHOCOLATE);
		
		return rect5;
	}
	
	public Rectangle addBarrier6() {
		Rectangle rect6 = new Rectangle(NODE_SIZE * 10,0,NODE_SIZE * 2, NODE_SIZE * 30);
		rect6.setFill(Color.CHOCOLATE);
		
		return rect6;
	}
}
