/*
 * TC: O( m * n ) (BFS visits every cell once)
 * SC: O( m * n ) (flatBoard, queue)
 */
import java.util.ArrayDeque;
import java.util.Queue;

public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        // unroll the board into a 1D array
        int[] flatBoard = unrollBoard(board);

        // BFS to find the shortest path from 0 to n*n-1
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        flatBoard[0] = -2; // mark as visited

        int moves = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                // if we reached the last cell, return the number of moves
                if (curr == n * n - 1) {
                    return moves;
                }
                // try all possible moves from the current cell
                for (int j = 1; j <= 6; j++) {
                    int dest = curr + j;

                    // if we go beyond the last cell, skip this move
                    if (dest >= n * n) continue;

                    // if the destination cell is already visited, skip this move
                    if (flatBoard[dest] == -2) continue;

                    // if the destination cell is a snake or ladder, move to the end
                    // of the snake or ladder
                    if (flatBoard[dest] == -1) {
                        flatBoard[dest] = -2;
                        q.add(dest);
                    }
                    // else just go to the destination cell
                    else {
                        q.add(flatBoard[dest]);
                        flatBoard[dest] = -2;
                    }
                }
            }
            // increment the number of moves after processing all cells at the current level
            moves++;
        }
        // if we reach here, it means we couldn't reach the last cell
        return -1;
    }

    /**
     * Unrolls the board into a 1D array, where -1 represents a regular cell,
     * while a value greater than 0 represents a snake or ladder to the destination
     * given by the value.
     * Takes into account the Bosphorous pattern of the board.
     */
    int[] unrollBoard(int[][] board) {
        int n = board.length;
        int[] flatBoard = new int[n * n];
        int index = 0;
        int row = n - 1;
        int col = 0;
        boolean even = true;
        while (index < n * n) {
            if (board[row][col] == -1) {
                flatBoard[index] = -1;
            } else {
                flatBoard[index] = board[row][col] - 1;
            }
            if (even) {
                col++;
                if (col == n) {
                    even = false;
                    row--;
                    col--;
                }
            } else {
                col--;
                if (col == -1) {
                    even = true;
                    row--;
                    col++;
                }
            }
            index++;
        }
        return flatBoard;
    }
}