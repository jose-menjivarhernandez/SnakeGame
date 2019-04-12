package logic;

/**
 * 
 * This class takes care of the movement of the snake when certain events are triggered
 *
 */
public class Moving{
	private int width;
	private int height;
	private char consoleBoard[][];
	private char berry;
	private char snake;
	private char back;
	
	public Moving (ClassicBoardConsole board) {
		this.width = new Integer(board.getWidth());
		this.height = new Integer(board.getHeight());
		this.consoleBoard = board.getConsoleBoard();
		this.berry = new Character(board.getBerry());
		this.snake = new Character (board.getSnake());
		this.back = new Character(board.getBack());
	}

	//Moving SNAKE right
	public void moveRight() {
		for (int widthIndex = width-1; widthIndex > 0;widthIndex--) {
			for (int heightIndex = height-1; heightIndex > 0;heightIndex--) {
				if (consoleBoard [widthIndex][heightIndex] == snake) {
					if(consoleBoard[widthIndex][heightIndex] != berry) {
						consoleBoard[widthIndex][heightIndex] = back;
					}
					consoleBoard[widthIndex+1][heightIndex] = snake;
				}
			}
		}
	}
	
	//Moving SNAKE left
	public void moveLeft() {
		for (int widthIndex = 0; widthIndex < width-1; widthIndex++) {
			for (int heightIndex = 0; heightIndex< height-1; heightIndex++) {
				if (consoleBoard [widthIndex][heightIndex] == snake) {
					if(consoleBoard[widthIndex][heightIndex] != berry) {
						consoleBoard[widthIndex][heightIndex] = back;
					}
					consoleBoard[widthIndex-1][heightIndex] = snake;
				}
			}
		}	
	}
	
	//Moving SNAKE up
	public void moveUp() {
		for (int widthIndex = 0; widthIndex < width-1; widthIndex++) {
			for (int heightIndex = 0; heightIndex< height-1; heightIndex++) {
				if (consoleBoard [widthIndex][heightIndex] == snake) {
					if(consoleBoard[widthIndex][heightIndex] != berry) {
						consoleBoard[widthIndex][heightIndex] = back;
					}
					consoleBoard[widthIndex][heightIndex-1] = snake;
				}
			}
		}
	}
	
	//Moving SNAKE down
	public void moveDown() {
		for (int widthIndex = width-1; widthIndex >0;widthIndex--) {
			for (int heightIndex= height-1; heightIndex>0;heightIndex--) {
				if (consoleBoard [widthIndex][heightIndex] == snake) {
					if(consoleBoard[widthIndex][heightIndex] != berry) {
						consoleBoard[widthIndex][heightIndex] = back;
					}
					consoleBoard[widthIndex][heightIndex+1] = snake;
				}
			}
		}
	}
}
