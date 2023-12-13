// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach
class MineSweeper {
    Queue<int[]> q;
    int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}, {1,1},{-1,-1},{1,-1},{-1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        q = new LinkedList<>();
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board,curr[0],curr[1],dirs);
           
            /*
            1. Check neighbors
            2. If there's a mine, then don't process neighbor
            */
            for(int[] dir: dirs){
                 if(count==0){
                int nr = curr[0] + dir[0];
                int nc = curr[1] + dir[1];
                if(nr >=0 && nr < m && nc >= 0 && nc < n && board[nr][nc]=='E'){
                   
                        q.add(new int[]{nr,nc});
                         board[nr][nc] = 'B';
                    }
                 }
                    else{
                        board[curr[0]][curr[1]] = (char)(count+'0');
                    }
                
            }
            
        }
        return board;
        
    }
    private int countMines(char[][] board, int r, int c, int[][] dirs){
        int count = 0;
        for(int[] dir: dirs){
            int nr = r+dir[0];
            int nc = c+dir[1];
            if(nr >=0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc]=='M'){
                count++;
        }
        }
        return count;
}
}