// Time Complexity : O(n*n) n -> Length of board
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem2;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
	public int snakesAndLadders(int[][] board) {
		if (board == null || board.length == 0) {
			return 0;
		}

		int[] moves = flattenBoard(board);
		Queue<Integer> queue = new LinkedList<>();
		queue.add(1);
		moves[1] = -2;

		int n = board.length;
		int min = 0;

		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int curr = queue.poll();
				if (curr == n * n) {
					return min;
				}

				for (int j = 1; j < 7; j++) {
					int child = curr + j;
					if (child > n * n) {
						break;
					}
					if (moves[child] != -2) {
						if (moves[child] == -1) {
							queue.add(child);
						} else {
							queue.add(moves[child]);
						}
						moves[child] = -2;
					}
				}
			}
			min++;
		}

		return -1;

	}

	private int[] flattenBoard(int[][] board) {
		int n = board.length;
		int[] moves = new int[n * n + 1];

		int i = n - 1;
		int j = 0;
		int idx = 1;
		int even = 0;

		while (i >= 0 && j >= 0) {
			moves[idx] = board[i][j];
			idx++;

			if (even % 2 == 0) {
				j++;
				if (j == n) {
					j--;
					i--;
					even++;
				}
			} else {
				j--;
				if (j == -1) {
					j++;
					i--;
					even++;
				}
			}
		}

		return moves;
	}

	public static void main(String[] args) {
		SnakesAndLadders obj = new SnakesAndLadders();
		int[][] board = { { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, -1, -1, -1, -1, -1 },
				{ -1, 35, -1, -1, 13, -1 }, { -1, -1, -1, -1, -1, -1 }, { -1, 15, -1, -1, -1, -1 } };

		System.out.println("Minimum number of moves to reach destination: " + obj.snakesAndLadders(board));
	}

}
