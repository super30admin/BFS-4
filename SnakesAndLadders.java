//Time Complexity: O(n^2) asymptotic
//Space Complexity: O(n^2) asymptotic Queue and Flattened Array

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
	public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int N = board.length * board[0].length;
        int r = board.length; int c = board[0].length;
        int i = r - 1; int j = 0; int even = 0; int idx = 0;
        int[] moves = new int[N];
        while(i >= 0 && j >= 0){
            if(board[i][j] == -1) moves[idx] = -1;
            else moves[idx] = board[i][j] - 1;
            if(even % 2 == 0) j++;
            else j--;
            if(j >= c){
                i--; even++; j--;
            } else if(j < 0){
                i--; even++; j++;
            }
            idx++;
        }
        
        int minNumOfMoves = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0); moves[0] = -2;
        while(!q.isEmpty()){
            int size = q.size();
            for(int l = 0; l < size; l++){
                int curr = q.poll();
                if(curr == N - 1) return minNumOfMoves;
                for(int k = 1; k < 7; k++){
                    int child = k + curr;
                    if(child < N && moves[child] != -2){
                        if(moves[child] > -1) q.add(moves[child]);
                        else q.add(child);
                        moves[child] = -2;
                    }
                }
            }
            minNumOfMoves++;
        }
        return -1;
    }
}
