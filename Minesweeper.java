// Time Complexity :O(mn)
// Space Complexity :O(1)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this : getting started

// Your code here along with comments explaining your approach
public class Minesweeper {

    class Solution {
        private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
        public char[][] updateBoard(char[][] board, int[] click) {
            int row = click[0], col = click[1];
            int m = board.length, n = board[0].length;

            //change M or X location on the board to X
            if (board[row][col] == 'M' || board[row][col] == 'X') {
                board[row][col] = 'X';
                return board;
            }

            //count number of M's in the direction of travel
            int num = 0;
            for (int[] dir : dirs) {
                int newRow = dir[0] + row;
                int newCol = dir[1] + col;

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && board[newRow][newCol] == 'M') {
                    num++;
                }
            }

            //update board at row col to digit value
            if (num > 0) {
                board[row][col] = (char) (num + '0');
                return board;
            }

            board[row][col] = 'B';//update board to B if there is no mine

            for (int[] dir : dirs) {
                int newRow = dir[0] + row;
                int newCol = dir[1] + col;

                //check if any of the direction of travel has an E and then update the board

                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && board[newRow][newCol] == 'E') {
                    updateBoard(board, new int[]{newRow, newCol});
                }
            }

            return board;
        }
    }

}
