// Time Complexity : O(N*M)
// Space Complexity : O(N*M)
// Did this code successfully run on Leetcode : Yes


class Solution {
    char[][] b;
    int rowMax;
    int colMax;
    public char[][] updateBoard(char[][] b, int[] c) {
        this.b = b;
        this.rowMax = b.length;
        this.colMax = b[0].length;
        /* case 1: click on M, change to X, stop searching */
        if (this.b[c[0]][c[1]] == 'M') {
            this.b[c[0]][c[1]] = 'X';
            return this.b;
        }
        dfs(c[0], c[1]);

        return this.b;
    }

    private int checkMine(int row, int col) {
        int mine = 0;
        /* check surrounding cells */
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) // skip current cell
                    continue;
                int x = row + i, y = col + j;
                if (x < 0 || x >= rowMax || y < 0 || y >= colMax) //skip out board
                    continue;
                if (b[x][y] == 'M') 
                    mine++;
            }
        }

        return mine;
    }

    private void dfs(int row, int col) {
        /* base case : out of board or current cell is B*/
        if (row < 0 || row >= rowMax || col < 0 || col >= colMax || b[row][col] == 'B')
            return;
        /* case 1: click on E*/
        int numOfMine = checkMine(row, col);
        if (numOfMine > 0) {
            b[row][col] = (char)('0' + numOfMine);
            return;
        }
        /* case 2: no mines besides */
        b[row][col] = 'B';
        /* recursive call on surrounding cells */
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) // skip current cell
                    continue;
                dfs(row + i, col + j);
            }
        }
    }
}