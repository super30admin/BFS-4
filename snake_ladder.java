import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int snakesAndLadders(int[][] board) {

        //edge

        if (board == null || board.length == 0) return 0;

        int n = board.length * board[0].length;

        int[] moves = new int[n];

        int rows = board.length;
        int cols = board[0].length;

        int row = rows - 1; // Replaces i
        int col = 0;        // Replaces j
        int even = 0;       // Keeps track of row traversal direction
        int counter = 0;    // Keeps track of moves array index

        while (row >= 0 && col >= 0) {

            // Entry in moves
            if (board[row][col] != -1) {
                moves[counter] = board[row][col] - 1;
            } else {
                moves[counter] = -1;
            }

            counter++;

            if (even % 2 == 0) {
                col += 1;
            } else {
                col -= 1;
            }

            // Handle boundaries and switch direction
            if (col >= cols) {
                row--;
                even += 1;
                col -= 1;
            } else if (col < 0) {
                row--;
                even += 1;
                col += 1;
            }
        }

        int min = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2; // Mark the start position as visited

        while (!q.isEmpty()) {

            int size = q.size();

            for (int k = 0; k < size; k++) {

                int curr = q.poll();

                if (curr == n - 1) return min; // Reached the last square

                for (int l = 1; l < 7; l++) {

                    int child = l + curr;

                    if (child < n && moves[child] != -2) {

                        if (moves[child] > -1) {
                            q.add(moves[child]); // Move to the destination of ladder/snake
                        } else {
                            q.add(child); // Regular move
                        }

                        moves[child] = -2; // Mark as visited
                    }
                }
            }

            min++;
        }

        return -1; // If no solution is found
    }
}