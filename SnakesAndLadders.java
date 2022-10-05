import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// Time Complexity :
// Space Complexity :
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
//909. Snakes and Ladders (Medium) - https://leetcode.com/problems/snakes-and-ladders/
//Time Complexity : O(n*n) where n = number of rows == number of columns
//Space Complexity : O(n*n) where n = number of rows == number of columns
class Solution {
	public int snakesAndLadders(int[][] board) {
		if (board == null || board[0].length == 0)
			return 0;

		// flatten the array
		boolean flag = true;
		int m = board.length, n = board[0].length;

		int[] arr = new int[n * n];
		int index = 0;

		int i = n - 1, j = 0;

		while (index < arr.length) {
			if (board[i][j] == -1) {
				arr[index] = -1;
			} else {
				arr[index] = board[i][j] - 1;
			}
			index++;

			if (flag) {
				j++;

				if (j == n) {
					i--;
					j--;
					flag = false;
				}
			} else {
				j--;

				if (j == -1) {
					j++;
					i--;
					flag = true;
				}
			}
		}

		int moves = 0;

		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		arr[0] = -2;

		while (!queue.isEmpty()) {
			int size = queue.size();

			for (int k = 0; k < size; k++) {
				int curr = queue.poll();

				for (int d = 1; d < 7; d++) {
					int child = curr + d;

					if (arr[child] != -2) {
						if (arr[child] >= 0) {
							if (arr[child] == n * n - 1)
								return moves + 1;
							queue.add(arr[child]);
						} else {
							if (child == n * n - 1)
								return moves + 1;
							queue.add(child);
						}

						arr[child] = -2;
					}
				}
			}

			moves++;
		}

		return -1;
	}
}