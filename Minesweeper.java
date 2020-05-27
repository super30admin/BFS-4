//Time Complexity: O(m * n)
//Space Complexity: O(m * n)

import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
	
	class DFSSolution {
	    int[][] dirs = {{0, -1},{0, 1}, {1, 0}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	    int m; int n;
	    
	    public char[][] updateBoard(char[][] board, int[] click) {
	        if(board == null || board.length == 0) return board;
	        m = board.length; n = board[0].length;
	        int i = click[0]; int j = click[1];
	        if(board[i][j] == 'M'){
	            board[i][j] = 'X';
	            return board;
	        }
	        dfs(board, i, j);
	        return board;
	    }
	    
	    private void dfs(char[][] board, int i, int j){
	        if(i < 0 || j < 0 || i >= m || j >= n || board[i][j] != 'E') return;
	        
	        int mines = getMines(board, i, j);
	        if(mines == 0){
	            board[i][j] = 'B';
	            for(int[] dir: dirs){
	                int r = dir[0] + i;
	                int c = dir[1] + j;
	                dfs(board, r, c);
	            }
	        } else {
	            board[i][j] = (char)(mines + '0');
	        }
	    }
	    
	    private int getMines(char[][] board, int i, int j){
	        int count = 0;
	        for(int[] dir: dirs){
	            int r = dir[0] + i;
	            int c = dir[1] + j;
	            if(r < 0 || c < 0 || r >= m || c >= n) continue;
	            if(board[r][c] == 'M') count++;
	        }
	        return count;
	    }
	}
	
	class BFSSolution {
	    int[][] dirs = {{0, -1},{0, 1}, {1, 0}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
	    int m; int n;
	    
	    public char[][] updateBoard(char[][] board, int[] click) {
	        if(board == null || board.length == 0) return board;
	        m = board.length; n = board[0].length;
	        int i = click[0]; int j = click[1];
	        if(board[i][j] == 'M'){
	            board[i][j] = 'X';
	            return board;
	        }
	        
	        Queue<int[]> q = new LinkedList<>();
	        q.add(new int[]{i, j}); board[i][j] = 'B';
	        
	        while(!q.isEmpty()){
	            int[] curr = q.poll();
	            int mines = getMines(board, curr[0], curr[1]);
	            if(mines == 0){
	                for(int[] dir: dirs){
	                    int r = dir[0] + curr[0];
	                    int c = dir[1] + curr[1];
	                    if(r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'E'){
	                        q.add(new int[]{r, c});
	                        board[r][c] = 'B';
	                    }
	                }
	            } else {
	                board[curr[0]][curr[1]] = (char)(mines + '0');
	            }
	        }
	        
	        return board;
	    }

	    private int getMines(char[][] board, int i, int j){
	        int count = 0;
	        for(int[] dir: dirs){
	            int r = dir[0] + i;
	            int c = dir[1] + j;
	            if(r < 0 || c < 0 || r >= m || c >= n) continue;
	            if(board[r][c] == 'M') count++;
	        }
	        return count;
	    }
	}
	
}
