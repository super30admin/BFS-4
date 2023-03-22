class Solution {
    
    // 8 directions that we can go from current cell
    int[][] directions = {
        {0, -1}, {-1, 0},
        {1, 0}, {0, 1},
        {1, 1}, {1, -1},
        {-1, 1}, {-1, -1}  
    };



    //Approach 3, DFS
    //Time Complexity: O(m*n)
    //Space Complexity: O(m*n)

    public char[][] updateBoard(char[][] board, int[] click) {
        
        //current click
        int row = click[0];
        int col = click[1];
        
        //if it's mine, then just update the board and return the board
        if(board[row][col] == 'M' || board[row][col] == 'X'){
            board[row][col] = 'X';
            return board;
        }
        
        //if it's not mine, then calculate the mine that are present in the 8 directions
        int mines = calculateMines(board, row, col);
       
        //if it's more than 0 then just update the board and return
        if(mines>0){
            board[row][col] = (char)(mines + '0');
            return board;
        }
        
        //if there's not any mines in 8 directions, means we have blank cell, so we update the board, and explore it's children
        board[row][col] = 'B';
        
        for(int[] dir : directions){
            
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
            
            //checking the children's inbound conditions and also check it's not visited
            if(newRow>=0 && newCol>=0 && newRow<board.length && newCol<board[0].length 
                && board[newRow][newCol] == 'E'){
                
                updateBoard(board, new int[]{newRow, newCol});      //if all of the conditions are satisfying, then we have to make dfs call on the children
            }
        }
        return board;   
    }




    //Approach 2, BFS
    //Time Complexity: O(m*n)
    //Space Complexity: O(m*n)

    // public char[][] updateBoard(char[][] board, int[] click) {

    //     if(board == null || board.length == 0) return board;            //checking the null case

    //     if(board[click[0]][click[1]] == 'M') {                  //if it's click is at mine, them update the board and return
    //         board[click[0]][click[1]] = 'X';            
    //         return board;
    //     }

    //     int m = board.length;
    //     int n = board[0].length;

    //     Queue<int[]> queue = new LinkedList<>();                //creating the queue
    //     queue.add(click);                                       //add the first click in queue

    //     board[click[0]][click[1]] = 'B';                        //and update the board, mark as visited

    //     while(!queue.isEmpty()){                                //iterating while queue is empty
            
    //         int[] curr = queue.poll();                          //getting the element from queue

    //         int mines = calculateMines(board, curr[0], curr[1]);        //calculate the mines in 8 directions

    //         if(mines > 0){                                      //if mines greater than zero, then update the board and contiue the while loop, don't explore the children
    //             board[curr[0]][curr[1]] = (char)(mines + '0');
    //             continue;
    //         }

    //         //if there'e not mines in the 8 directions, then iterating in 8 directions
    //         for(int[] dir: directions){
                
    //             int nr = dir[0] + curr[0];
    //             int nc = dir[1] + curr[1];

    //             //check the new children is in the board, and check is not visited as well
    //             if(nr>=0 && nc>=0 && nr < m && nc< n && board[nr][nc] == 'E'){
                    
    //                 queue.add(new int[]{nr, nc});           //if all condition satisfyes, then add it in the queue and mark current cell as visited
    //                 board[nr][nc] = 'B';
    //             }
    //         }
    //     }
    //     return board;
    // }




    //Approach 1, DFS
    //Time Complexity: O(m*n)
    //Space Complexity: O(m*n)

    // public char[][] updateBoard(char[][] board, int[] click) {

    //     if(board == null || board.length == 0) return board;            //check for null conditions

    //     if(board[click[0]][click[1]] == 'M'){                           //if we first click at mines, then update the board and return
    //         board[click[0]][click[1]] = 'X';
    //         return board;
    //     }

    //     dfs(board, click[0], click[1]);                             //otherwise call dfs on click row and column
    //     return board;
    // }

    // private void dfs(char[][] board, int row, int col){

    //     //check for boundry conditions and visited case
    //     if(row<0 || col <0 || row == board.length || col == board[0].length || board[row][col] != 'E') return;

    //     //logic
    //     //get the mines near the 8 directions
    //     int mines = calculateMines(board, row, col);

    //     //if there's a mine then update the board and return from there
    //     if(mines > 0){
    //         board[row][col] = (char) (mines + '0');
    //         return;
    //     }

    //     //if there's no mine, we have to put blank in that cell
    //     board[row][col] = 'B';

    //     //go in 8 directions
    //     for(int[] dir: directions){
            
    //         int nr = dir[0] + row;
    //         int nc = dir[1] + col;
    //         dfs(board, nr, nc);             //call the dfs on it's 8 directions children
    //     }
    // }

    
    private int calculateMines(char[][] board, int row, int col){
        
        int count = 0;

        for(int[] dir : directions){
            
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
            
            if(newRow>=0 && newCol>=0 && newRow<board.length && newCol<board[0].length && board[newRow][newCol] == 'M'){
                count++;
            }
        }
        return count;
    }
}
