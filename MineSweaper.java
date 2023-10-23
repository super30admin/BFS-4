//TC = O(MN)
//SC = O(M+N)
class Solution {
    private int[][] dirs;
    
    public char[][] updateBoard(char[][] board, int[] click) {
        dirs = new int[][] {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };
        
        dfs(board, click[0], click[1]);
        
        return board;
    }
    
    private void dfs(char[][] board, int i, int j) {
        if (
            !isValidPosition(i, j, board) ||
            !(board[i][j] == 'M' || board[i][j] == 'E')
        ) {
            return;
        }
        
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return;
        } else {
            int bombNeighborCount = getBombNeighborCount(i, j, board);
            
            if (bombNeighborCount == 0) {
                board[i][j] = 'B';
                for (int[] dir : dirs) {
                    dfs(board, i + dir[0], j + dir[1]);
                }
            } else {
                board[i][j] = (char)(bombNeighborCount + '0');
            }
        }
        
    }
    
    private boolean isValidPosition(int i, int j, char[][] board) {
        return i >= 0 && i < board.length && j >= 0 && j < board[i].length;
    }
    
    private int getBombNeighborCount(int i, int j, char[][] board) {
        int x, y, count = 0;
        for (int[] dir : dirs) {
            x = i + dir[0];
            y = j + dir[1];
            
            if (isValidPosition(x, y, board) && (board[x][y] == 'M' || board[x][y] == 'X')) {
                count++;
            }
        }
        return count;
    }
}