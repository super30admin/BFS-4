import java.util.LinkedList;
import java.util.Queue;
//Time Complexity : O(M*N)
//Space Complexity : O(M*N)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Apply BFS on the current click. Push the click to the queue. Check if
 * adjacent indices have any mines. If so, update the current index with the
 * mines count and continue without checking for its adjacent indices. If no
 * mines are present, then check for its 8 directions and push them to the queue
 * only if there are no mines in their adjacent directions and update them to
 * 'B'. Finally return the updated board.
 *
 */
class Solution {
	private static final int[][] DIRS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 },
			{ -1, 1 } };

	public char[][] updateBoard(char[][] board, int[] click) {
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}

		Queue<int[]> queue = new LinkedList<>();
		queue.offer(click);
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int mines = getMinesCount(board, cur[0], cur[1]);
			if (mines != 0) {
				board[click[0]][click[1]] = (char) (mines + '0');
				continue;
			}
			board[click[0]][click[1]] = 'B';
			for (int[] d : DIRS) {
				int x = cur[0] + d[0];
				int y = cur[1] + d[1];
				if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E') {
					continue;
				}
				mines = getMinesCount(board, x, y);
				if (mines != 0) {
					board[x][y] = (char) (mines + '0');
					continue;
				}
				board[x][y] = 'B';
				queue.offer(new int[] { x, y });
			}
		}

		return board;
	}

	private int getMinesCount(char[][] board, int x, int y) {
		int mines = 0;
		for (int[] d : DIRS) {
			int r = x + d[0];
			int c = y + d[1];
			if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'M') {
				mines++;
			}
		}
		return mines;
	}
}