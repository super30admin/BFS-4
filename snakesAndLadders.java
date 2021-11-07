// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int n = board.length;
        int [] moves = new int[n*n]; // if visited, mark as -2
        int row = n-1; int col = 0;
        
        // flatten the matrix
        int even = 0; int idx = 0; // index on moves
        while(row >= 0 && col >= 0){
            if(board[row][col] == -1){
                moves[idx] = -1;
            }else{
                moves[idx] = board[row][col] - 1;
                // 2 contains 15 but idx of 15 is 14 in the flattened list
            }
            idx++;
            if(even % 2 == 0){
                col++;
                if(col == n){
                    row--; even++; col--;
                }
            } else{
                // odd
                col--;
                if(col == -1){
                    row--; even++; col++;
                }
            }
        }
        
        // BFS
        Queue<Integer> q = new LinkedList<>();
        int countOfMoves = 0; 
        q.add(0); //init at 0th index
        moves[0] = -2; // marked visited
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int curr = q.poll();
                if(curr == n*n-1) return countOfMoves;
                for(int j=1; j < 7; j++){
                    int child = curr + j; // baby index
                    if(child >= n*n) continue;
                    if(moves[child] != -2){ // not yet visited
                        if(moves[child] == -1){
                            q.add(child);
                        } else{
                            q.add(moves[child]); // number at that index
                        }
                        moves[child] = -2; // mark visited!
                    }
                }
            }
            countOfMoves++;
        }
        return -1;
    }
}