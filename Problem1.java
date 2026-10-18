Worst Time complexity : O(mxn)
 Space Complexity : O(mxn)
  using BFS approach


class Solution {

    int[][] dirs;
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {

     this.dirs = new int[][]{{-1,0},{-1,-1},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
     this.m = board.length;
     this.n = board[0].length;

     if(board[click[0]][click[1]] == 'M'){
        board[click[0]][click[1]] = 'X';
        return board;
     }

        // store next click position
       // and if next click position in the board is not mins make it empty 
       // once its empty store neighboring 8 sides if it allows into queue (if no mine found , keep it the same if mine is found update the value in there
      // and keep track of already visited rows and columns)
       // if no mine is found allow it recusrsively to find empty values
    

       Queue<int[]> q = new LinkedList<>();
       q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int i = curr[0];
            int j = curr[1];

            int minesCount = countMines(board, i , j);
            if(minesCount == 0){
                for(int[] dir : dirs) {
                    int r = i + dir[0];
                    int c = j + dir[1];
                if(r>= 0 && c>=0 && r <m && c <n && board[r][c] == 'E'){
                    q.add(new int[]{r,c});
                    board[r][c] = 'B';
                }
                
                }
            } else {
                board[i][j] = (char)(minesCount + '0');
            }
        }
        return board;
        
    }

    private int countMines(char[][] board , int i , int j) {
        int count = 0;

        for(int[] dir: dirs){
            int r = i + dir[0];
            int c = j + dir[1];
            if(r>= 0 && c>=0 && r <m && c <n && board[r][c] == 'M'){
                count = count +1;
            }
            
        }
        return count;
    }
}
