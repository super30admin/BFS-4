package bfs2;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
/*
 * Approach: Do a BFS,
 * For each 'E', check if any of the 8 neighbor cells has 'M' and get count of 'M'. 
 * If count of M > 0 then dont add neighbors to queue and put the count of M into thhat cell.
 * If count of M == 0 then add all 8 neighbors into queue and mark the current cell as 'B'.
 * to avoid duplication and infinite looping, maintain a boolean array of visited nodes and add into Queue if only not visited.
 * 
 * TC: O(8 * N) =  O(N) N: row * col
 * SC: O(2 * N) = O(N)
 * 
 */


public class Minesweeper {

	class cordinate {
		int row;
		int col;

		public cordinate(int _row, int _col) {
			row = _row;
			col = _col;
		}

	}

	public char[][] updateBoard(char[][] board, int[] click) {

		// default value is false
		boolean[][] visited = new boolean[board.length][board[0].length];
		Queue<cordinate> q = new ArrayDeque<>();

		int intialRow = click[0];
		int initialCol = click[1];

		if (board[intialRow][initialCol] == 'M') {
			board[intialRow][initialCol] = 'X';
			return board;
		}

		q.offer(new cordinate(intialRow, initialCol));
		List<cordinate> valid = new ArrayList<cordinate>();

		while (!q.isEmpty()) {

			cordinate co = q.poll();

			int rowC = co.row;
			int colC = co.col;

			valid.clear();
			if (board[rowC][colC] == 'E') {
				int Ms = checkMs(board, rowC, colC, valid, visited);

				if (Ms > 0) {
					board[rowC][colC] = (char) (((int) '0') + Ms);
				} else {
					board[rowC][colC] = 'B';
					for (cordinate validCo : valid) {
						q.offer(validCo);
						visited[validCo.row][validCo.col] = true;
					}
				}
			}

		}

		return board;
	}

	private int checkMs(char[][] board, int row, int col, List<cordinate> valid, boolean[][] visited) {
		int count = 0;
		int[] rows = { 0, 1, -1, 0, 1, -1, 1, -1 };
		int[] cols = { 1, 0, 0, -1, 1, -1, -1, 1 };

		for (int i = 0; i < rows.length; i++) {
			int tempRow = row + rows[i];
			int tempCol = col + cols[i];

			if (tempRow >= 0 && tempRow < board.length && tempCol >= 0 && tempCol < board[0].length) {
				if (board[tempRow][tempCol] == 'M')
					count++;

				if (!visited[tempRow][tempCol])
					valid.add(new cordinate(tempRow, tempCol));
			}
		}

		return count;
	}
}
