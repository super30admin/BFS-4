import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// Time Complexity : O(m*n) where m = number of rows, n = number of columns
// Space Complexity : O(m*n) where m = number of rows, n = number of columns
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
//529. Minesweeper (Medium) - https://leetcode.com/problems/minesweeper/
//Time Complexity : O(m*n) where m = number of rows, n = number of columns
//Space Complexity : O(m*n) where m = number of rows, n = number of columns
//class Solution {
//
//	int[][] dirs;
//
//	public char[][] updateBoard(char[][] board, int[] click) {
//		if (board == null || board.length == 0)
//			return board;
//
//		if (board[click[0]][click[1]] == 'M') {
//			board[click[0]][click[1]] = 'X';
//			return board;
//		}
//
//		int m = board.length, n = board[0].length;
//		dirs = new int[][] { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };
//		Queue<int[]> queue = new LinkedList<>();
//		queue.add(click);
//		board[click[0]][click[1]] = 'B';
//
//		while (!queue.isEmpty()) {
//			int[] curr = queue.poll();
//
//			int count = countMines(board, curr[0], curr[1], m, n);
//
//			if (count == 0) {
//				for (int[] dir : dirs) {
//					int nr = curr[0] + dir[0];
//					int nc = curr[1] + dir[1];
//
//					if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
//						queue.add(new int[] { nr, nc });
//						board[nr][nc] = 'B';
//					}
//				}
//			} else {
//				board[curr[0]][curr[1]] = (char) (count + '0');
//			}
//		}
//
//		return board;
//	}
//
//	private int countMines(char[][] board, int i, int j, int m, int n) {
//		int count = 0;
//
//		for (int[] dir : dirs) {
//			int nr = i + dir[0];
//			int nc = j + dir[1];
//
//			if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
//				count++;
//			}
//		}
//
//		return count;
//	}
//}

//Time Complexity : O(m*n) where m = number of rows, n = number of columns
//Space Complexity : O(m*n) where m = number of rows, n = number of columns
class Solution {

	int[][] dirs;

	public char[][] updateBoard(char[][] board, int[] click) {
		if (board == null || board.length == 0)
			return board;

		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}

		int m = board.length, n = board[0].length;
		dirs = new int[][] { { 0, -1 }, { -1, 0 }, { 0, 1 }, { 1, 0 }, { -1, -1 }, { -1, 1 }, { 1, 1 }, { 1, -1 } };
		dfs(board, click[0], click[1], m, n);

		return board;
	}

	private void dfs(char[][] board, int i, int j, int m, int n) {
		// base
		if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == 'B')
			return;

		// logic
		board[i][j] = 'B';
		int count = countMines(board, i, j, m, n);

		if (count == 0) {
			for (int[] dir : dirs) {
				int nr = i + dir[0];
				int nc = j + dir[1];
				dfs(board, nr, nc, m, n);
			}
		} else {
			board[i][j] = (char) (count + '0');
		}
	}

	private int countMines(char[][] board, int i, int j, int m, int n) {
		int count = 0;

		for (int[] dir : dirs) {
			int nr = i + dir[0];
			int nc = j + dir[1];

			if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
				count++;
			}
		}

		return count;
	}
}