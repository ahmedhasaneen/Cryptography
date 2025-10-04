package com.example.demo.crypto;

import java.util.*;

public class PlayfairCipher {

	private char[][] matrix = new char[5][5];
	private static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

	public PlayfairCipher(String key) {
		generateMatrix(key);
	}
	
// generate the matrix with the key
	private void generateMatrix(String key) {

		String keyString = (key + ALPHABET).toUpperCase().replaceAll("[J]", "I");
		Set<Character> seen = new LinkedHashSet<>();

		for (char c : keyString.toCharArray()) {
			if (Character.isLetter(c)) {
				seen.add(c);
			}
		}

		Iterator<Character> iterator = seen.iterator();
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				matrix[i][j] = iterator.next();

			}
		}
	}

	private String formatText(String text) {
		text = text.toUpperCase().replaceAll("[J]", "I").replaceAll("[^A-Z]", "");
		StringBuilder formatted = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			formatted.append(text.charAt(i));
			if (i < text.length() - 1 && text.charAt(i) == text.charAt(i + 1)) {
				formatted.append('X');
			}
		}

		if (formatted.length() % 2 != 0) {
			formatted.append('X');
		}

		return formatted.toString();
	}

	private int[] findPosition(char c) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (matrix[i][j] == c) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}

//	this is the main step
	private String process(String text, boolean encryptOrNot) {
		text = formatText(text);

		StringBuilder result = new StringBuilder();

		for (int i = 0; i < text.length(); i += 2) {
//			store the (row, column) for the two char
			int[] pos1 = findPosition(text.charAt(i));
			int[] pos2 = findPosition(text.charAt(i + 1));

//			if they in the same row
			if (pos1[0] == pos2[0]) {
				result.append(matrix[pos1[0]] [(pos1[1] + (encryptOrNot ? 1 : 4)) % 5]);
				result.append(matrix[pos2[0]] [(pos2[1] + (encryptOrNot ? 1 : 4)) % 5]);

//			if they in the same column
			} else if (pos1[1] == pos2[1]) {
				result.append(matrix[(pos1[0] + (encryptOrNot ? 1 : 4)) % 5] [pos1[1]]);
				result.append(matrix[(pos2[0] + (encryptOrNot ? 1 : 4)) % 5] [pos2[1]]);

//				rectangle
			} else {
				result.append(matrix[pos1[0]] [pos2[1]]);
				result.append(matrix[pos2[0]] [pos1[1]]);
			} 
		}

		return result.toString();

	}

	public String encrypt(String text) {
		return process(text, true);
	}

	public String decrypt(String text) {
		return process(text, false);
	}

//	this for return the plantext
	public String restor(String text) {
		StringBuilder restorText = new StringBuilder();

		for (int i = 0; i < text.length(); i++) {
			char current = text.charAt(i);
			if (current == 'X' && (i > 0 && i < text.length() - 1) && text.charAt(i - 1) == text.charAt(i + 1)) {
				continue;
			}

			restorText.append(current);

		}
		if (restorText.length() > 0 && restorText.charAt(restorText.length() - 1) == 'X') {
			restorText.deleteCharAt(restorText.length() - 1);
		}

		return restorText.toString();

	}

	public void printMatrix() {
		for (char[] row : matrix) {
			System.out.println(Arrays.toString(row));
		}
	}

}
