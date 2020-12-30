import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadders {
	public int snakesAndLadders(int[][] board) {

		if (board == null || board.length == 0)
			return 0;

		int r = board.length;
		int[] singleBoard = new int[r * r]; // we are converting 2-D board to single-D array(board)
		int index = 0;
		int i = r - 1;// 5
		int j = 0; // 0 board[5][0]
		int even = 0; // board is zig zag so we are maintaining even and odd direction
		
		//converting 2-D array to single Array or single board
		while (i >= 0 && j >= 0) {
			if (board[i][j] == -1) {
				singleBoard[index] = -1;

			} else {
				singleBoard[index] = board[i][j] - 1;
			}
			index++;

			if (even % 2 == 0) {

				j++;
			} else {

				j--;
			}
			// just checking
			if (j == r) {
				i--;
				j--;
				even++;

			}
			if (j < 0) {
				i--;
				j++;
				even++;
			}

		}
		int minMoves = 0;

		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		singleBoard[0] = -2;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {
				int curr = q.poll();
				if (curr == singleBoard.length - 1) {
					return minMoves;
				}
				for (int l = 1; l <= 6; l++) {
					int baby = curr + l;
					if (baby <= singleBoard.length - 1 && singleBoard[baby] != -2) {
						if (singleBoard[baby] == -1) {
							q.add(baby);
						} else {
							q.add(singleBoard[baby]);
						}
						singleBoard[baby] = -2;
					}

				}

			}
			minMoves++;
		}

		return -1;
	}
}
