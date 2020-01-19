//dfs recursion to check adjustence block in all 8 direction..
//time complexity o(m*n)
//space complexityis o(1)
//unreveal mines M -> reveled X
//unrevealed blank block E-> revealed B and all its adjust unrevealed should be revealed recursively
//digits if neaby M present.

// return board when all square unrevealed
public class MinesWeeper {
	    private int[][] dirs = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
	    public char[][] updateBoard(char[][] board, int[] click) {
	       int row = click[0];
	       int col = click[1];
	       int m = board.length ;
	       int n = board[0].length;
	       if(board[row][col] == 'M' || board[row][col]== 'X'){
	           board[row][col] = 'X';
	           return board;
	       }
	       
	       int countOfAdjacentMines =0;
	       for(int[] dir :dirs){
	           int r = dir[0]+row;
	           int c = dir[1]+col;
	           if( r<m && r>=0 && c<n && c>=0 && board[r][c] == 'M'){
	               countOfAdjacentMines++;
	           }
	       }
	           if(countOfAdjacentMines>0){
	               board[row][col] = (char) (countOfAdjacentMines + '0');
	               return board;
	           }
	           board[row][col] = 'B';
	           
	           for(int[] dir:dirs){
	               int r1 = dir[0]+ row;
	               int c1 = dir[1]+ col;
	               if( r1<m && r1>=0 && c1<n && c1>=0 && board[r1][c1] == 'E'){
	                  updateBoard(board, new int[]{r1,c1});
	               }
	           }
	       
	        return board;
	    }
	
}
