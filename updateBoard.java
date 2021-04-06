// Time Complexity : O(m*n)
// Space Complexity : O(m*n) 
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : NO

// Your code here along with comments explaining your approach
class Solution {
    
    int dirs[][] = {{1,1},{1,0},{0,1},{-1,-1},{-1,0},{0,-1},{1,-1},{-1,1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        
        if(board[click[0]][click[1]]=='M') {
            board[click[0]][click[1]]='X';
            return board;
        }
        
        dfs(board, click[0], click[1]);
        
        return board;
        
    }
    
    public void dfs(char[][] board, int i,int j) {
        
        int mines = getMines(board, i, j);
        
        if(mines==0) {
            board[i][j]='B';
            for(int[] dir : dirs) {
            
                int x = i+dir[0];
                int y = j+dir[1];
                if(x<0 || x>=board.length || y<0 || y>=board[0].length || board[x][y]=='B') continue;
                dfs(board, x, y);
            }
        } else {
            board[i][j]=(char)('0'+mines);
        }
        
        
    }
    
    public int getMines(char[][] board, int i,int j) {
        
        int mines=0;
        for(int[] dir : dirs) {
            
            int x = i+dir[0];
            int y = j+dir[1];
            if(x<0 || x>=board.length || y<0 || y>=board[0].length) continue;
            if(board[x][y]=='M') mines++;
        }
        return mines;
    }
}
