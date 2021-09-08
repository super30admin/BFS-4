// Time Complexity : O(m*n) m -> Number of rows, n -> Number of columns
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
	int[][] dirs;
	int m;
	int n;

	/********************* DFS *********************/
	public char[][] updateBoardDFS(char[][] board, int[] click) {
		if (board == null || board.length == 0) {
			return board;
		}

		m = board.length;
		n = board[0].length;
		dirs = new int[][] { { -1, 0 }, { -1, -1 }, { -1, 1 }, { 1, 0 }, { 1, 1 }, { 1, -1 }, { 0, 1 }, { 0, -1 } };
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}

		dfs(board, click[0], click[1]);
		return board;
	}

	private void dfs(char[][] board, int row, int col) {
		// Base
		if (row < 0 || row >= m || col < 0 || col >= n || board[row][col] != 'E') {
			return;
		}

		// Logic
		board[row][col] = 'B';
		int mines = getSurroundingMines(board, row, col);
		if (mines != 0) {
			board[row][col] = (char) (mines + '0');
		} else {
			for (int[] dir : dirs) {
				int nr = dir[0] + row;
				int nc = dir[1] + col;
				dfs(board, nr, nc);
			}
		}
	}

	/********************* BFS *********************/
	public char[][] updateBoard(char[][] board, int[] click) {
		if (board == null || board.length == 0) {
			return board;
		}

		m = board.length;
		n = board[0].length;
		dirs = new int[][] { { -1, 0 }, { -1, -1 }, { -1, 1 }, { 1, 0 }, { 1, 1 }, { 1, -1 }, { 0, 1 }, { 0, -1 } };
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}

		Queue<int[]> queue = new LinkedList<>();
		queue.add(click);
		board[click[0]][click[1]] = 'B';

		while (!queue.isEmpty()) {
			int[] curr = queue.poll();
			int mines = getSurroundingMines(board, curr[0], curr[1]);
			if (mines != 0) {
				board[curr[0]][curr[1]] = (char) (mines + '0');
			} else {
				for (int[] dir : dirs) {
					int nr = dir[0] + curr[0];
					int nc = dir[1] + curr[1];
					if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E') {
						queue.add(new int[] { nr, nc });
						board[nr][nc] = 'B';
					}
				}
			}
		}
		return board;
	}

	private int getSurroundingMines(char[][] board, int row, int col) {
		int mines = 0;
		for (int[] dir : dirs) {
			int nr = dir[0] + row;
			int nc = dir[1] + col;

			if (nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'M') {
				mines++;
			}
		}
		return mines;
	}

	private void print(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Minesweeper obj = new Minesweeper();
		char[][] board = { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' },
				{ 'E', 'E', 'E', 'E', 'E' } };
		int[] click = { 3, 0 };

		System.out.println("Board after first move: ");
		obj.updateBoard(board, click);
		obj.print(board);
	}

}
