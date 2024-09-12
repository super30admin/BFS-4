// Time Complexity: O(M*N)
// Space Complexity: O(M*N)

class Solution {
    
    int[] dx = {0, 0, 1, -1, 1, 1, -1, -1};
    int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};

    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;

        int m = board.length;
        int n = board[0].length;

        int row = click[0], col = click[1];

        if(board[row][col] == 'M') {
        	board[row][col] = 'X';
        	return board;
        }

        dfsVisit(board, row, col);
        
        return board;

    }

    private void dfsVisit(char[][] board, int row, int col) {

    	int count = countMines(board, row, col);
    	if(count == 0) {
    		board[row][col] = 'B';
    		for(int i = 0; i < 8; i++) {
    			int x = row + dx[i];
    			int y = col + dy[i];

    			if(isValid(board, x, y) && board[x][y] == 'E') {
    				dfsVisit(board, x, y);
    			}
    		} 
         } else {
    		board[row][col] = (char) (count + '0');
         }
    }

    private int countMines(char[][] board, int row, int col) {
    	int count = 0;
    	for(int i = 0; i < 8; i++) {
    		int x = row + dx[i];
    		int y = col + dy[i];
    		if(isValid(board, x, y) && board[x][y] == 'M') count++;
    	}

    	return count;
    }

    private boolean isValid(char[][] board, int x, int y) {
    	return x >= 0 && x < board.length && y >=0 && y < board[0].length;
    }
}

