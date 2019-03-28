package logic;
/**
 * 
 * This class is in charge of the creation of a board where the game snake 
 * in the console is played.
 * The instance variable width and height are used for respective width and height designations
 * the snake, berry, wall, and back characters determine the characters to be used 
 * for these objects in the board
 * The consoleBoard creates an 2x2 grid with arrays used as board later on.
 *
 */
public class ClassicBoardConsole{
	//Console board is 65 x 65 units 
	//GUI is 700 x 650 because each block is ten pixels
	private int width;
	private int height;
	private char snake;
	private char berry;
	public char consoleBoard[][];
	private char wall;
	private char back;
	//Choosing values only works if the values are the same, can't have different width and heights
	//Constructor for board
	public ClassicBoardConsole(int WIDTH, int HEIGHT, char consoleBoardCon[][], char snake, char berry, char WALL, char BACK) {
		this.width = new Integer (WIDTH);
		this.height = new Integer (HEIGHT);
		this.wall = new Character(WALL);
		this.back = BACK;
		//creating new instance of the board to be used
		consoleBoardCon = new char [width][height];
		this.consoleBoard = consoleBoardCon;
		//Filling array with '.'
		for (int  i= 1; i < width-1; i++) {
			for (int k = 1; k < height-1; k++) {
				consoleBoard[k][i]= back;
			}
		}
		//Adding the top border characters
		for (int i = 0; i< width; i++) {
			consoleBoard[i][0] = wall;
		}
		//Adding the bottom border characters
		for (int i=0; i< width; i++) {
			consoleBoard[i][height-1] = wall;
		}
		//Adding the left border characters
		for (int i=0; i< height; i++) {
			consoleBoard[0][i] = wall;
		}
		//Adding the right bottom border characters
		for (int i=0; i<height; i++) {
			consoleBoard[WIDTH-1][i]= wall;
		}
		initialPlaceBerry(berry);
		placeSnake(snake);
		printBoard();
	}
	
	/**
	 * Determines the initial random placement of a character "berry"
	 * @param berry is used as the character to be placed randomly in the board. 
	 */
	public void initialPlaceBerry(char berry) {
		//Generates random placement for berry
		this.berry = new Character (berry);
		int randomXVar = (int) (Math.random() * width -1);
		int randomYVar = (int) (Math.random() * height -1);
		//Checks if berry will go on any of the borders
		if (randomXVar == 0) {
			randomXVar++;
		}
		if(randomXVar == height) {
			randomXVar--;
		}
		if (randomYVar == 0) {
			randomYVar++;
		}
		if(randomYVar == width) {
			randomYVar--;
		}
		//Puts the character into the array
		consoleBoard[randomXVar][randomYVar] = berry;
	}
	
	/**
	 * This method places the char which would be the snake in the board
	 * @param snake is the char which is going to be the snake in the board
	 */
	public  void placeSnake(char snake) {
		this.snake = new Character (snake);
		int Xcoord = width/2;
		int Ycoord = height/2;
		consoleBoard[Xcoord][Ycoord] = snake;
	}
	
	/**
	 * Prints the board at any instance it may be
	 */
	public void printBoard() {
		for (int i= 0; i < width; i++) {
			for (int j= 0; j< height;j ++) {
				System.out.print(consoleBoard[j][i]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Although name may be a little contradicting, this method is checking if the 
	 * berry has been eaten
	 * @return a boolean true is has been eaten and false otherwise
	 */
	public boolean hasNotEaten() { //Checking characters not location, overlap changes and wont re-spawn, need to see if no longer on screen
		boolean eaten = false;
		for(int indexW = 0; indexW < width-1 ; indexW++) {
			for (int IndexY = 0; IndexY < height-1 ; IndexY++) {
				if(consoleBoard[indexW][IndexY] == berry) {
					return eaten = true;
				}
			}
		}
		return eaten;
	}
	
	/**
	 * This method is checking if the snake collided with one of the walls
	 * This is the losing condition
	 * @return a boolean that is true is game has been lost, and false otherwise 
	 */
	public boolean gameOver() { 
		boolean lost = false;
		for (int i = 0; i< width; i++) {
			if(consoleBoard[i][0] == snake) {
				return lost = true;
			};
		}
		//Adding the bottom border characters
		for (int i=0; i< width; i++) {
			if(consoleBoard[i][height-1] == snake) {
				return lost = true;
			}
		}
		//Adding the left border characters
		for (int i=0; i< height; i++) {
			if(consoleBoard[0][i] == snake) {
				return lost = true;
			}
		}
		//Adding the right bottom border characters
		for (int i=0; i<height; i++) {
			if(consoleBoard[width-1][i] == snake) {
				return lost = true;
			}
		}
		return lost;
	}
	
	//Respective getter and setter methods for the instance variables 
	public int getWidth() {
		return new Integer(width);
	}
	public int getHeight() {
		return new Integer(height);
	}
	public char[][] getConsoleBoard(){
		return consoleBoard;
	}
	public char getBerry() {
		return new Character(berry);
	}
	public char getSnake() {
		return new Character(snake);
	}
	public char getBack() {
		return new Character(back);
	}
}
