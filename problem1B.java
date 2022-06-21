class minesWeeper {
    int[][] dirs;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        
        m = board.length;
        n = board[0].length;
        dirs = new int[][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<int[]> q = new LinkedList();
        q.add(new int[] {click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1]);
            if(count > 0){
                board[curr[0]][curr[1]] = (char) (count + '0');
            }
            else{
                for(int[] dir : dirs){
                    int i = curr[0] + dir[0];
                    int j = curr[1] + dir[1];
                    if(i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'E'){
                        q.add(new int[] {i, j});
                        board[i][j] = 'B';
                    }
                }
            }
        }
        return board;
    }
    
    private int countMines(char[][] board, int r, int c){
        int count = 0;
        for(int[] dir : dirs){
            int i = r + dir[0];
            int j = c + dir[1];
            if(i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'M'){
                count++;
            }
        }
        return count;
    }
}

//time complexity O(m * n)
//space complexity O(m * n) where m and n is the length of board's row & column