// Time Complexity : O(n^2) where n is the dimension of matrix
// Space Complexity : O(n^2) for linear board array
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// Traverse the board in the format to take the values from the board and store them in an array.
// Now we will traverse the array using queue in level order way taking 6 possible outcomes at once 
// if not visited then adding it to the queue.
// If at somepoint we find a scenario where we reach the final point we will return the level/steps count
// Finally if its not possible to reach the final location we will return -1
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        if(n == 1)
            return 1;
        boolean dir = true;
        int[] linearBoard = new int[n*n +1];
        int cur = 1;
        for(int i = n-1; i >=0; i--){
            if(dir){
                dir = false;
                for(int j = 0; j < n; j++){
                   linearBoard[cur++] = board[i][j];
                }
            }
            else{
                dir = true;
                for(int j = n-1; j >=0; j--){
                   linearBoard[cur++] = board[i][j];
                }
            }
        }
        Queue<Integer> q = new LinkedList();
        q.add(1);
        int steps = 0;
        while(!q.isEmpty()){
           // int curCell = q.poll();
            steps++;
            Queue<Integer> q1 = new LinkedList();
            while(!q.isEmpty()){
                int curCell = q.poll();
                for(int i = 1; i <= 6; i++){
                   int nextCell = curCell + i;
                   if(nextCell == n*n)
                        return steps;
                   
                   if(linearBoard[nextCell] == -1){
                       linearBoard[nextCell] = -2;
                       q1.add(nextCell);
                   }
                    else if(linearBoard[nextCell] != -2){
                        if(linearBoard[nextCell] == n*n)
                            return steps;
                        q1.add(linearBoard[nextCell]);
                        linearBoard[nextCell] = -2;
                    }
                }
            }
            q = q1;
        }
        return -1;
    }
}