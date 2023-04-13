import java.util.LinkedList;
import java.util.Queue;
/*
Snakes and Ladders
approach: convert given board into 1d array and perform bfs
time & space: O(n^2)
 */
public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] dp = new int[n*n];
        boolean flag = false;
        int i=n-1, j=0;
        int k=0;
        while (k<(n*n)) {
            if (!flag) {
                dp[k++] = board[i][j]==-1?-1:board[i][j]-1;
                j++;
                if (j==n) {
                    flag = true;
                    j = n-1;
                    i--;
                }
            }
            else {
                dp[k++] = board[i][j]==-1?-1:board[i][j]-1;
                j--;
                if (j==-1) {
                    flag = false;
                    j = 0;
                    i--;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        dp[0] = -2;
        int moves = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (i=0;i<size;i++) {
                int curr = q.poll();
                if (curr == (n*n)-1) return moves;

                for (j = 1;j<7;j++) {
                    int next = curr+j;
                    if (next<(n*n)) {
                        if(dp[next]!=-2) {
                            if (dp[next]==-1) {
                                q.add(next);
                            }
                            else {
                                q.add(dp[next]);
                            }
                            dp[next] = -2;
                        }

                    }
                }

            }
            moves++;
        }
        return -1;
    }

    public static void main(String[] args) {
        SnakesAndLadders snakesAndLadders = new SnakesAndLadders();
        snakesAndLadders.snakesAndLadders(new int[][]{{1,1,-1},{1,1,1},{-1,1,1}});
    }
}
