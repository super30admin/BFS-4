import java.util.LinkedList;
import java.util.Queue;

public class Problem2 {

//    public int snakesAndLadders(int[][] board) {
//        if (board == null || board.length == 0) return 0;
//
//        int n = board.length;
//        System.out.println("n value " + n);
//        Queue<int[]> que = new LinkedList<>();
//
//        que.add(new int[]{n - 1, 0});
//        int result = 1;
//        while (!que.isEmpty()) {
//            int count = que.size();
//
//            for (int i = 0; i < count; i++) {
//                int[] curr = que.poll();
//                assert curr != null;
//                int r = curr[0];
//                int c = curr[1];
//                if (r == 0 && c == 0) return result;
//                if (board[r][c] != 0) {
//                    if (board[r][c] == -1) {
//                        for (int j = 1; j <= 6; j++) {
//
//                            if (c + j < n) {
//                                que.add(new int[]{r, c + j});
//                            } else {
//                                int rr = r;
//                                int cc = n - c - 1;
//                                rr--;
//                                que.add(new int[]{rr, cc});
//                            }
//                        }
//                        board[r][c] = 0;
//                    } else if (board[r][c] != 0 && board[r][c] != -1) {
//                        int temp = board[r][c];
//                        int tempr = temp / n;
//                        int tempc = n - 1 - (temp % n);
//                        System.out.println("current raw in else if condition " + r);
//                        System.out.println("current column in else if condition " + c);
//                        que.add(new int[]{tempr, tempc});
//                    }
//                }
//            }
//            result++;
//        }
//        return -1;
//    }

    // TC : O(n^2)
    // SC : O(n^2)
    public int snakesAndLadders1(int[][] board) {
        if (board == null || board.length == 0) return 0;
        int n = board.length;
        int[] visited = new int[n * n];
        int r = n - 1, c = 0, even = 0, idx = 0;
        while (r >= 0 && c >= 0) {
            if (board[r][c] == -1) {
                visited[idx] = -1;
            } else {
                visited[idx] = board[r][c] - 1;
            }
            idx++;
            if (even % 2 == 0) {
                c++;
                if (c == n) {
                    r--;
                    c--;
                    even++;
                }
            } else {
                c--;
                if (c == -1) {
                    even++;
                    r--;
                    c++;
                }
            }
        }

        Queue<Integer> que = new LinkedList<>();
        que.add(0);
        int result = 0;
        while (!que.isEmpty()) {
            int size = que.size();

            for (int i = 0; i < size; i++) {
                int curr = que.poll();
                if (curr == n * n - 1) return result;
                for (int j = 1; j <= 6; j++) {
                    int temp = curr + j;
                    if (temp > n * n - 1) continue;
                    if (visited[temp] != -3) {
                        if (visited[temp] == -1) {
                            que.add(temp);
                        } else {
                            que.add(visited[temp]);
                        }
                        visited[temp] = -3;
                    }
                }
            }
            result++;
        }
        return -1;
    }

    public static void main(String[] args) {
        Problem2 problem2 = new Problem2();
        int[][] temp = new int[][]{{-1, -1, -1, -1, -1, -1}, {-1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1}, {-1, 35, -1, -1, 13, -1}, {-1, -1, -1, -1, -1, -1}, {-1, 15, -1, -1, -1, -1}};
        System.out.println("final value " + problem2.snakesAndLadders1(temp));
    }
}
