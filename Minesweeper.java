// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class Minesweeper {
    class Solution {
        public char[][] updateBoard(char[][] board, int[] click) {
            int m = board.length, n = board[0].length;

            if(board[click[0]][click[1]] == 'M'){
                board[click[0]][click[1]] = 'X';
                return board;
            }

            int[][] dirs = new int[][]{{0, 1}, {0, -1}, { 1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, { 1, -1}, {1, 1}};
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{click[0], click[1]});
            board[click[0]][click[1]] = 'B';

            while(!q.isEmpty()){
                int[] curr = q.poll();
                int mineCount = countMines(board, curr[0], curr[1], dirs);
                if(mineCount == 0){
                    for(int[] dir : dirs){
                        int r = dir[0] + curr[0];
                        int c = dir[1] + curr[1];

                        if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E'){
                            q.add(new int[]{r, c});
                            board[r][c] = 'B';
                        }
                    }
                }
                else{
                    board[curr[0]][curr[1]] = (char)(mineCount + '0');
                }
            }
            return board;
        }

        private int countMines(char[][] board, int r, int c, int[][] dirs){
            int count = 0;

            for(int[] dir : dirs){
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'M'){
                    count++;
                }
            }
            return count;
        }
    }
}
