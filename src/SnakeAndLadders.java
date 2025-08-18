//time complexity- O(n^2)
//space complexity- O(n^2)
import java.util.*;
public class SnakeAndLadders {
    static int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        int r = n - 1, c = 0;
        int i = 0;
        boolean leftToRight = true;
        while (i < n * n) {
            if (board[r][c] == -1) {
                arr[i] = -1;
            } else {
                arr[i] = board[r][c] - 1;
            }
            i++;
            if (leftToRight) {
                c++;
                if (c == n) {
                    r--;
                    c--;
                    leftToRight = false;
                }
            } else {
                c--;
                if (c == -1) {
                    r--;
                    c++;
                    leftToRight = true;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n * n];
        queue.add(0);
        visited[0] = true;
        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int curr = queue.poll();
                if (curr == n * n - 1) {
                    return moves;
                }
                for (int step = 1; step <= 6; step++) {
                    int next = curr + step;
                    if (next >= n * n) break;
                    int dest = arr[next] == -1 ? next : arr[next];
                    if (!visited[dest]) {
                        visited[dest] = true;
                        queue.add(dest);
                    }
                }
            }
            moves++;
        }
        return -1;
    }
    public static void main(String[] args) {
        int[][] board =  {{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},{-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}};
        System.out.println("Board:");
        for (int[] b: board) {
            System.out.println(Arrays.toString(b));
        }
        int moves = snakesAndLadders(board);
        if(moves == -1) {
            System.out.println("No way to reach end");
        } else {
            System.out.println("Minimum dice rolls: "+ moves);
        }

    }
}
