package logic;

public class ClassicBoardConsole{
	//Console is 65 x 65
	//GUI is 650 x 650 because each block is ten pixels
	private int width;
	private int height;
	private char snake;
	private char berry;
	private char consoleBoard[][];
	private char wall;
	private char back;
	//Choosing values only works if the values are the same, can't have different width and heights
	//Constructor for board
	
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
	public ClassicBoardConsole(int WIDTH, int HEIGHT, char consoleBoardCon[][], char snake, char berry, char WALL, char BACK) {
		this.width = new Integer (WIDTH);
		this.height = new Integer (HEIGHT);
		this.wall = new Character(WALL);
		this.back = BACK;
		
		//setting the parameter
		consoleBoardCon = new char [width][height];
		//setting the instance variable to the parameter
		this.consoleBoard = consoleBoardCon;
		//Filling array with '.'
		for (int  index= 1; index < width-1; index++) {
			for (int index2 = 1; index2 < height-1; index2++) {
				consoleBoard[index2][index]= back;
			}
		}
		//Adding the top border characters
		for (int index = 0; index< width; index++) {
			consoleBoard[index][0] = wall;
		}
		//Adding the bottom border characters
		for (int index=0; index< width; index++) {
			consoleBoard[index][height-1] = wall;
		}
		//Adding the left border characters
		for (int index=0; index< height; index++) {
			consoleBoard[0][index] = wall;
		}
		//Adding the right bottom border characters
		for (int index=0; index<height; index++) {
			consoleBoard[WIDTH-1][index]= wall;
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
	
	//Printing the game board
	public void printBoard() {
		for (int index= 0; index< width; index++) {
			for (int index2= 0; index2< height;index2 ++) {
				System.out.print(consoleBoard[index2][index]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Although name may be a little contradicting, this method is checking if the 
	 * berry has been eaten
	 * @return a boolean true is has been eaten and false otherwise
	 */
	public boolean hasNotEaten() { //Checking characters not location, overlap changes and wont respawn, need to see if no longer on screen
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
		for (int index = 0; index< width; index++) {
			if(consoleBoard[index][0] == snake) {
				return lost = true;
			};
		}
		//Adding the bottom border characters
		for (int index=0; index< width; index++) {
			if(consoleBoard[index][height-1] == snake) {
				return lost = true;
			}
		}
		//Adding the left border characters
		for (int index=0; index< height; index++) {
			if(consoleBoard[0][index] == snake) {
				return lost = true;
			}
		}
		//Adding the right bottom border characters
		for (int index=0; index<height; index++) {
			if(consoleBoard[width-1][index] == snake) {
				return lost = true;
			}
		}
		return lost;
	}
	
	//Getter and setter methods
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
