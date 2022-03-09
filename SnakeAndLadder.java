import java.util.LinkedList;
import java.util.Queue;

//Time Complexity: O(n*n)
//Space Complexity: O(n*n)
// BFS
public class SnakeAndLadder {
    public int snakesAndLadders(int[][] board) {
        if (board == null)
            return 0;
        int n = board.length;

        int moves[] = new int[n * n];

        int r = n - 1, c = 0, idx = 0;
        int even = 0;
        while (r >= 0 && c >= 0) {
            if (board[r][c] == -1)
                moves[idx++] = -1;
            else {
                moves[idx++] = board[r][c] - 1;
            }

            if (even % 2 == 0) // even row
            {
                c++;
                if (c == n) {
                    r--;
                    c--; // reset column to last column
                    even++;
                }
            } else {
                c--;
                if (c < 0) {
                    r--;
                    c = 0; // reset first column
                    even++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0); // add index to queue
        // visited
        moves[0] = -2;
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                if (curr == n * n - 1)
                    return count;
                for (int j = 1; j <= 6; j++) {
                    int child = j + curr;
                    if (child > n * n - 1)
                        continue;
                    if (moves[child] != -2) {
                        if (moves[child] == -1) {
                            q.offer(child);
                        } else {
                            q.offer(moves[child]);
                        }
                        // mark as visited
                        moves[child] = -2;
                    }
                }
            }
            count++;
        }
        return -1;
    }
}
