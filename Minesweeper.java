//Time: O(m * n)
//Space: O(m * n)

//BFS Solution

class Solution {
    int m; int n;
    int [][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        m = board.length; n = board[0].length;
        
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dirs = new int [][] {{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
        
        Queue<int []> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        
        while(!q.isEmpty()) {
            int [] curr = q.poll();
            
            int mineCount = countMines(board, curr[0], curr[1]);
            
            if(mineCount > 0) {
                board[curr[0]][curr[1]] = (char) (mineCount + '0');
            } else {
                for(int []dir: dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr >= 0 && nr < m && nc >= 0 && nc <n && board[nr][nc] =='E') {
                        q.add(new int[] {nr, nc});
                        board[nr][nc] = 'B';      
                    }
                }
            }
        }
        return board;
    }
    
    private int countMines(char board[][], int r, int c) {
        int count = 0;
        for(int [] dir: dirs) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >= 0 && nr < m && nc >= 0 && nc <n && board[nr][nc] =='M') {
                count++;      
            }
        }
        return count;
    }
}
