import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class FourSquareCipher {
	//10x10 matrix that will store four 5x5 matrices (quadrants)
	private char[][] matrix;
	
	//The unordered ArrayList will populate both the upper-right and bottom-left matrices
	private static ArrayList<Character> randomChar;

	//The ordered ArrayList will populate both the upper-left and bottom-right matrices
	private static ArrayList<Character> orderedChar;

	/**
	 * 
	 */
	public FourSquareCipher() {
		matrix = new char[10][10];
		randomChar = new ArrayList<>();
		orderedChar = new ArrayList<>();
		createArrayLists();
		populateMatrix();
	}

	/**
	 * 
	 */
	public static void createArrayLists() {
		for (int i = 0; i <= 25; i++) {
			//I randomly chose to put the letter J away
			if(i != 9) {
				char c = (char) (i + 'A');
				randomChar.add(c);
				orderedChar.add(c);
			}
		}
	}
	
	/**
	 * 
	 */
	public void populateMatrix() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if(i == 0 && j == 5) {
					Collections.shuffle(randomChar);
				}

				if(i == 5 && j == 0) {
					Collections.shuffle(randomChar);
				}

				if(i >= 0 && i <= 4 && j <= 4) {
					matrix[i][j] = orderedChar.remove();
				} 
				
				else if(i >= 5 && j <= 9 && j >= 5) {
					//do something else
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			System.out.println("");
			for (int j = 0; j < 10; j++) {
				System.out.print(matrix[i][j] + " ");
			}
		}
	}

	public static void main(String[] args) {
		FourSquareCipher f = new FourSquareCipher();
	}
}
