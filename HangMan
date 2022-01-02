import java.util.Scanner;

public class HangMan 
{
	public static void main(String[] args) 
	{
		String[] words = {"spider", "criticism", "belly", "legend", "width", "invisible", "summit", "joy", "hour", "rotate", "blind", 
				"acute", "asset", "virtue", "sound", "base", "unique", "personal", "legislature", "deficit", "girl", "indication", 
				"small", "wonder", "government", "glow", "mature", "decisive", "marriage", "skip", "midnight", "leave", "establish", 
				"cheque", "fox", "put", "banquet", "original", "smash", "tender", "plagiarize", "mechanical", "employ", "conservation",
				"peasant", "party", "exempt", "helicopter", "parachute", "enhance"};
		Scanner scan = new Scanner(System.in);
		boolean won = false, playing = true;
		
		while(playing) 
		{
			char[] pickedWord = randomWordPicker(words);
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
			
			System.out.print("Do you want to guess another word? Enter y or n>");
			char playAgain = scan.next().toLowerCase().charAt(0);
			
			if(playAgain == 'n')
				playing = false;
		}
	}

	// Picks a random word from the string array and turns it into a char array for easy comparisons
	public static char[] randomWordPicker(String[] words)
	{
		String randomWord = words[((int)(Math.random() * words.length))];
		char[] word = new char[randomWord.length()];

		for(int i = 0; i < randomWord.length(); i++)
			word[i] = randomWord.charAt(i);

		return word;
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
