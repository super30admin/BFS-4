// Time Complexity : o(m*n)
// Space Complexity : o(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    
    int[][] dirs;
     int m, n;
    
    public char[][] updateBoard(char[][] board, int[] click) {
       
        if(board == null || board.length == 0) 
            return board;

        m = board.length;
        n = board[0].length;
        dirs = new int[][] {{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1}};
        
        if(board[click[0]][click[1]]=='M')
        {
            board[click[0]][click[1]]='X';
            return board;
        }
        
        Queue<int[]> queue=new LinkedList<>();
        
        queue.add(click);
        board[click[0]][click[1]] = 'B';
        
        while(!queue.isEmpty())
        {
            int[] curr=queue.poll();
            
            int mines=getSurroundingMines(board,curr[0],curr[1]);
            
            if(mines!=0)
            {
                board[curr[0]][curr[1]]=(char)(mines+'0');
            }
            else
            {
                for(int dir[] :dirs)
                {
                    int nR=curr[0]+dir[0];
                    int nC=curr[1]+dir[1];
                    
                    if(nR >=0 && nC >=0 && nR <m && nC <n && board[nR][nC]=='E')
                    {
                        queue.add(new int[]{nR,nC});
                         board[nR][nC] = 'B';
                    }
                }
            }
        }
        return board;
    }
    
     private int getSurroundingMines(char[][] board, int i, int j) {
        int mines = 0;
         
          for(int[] dir : dirs) {
            int nr = i + dir[0];
            int nc = j + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] =='M') {
                mines++;
            }
        }
        return mines;
     }
}