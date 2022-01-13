import java.util.*;

public class MainMenu 
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		Board board;
		Player[] players = { new Player('X'), new Player('O') };
		String playingAnswer = "";
		int playerCounter;
		int row = 0;
		int col = 0;
		int numPlayers;
		boolean won;
		AI ai = new AI();

		// Validates user's input for the number of players
		do
		{
			System.out.println("Are there 1 or 2 players?");
			numPlayers = input.nextInt();
		} while(numPlayers < 1 || numPlayers > 2);

		// Allows to play again
		while(!playingAnswer.equals("n"))
		{
			playerCounter = 0;
			won = false;
			board = new Board();

			// Repeats if the game is not won or tied
			while(!won && !board.isFull())
			{

				board.displayBoard();

				// Checks to see if it's the player's turn or the game is set to multiplayer
				if(players[playerCounter % players.length].getPlayerChar() == 'X' || numPlayers == 2)
					// Repeats if the player's input spot is played
					do
					{
						// Validates the user's input
						do
						{
							System.out.println("\nPlayer " + players[playerCounter % players.length].getPlayerChar() + " what location would you like to play? ex: 1 1 is the top left spot.");
							row = input.nextInt();
							col = input.nextInt();
						} while(row > 3 || row < 1 || col > 3 || col < 1);

						row = (row - 1) * 2;
						col = (col - 1) * 2;

						// Validates spot being played
						if(board.isPlayed(row, col))
							board.displayBoard();
					} while(board.isPlayed(row, col));

				// Plays Ai's random spot
				else
					do 
					{
						aiPlay(ai);
						System.out.println(ai.getRow() + " " + ai.getCol());
					} while(board.isPlayed(ai.getRow(), ai.getCol()));

				// Plays the user's input into the board
				if(players[playerCounter % players.length].getPlayerChar() == 'X' || numPlayers == 2)
					board.play(row, col, players[playerCounter % players.length]);
				else
					board.play(ai.getRow(), ai.getCol(), players[playerCounter % players.length]);

				if(board.checkWon(players[playerCounter % players.length]))
					won = true;

				playerCounter++;
			}

			board.displayBoard();
			playerCounter--;

			// Displays winning condition outputs
			if(board.isFull())
				System.out.println("\nThe game was a draw.");
			else
				System.out.println("\nPlayer " + players[playerCounter % players.length].getPlayerChar() + " won.");

			// Ask user if they want to continue playing
			do 
			{
				System.out.println("\nWould you like to continue?");
				System.out.print("Input y to continue and n to terminate: ");
				playingAnswer = input.next();
			} while(!playingAnswer.equals("y") && !playingAnswer.equals("n"));
		}

		input.close();
	}

	// Allows Ai to play by creating random numbers for it's inputs
	public static void aiPlay(AI ai)
	{
		int row = (int)(Math.random() * 3) * 2;
		ai.setRow(row);

		int col = (int)(Math.random() * 3) * 2;
		ai.setCol(col);
	}
}
