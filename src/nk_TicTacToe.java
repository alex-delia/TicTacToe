//Alex D'Elia
//251021780
//adelia3
//Oct. 17/19

public class nk_TicTacToe {
	//instance variables
	private char[][] gameBoard;
	private String config = "";
	private int size;
	private int inline;
	Dictionary dict;
	
	//constructor for the board, creates a new board of the specified size and fills all slots with empty ' ' chars
	public nk_TicTacToe (int board_size, int inline, int max_levels) {
		size = board_size;
		this.inline = inline;
		gameBoard = new char[board_size][board_size];
		for(int i=0; i < size; i++) {
			for(int m=0; m < size; m++) {
				gameBoard[i][m] = ' ';
				}
			}
	}
	
	//dictionary method that uses the constructor to make a dict of the specified size
	public Dictionary createDictionary() {
		dict = new Dictionary(7669);
		return dict;
	}
	
	//checks to see if the gameboard current config already exists in the dictionary and then uses get from the Dictionary class
	//to return that config's score or -1 if it does not exist
	public int repeatedConfig(Dictionary configurations) {
		config = "";
		for(int i=0; i < size; i++) {
			for(int m=0; m < size; m++) {
				
				config += gameBoard[i][m];
			}
		}
		return configurations.get(config);
	}
	
	//takes the current config of the gameboard, turns it into a string, then converts that configuration and the specified 
	//score into a Record which is inserted into the given dictionary using insert from the Dictionary class
	public void insertConfig(Dictionary configurations, int score) {
		config = "";
		for(int i=0; i < size; i++) {
			for(int m=0; m < size; m++) {
				config += gameBoard[i][m];
			}
		}
		Record board = new Record(config, score);
		
		configurations.insert(board);
	}
	
	//stores the current play into a slot of the gameboard 2D array
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}
	
	//checks to see if a square in the gameboard is ' ' and therefore empty returning true, otherwise returns false
	public boolean squareIsEmpty (int row, int col) {
		if(gameBoard[row][col] == ' ') {
			return true;
		}else {
			return false;
		}
	}
	
	//checks to see if a player has won within the current configuration of the game
	public boolean wins(char symbol) {
		
		//Rows check: for each new row, the match counter is reset to 0
		for(int i=0; i < size;i++) {
			int match = 0;
			for(int m=0; m < size; m++) {
				//if the current position matches the given symbol the counter increments up by 1
				if(gameBoard[i][m] == symbol) {
					match++;
				}else {
					//otherwise the counter is reset to 0
					match = 0;
				}
				//if the match counter reaches the inline value specified in the gameboard constructor, true is returned
				if(match == inline) return true;
			}
		}
		
		
		//Columns Check: for each new column, the match counter is reset to 0
		for(int i=0; i < size;i++) {
			int match = 0;
			for(int m=0; m < size; m++) {
				//if the current position matches the given symbol the counter increments up by 1
				if(gameBoard[m][i] == symbol) {
					match++;
				}else {
					//otherwise the counter is reset to 0
					match = 0;
				}
				//if the match counter reaches the inline value specified in the gameboard constructor, true is returned
				if(match == inline) return true;
			}
		}
		
		//diagonal down match
		for(int i=0;i<size;i++) {
			for(int m = 0; m<size; m++) {
				//for each new block being tested the match counter is reset
				int match = 0;
				//if the current position matches the given symbol the counter increments up by 1
				if(gameBoard[i][m] == symbol) {
					match++;
					if(match == inline) return true;
					//while inbounds of the gameboard increment the i and m value up by one to check if the box diagonally 
					//down to the right of the current one is also the same symbol	
					while(m < size-1 && i < size-1) {
						m = m+1;
						i = i+1;
						//if this new block matches the symbol the match counter increments up by 1 and this block becomes the
						//new current block
						if(gameBoard[i][m] == symbol) {
							match++;
							//if the match counter equals the inline value at any point, true is returned
							if(match == inline) return true;
						}else {
							//if the new block does not match the symbol the counter is reset to 0
							match = 0;
						}
					}
				}
			}
		}
				
		
		//diagonal up match
		for(int i=0;i<size;i++) {
			for(int m=0;m<size;m++) {
				//for each new block being tested the match counter is reset
				int match = 0;
				//if the current position matches the given symbol the counter increments up by 1
				if(gameBoard[i][m] == symbol) {
					match++;
					if(match == inline) return true;
					//while inbounds of the gameboard increment the i value up by one and the m value 
					// down by 1 to check if the box diagonally up to the right of the current one is also the same symbol
					while(m > 0 && i < size-1) {
						m = m - 1;
						i = i + 1;
						//if this new block matches the symbol the match counter increments up by 1 and this block becomes the
						//new current block
						if(gameBoard[i][m] == symbol) {
							match++;
							//if the match counter equals the inline value at any point, true is returned
							if(match == inline) return true;
						}else {
							match = 0;
						}
					}
				}
			}
		}
		
		//if there is no current winner, false is returned
		return false;
	}
	
	//checks to see if the game is a draw
	public boolean isDraw() {
		boolean isFull = true;
		//iterates through each value of the gameboard and if any = ' ' then the gameboard is not full and isFull is set
		//to false
		for(int i=0; i < size; i++) {
			for(int m=0; m < size; m++) {
				if(gameBoard[i][m] == ' ') {
					isFull = false;
				}
			}
		}
		
		//the game can only be a tie if the gameboard is full, player X did not win, and player O did not win.
		if(isFull == true && wins('X') == false && wins('O') == false) {
			return true;
		}else {
			return false;
		}
	}
	
	//evaluates to board to decide if there is a winner, it is a draw, or if the game is still ongoingx
	public int evalBoard() {
		if(wins('O')) {
			return 3;
		} else if(wins('X')) {
			return 0;
		} else if(isDraw()) {
			return 2;
		}else {
			return 1;
		}
	}	
	
}
