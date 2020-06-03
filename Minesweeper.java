// Time Complexity : O(m*n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        int i = click[0];
        int j = click[1];

        if(board[i][j] == 'M'){
            board[i][j] = 'X';
            return board;
        }

        dfs(board, i, j);

        return board;
    }

    int[] dirX = {-1, 1, -1, 1, 0, 0, 1, -1};
    int[] dirY = {-1, 1, 1, -1, 1, -1, 0, 0};

    private void dfs(char[][] board, int i, int j){
        //base
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'E'){
            return;
        }

        //logic
        int mines = getNumberOfMines(board,i,j);

        if(mines == 0){
            board[i][j] = 'B';
            for(int x = 0; x < 8; x++){
                dfs(board, i + dirX[x], j + dirY[x]);
            }
        } else {
            board[i][j] = (char) (mines + '0');
        }

    }

    private int getNumberOfMines(char[][] board, int i, int j){
        int mines = 0;
        for(int x = 0; x < 8; x++){
            int xCoordinate = i + dirX[x];
            int yCoordinate = j + dirY[x];

            if(xCoordinate < 0 || xCoordinate >= board.length ||yCoordinate < 0 || yCoordinate >= board[0].length){
                continue;
            }

            if(board[xCoordinate][yCoordinate] == 'M'){
                mines++;
            }
        }

        return mines;
    }
}
