import java.util.LinkedList;
import java.util.Queue;

/*
 *  The Time complexity of the algorithm is O(m*n)) where m*n is no of elements in matrix.
 *  Space complexity of the algorithm is O(n) .
 */

public class SnakesandLadders {

	public int snakesAndLadders(int[][] board) {

		int r = board.length;
		int[] moves = new int[r * r];

		int idx = 0, j = 0, i = r - 1;
		int even = 0;

		while (i >= 0 && j >= 0) {

			if (board[i][j] == -1) {

				moves[idx] = -1;
			} else {
				moves[idx] = board[i][j] - 1;
			}

			idx++;

			if (even % 2 == 0) {
				j++;

			} else {

				j--;
			}

			if (j == r) {
				even++;
				j--;
				i--;

			}

			if (j < 0) {
				even++;
				j++;
				i--;

			}

		}

		Queue<Integer> q = new LinkedList<>();
		int min = 0;
		q.add(0);
		moves[0] = -2;

		while (!q.isEmpty()) {

			int size = q.size();

			for (int k = 0; k < size; k++) {

				int curr = q.poll();

				if (curr == moves.length - 1)
					return min;

				for (int l = 1; l <= 6; l++) {

					int child = curr + l;

					if (child <= moves.length - 1 && moves[child] != -2) {
						if (moves[child] == -1) {

							q.add(child);
						} else {
							q.add(moves[child]);
						}

						moves[child] = -2;
					}

				}

			}

			min++;

		}

		return -1;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
