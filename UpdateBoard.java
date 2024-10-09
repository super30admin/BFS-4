// Problem 529. Minesweeper
// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int m = board.length, n = board[0].length;
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1], dirs);
            if(count == 0){
                for(int[] dir: dirs){
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if(nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'E'){
                        q.add(new int[]{nr, nc});
                        board[nr][nc] = 'B';
                    }
                }
            }else{
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
        }
        return board;
    }

    // Helper function to count adjacent mines around a cell
    private int countMines(char[][] board, int i, int j, int[][] dirs){
        int result = 0;
        for(int[] dir : dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length && board[nr][nc] == 'M'){
                result++;
            }
        }
        return result;
    }
}
