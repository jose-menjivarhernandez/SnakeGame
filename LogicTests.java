package logic;
import static org.junit.Assert.*;
import org.junit.Test;


public class LogicTests{

  @Test
  public void test_Moving_Constructor(){
    //test-setup
    char consoleBoard [][] = null;
    ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
		Moving move = new Moving(board);
    //board.printBoard();
    //execution & verification
    assertEquals("The height of the board should be 20", board.getHeight(),20);
    assertEquals("The width of the board should be 20", board.getWidth(),20);
    assertEquals("The snake of the board should be @", board.getSnake(),'@');
    assertEquals("The berry of the board should be &", board.getBerry(),'&');
	}

  @Test
  public void test_PlaceSnake(){
    //test-setup
    char consoleBoard [][] = null;
    ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
		Moving move = new Moving(board);
    //board.printBoard();
    //execution & verification
    assertEquals("The snake should be in the position [10][10] of the board", board.consoleBoard[10][10],'@');
	}

  @Test
  public void test_Moving_Right(){
    //test-setup
    char consoleBoard [][] = null;
		ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
		Moving move = new Moving(board);
    //execution
		move.moveRight();
  //  board.printBoard();
  //verification
		assertEquals("The snake should be in the position [11][10] of the board", board.consoleBoard[11][10],'@');
		board.printBoard();
	}
  @Test
	public void test_Moving_Left(){
      //test-setup
    char consoleBoard [][] = null;
    ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
		Moving move = new Moving(board);
    //execution
		move.moveLeft();
  //  board.printBoard();
  //verification
    assertEquals("The snake should be in the position [9][10] of the board", board.consoleBoard[9][10],'@');
	board.printBoard();
  	}
  @Test
	public void test_Moving_Up(){
    //test-setup
    char consoleBoard [][] = null;
    ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
		Moving move = new Moving(board);
    //execution
		move.moveUp();
    //board.printBoard();
    //verification
    assertEquals("The snake should be in the position [10][9] of the board", board.consoleBoard[10][9],'@');
	board.printBoard();
  	}
  @Test
	public void test_Moving_Down(){
    //test-setup
    char consoleBoard [][] = null;
    ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
		Moving move = new Moving(board);
    //execution
		move.moveDown();
  //  board.printBoard();
  //verification
    assertEquals("The snake should be in the position [10][11] of the board", board.consoleBoard[10][11],'@');
	board.printBoard();
  }

  @Test
  public void test_Movement(){
    //test-setup
    char consoleBoard [][] = null;
    ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
    Moving move = new Moving(board);
    move.moveDown();
    //execution
    move.moveDown();
    move.moveDown();
    move.moveDown();
    move.moveLeft();
    move.moveLeft();
    move.moveUp();
    move.moveRight();
    //board.printBoard();
    //verification
    assertEquals("The snake should be in the position [9][13] of the board",board.consoleBoard[9][13],'@');
  board.printBoard();
  }
  @Test
	public void test_GameOver(){
    //test-setup
    char consoleBoard [][] = null;
    ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
    Moving move = new Moving(board);
    move.moveDown();
    move.moveDown();
    move.moveDown();
    move.moveDown();
    move.moveDown();
    move.moveDown();
    move.moveDown();
    move.moveDown();
    move.moveDown();
    //board.printBoard();
    //execution & verification
    assertTrue("The game should have ended because Snake hit the border", board.gameOver() == true);
	}
  @Test
	public void test_Snake_Has_Not_Eaten(){
    //test-setup
    char consoleBoard [][] = null;
    ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
    Moving move = new Moving(board);
  //  board.printBoard();
  //execution & verification
    assertTrue("The counter should still be 0 as snake has not eaten yet",board.hasNotEaten()== true);
  }
  @Test
	public void test_Snake_Has_Eaten(){
    //test-setup
    char consoleBoard [][] = null;
    boolean present=false;
    ClassicBoardConsole board = new ClassicBoardConsole(20,20,consoleBoard,'@','&','#','.');
    Moving move = new Moving(board);
    //execution
    for(int iW = 0; iW < 19 ; iW++) {
			for (int iY = 0; iY < 19 ; iY++) {
				if(board.consoleBoard[iW][iY] == '&') {
          board.consoleBoard[iW][iY]= '@';
          move.moveUp();
        }
      }
    }
    for(int indexW = 0; indexW < 19 ; indexW++) {
    	for (int IndexY = 0; IndexY < 19 ; IndexY++) {
    		if(board.consoleBoard[indexW][IndexY] == '&'){
          present=true;
        }
      }
    }
    board.printBoard();
    //verification
    assertEquals("Berry has re-spawned and Snake has now eaten",board.hasNotEaten()== true, present == true);
  }

}
