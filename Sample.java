//minesweeper
//tc n*n
//sc m+n

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        
        if(board[click[0]][click[1]] == 'M'){
            
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<Integer> row = new LinkedList<>();
        Queue<Integer> column = new LinkedList<>();
        
        row.add(click[0]);
        column.add(click[1]);
        
        board[click[0]][click[1]] = 'B';
        
        int [][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}, {-1,-1}, {-1, 1}, {1, -1}, {1,1}};
        
        while(!row.isEmpty()){
            
            int currRow = row.poll();
            int currColumn = column.poll();
                
                int mines = getMines(currRow, currColumn, dirs, board);
                
                if(mines != 0){
                    
                    board[currRow][currColumn] = (char) (mines + '0');   // 0 -> 48 so eg 2 + 48 = 50 convert to character
                
                } else {
                    
                        for(int[] dir : dirs){
                            
                            int nRow = currRow + dir[0];
                            int nCol = currColumn + dir[1];
                            
                            if(nRow>=0 && nRow<board.length && nCol>=0 && nCol<board[0].length && board[nRow][nCol]=='E'){
                                
                                board[nRow][nCol]='B';
                                row.add(nRow);
                                column.add(nCol);
                            }
                            
                        }
                    
                }
            
            
        }
        return board;
    }
    
        
        private int getMines(int currRow, int currColumn, int[][] dirs, char[][] board){
            
            int mines = 0;
            for(int[] dir : dirs){
                            
                            int nRow = currRow + dir[0];
                            int nCol = currColumn + dir[1];
                            
                            if(nRow>=0 && nRow<board.length && nCol>=0 && nCol<board[0].length && board[nRow][nCol]=='M'){
                                
                                mines++;
                            }
                            
                        }
            return mines;
        }
}

//snakes and ladders

//tc and sc in n*n

class Solution {
    public int snakesAndLadders(int[][] board) {
        
        int minMoves = 0;
        
        int n = board.length;
        int[] moves = new int[n * n];
        
        int row = n-1;
        int column = 0;
        int even = 0;
        int index = 0;
        
        //preprocessing
        while(row >= 0) {
            
            if(board[row][column] == -1) {
                
                moves[index] = index;
                
            } else {
                
                moves[index] = board[row][column] - 1;
            }
            
            index++;
            
            if(even % 2 == 0) {
                
                column++;
                
            } else {
                
                column--;
            }
            
            if(column == n) {
                
                column--;
                row--;
                even++;
            }
            
            if(column == -1) {
                
                column++;
                row--;
                even++;
            }
        }
        //preprocessing
        
        //queue
        Queue<Integer> q = new LinkedList<>();
        q.add(moves[0]);
        moves[0] = -2;
        
        while(!q.isEmpty()) {
            
            int size = q.size();
            
            for(int i = 0; i < size; i++) {
                
                int currPosition = q.poll();
                
                if(currPosition == n * n - 1) {
                    
                    return minMoves;
                }
                
                for(int j = 1; j <= 6; j++) {
                    
                    int newPosition = currPosition + j;
                    
                    if(newPosition < n*n && moves[newPosition] != -2){
                        
                        q.add(moves[newPosition]);
                        moves[newPosition] = -2;
                        
                    }
                    
                }
                
            }
            minMoves++;
            
        }
        
        return -1;
    }
}
