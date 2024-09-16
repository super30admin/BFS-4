package bfs2;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/*
 * approach: BFS As discussed in class
 * TC: O(n*n) => O(n^2)
 * SC: O(n^2)
 * 
 */
public class SnakeLadder {

	public int snakesAndLadders(int[][] board) {

		Map<Integer, Integer> seenMap = new HashMap<Integer, Integer>();
		Queue<Integer> q = new ArrayDeque<Integer>();
		int N = board.length;
		q.offer(1);
		seenMap.put(1, 0);
		while (!q.isEmpty()) {

			int poped = q.poll();

			if (poped == N * N)
				return seenMap.get(poped);

			for (int next = poped + 1; next <= Math.min(poped + 6, N * N); next++) {
				int reallyNext = next;
				int[][] cordinate = getCordinates(next, N);
				int row = cordinate[0][0];
				int col = cordinate[0][1];

				if (board[row][col] != -1)
					reallyNext = board[row][col];

				if (!seenMap.containsKey(reallyNext)) {
					q.offer(reallyNext);
					seenMap.put(reallyNext, seenMap.get(poped) + 1);
				}
			}
		}
		return -1;
	}

	private int[][] getCordinates(int pos, int N) {

		int[][] cordinate = new int[1][2];

		int quo = (pos - 1) / N;
		int rem = (pos - 1) % N;

		int row = N - 1 - quo;
		int col = row % 2 != N % 2 ? rem : N - 1 - rem;

		cordinate[0][0] = row;
		cordinate[0][1] = col;

		return cordinate;
	}
}