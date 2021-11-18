// Time Complexity : O(n*m)
// Space Complexity : O(m+n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

class Solution {
    private int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        if(board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                x = pos[0];
                y = pos[1];
                if(board[x][y] == 'E') {
                    board[x][y] = '0';
                    getMines(x, y, true, queue, board);
                    if(board[x][y] == '0') {
                        board[x][y] = 'B';
                        getMines(x, y, false, queue, board);
                    }
                }
            }
        }
        return board;
    }
    
    
    private void getMines(int x, int y, boolean isMine, Queue<int[]> q, char[][] board) {
        for(int[] dir : dirs) {
            int adjX = x + dir[0], adjY = y + dir[1];
            if(adjX >= 0 && adjX < board.length && adjY >= 0 && adjY < board[0].length) {
               if(board[adjX][adjY] == 'M' && isMine) {
                    board[x][y]++;
               }
               if(board[adjX][adjY] == 'E' && !isMine) {
                   q.add(new int[]{adjX, adjY});
               }
            }
        }
    }
}