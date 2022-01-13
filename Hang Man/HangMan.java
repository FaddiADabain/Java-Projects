import java.util.Scanner;

public class HangMan 
{

	public static void main(String[] args) 
	{
		Scanner scan = new Scanner(System.in);
		boolean won = false;
		boolean playing = true;

		while(playing) 
		{
			RandomWord word = new RandomWord();
			char[] pickedWord = word.randomWordPicker();
			char[] unsolvedWord = new char[pickedWord.length];
			int counter = 0;
			won = false;

			for(int i = 0; i < pickedWord.length; i++)
				unsolvedWord[i] = '*';

			while(!won)
			{
				System.out.print("(Guess) Enter a letter in word ");
				printUnsolvedWord(unsolvedWord);
				System.out.print(" > ");
				counter += revealUnsolvedWord(unsolvedWord, pickedWord);

				if(counter >= 6)
				{
					System.out.println("You lost.");
					won = true;
				}

				if(isWin(unsolvedWord))
					won = true;
			}

			System.out.print("The word is ");
			printUnsolvedWord(pickedWord);
			if(counter == 1)
				System.out.println(". You missed " + counter + " time.");
			else
				System.out.println(". You missed " + counter + " times.");

			System.out.print("Do you want to guess another word? Enter y or n: ");
			char playAgain = scan.next().toLowerCase().charAt(0);

			if(playAgain == 'n')
				playing = false;
		}
	}

	// Takes in user input then compares it to the letters in the random word. If the letter isn't in the word then it sends a 1 to update the counter outside of the method.
	public static int revealUnsolvedWord(char[] unsolvedWord, char[] pickedWord)
	{
		Scanner scan = new Scanner(System.in);
		char input = scan.next().toLowerCase().charAt(0);
		int isContained = 0;
		boolean twice = false;

		for(int i = 0; i < pickedWord.length; i++)
		{
			if(unsolvedWord[i] == input)
				twice = true;
			else if(input == pickedWord[i])
				unsolvedWord[i] = pickedWord[i];
			else
				isContained++;
		}

		if(twice == true)
			System.out.println("\t" + input + " is already in the word");

		if(isContained == pickedWord.length)
		{
			System.out.println("\t" + input + " is not in the word");
			return 1;
		}
		else
			return 0;
	}

	// Prints all the letters in the unsolved word
	public static void printUnsolvedWord(char[] word)
	{
		for(int i = 0; i < word.length; i++)
			System.out.print(word[i]);
	}

	// Determines if the user won
	public static boolean isWin(char[] word)
	{
		for(int i = 0; i < word.length; i++)
		{
			if(word[i] == '*')
				return false;
		}

		return true;
	}
}
