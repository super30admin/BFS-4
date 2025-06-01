class MineSweeper {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
         this.dirs = new int[][]{{-1,-1}, {-1,0}, {1,0}, {0,-1}, {1,-1}, {-1,1}, {0,1}, {1,1}};
         Queue<int[]> myQueue = new LinkedList<>();

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
         }
         
         myQueue.add(new int[]{click[0], click[1]});
         board[click[0]][click[1]] = 'B';

         while(!myQueue.isEmpty()){
            int[] curr = myQueue.poll();
            int count = countMines(board, curr[0], curr[1]);
            if(count == 0){
                for(int[] dir: dirs){
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    if(r>=0 && c>=0 && r<board.length && c < board[0].length && board[r][c] == 'E'){
                        board[r][c] = 'B';
                        myQueue.add(new int[]{r,c});
                    }
                }
            }
            else{
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
         }
         return board;
    }

    private int countMines(char[][] board, int i, int j){
            int count = 0;

            for(int[] dir: dirs){
                int r = i + dir[0];
                int c = j + dir[1];

                if(r>=0 && c >=0 && r <board.length && c<board[0].length && board[r][c]=='M'){
                    count++;
                }
            }
        return count;
    }
}