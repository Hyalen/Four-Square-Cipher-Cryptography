import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FourSquareCipher {
	/**
	 * four squares that will be disposed with the following pattern:
	 * --
	 * square1 - upper-left and square4 - bottom-right = stores the alphabet, excluding
	 * the letter J (that was chosen randomly), dropping any duplicate letters
	 * --
	 * square2 - upper-right and square3 - bottom-left = stores the keywords, following
	 * the same rules as the above, but stored in random positions
	 */
	private char[][] square1;
	private char[][] square2;
	private char[][] square3;
	private char[][] square4;

	//The unordered ArrayLists will populate both the upper-right and bottom-left squares
	private static ArrayList<Character> randomChar1;
	private static ArrayList<Character> randomChar2;

	/**
	 * 
	 */
	public FourSquareCipher() {
		square1 = new char[5][5];
		square2 = new char[5][5];
		square3 = new char[5][5];
		square4 = new char[5][5];
		randomChar1 = new ArrayList<Character>();
		randomChar2 = new ArrayList<Character>();

		createArrayList();
		createNormalSquare();
		createKeywordSquare();
	}

	/**
	 * 
	 */
	public static void createArrayList() {
		for (int i = 0; i <= 25; i++) {
			if (i == 9) i++;
			char c = (char) (i + 'A');
			randomChar1.add(c);
			randomChar2.add(c);
		}

		Collections.shuffle(randomChar1);
		Collections.shuffle(randomChar2);
	}
	
	/**
	 * 
	 */
	public void createNormalSquare() {
		int count = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if(count == 9) count++;
				square1[i][j] = (char)('A' + count);
				square4[i][j] = (char)('A' + count);
				count++;
			}
		}	
	}

	public void createKeywordSquare() {
		int count1 = 0;
		int count2 = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				square2[i][j] = randomChar1.get(count1);
				count1++;
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				square3[i][j] = randomChar2.get(count2);
				count2++;
			}
		}
	}

	public String trimString(String key) {
		String trimmed = "";

		for (int i = 0; i < key.length(); i++) {
			if(key.charAt(i) != ' ')
				trimmed += key.charAt(i);
		}

		return trimmed;
	}

	public String convertToBigram(String key) {
		String bigram = trimString(key);
		/* 
		for (int i = 0; i < word.length(); i++) {

		} */
		return bigram;
	}

	/**
	 * 
	 */
	public void Encrypt() {
		
	}

	/**
	 * 
	 */
	public void Decrypt() {

	}

	public static void main(String[] args) {
		FourSquareCipher f = new FourSquareCipher();
		String word = "a a a a a a a           x e r e ca asdkjlhasil s s                      .";
		System.out.println(f.convertToBigram(word));
	}
}
