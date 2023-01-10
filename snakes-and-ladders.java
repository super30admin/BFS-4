import java.util.*;

class Solution {
    // tc is O(n*n)
    // sc is O(n*n)
    public int snakesAndLadders(int[][] board) {

        if (board == null || board.length == 0)
            return 0;

        int n = board.length; // row and col are same since square
        int[] grid = new int[n * n];
        int row = n - 1;
        int col = 0;

        int index = 0;
        int even = 0;

        int moves = 0; // this is the level in bfs
        Queue<Integer> q = new LinkedList<>();

        while (index < n * n) {
            if (board[row][col] == -1) {
                grid[index] = -1;
            } else {
                // there is snake or ladder
                grid[index] = board[row][col] - 1; // -1 because what is 1 here on board is 0 on grid

            }
            index++;
            if (even % 2 == 0) {

                col++;
                if (col == n) {
                    col--;
                    row--;
                    even++;
                }
            } else {
                // odd
                col--;
                if (col == -1) {
                    col++;
                    row--;
                    even++;
                }
            }

        }

        q.add(0); // we just added the index 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr == n * n - 1)
                    return moves;

                // simulating a dice roll
                for (int j = 1; j <= 6; j++) {
                    int baby = curr + j;
                    if (baby > n * n - 1)
                        continue;
                    if (grid[baby] == -1) {
                        // normal cell
                        q.add(baby);
                        grid[baby] = -2; // to mark as visited

                    } else {
                        // snake or ladder or already visited
                        if (grid[baby] > 0) {
                            // we did greater than 0 ti esure it is not -2

                            q.add(grid[baby]);
                            grid[baby] = -2;

                        }

                    }
                }
            }

            moves++;
        }

        return -1;

    }
}