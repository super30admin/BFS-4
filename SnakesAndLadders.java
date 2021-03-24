// TC: O(N*N), N*N -> dimensions of board
// SC: O(N*N), N*N -> dimensions of board
// Did it run successfully on Leetcode? : Yes
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0)
            return 0;
        int n = board.length;
        int[] moves = new int[n*n];
        int i = n-1;
        int j = 0;
        int even = 0;
        int index = 0;
        while (i >=0 && j >=0){
           if(board[i][j] == -1){
               moves[index] = -1;
           }
           else{
               moves[index] = board[i][j] - 1;
           }
           index++;
           if (even % 2 == 0)
               j++; // move right
           else
               j--; // move left
           if (j == n){
               i--;       // go to prev row
               even++;    //change the direction
               j--;      // bring back j as it has gone out of bounds
           }
            if (j < 0){
               i--;        // go to prev row
               even++;      //change the direction
               j++;       // increase j as it has gone out of bounds
           }  
        }
        //BFS
        Queue<Integer> q = new LinkedList();
        q.add(0);
        // change the value to -2 to mark it visited
        moves[0] = -2;
        int min = 0;
        while (!q.isEmpty()){
            int size = q.size();
            for (int k = 0; k < size; k++){
                int curr = q.poll();
                if (curr == moves.length - 1)
                    return min;
                // 6 possible destinations-> curr+1, curr+2 ,...., curr+6 ( dice can roll to number 6 at max)
                for ( int d = 1; d <=6; d++){
                    int child = curr + d;
                    if (child < n*n && moves[child] != -2){
                    // -1 that means no ladder or snake at that index
                        if(moves[child] == -1 ){
                            q.add(child);
                        } else {
                            q.add(moves[child]);
                        }
                        // mark the child as visited
                        moves[child] = -2;
                    }             
                }
            }
            min++;
        }
        return -1;
    }
}
