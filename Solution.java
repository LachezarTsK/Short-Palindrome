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

			int current = (int) inputString.charAt(i) % 97;

			for (int c = 'a' % 97; c <= 'z' % 97; c++) {

				int previous = four[current][c][c][current];
				four[current][c][c][current] = (four[current][c][c][current] + three[current][c][c]) % modulus;
				three[c][current][current] = (three[c][current][current] + two[c][current]) % modulus;
				two[c][current] = (two[c][current] + one[c]) % modulus;
				totalPalindromes += four[current][c][c][current] - previous;
			}

			one[current]++;
		}

		System.out.println(totalPalindromes % modulus);
	}
}
