class Minesweeper {
    
    /*
        Time : O(N * N)  
        Space : O(N * N) 
        Leetcode : YES
    */
    int[][] dirs = {{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0},{-1,1}};
     
    // BFS
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null) return board;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int m = board.length;
        int n = board[0].length;
        
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {click[0],click[1]});
        board[click[0]][click[1]] = 'B';
        
        while(!que.isEmpty()){
            int[] curr = que.poll();
            int mines = getMines(board, curr[0], curr[1], m, n);
            if(mines == 0){
                for(int[] dir : dirs){
                    int i = curr[0] + dir[0];
                    int j = curr[1] + dir[1];

                    if(i < m && i >= 0 && j < n && j >=0 && board[i][j] == 'E'){
                        board[i][j] = 'B';
                        que.add(new int[]{i,j});
                    }
                }            
            }else{
                board[curr[0]][curr[1]] = (char)(mines + '0');
            }
            
        }
        
        return board;
    }

    
    int getMines(char[][] board, int row, int col,int m, int n){
        int count = 0;
        
        for(int[] dir : dirs){
            int i = row + dir[0];
            int j = col + dir[1];
            if(i < m && i >= 0 && j < n && j >=0 && board[i][j] == 'M'){
                count++;
            }
        }
// System.out.println("Coutn : "+count);
        return count;
    }
}
