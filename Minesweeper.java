/**
 * BFS flood-fill solution for Minesweeper click expansion.
 * Expands empty cells while counting adjacent mines and revealing numbers or blanks.
 * Time: O(m*n) worst case, Space: O(m*n) for BFS queue.
 */

package BFS-4;

public class Minesweeper {
    int[][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {

        this.dirs = new int[][] {{-1,0}, {-1,-1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
        this.m = board.length;
        this.n = board[0].length;

        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] == 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()) {
            int[] curr = q.poll(); 

            int count = countMines(board, curr[0], curr[1]);
            if(count = 0) {
                for(int[] dir : dirs) {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];

                    if(r >= 0 && c>=0 && r<m && c < n && board[r][c] == 'E') {
                        q.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }
                }
            } else {
                board[curr[0]curr[1]] = char(count + '0');
            }
        }

        return board;
    }

    private int countMines(char[][] board, int i, int j) {
        int count = 0; 

        for(int[] dir : dirs) {
            
        }

        return count;
    } 
}
