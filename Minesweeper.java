//Problem 147: Minesweeper
//TC:O(8*m*n)=>O(m*n)
//SC:O(m*n)

/*
First Find the number of mines of coordinate to be processed in the queue. If mines count>0, just replace it with the the count otherwise just traverse all its 8 coordinates and if they are 'E', just add it into the queue.
//Can be done by wither using BFS or DFS


*/

import java.util.*;
class Solution147 {
    private int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        
        //TC:O(8*m*n)=>O(m*n) || SC:O(m*n)
        if(board==null || board.length==0) return new char[0][];
        
        dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}};
        
        Queue<int[]> q = new LinkedList<>();
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        q.offer(new int[]{click[0],click[1]});
        board[click[0]][click[1]] = 'B';
        
        while(!q.isEmpty()){
            
            int curr[] = q.poll();
            int minesCount = getMinesCount(board,curr);
            
            if(minesCount==0){
                for(int[] dir : dirs){
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    
                     if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[r][c]=='E'){
                         board[r][c] = 'B';
                         q.offer(new int[]{r,c});
                     }
                    
                    
                }
            }else{
                board[curr[0]][curr[1]] = (char)(minesCount+'0');//converting to int
            }
            
        }
        
        return board;
    }
    
    private int getMinesCount(char[][] board, int[] curr){
        int count =0;
        for(int[] dir:dirs){
            int r = curr[0]+dir[0];
            int c = curr[1]+dir[1];
            
            if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[r][c]=='M'){
                count++;
            }
        }
        
        return count;
    }
    
}