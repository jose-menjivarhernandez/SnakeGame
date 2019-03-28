package controller;
import java.util.Scanner;
import logic.*;
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

	public void play() {
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
			//Checking if you died
	
			
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
			
			if(board.gameOver()) {
				System.out.println("LOST");
				go = false;
			}		 			  
		}
		//Closing the scanner privacy leak yall
		scanner.close();
	}
	//***MAIN***
	public static void main(String[] args) {
		SnakeConsoleController snake = new SnakeConsoleController();
		snake.play();
	}
}