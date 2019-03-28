package logic;

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
		for (int iW = width-1; iW > 0;iW--) {
			for (int iY = height-1; iY > 0;iY--) {
				if (consoleBoard [iW][iY] == snake) {
					if(consoleBoard[iW][iY] != berry) {
						consoleBoard[iW][iY] = back;
					}
					consoleBoard[iW+1][iY] = snake;
				}
			}
		}
	}
	
	//Moving SNAKE left
	public void moveLeft() {
		for (int iW = 0; iW < width-1; iW++) {
			for (int iY = 0; iY< height-1; iY++) {
				if (consoleBoard [iW][iY] == snake) {
					if(consoleBoard[iW][iY] != berry) {
						consoleBoard[iW][iY] = back;
					}
					consoleBoard[iW-1][iY] = snake;
				}
			}
		}	
	}
	
	//Moving SNAKE up
	public void moveUp() {
		for (int iW = 0; iW < width-1; iW++) {
			for (int iY = 0; iY< height-1; iY++) {
				if (consoleBoard [iW][iY] == snake) {
					if(consoleBoard[iW][iY] != berry) {
						consoleBoard[iW][iY] = back;
					}
					consoleBoard[iW][iY-1] = snake;
				}
			}
		}
	}
	
	//Moving SNAKE down
	public void moveDown() {
		for (int iW = width-1; iW >0;iW--) {
			for (int iY= height-1; iY>0;iY--) {
				if (consoleBoard [iW][iY] == snake) {
					if(consoleBoard[iW][iY] != berry) {
						consoleBoard[iW][iY] = back;
					}
					consoleBoard[iW][iY+1] = snake;
				}
			}
		}
	}
}