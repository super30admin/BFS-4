import java.util.LinkedList;
import java.util.Queue;
/*
MineSweeper
approach: do a bfs or dfs, at each cell check number of mines in all 8 directions
time: O(mxn)
space: O(mxn)
 */
public class MineSweeper {
    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1},{-1,-1},{-1,+1},{1,-1},{1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length, n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        if (board[click[0]][click[1]]=='M') {
            board[click[0]][click[1]]='X';
            return board;
        }
        q.add(click);
        board[click[0]][click[1]] = 'B';

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int mines = countMines(board, curr, m, n);

            if (mines==0) {

                for (int[] dir: dirs) {
                    int nr = curr[0]+dir[0];
                    int nc = curr[1]+dir[1];

                    if (nr>=0 && nc>=0 && nr<m && nc<n && board[nr][nc]=='E') {
                        q.add(new int[]{nr, nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
            else {
                board[curr[0]][curr[1]] = (char)(mines+'0');
            }
        }

        return board;
    }

    private int countMines(char[][] board, int[] curr, int m, int n) {
        int mines = 0;
        for (int[] dir:dirs) {
            int nr = curr[0]+dir[0];
            int nc = curr[1]+dir[1];

            if (nr>=0 && nc>=0 && nr<m && nc<n && board[nr][nc]=='M') {
                mines++;
            }
        }
        return mines;
    }
}
