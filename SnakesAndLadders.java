import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SnakesAndLadders {
    // TC: O(M * N) where M is number of rows and N is number of cols
    // SC: O(M * N) where M is number of rows and N is number of cols
    public int snakesAndLadders(int[][] board) {
        int len = board.length;
        int[] arr = new int[(len * len) + 1];
        int row = len - 1, col = 0;
        boolean flag = true;
        int index = 1;
        // convert board to arr
        while (row >= 0) {
            if (board[row][col] == -1) {
                arr[index] = index;
            } else {
                arr[index] = board[row][col];
            }
            if (flag) {
                col++;
                if (col == len) {
                    flag = false;
                    col = len - 1;
                    row--;
                }
            } else {
                col--;
                if (col < 0) {
                    flag = true;
                    col = 0;
                    row--;
                }
            }
            index++;
        }
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(1);
        visited.add(1);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == len * len) {
                    return level;
                }
                for (int j = 1; j <= 6; j++) {
                    int neighbor = curr + j;
                    if (neighbor <= len * len && !visited.contains(arr[neighbor])) {
                        queue.add(arr[neighbor]);
                        visited.add(arr[neighbor]);
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
