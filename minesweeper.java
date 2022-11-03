/*
TC: O(m*n)
SC: O(m+n)
*/
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'B') {
            return board;
        } else if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        minesweeper(board, click[0], click[1]);
        return board;
    }
    
    
    private void minesweeper(char[][] board, int row, int col) {
        if(!isValid(board, row, col)) return;
        
        int adjacentMines = adjacentMinesCount(board, row, col);
        if(adjacentMines > 0) {
            board[row][col] = String.valueOf(adjacentMines).charAt(0);
            return;
        } 
        
        board[row][col] = 'B';
        minesweeper(board, row, col - 1);
        minesweeper(board, row - 1, col - 1);
        minesweeper(board, row - 1, col);
        minesweeper(board, row - 1, col + 1);
        minesweeper(board, row, col + 1);
        minesweeper(board, row + 1, col + 1);
        minesweeper(board, row + 1, col);
        minesweeper(board, row + 1, col - 1);
        
    }
    
    
    private int adjacentMinesCount(char[][] board, int row, int col) {
        int adjacentMines = 0;
        if(isValid(board, row, col - 1)) {
            if(board[row][col - 1] == 'M') adjacentMines++;
        }
        
        if(isValid(board, row - 1, col - 1)) {
            if(board[row - 1][col - 1] == 'M') adjacentMines++;
        }
        
        if(isValid(board, row - 1, col)) {
            if(board[row - 1][col] == 'M') adjacentMines++;
        }
        
        if(isValid(board, row - 1, col + 1)) {
            if(board[row - 1][col + 1] == 'M') adjacentMines++;
        }
        
        if(isValid(board, row, col + 1)) {
            if(board[row][col + 1] == 'M') adjacentMines++;
        }
        
        if(isValid(board, row + 1, col + 1)) {
            if(board[row + 1][col + 1] == 'M') adjacentMines++;
        }
        
        if(isValid(board, row + 1, col)) {
            if(board[row + 1][col] == 'M') adjacentMines++;
        }
        
        if(isValid(board, row + 1, col - 1)) {
            if(board[row + 1][col - 1] == 'M') adjacentMines++;
        }
        
        return adjacentMines;
    }
    
    private boolean isValid(char[][] board, int row, int col) {
        return row >= 0 && col >= 0 && row < board.length && col < board[0].length && (board[row][col] == 'E' || board[row][col] == 'M'); 
    }
    
}