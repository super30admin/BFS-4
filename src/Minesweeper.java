import java.util.LinkedList;
import java.util.Queue;

/*
 *  The Time complexity of the algorithm is O(m*n)) where m*n is no of elements in matrix.
 *  Space complexity of the algorithm is O(n) .
 */

public class Minesweeper {

	int[][] dirs;
	int m, n;

	public char[][] updateBoard(char[][] board, int[] click) {

		if (board == null || board.length == 0) {
			return board;
		}

		dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };

		m = board.length;
		n = board[0].length;

		if (board[click[0]][click[1]] == 'M') {

			board[click[0]][click[1]] = 'X';
			return board;
		}

		Queue<int[]> q = new LinkedList<>();

		board[click[0]][click[1]] = 'B';
		q.add(new int[] { click[0], click[1] });

		while (!q.isEmpty()) {

			int[] curr = q.poll();

			int mines = getMinesCount(board, curr[0], curr[1]);

			if (mines == 0) {

				for (int[] dir : dirs) {
					int r = curr[0] + dir[0];
					int c = curr[1] + dir[1];

					if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E') {
						board[r][c] = 'B';
						q.add(new int[] { r, c });
					}

				}

			} else {

				board[curr[0]][curr[1]] = (char) (mines + '0');

			}

		}

		return board;
	}

	private int getMinesCount(char[][] board, int i, int j) {

		int result = 0;

		for (int[] dir : dirs) {

			int r = i + dir[0];
			int c = j + dir[1];

			if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M')
				result++;

		}

		return result;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
