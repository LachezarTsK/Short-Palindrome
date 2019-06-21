import java.util.Scanner;

public class Solution {
	/**
	 * Array "one": frequency of each character.
	 * 
	 * Array "two": frequency of the combinations of the first two characters that
	 * could potentially form a palindrome.
	 * 
	 * Array "three": frequency of the combinations of the first three characters
	 * that could potentially form a palindrome.
	 * 
	 * Array "four": frequency of the combinations of four characters that actually
	 * form a palindrome.
	 */
	private static int[] one = new int[26];
	private static int[][] two = new int[26][26];
	private static int[][][] three = new int[26][26][26];
	private static int[][][][] four = new int[26][26][26][26];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String inputString = scanner.next();
		scanner.close();

		long totalPalindromes = 0;
		int modulus = (int) Math.pow(10, 9) + 7;

		/**
		 * ASCII Table: 'a'=97;
		 */
		for (int i = 0; i < inputString.length(); i++) {

			int currentChar = (int) inputString.charAt(i) % 97;

			for (int c = 'a' % 97; c <= 'z' % 97; c++) {

				int previousValue = four[currentChar][c][c][currentChar];
				four[currentChar][c][c][currentChar] = (four[currentChar][c][c][currentChar] + three[currentChar][c][c]) % modulus;
				three[c][currentChar][currentChar] = (three[c][currentChar][currentChar] + two[c][currentChar]) % modulus;
				two[c][currentChar] = (two[c][currentChar] + one[c]) % modulus;
				totalPalindromes += four[currentChar][c][c][currentChar] - previousValue;
			}

			one[currentChar]++;
		}

		System.out.println(totalPalindromes % modulus);
	}
}
