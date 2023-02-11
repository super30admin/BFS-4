import java.util.Queue;
import java.util.LinkedList;
//Time O(M*N)
//Space O(M*N) 
public class MineSweeper {
	 int[][] dirs;
	    int m ;
	    int n ;
	    public char[][] updateBoard(char[][] board, int[] click) {
	         m = board.length;
	         n = board[0].length;
	        dirs=new int[][]{{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{-1,1},{1,-1},{1,1}};
	        Queue<Integer> q = new LinkedList<>();
	        if(board[click[0]][click[1]] =='M'){
	            board[click[0]][click[1]] = 'X';
	            return board;
	        }     
	       
	        q.add(click[0]);
	        q.add(click[1]);
	        board[click[0]][click[1]] = 'B';

	        while(!q.isEmpty()){
	            int r = q.poll();
	            int c = q.poll();
	            int mineCount = countMines(board,r,c);
	            if(mineCount==0){
	                // Process its babies
	                for(int[] dir:dirs){
	                    int nr = r+dir[0];
	                    int nc = c+dir[1];
	                    if(nr >= 0 && nr <m && nc >=0 && nc<n && board[nr][nc]=='E'){
	                        board[nr][nc] = 'B';
	                        q.add(nr);
	                        q.add(nc);
	                    }
	                }
	            }else{
	                board[r][c]=(char)(mineCount+'0');
	            }
	        
	    
	        }
	        return board;
	    }

	    private int countMines(char[][] board,int i,int j){
	        int count =0;
	        for(int[] dir:dirs){
	            int x = dir[0]+i;
	            int y = dir[1]+j;
	            if(x >= 0 && x <m && y >=0 && y<n && board[x][y]=='M'){
	                count++;
	            }    
	        }
	        return count;
	    }


}
