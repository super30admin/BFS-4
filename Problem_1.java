// Time complexity - O(mn)
// Space complexity - O(1)

// BFS

class Solution {
     int [][] dirs;
    int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        // edge
        if(board == null || board.length == 0) return board;
        
      dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1}, {-1,1},{-1,-1}};
        
        // if click is mine
         m = board.length;  n = board[0].length;
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }

        // BFS
        Queue<int []> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int r = curr[0]; int c = curr[1];
            int mines = getMines(board, r,c);
            if(mines == 0) {
                for(int [] dir : dirs){
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    if(nr >= 0 && nr < m && nc >=0 && nc<n && board[nr][nc] =='E'){
                        board[nr][nc] = 'B';
                        q.add(new int []{nr,nc});
                    }
                }
            } else {
                board[r][c] = (char)(mines+'0');
            }
        }
        return board;
    }

    private int getMines(char[][] board, int i, int j){
        int count = 0;
        for(int [] dir : dirs){
            int r = i + dir[0];
            int c = j + dir[1];
           if(r >= 0 && c >= 0 &&  r < m && c < n && board[r][c] == 'M'){
               System.out.println(board[r][c]);
               count++;
           }
        }
        return count;
    }
}
