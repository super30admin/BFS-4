//Time - O(m*n)
//Space - O(m*n)
class Solution {
    int[][] dirs = {{0,1},{0,-1},{-1,0},{1,0},{1,1},{-1,-1},{1,-1},{-1,1}}; 
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
      dfs(board,click[0],click[1]);
      return board; 
        
    }
    
    private void dfs(char[][] board, int i, int j){
        System.out.println(Arrays.toString(new int[]{i,j}));
        //base
        if(board[i][j]!='E'){ 
            return;                                                             }
        //logic
        int count = countMines(board,i,j);
        if(count!=0){
            board[i][j] = (char) (count + '0');
            return;
        }else{
            board[i][j] = 'B';
            for(int[] dir : dirs){
               int r = i+dir[0];
               int c = j+dir[1]; 
               if(r>=0 && r<board.length && c>=0 && c<board[0].length && board[r][c] == 'E') {
                   dfs(board, r,c);
               }
            }
            
        }
        
    }
    
    private int countMines(char[][] board , int i, int j){
       int count=0;
        for(int[] dir : dirs){
            int r = i + dir[0];
            int c = j + dir[1];
            if(r>=0 && r< board.length && c>=0 && c<board[0].length && board[r][c] == 'M'){
                count++;
            }        
        }
        return count;
    }
}