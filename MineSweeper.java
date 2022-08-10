class MineSweeper {
    int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,1},{1,-1},{-1,-1}};
    //TC is O(m*n)
    //SC is O(m*n)
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        int m = board.length;
        int n = board[0].length;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        board[click[0]][click[1]] = 'B';
        q.add(click);
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int mines = neighborMines(curr,board, m,n);
            if(mines>0) {
                board[curr[0]][curr[1]] = (char)(mines+'0');
            }else{
                for(int[] dir: dirs){
                int nr = curr[0]+dir[0];
                int nc = curr[1]+dir[1];
                if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc] == 'E'){
                    board[nr][nc]='B';
                    q.add(new int[]{nr,nc});
                }
            }
            }
            
        }
        return board;
    }
    
    private int neighborMines(int[] curr, char[][] board, int m, int n){
        int count = 0;
        for(int[] dir: dirs){
                int nr = curr[0]+dir[0];
                int nc = curr[1]+dir[1];
                if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc] == 'M'){
                    count++;
                }
            }
        return count;
    }
}