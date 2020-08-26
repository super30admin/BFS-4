// Time Complexity - O(mn) m = no of rows, n = no of cols
// Space Complexity - O(1)

// Approach:
// Either BFS or DFS will work. Count the no of mines. If the click is on mine, change that particular char to M
// and return the board. Otherwise count the no of mines, if its 0, then we traverse over E cells and convert them to B.
// If no of mines >= 1, then we take care on how many mines are there and we display the no on cell.

// BFS
class Solution {
    int[][] dirs = new int[][]{{0,1},{1,0},{-1,1},{-1,0},{0,-1},{1,1},{1,-1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(click == null || click.length == 0) return board;
        Queue<int[]> q = new LinkedList<>();
        int r = click[0], c= click[1];
        q.add(click);
        if(board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        board[r][c] = 'B';
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            r = curr[0];
            c = curr[1];
            int mines = getMines(board,r,c);
            if(mines == 0) {
                for(int[] dir:dirs) {
                    int i = dir[0]+curr[0];
                    int j = dir[1]+curr[1];
                    if(i >= 0 && j >= 0 && i< board.length && j < board[0].length && board[i][j] == 'E') {
                        q.add(new int[]{i,j});
                        board[i][j] = 'B';
                    }
                }
            } else {
                board[r][c] = (char)(mines+'0');
            }
        }
        return board;
    }
    
    private int getMines(char[][] board, int i ,int j) {
        int count = 0;
        for(int[] dir:dirs) {
            int r = i+dir[0];
            int c= j+dir[1];
            if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[r][c]=='M') {
                count++;
            }
        }
        return count;
    }
    
}