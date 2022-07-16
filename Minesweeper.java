// Time: O(MN) | Space: O(MN)

//dfs

class Solution {
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        dirs = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        // if the starting click itself has mine, then we should mark it 'X', then game over
        if (board[click[0]][click[1]] == 'M')
            board[click[0]][click[1]] = 'X';
        else
            // else we process each cell
            dfs(board, click[0], click[1]);
        return board;
    }

    private void dfs(char[][] board, int row, int col) {

        int countMines = countMines(board, row, col);
        // if mine nearby, we should take counts of them and update it to a cell and don't process any neighbours of it further
        if (countMines > 0) {
            board[row][col] = (char) (countMines + '0');
            return;
        }
        // else we process each cell
        board[row][col] = 'B';
        for (int[] dir : dirs) {
            int nr = dir[0] + row;
            int nc = dir[1] + col;
            // every empty cell is processed
            if (nc >= 0 && nc < board[0].length && nr >= 0 && nr < board.length && board[nr][nc] == 'E') {
                dfs(board, nr, nc);
            }
        }
    }

    private int countMines(char[][] board, int i, int j) {
        int count = 0;
        for (int[] dir : dirs) {
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if (nc >= 0 && nc < board[0].length && nr >= 0 && nr < board.length && board[nr][nc] == 'M') count++;
        }
        return count;
    }
}


//bfs

class Solution {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        dirs = new int[][]{{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int []> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int countMines = countMines(board, curr[0], curr[1]);
            if(countMines > 0) {
                board[curr[0]][curr[1]] = (char) (countMines + '0');
            } else {
                for(int[] dir: dirs) {
                    int nr = dir[0]+curr[0];
                    int nc = dir[1]+curr[1];
                    if(nc >=0 && nc < board[0].length && nr >=0 && nr < board.length && board[nr][nc] == 'E') {
                        board[nr][nc] = 'B';
                        q.add(new int[]{nr, nc});
                    }
                }}
        }
        return board;
    }

    private int countMines(char[][] board, int i,int j) {
        int count = 0;
        for(int[] dir: dirs) {
            int nr = dir[0]+i;
            int nc = dir[1]+j;
            if(nc >=0 && nc < board[0].length && nr >=0 && nr < board.length && board[nr][nc] == 'M') count++;
        }
        return count;
    }
}