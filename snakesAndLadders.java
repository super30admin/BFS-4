// Time Complexity : O(N*N)
// Space Complexity : O(N*N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null) return 0;
        int n = board.length;
        int[] flatBoard = new int[n*n];
        int row = n-1;
        int col = 0;
        int ctr = 0;
        while(row >= 0 && col >= 0){
            if(board[row][col] == -1) flatBoard[ctr] = board[row][col];
            else flatBoard[ctr] = board[row][col]-1;
            //System.out.println(ctr+" "+" "+row+" "+col+" "+board[row][col]);
            ctr++;
            if((n-row+1) % 2 == 0) col++;
            else col--;
            if(col == -1){
                row--;
                col = 0;
            }else if(col == n){
                row--;
                col = n-1;
            }  
        }
        Queue<Integer> q = new LinkedList<>();
        
        q.add(0);
        flatBoard[0] = -2;
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            System.out.println(level);
            for(int i = 0 ; i < size ; i++){
                int index = q.poll();
                if(index == n*n-1) return level;
                for(int dice = 1 ; dice <=6 ; dice++){
                    int newPos = index+dice;
                    if(newPos >= n*n || flatBoard[newPos]== -2 ) continue;
                    if(flatBoard[newPos]== -1) q.add(newPos);
                    else q.add(flatBoard[newPos]);
                    flatBoard[newPos] = -2;
                }
            }
            level++;
        }
        return -1;
    }
}
