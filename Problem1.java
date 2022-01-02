// 529 Minesweeper
// time - O(n2)
// space - O(m + n)


class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        
        if(board[click[0]][click[1]] == 'M'){
            
            board[click[0]][click[1]] = 'X';
            
            return board;
        }
        
        Queue<Integer> row = new LinkedList<>();
        Queue<Integer> col = new LinkedList<>();
        
        row.add(click[0]);
        col.add(click[1]);
        
        board[click[0]][click[1]] = 'B';
            
            
        int[][] dirs = {{0,1}, {0,-1}, {-1, 0}, {1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1} };
        
        while(!row.isEmpty()){
            int currRow = row.poll();
            int currCol = col.poll();
            
            int mines = getMines(currRow, currCol, board, dirs);
            System.out.println(mines);
            if(mines != 0){
                
                board[currRow][currCol] = (char) (mines + '0');
            }else{
                
                for(int[] dir : dirs){
                    
                    int nRow = currRow + dir[0];
                    int nCol = currCol + dir[1];
                    
                    if(nRow >= 0 && nCol >=0 && nRow < board.length  && nCol < board[0].length && board[nRow][nCol] == 'E'){
                        
                        board[nRow][nCol] = 'B';
                        row.add(nRow);
                        col.add(nCol);
                    }
                }
            }
        }
        
        return board;
    }
    
    private int getMines(int currRow, int currCol, char[][] board, int[][] dirs){
        
        int mines = 0;
        
        for(int[] dir : dirs){
                    
                    int nRow = currRow + dir[0];
                    int nCol = currCol + dir[1];
                    
                    if(nRow >= 0 && nCol >=0 && nRow < board.length  && nCol < board[0].length && board[nRow][nCol] == 'M'){
                        
                        mines++;
                    }
        }
        return mines;
    }
}