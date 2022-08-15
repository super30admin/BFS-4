// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class Solution {
    int[][] dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0],click[1]});
        board[click[0]][click[1]] = 'B';
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int mines = countMines(board, curr[0], curr[1]);
            if(mines>0){
                board[curr[0]][curr[1]] = (char)(mines+'0');
            }else{
                for(int[] dir : dirs) {
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    
                    //check bounds
                    if(nc<0 || nc>=board[0].length || nr<0 || nr>=board.length || board[nr][nc] != 'E')
                        continue;
                    
                    board[nr][nc] = 'B';
                    q.add(new int[] {nr,nc});
                }
            }
        }
        
        return board;
    }
    
    private int countMines(char[][] board, int i, int j) {
        int count = 0;
        for(int[] dir : dirs) {
            int nr = dir[0] + i;
            int nc = dir[1] + j;
                    
            //check bounds
            if(nc<0 || nc>=board[0].length || nr<0 || nr>=board.length)
                continue;
                    
            if(board[nr][nc] == 'M')
                count++;
        }
        
        return count;
    }
}
