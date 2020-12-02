// Time Complexity : O(mn), we would reach each node once in worst case
// Space Complexity : O(mn), max recursion depth in worst case
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// do a dfs/bfs traversal on the board starting with the element at that position if == 'E', in all 8 dirs
// if when at position (i,j) any neighbouring mine exists update position with mine count and stop dfs
// edge case, if at start position == 'M', set to 'X' and return board


class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null || board.length==0)
            return board;
        
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board, click[0], click[1]);
        
        return board;
    }
    
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{-1,-1},{1,1},{-1,1},{1,-1}};
    
    private int getMines(char[][] board, int i, int j){
        int count = 0;
        
        for(int[] dir : dirs){
                int r = dir[0] + i;
                int c = dir[1] + j;
                
                if(r>=0 && r<board.length && c>=0 && c<board[0].length && board[r][c]=='M'){
                    count++;
                     
            }
        }
        
        return count;
    }
    
    private void dfs(char[][] board, int i, int j){
        
        int mines = getMines(board, i, j);
        
        if(mines==0){
            board[i][j] = 'B';
            //do dfs on all 8 neighbours
            for(int[] dir : dirs){
                int r = dir[0] + i;
                int c = dir[1] + j;
                
                if(r>=0 && r<board.length && c>=0 && c<board[0].length && board[r][c]=='E'){
                    dfs(board, r, c);
                     
                }
            }
        }
        else{
            board[i][j] = (char) (mines + '0');
        }
    }
    
}