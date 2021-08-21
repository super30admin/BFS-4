// Time Complexity : O(m * n)
// Space Complexity : O(m * n)

class Solution {
    int m, n;
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
            return board;
        m = board.length;
        n = board[0].length;
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dirs = new int[][]{{-1, -1},{-1, 0},{-1, 1},{0, -1},{0, 1},{1, -1},{1, 0},{1, 1}};
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int mines = getMines(board, cur[0], cur[1]);
            if(mines != 0) {
                board[cur[0]][cur[1]] = (char)(mines + '0');
            }
            else {
                for(int[] dir: dirs){
                    int i = dir[0] + cur[0];
                    int j = dir[1] + cur[1];
                    if(i >= 0 && j >= 0 && i < m && j < n && board[i][j] == 'E'){
                        q.add(new int[] {i, j});
                        board[i][j] = 'B';
                    }
                }
            }
        }
        return board;
    }
    
    private int getMines(char[][] board, int r, int c) {
        int count = 0;
        for(int[] dir: dirs){
            int i = dir[0] + r;
            int j = dir[1] + c;
            if(i >= 0 && j >= 0 && i < m && j < n && board[i][j] == 'M')
                count++;
        }
        return count;
    }
}