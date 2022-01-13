public class Board
{
	private char board[][];

	// Creates a fresh clean board
	Board()
	{
		board = new char[][] { { ' ', '|', ' ', '|', ' ' },
			{ '-', '+', '-', '+', '-' },
			{ ' ', '|', ' ', '|', ' ' },
			{ '-', '+', '-', '+', '-' },
			{ ' ', '|', ' ', '|', ' ' }
		};
	}

	// Displays board
	public void displayBoard()
	{
		System.out.println();
		for(int row = 0; row < board.length; row++) 
		{
			for(int col = 0; col < board[row].length; col++)
				System.out.print(board[row][col]);
			System.out.println();
		}
	}

	// Takes the users input and their player to play the spot
	public void play(int row, int col, Player p)
	{
		board[row][col] = p.getPlayerChar();
	}
	
	// Checks if the spot is played or empty
	public boolean isPlayed(int row, int col)
	{
		if(board[row][col] == 'X' || board[row][col] == 'O')
			return true;
		return false;
	}

	// Checks if the board is full
	public boolean isFull()
	{
		for(int row = 0; row < board.length; row++)
			for(int col = 0; col < board[row].length; col++)
				if(board[row][col] == ' ')
					return false;
		return true;
	}
	
	// Checks all the winning conditions
	public boolean checkWon(Player p)
	{
		// Won is always false unless one of the statements below are true
		boolean won = false;
		
		// Checks if winning on row
		if( board[0][0] == p.getPlayerChar() && board[0][2] == p.getPlayerChar() && board[0][4] == p.getPlayerChar() )
			won = true;
		else if( board[2][0] == p.getPlayerChar() && board[2][2] == p.getPlayerChar() && board[2][4] == p.getPlayerChar() )
			won = true;
		else if( board[4][0] == p.getPlayerChar() && board[4][2] == p.getPlayerChar() && board[4][4] == p.getPlayerChar() )
			won = true;
		
		// Checks if winning on column
		else if( board[0][0] == p.getPlayerChar() && board[2][0] == p.getPlayerChar() && board[4][0] == p.getPlayerChar() )
			won = true;
		else if( board[0][2] == p.getPlayerChar() && board[2][2] == p.getPlayerChar() && board[4][2] == p.getPlayerChar() )
			won = true;
		else if( board[0][4] == p.getPlayerChar() && board[2][4] == p.getPlayerChar() && board[4][4] == p.getPlayerChar() )
			won = true;
		
		// Checks if winning across
		else if( board[0][0] == p.getPlayerChar() && board[2][2] == p.getPlayerChar() && board[4][4] == p.getPlayerChar() )
			won = true;
		else if( board[0][4] == p.getPlayerChar() && board[2][2] == p.getPlayerChar() && board[4][0] == p.getPlayerChar() )
			won = true;
		
		return won;
	}
}
