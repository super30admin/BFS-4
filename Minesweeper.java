package com.example.problems;

import java.util.*;

//Time Complexity : O(N^2) for all approach 
//Space Complexity : O(N) for BFS,O(1) for DFS
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

//Your code here along with comments explaining your approach


public class Minesweeper {
	private static boolean isDFS = false;

	public char[][] updateBoard(char[][] board, int[] click) {
		if (isDFS) {
			int x = click[0], y = click[1];

			if (board[x][y] == 'M') {
				board[x][y] = 'X';
				return board;
			}

			dfs(board, x, y);
			return board;
		}
		return bfs(board, click);
	}

	int[] dx = { -1, 0, 1, -1, 1, 0, 1, -1 };
	int[] dy = { -1, 1, 1, 0, -1, -1, 0, 1 };

	private char[][] bfs(char board[][], int click[]) {
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}

		int m = board.length, n = board[0].length;

		Queue<int[]> queue = new LinkedList<>();

		boolean[][] visited = new boolean[m][n];

		queue.offer(click);
		visited[click[0]][click[1]] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int x = cur[0], y = cur[1];
			int mines = getNumsOfBombs(board, x, y);

			if (mines == 0) {
				board[x][y] = 'B';
				for (int i = 0; i < 8; ++i) {
					int nx = x + dx[i], ny = y + dy[i];

					if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[0].length && !visited[nx][ny]
							&& board[nx][ny] == 'E') {
						queue.offer(new int[] { nx, ny });
						visited[nx][ny] = true;
					}
				}
			} else {
				board[x][y] = (char) (mines + '0');
			}
		}
		return board;
	}

	private void dfs(char[][] board, int x, int y) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E')
			return;

		int num = getNumsOfBombs(board, x, y);

		if (num == 0) {
			board[x][y] = 'B';
			for (int i = 0; i < 8; i++) {
				int nx = x + dx[i], ny = y + dy[i];
				dfs(board, nx, ny);
			}
		} else {
			board[x][y] = (char) ('0' + num);
		}

	}

	private int getNumsOfBombs(char[][] board, int x, int y) {
		int num = 0;
		for (int i = 0; i < 8; i++) {
			int nx = x + dx[i], ny = y + dy[i];
			if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length)
				continue;
			if (board[nx][ny] == 'M' || board[nx][ny] == 'X') {
				num++;
			}
		}
		return num;
	}

	public static void main(String args[]) {
		char board[][] = { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' },
				{ 'E', 'E', 'E', 'E', 'E' } };
		int click[] = { 3, 0 };

		Minesweeper sweeper = new Minesweeper();
		System.out.println(Arrays.deepToString(sweeper.updateBoard(board, click)));
	}
}