/*Time Complexity: O(2(N*N))

Space Complexity: O(N^2)

Did this code successfully run on Leetcode : Yes

Approach: BFS - Create a 1D array to flatten and the 2D array. Create a queue to store the next
moves of the board and keep polling the next moves to check if the target is reached and keep
track of visited.

Prob: 909. Snakes and Ladders
*/

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean dir = true;
        int [] arr = new int[n*n+1];
        int idx = 1; int r = n-1; int c = 0;
        while(idx <= n*n){
            if(board[r][c] == -1){
                arr[idx] = idx;
            }
            else{
                arr[idx] = board[r][c];
            }
            idx++;
            if(dir){
                c++;
                if(c == n){
                    dir = false;
                    r--;
                    c--;
                }
            }
            else{
                c--;
                if(c < 0){
                    dir = true;
                    r--;
                    c++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        int moves = 0;
        q.add(1);
        arr[1] = -1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0;i<size;i++){
                int curr = q.poll();
                for(int j = 1; j <= 6; j++){
                    int next = curr + j;
                    if(arr[next] >= n*n){
                        return moves + 1;
                    }
                    if(arr[next] != -1){
                        q.add(arr[next]);
                        arr[next] = -1;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}