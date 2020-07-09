// Time Complexity : O(M*N)
// Space Complexity : O(M*N) for DFS. For BFS, total number of cells in queue at a particular time, which is O(M+N). In worst case can be O(M*N).
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : After class solution


// Your code here along with comments explaining your approach
//The idea is to first check whether we clicked in the cell containing a mine ('M'). If yes, then we return the board as we cannot move further.
//We keep track of total mines in neighbours.
//If we clicked on a cell containing empty 'E', and it contains no neighbours which contains a mine then we mark it 'B' and explore it's neighbours.
//If it contains one or more mine, then mark it with the total number of mines and don't explore it's neighbours (according to problem definition).
//Keep track of int to char convertions.

//DFS Version

class Solution {
         
    public char[][] updateBoard(char[][] board, int[] click) {
       //base check
        if(board == null || board.length == 0) return board;
        
        //find rows and cols
        int row = board.length;
        int col = board[0].length;
        
        //if clicked on a mine marked by 'M' then mark it 'X' and game is over 
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
             return board;
            } 

        //arrays used to traverse the neighbours. Here neighbours are 8, means diagonals allowed.
        int[] dx = {-1, -1, -1, 0,  0,  1, 1, 1};
        int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};
        
        //call the dfs function
        dfs_Visit(click[0], click[1], board, dx, dy);
        
        return board;
    }
     
    private void dfs_Visit(int row, int col, char[][] board, int[] dx, int[] dy){
              
             int count = countMines(row, col, board, dx, dy);
             if(count == 0){
                 //if no neighbours exist which contain a mine, mark it 'B'
                 board[row][col] = 'B';
             //traverse the neighbours
                for(int k=0; k<8; k++){                
                    int x = row + dx[k];
                    int y = col + dy[k];
                        
                    if(isValid(x, y, board) && board[x][y] == 'E'){
                        //call the dfs function again to process it's neighbours
                        dfs_Visit(x, y, board, dx, dy);
                     }                                  
                   }
                }else{
                     board[row][col] = (char)(count + '0');
                 }
         }

       
                
    private boolean isValid(int x, int y, char[][] board){
        return x>=0 && x<board.length && y>=0 && y<board[0].length;
    } 
    
    //count the number of adjacent mines
    private int countMines(int x, int y, char[][] board, int[] dx, int[] dy){
        int count = 0;
        
        for(int i=0; i<8; i++){
            int new_x = x + dx[i];
            int new_y = y + dy[i];
            
            if(isValid(new_x, new_y, board) && board[new_x][new_y] == 'M')
                count++;
        }
        
        return count;
    }
}

//============**************************************==============================================================

//BFS solution

class Solution {
       
    
    public char[][] updateBoard(char[][] board, int[] click) {
       //base check
        if(board == null || board.length == 0) return board;
        
        //find rows and cols
        int row = board.length;
        int col = board[0].length;
        
        //if clicked on a mine marked by 'M' then mark it 'X' and game is over 
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
             return board;
            } 
        
        //BFS traversal. We will use a queue to store the cells
        Queue<Pair> q = new LinkedList<>();
        //add the starting point to the queue
        q.offer(new Pair(click[0], click[1]));
        //mark it visited
        board[click[0]][click[1]] = 'B';
        
        //arrays used to traverse the neighbours. Here neighbours are 8, means diagonals allowed.
        int[] dx = {-1, -1, -1, 0,  0,  1, 1, 1};
        int[] dy = {-1,  0,  1, -1, 1, -1, 0, 1};
                   
        //start traversing the queue
         while(!q.isEmpty()){
            Pair current = q.poll();
              
             int count = countMines(current.x, current.y, board, dx, dy);
             if(count == 0){
             //traverse the neighbours
             for(int k=0; k<8; k++){                
                    int x = current.x + dx[k];
                    int y = current.y + dy[k];
                        
                    if(isValid(x, y, board) && board[x][y] == 'E'){
                         //mark it 'B'
                         board[x][y] = 'B';
                         //add it to the queue
                         q.offer(new Pair(x,y));
                     }                                  
                   }
                }else{
                     board[current.x][current.y] = (char)(count + '0');
                 }
               }    
                return board;
         }
                
    private boolean isValid(int x, int y, char[][] board){
        return x>=0 && x<board.length && y>=0 && y<board[0].length;
    } 
    
    //count the number of adjacent mines
    private int countMines(int x, int y, char[][] board, int[] dx, int[] dy){
        int count = 0;
        
        for(int i=0; i<8; i++){
            int new_x = x + dx[i];
            int new_y = y + dy[i];
            
            if(isValid(new_x, new_y, board) && board[new_x][new_y] == 'M')
                count++;
        }
        
        return count;
    }
}


class Pair{
    int x, y;
    
  Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
