//Time Complexity O(N*M)
//Space Complexity O(N*M)
//Leetcode tested

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int n = board.length;
        int[] moves = new int[n*n];
        int idx = 0;
        int i= n - 1;int j = 0;
        int even = 0;
        while (idx < n*n){
            if(board[i][j] == -1){
                moves[idx] = board[i][j];
            }else{
                moves[idx] = board[i][j] - 1;
            }
            idx++;
            if(even%2 == 0){
                j++;
                if(j==n){
                    j--;i--;even++;
                }
            }else{
                j--;
                if(j == -1){
                    j++;i--;even++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);moves[0] = -2;
        int result = 0;
        while (!q.isEmpty()){
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int curr = q.poll();
                if(curr == n*n) return result;
                for (int l = 1; l <= 6; l++) {
                    int child = curr + k;
                    if(child < n*n){
                        if(moves[child]!=-2){
                            if(moves[child] == -1){
                                q.add(child);
                            }else{
                                q.add(moves[child]);
                            }
                            moves[child] = -2;
                        }
                    }
                }
                result++;
            }
        }
        return -1;
    }
}
