import java.util.LinkedList;
import java.util.Queue;

// TC : O(m * n)
// SC : O(m * n)
public class Minesweeper {
    int[][] dirs;
    int m,n;
    // BFS Approach
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return new char[][]{};

        m = board.length;
        n = board[0].length;

        // If the click position has mine then mark it as X and return
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dirs = new int[][]{
                {0,1},
                {0,-1},
                {1,0},
                {-1,0},
                {1,1},
                {1,-1},
                {-1,1},
                {-1,-1}
        };

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1]);
            if(count > 0){
                board[curr[0]][curr[1]] = (char)(count + '0'); // 1 + '0' will give the ASCII value of 1. And then converting it to character
            }else {
                for(int[] dir : dirs) {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    if(r >=0 && r < m && c >= 0 && c < n && board[r][c] == 'E'){
                        q.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }
                }
            }
        }
        return board;
    }

    public char[][] updateBoardDFS(char[][] board, int[] click) {
        if(board == null || board.length == 0) return new char[][]{};

        m = board.length;
        n = board[0].length;

        // If the click position has mine then mark it as X and return
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dirs = new int[][]{
                {0,1},
                {0,-1},
                {1,0},
                {-1,0},
                {1,1},
                {1,-1},
                {-1,1},
                {-1,-1}
        };

        dfs(board, click);
        return board;
    }

    private void dfs(char[][] board, int[] click) {
        if(click[0] < 0 || click[1] < 0 || click[0] == m || click[1] == n || board[click[0]][click[1]] != 'E')
            return;

        board[click[0]][click[1]] = 'B';
        int count = countMines(board, click[0], click[1]);
        if(count > 0){
            board[click[0]][click[1]] = (char)(count + '0');
        }else {
            for(int[] dir : dirs) {
                int r = click[0] + dir[0];
                int c = click[1] + dir[1];
                dfs(board, new int[]{r,c});
            }
        }
    }

    // Check number of mines in the vicinity of the given click position
    private int countMines(char[][] board, int i, int j) {
        int count = 0;
        for(int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M') count++;
        }
        return count;
    }
}
