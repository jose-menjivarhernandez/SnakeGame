package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	String name;
	String filename = "highscoreconsole.txt";
	int counter = 0;
	public void play() {
		//Getting name for high score
		Scanner scanner = new Scanner(System.in);		
		System.out.println("Enter your name: ");
		name = scanner.nextLine();	

		//Creates a specific board and an instance of Moving to use its methods
		ClassicBoardConsole board = new ClassicBoardConsole (width, height, consoleBoard, snake, berry, wall, back);
		Moving move = new Moving(board);
		boolean go = true;		
	

		//instructions
		System.out.println("Press 'a' to go left, 'w' to go up, 'd' to go right, and 's' to go down");
		
		/**
		 * This method is in charge of most things that happen in the main, including the board creation and 
		 * the input reading and control
		 */
		//playing game
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
			//ending game
			if(board.gameOver()) {
				System.out.println("LOST");
			
			//Creating file
				File file = new File (filename);
				if(file.exists()) {
					
				}
				go = false;	 
				//"Saving" score to file, this also overwrites the old file so it is empty
				try {
					FileWriter fwriter = new FileWriter(filename, true);
					BufferedWriter buffwriter = new BufferedWriter (fwriter);
					buffwriter.write(name +":" +Integer.toString(counter));
					buffwriter.newLine();
					buffwriter.close();
				}catch(FileNotFoundException fnfe) {}
				catch (IOException ioe) {}
				//Reading and reporting score from file
				try {
				FileReader freader = new FileReader(filename);
				BufferedReader reader = new BufferedReader(freader);
				while(reader.readLine()!=null ||reader.readLine() == "\n") {
					System.out.println(reader.readLine()); 
				}
				reader.close();
				}catch (FileNotFoundException fnfe) {}
				catch (IOException ioe) {}
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
