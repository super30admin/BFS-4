// Time Complexity : O(N) where N is the number of blocks
// Space Complexity : O(N) where N is the number of blocks
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    Queue<int[]> q1 = new LinkedList<>();
    Queue<int[]> q2 = new LinkedList<>();
    int l; int b;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        dfs(board,click[0],click[1]);
        return board;
    }
    int[][] dirs={{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1}}; 

    public void dfs(char [][] board, int i, int j){
        int mines=getmines(board,i,j);
        if(mines==0){
            board[i][j]='B';
            for(int[] dir :dirs){
                int x=i+dir[0];
                int y=j+dir[1];
                if(x>=0 && x<board.length && y>=0 && y<board[0].length && board[x][y]=='E'){
                    dfs(board,x,y);
                }     
            }
        }
        else{
            board[i][j]=(char)(mines+'0');
        }
    }
    public int getmines(char[][] board, int i, int j){
        int mines=0;
        for(int[] dir :dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if(x>=0 && x<board.length && y>=0 && y<board[0].length && board[x][y]=='M')
                mines++;
        }
        return mines;
    }
}
