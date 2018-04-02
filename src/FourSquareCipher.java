import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FourSquareCipher {
	/**
	 * four squares that will be disposed with the following pattern:
	 * -----
	 * squareAlphabet will represent both upper-left and bottom-right squares. It will store the alphabet,
	 * excluding the letter J (that was chosen randomly), dropping any duplicate letters
	 * -----
	 * squareKeyword1 will represent the upper-right square and squareKeyword2 the bottom-left. They will
	 * store the keywords, following the same rules as the above, but stored in random positions
	 */
	private char[][] squareAlphabet;
	private char[][] squareKeyword1;
	private char[][] squareKeyword2;

	//The unordered ArrayLists will populate both the upper-right and bottom-left squares
	private static ArrayList<Character> randomChar1;
	private static ArrayList<Character> randomChar2;

	//variables
	private String trimmed;
	private String newKey;
	private String[] bigram;
	private String[] encryptedMsg;
	private String[] decryptedMsg;
	private int sizeBigram;

	/**
	 * 
	 */
	public FourSquareCipher() {
		squareAlphabet = new char[5][5];
		squareKeyword1 = new char[5][5];
		squareKeyword2 = new char[5][5];

		randomChar1 = new ArrayList<Character>();
		randomChar2 = new ArrayList<Character>();

		trimmed = "";
		newKey = "";
		sizeBigram = 0;

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
				squareAlphabet[i][j] = (char)('A' + count);
				count++;
			}
		}	
	}

	public void createKeywordSquare() {
		int count1 = 0;
		int count2 = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				squareKeyword1[i][j] = randomChar1.get(count1);
				count1++;
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				squareKeyword2[i][j] = randomChar2.get(count2);
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

		for (int i = 0; i < key.length(); i++) size++;

		if (size % 2 == 0) verify = false;

		return verify;
	}

	/**
	 * 
	 */
	public String catchSubstring(String word, int begin, int end) {
		String substring = "";

		for (int i = begin; i < end; i++) substring += word.charAt(i);

		return substring;
	}

	/**
	 * 
	 */
	public void convertToBigram(String key) {
		//trim the String first
		trimmed = trimString(key);

		//then, convert it to upperCase letters
		newKey = toUpperCase(trimmed);
		boolean verify = hasOddNumberOfChars(newKey);

		if(verify) sizeBigram = (newKey.length() / 2) + 1;
		else sizeBigram = newKey.length() / 2;

		bigram = new String[sizeBigram]; 

		String c = catchSubstring(newKey, newKey.length()-1, newKey.length());

		//if the number of characters inside the String is an odd number, then
		//the bigram wont work properly. For that, it must be added the next
		//letter from the alphabet, according to the last character of the String
		if (verify && c.charAt(0) != 'Z') {
			newKey += (char)(c.charAt(0) + 1); 
		} else if (verify && c.charAt(0) == 'Z') {
			newKey += 'A';
		}

		int count = 0;

		for (int i = 0; i < bigram.length; i++) {
			bigram[i] = catchSubstring(newKey,  count, count+2);
			count += 2;
		}

/* 		for (int i = 0; i < bigram.length; i++) {
			System.out.println(bigram[i]);
		} */
	}

	/**
	 * 
	 */
	public void Encrypt() {
		int colTopLeft = 0, colBotRight = 0, lineTopLeft = 0, lineBotRight = 0;
		char first, second;
		encryptedMsg = new String[sizeBigram];

		for(int i = 0; i < bigram.length; i++) {
			first = bigram[i].charAt(0);
			second = bigram[i].charAt(1);
			for(int m = 0; m < 5; m++) {
				for(int n = 0; n < 5; n++) {
					//if both chars are equal, the second will be at none of the if statements
					if(squareAlphabet[m][n] == first && squareAlphabet[m][n] == second) {
						System.out.println("Passou no primeiro hehehe");
						lineTopLeft = m;
						colTopLeft = n;
						lineBotRight = m;
						colBotRight = n;
					} else if(squareAlphabet[m][n] == first) {
						System.out.println("Passou no segundo hehehe");
						lineTopLeft = m;
						colTopLeft = n;
					} else if(squareAlphabet[m][n] == second) {
						System.out.println("Passou no terceiro hehehe");
						lineBotRight = m;
						colBotRight = n;
					}
				}
			}
			encryptedMsg[i] = "" + squareKeyword1[lineTopLeft][colBotRight] + squareKeyword2[lineBotRight][colTopLeft];
		}
	}

	/**
	 * 
	 */
	public void Decrypt() {
		int colTopRight = 0, colBotLeft = 0, lineTopRight = 0, lineBotLeft = 0;
		char first, second;
		decryptedMsg = new String[sizeBigram];

		for(int i = 0; i < encryptedMsg.length; i++) {
			first = encryptedMsg[i].charAt(0);
			second = encryptedMsg[i].charAt(1);
			System.out.println(first + " " + second);
			for(int m = 0; m < 5; m++) {
				for(int n = 0; n < 5; n++) {
					if(squareKeyword1[m][n] == first && squareKeyword2[m][n] == second) {
						//System.out.println("1 - " + m + "" + n);
						lineTopRight = m;
						colTopRight = n;
						lineBotLeft = m;
						colBotLeft = n;
					} else if(squareKeyword1[m][n] == first) {
						lineTopRight = m;
						colTopRight = n;
					} else if(squareKeyword2[m][n] == second) {
						//System.out.println("2 - " + m + "" + n);
						lineBotLeft = m;
						colBotLeft = n;
					}
				}
			}
			decryptedMsg[i] = "" + squareAlphabet[lineTopRight][colBotLeft] + squareAlphabet[lineBotLeft][colTopRight];
		}
	}

	public void print() {
		System.out.println("Alphabet Square");
		//print the alphabet square
		for (int i = 0; i < 5; i++) {
			System.out.println("");
			for (int j = 0; j < 5; j++) {
				System.out.print(squareAlphabet[i][j] + " ");
			}
		}

		System.out.println("");
		System.out.println("Keyword Square 1");
		//prints the keyword square 1
		for (int i = 0; i < 5; i++) {
			System.out.println("");
			for (int j = 0; j < 5; j++) {
				System.out.print(squareKeyword1[i][j] + " ");
			}
		}

		System.out.println("");
		System.out.println("Keyword Square 2");
		//prints the keyword square 2
		for (int i = 0; i < 5; i++) {
			System.out.println("");
			for (int j = 0; j < 5; j++) {
				System.out.print(squareKeyword2[i][j] + " ");
			}
		}

		System.out.println("");
		System.out.println("Bigram to be encrypted: ");
		//prints the bigram
		for (int i = 0; i < encryptedMsg.length; i++) {
			System.out.print(bigram[i]);
		}

		System.out.println("");
		System.out.println("Encrypted message: ");
		//prints the encrypted message
		for (int i = 0; i < encryptedMsg.length; i++) {
			System.out.print(encryptedMsg[i]);
		}

		System.out.println("");
		System.out.println("Decrypted message: ");
		//prints the decrypted message
		for (int i = 0; i < decryptedMsg.length; i++) {
			System.out.print(decryptedMsg[i]);
		}

		System.out.println("");
	}

	public static void main(String[] args) {
		FourSquareCipher f = new FourSquareCipher();
		f.convertToBigram("Hello World");
		f.Encrypt();
		f.Decrypt();
		f.print();
	}
}