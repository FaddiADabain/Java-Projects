import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomWord {
    // Picks a random word from the text file and turns it into a char array for easy comparisons
    public char[] randomWordPicker() {
        String dir = System.getProperty("user.dir");
        File wordFile = new File(dir + "\\src\\words.txt");
        char[] word = null;

        try(RandomAccessFile file = new RandomAccessFile(wordFile, "r")) 
        {
            long pos = (long) (Math.random() * file.length());
            file.seek(pos);
            file.readLine();
            String randomWord = file.readLine();

            word = new char[randomWord.length()];

            for (int i = 0; i < randomWord.length(); i++)
                word[i] = randomWord.charAt(i);

        } catch (IOException e) {
            e.getMessage();
        }
        return word;
    }
}
