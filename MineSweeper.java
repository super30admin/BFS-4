// Time Complexity : O(V+E)
// Space Complexity : O(V+E) for recursive stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    private int[][] dirs={{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null||board.length==0||board[0].length==0){
            return board;
        }
        int i=click[0],j=click[1];
        if(board[i][j]=='M'){
            board[i][j]='X';
            return board;
        }
        dfs(board,i,j);
        return board;
    }
    private void dfs(char[][] board,int i, int j){
        int mine=getMine(board, i , j);
        
        if(mine==0){
            board[i][j]='B';
            for(int[] direc:dirs){
                int r=i+direc[0];
                int c=j+direc[1];
                
                if(r>=0 && r<board.length && c>=0 && c<board[0].length && board[r][c]=='E'){
                    dfs(board, r , c);
                }
            }
        }
            else{
                board[i][j]=(char)(mine+'0');
            }
        }
    private int getMine(char[][] board, int i, int j){
        int mine=0;
        for(int[] direc:dirs){
            int r=i+direc[0];
            int c=j+direc[1];
            if(r>=0 && r<board.length && c>=0 && c<board[0].length && board[r][c]=='M'){
                mine++;
            }
        }
        return mine;
    }
    
    
}