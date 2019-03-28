package controller;
import java.util.Scanner;
import logic.*;
/**
 * 
 * This class serves as a general controller for the console-based version of the project
 * The different characters as instances variables are the characters that represent the snake, 
 * the berry, the background and the walls as depicted. 
 * The consoleBoard creates a visible grid as a 2x2 array
 *
 */
public class SnakeConsoleController{
	//Variables passed in to constructors in ***MAIN***
	private char snake = '@';
	private char berry = '&';
	private char consoleBoard[][] = null;;
	private int height = 20;
	private int width = 20;
	private char wall = '#';
	private char back = '.';
	public SnakeConsoleController(){}
	
	/**
	 * This method is in charge of most things that happen in the main, including the board creation and 
	 * the input reading and control
	 */
	public void play() {
		
		//Creates a specific board and an instance of Moving to use its methods
		ClassicBoardConsole board = new ClassicBoardConsole (width, height, consoleBoard, snake, berry, wall, back);
		Moving move = new Moving(board);
		boolean go = true;		
		int counter = 0;		
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Press 'a' to go left, 'w' to go up, 'd' to go right, and 's' to go down");
	
		while (go) {
		//Check if eating berry and calling for a re-spawn
			if(!board.hasNotEaten()) {
				board.initialPlaceBerry(board.getBerry());
				counter++;
			}
	
			
			//Getting user input
			char symbol = scanner.next().charAt(0);			
	
			//Moving snake with user input
			if (symbol == 'a') {
				 move.moveLeft();
				 board.printBoard();
				System.out.println("Score:" +counter);
				 go = true;			  
			}else if (symbol == 'w') {
				 move.moveUp();
				 board.printBoard();
				 System.out.println("Score:" +counter);
				 go = true;			  			 
			}else if(symbol == 'd') {
				 move.moveRight();
				 board.printBoard();
				 System.out.println("Score:" +counter); 
				 go = true;			  			 
			}else if (symbol == 's') {
				move.moveDown();
				board.printBoard();
				System.out.println("Score:" +counter);
				go = true;					 
			}else if (symbol =='q') {
				 go = false;	
				 System.out.println("GAME OVER\nSCORE: " +counter);
			}			
			
			//Checking if you died
			if(board.gameOver()) {
				System.out.println("LOST");
				go = false;
			}		 			  
		}
		//Closing the scanner to ensure protection for privacy leaks
		scanner.close();
	}
	//***MAIN***
	public static void main(String[] args) {
		SnakeConsoleController snake = new SnakeConsoleController();
		snake.play();
	}
}
