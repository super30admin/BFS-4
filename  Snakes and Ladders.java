//Time Complexity : O(n^2)
//Space Complexity : O(n^2)
// Did this code successfully run on Leetcode :yes
// Your code here along with comments explaining your approach
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board==null||board.length==0){
            return 0;
        }
        int n = board.length;
        //to navigate the board, convert it into a 1D array
        int[] grid = new int[n*n];
        //fill the grid
        int idx=0,i=n-1,j=0,even=0;//last row first col, even is used to keep track of the rows
        while(i>=0 && j>=0){
            //regular cell
            if(board[i][j]== -1){
                grid[idx] = idx;//fill it with index
            }
            //if ladder or snake
            else{
                grid[idx] = board[i][j] - 1;//-1 as index starts from 0
            }
            //if its even row, then the col moves from left to right
            if(even%2==0){
                j++;
            }
            //if the row is odd, then the col moves from right to left
            else{
                j--;
            }
            //if col is going out of bounds
            //towards right
            if(j==n){
                j--;
                //go to next row
                even++;
                i--;
            }
            //if col is out of bounds towards left
            if(j<0){
                j++;
                //go to next row
                even++;
                i--;
            }
            idx++;
        }
        //calculate the moves through BFS
        Queue<Integer> q = new LinkedList<>();
        int moves = 0;
        q.add(grid[0]);//start from the first cell
        grid[0] = -2;//mark it as visited
        while(!q.isEmpty()){
            int size = q.size();
            for(int k=0;k<size;k++){
                int cur = q.poll();
                //if the last index has already been reached stop and return
                if(cur == n*n-1){
                    return moves;
                }
                //roll the dice: so we have six options to choose a move
                for(int l=1;l<=6;l++){
                    //move from current cell
                    int pos = cur + l;
                    //if this position is not visited
                    if(pos<n*n && grid[pos]!=-2){
                        //add it to queue
                        q.add(grid[pos]);
                        //mark visited
                        grid[pos] = -2;
                    }
                }
            }
            moves++;//the level/size determines the number of moves
        }
        return -1;
    }
}