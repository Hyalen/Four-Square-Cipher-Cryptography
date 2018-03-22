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

	/**
	 * 
	 */
	public String trimString(String key) {
		String trimmed = "";

		for (int i = 0; i < key.length(); i++) {
			if(key.charAt(i) != ' ')
				trimmed += key.charAt(i);
		}

		return trimmed;
	}

	/**
	 * 
	 */
	public String toUpperCase(String key) {
		String upperCase = "";
		final int conversion = 32;

		for (int i = 0; i < key.length(); i++) {
			if(key.charAt(i) >= 97 && key.charAt(i) <= 122) {
				upperCase += (char)(key.charAt(i) - conversion);
			} else {
				upperCase += key.charAt(i);
			}
		}

		return upperCase;
	}

	/**
	 * 
	 */
	public boolean hasOddNumberOfChars(String key) {
		boolean verify = true;
		int size = 0;

		for (int i = 0; i < key.length(); i++) {
			size++;
		}

		if (size % 2 == 0) verify = false;

		return verify;
	}

	/**
	 * 
	 */
	public String catchSubstring(String word, int begin, int end) {
		String substring = "";

		for (int i = begin; i < end; i++) {
			substring += word.charAt(i);
		}

		return substring;
	}

	/**
	 * 
	 */
	public String convertToBigram(String key) {
		//trim the String first
		String trimmed = trimString(key);

		//then, convert it to upperCase letters
		String newKey = toUpperCase(trimmed);

		int size = newKey.length();

		//ArrayList<String> bigram = new ArrayList();
		String []bigram = new String[20]; 

		//if the number of characters in the String is an odd number, then
		//the bigram wont work properly. For that, it must be added the next
		//letter from the alphabet, according to the last character of the String
		boolean verify = hasOddNumberOfChars(newKey);
		String c = catchSubstring(newKey, size-1, size);

		if (verify && c.charAt(0) != 'Z') {
			newKey += (char)(c.charAt(0) + 1); 
		} else if (verify && c.charAt(0) == 'Z') {
			newKey += 'A';
		}

		int count = 0;

		for (int i = 0; i < newKey.length() / 2; i++) {
			bigram[i] = catchSubstring(newKey, count, count+2);
			count += 2;
		}

		return newKey;
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
	}
}