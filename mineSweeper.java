/*
Problem: https://leetcode.com/problems/minesweeper/
TC: O(n * m)
SC: O(n * m)
*/

// Approach 1: BFS
class Solution {
    int dirs[][] = new int[][]{
        {-1,0}, {0,-1}, {1,0}, {0,1}, {-1,-1}, {1,1}, {-1,1}, {1,-1}  
    };
    
    public char[][] updateBoard(char[][] board, int[] click) {
        
        if (board == null || board.length == 0 || board[0].length == 0) {
            return board;
        }
        
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        int n = board.length;
        int m = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        
        while (!queue.isEmpty()) {
            int pos[] = queue.poll();
            int mines = getMineCount(board, pos[0], pos[1]);
            if (mines > 0) {
                board[pos[0]][pos[1]] = (char)(mines + '0');
            } else {
                for (int d[] : dirs) {
                    int x = pos[0] + d[0];
                    int y = pos[1] + d[1];

                    if (x >= 0 && x < n && y >= 0 && y < m && board[x][y] == 'E') {
                        board[x][y] = 'B';
                        queue.add(new int[]{x, y});
                    }
                }
            }
        }
        
        return board;
    }
    
    private int getMineCount(char board[][], int r, int c) {
        int n = board.length;
        int m = board[0].length;
        int count = 0;
        for (int d[] : dirs) {
            int x = r + d[0];
            int y = c + d[1];

            if (x >= 0 && x < n && y >= 0 && y < m && board[x][y] == 'M') {
                ++count;
            }
        }
        
        return count;
    }
}

// Approach 2: DFS
class Solution {
    int dirs[][] = new int[][]{
        {-1,0}, {0,-1}, {1,0}, {0,1}, {-1,-1}, {1,1}, {-1,1}, {1,-1}  
    };
    int n, m;
    
    public char[][] updateBoard(char[][] board, int[] click) {
        
        if (board == null || board.length == 0 || board[0].length == 0) {
            return board;
        }
        
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        n = board.length;
        m = board[0].length;
        
        dfs(board, click);
        
        return board;
    }
    
    private void dfs(char board[][], int click[]) {
        if (click[0] < 0 || click[1] < 0 || click[0] >= n || click[1] >= m || board[click[0]][click[1]] != 'E')
            return;
        
        board[click[0]][click[1]] = 'B';
        int mines = getMineCount(board, click[0], click[1]);
        
        if (mines > 0) {
            board[click[0]][click[1]] = (char)(mines + '0');
        } else {
            for (int d[] : dirs) {
                int x = click[0] + d[0];
                int y = click[1] + d[1];
                dfs(board, new int[]{x, y});
            }
        }
    }
    
    private int getMineCount(char board[][], int r, int c) {
        int count = 0;
        for (int d[] : dirs) {
            int x = r + d[0];
            int y = c + d[1];

            if (x >= 0 && x < n && y >= 0 && y < m && board[x][y] == 'M') {
                ++count;
            }
        }
        
        return count;
    }
}