// Approach : Using BFS Approach . At every Click position check if it is mine then return else check 8
// neighbors of that click position to see if we have mines if yes replace the position with mine count and donot add to queue
// If there is no mine count then add those neigbours to Queue
// Time : O(m*n)
// Space : O(m*n)
class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        this.m = board.length;
        this.n = board[0].length;
        Queue<int []> q = new LinkedList<>();
        if(board[click[0]][click[1]] == 'M'){
            // Landed on revealed mine - so game over
            board[click[0]][click[1]] = 'X';
            return board;
        }
        q.add(new int[]{click[0],click[1]});
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int mineCount = mineCount(board,curr[0],curr[1]); // get minecount of this curr within all 8 neighbours
            if(mineCount == 0){
                board[curr[0]][curr[1]] = 'B';
                // We can add all its neigbours
                for(int [] dir : dirs){
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    if(r >= 0 && r < m && c >=0 && c<n && board[r][c] =='E'){ // visited or not
                        board[r][c] = 'B'; // revealed Blank Square
                        q.add(new int []{r,c});
                    }

                }
            } else {
                // update the mine count at its position and don't add neighbours to Queue
                board[curr[0]][curr[1]] = (char)(mineCount+'0');
            }
        }

        return board;
    }

    public int mineCount(char[][] board, int i, int j){
        int count =0;
        for(int [] dir : dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r < 0 || r>=m || c < 0 || c >= n) continue;
            if(board[r][c] == 'M') count++;
        }
        return count;
    }
}

// Approach : Using DFS
// Time : O(m*n)
// Space : O(m*n)
class Solution {
    int[][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {
        this.dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
                { -1, -1 } };
        this.m = board.length;
        this.n = board[0].length;
        if (board[click[0]][click[1]] == 'M') {
            // Landed on revealed mine - so game over
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click[0], click[1]);
        return board;
    }

    public void dfs(char[][] board, int i, int j) {

        board[i][j] = 'B';// Initially reveal it as blank square because we already checked it is not mine
        int mineCount = mineCount(board, i, j);
        if (mineCount != 0) {
            board[i][j] = (char) (mineCount + '0');
        } else {
            // We can add all its neigbours
            for (int[] dir : dirs) {
                int r = i + dir[0];
                int c = j + dir[1];
                if (r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E') { // unrevealed
                    dfs(board, r, c);
                }
            }
        }
    }

    public int mineCount(char[][] board, int i, int j) {
        int count = 0;
        for (int[] dir : dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if (r < 0 || r >= m || c < 0 || c >= n)
                continue;
            if (board[r][c] == 'M')
                count++;
        }
        return count;
    }
}
