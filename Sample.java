## Problem1: Minesweeper (https://leetcode.com/problems/minesweeper/)

// Time Complexity - 0(m * n)
// Space Complexity - 0(m * n)

class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || board.length == 0) {
            return board;
        }
        m = board.length;
        n = board[0].length;
        dirs = new int[][] {{-1, 0}, {1,0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board, click);
        return board;      
    }
    private void dfs (char[][] board, int[] click) {
        if (click[0] < 0 || click[0] == m || click[1] < 0 || click[1] == n || board[click[0]][click[1]] != 'E') {
            return;
        }
        board[click[0]][click[1]] = 'B';
        int countmines = countMines(click, board);
        if (countmines == 0) {
            for (int[] dir : dirs) {
                int nr = click[0] + dir[0];
                int nc = click[1] + dir[1];
                dfs(board, new int[] {nr, nc});
            }
        }
        else {
            board[click[0]][click[1]] = (char)(countmines + '0');
        }
    }
    private int countMines (int[] curr, char[][] board) {
        int mines = 0;
        
        for (int[] dir : dirs) {
            int nr = curr[0] + dir[0];
            int nc = curr[1] + dir[1];
            if (nr >= 0 && nr < m & nc >= 0 && nc < n && board[nr][nc] == 'M') {
                mines++;
            }
        }
        return mines;
    }
}



## Problem 2 Snakes and ladders (https://leetcode.com/problems/snakes-and-ladders/)

// Time Complexity - 0(n^2)
// Space Complexity - 0(n^2)

class Solution {
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }
        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        int[] flat = new int[n * n];
        int r = n - 1;
        int c = 0;
        int even = 0;
        int index = 0;

        while (index < n * n) {
            if (board[r][c] == -1) {
                flat[index] = board[r][c];
            }
            else {
                flat[index] = board[r][c] - 1;
            }
            if (even % 2 == 0) {
                c++;
                if (c == n) {
                    r--;
                    even++;
                    c = n - 1;
                }
            }
            else {
                c--;
                if (c == -1) {
                    r--;
                    even++;
                    c = 0;
                }
            }
            index++;
        }
        int moves = 0;
        q.add(0);
        flat[0] = -2;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int curr = q.poll();
                for (int j = 1; j <= 6; j++) {
                    int child = curr + j;
                    if (child >= n * n) continue;
                    if (child == n * n - 1 || flat[child] == n * n - 1) {
                        return moves + 1;
                    }
                    if (flat[child] != -2) {
                        if (flat[child] == -1) {
                            q.add(child);
                            flat[child] = -2;
                        }
                        else {
                            q.add(flat[child]);
                            flat[child] = -2;
                        }
                    }
                }
            }
            moves++;
        }   
        return -1;    
    }
}