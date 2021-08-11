
// time - O(M*N)
// space - O(M*N)

// BFS


class Solution {

    int [][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {

        if(board == null || board.length == 0 || board[0].length == 0) return board;
        dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};

        m = board.length; n = board[0].length;

        Queue<int []> q = new LinkedList<>();

        if(board[click[0]][click[1]] == 'M') { // check if the first click is mine then change it to 'X' and return the board
            board[click[0]][click[1]] = 'X';
            return board;

        }

        q.add(new int[] {click[0], click[1]}); // add the first click element to the queue
        board[click[0]][click[1]] = 'B'; // mark it as revealed

        while(!q.isEmpty()) {

            int [] curr = q.poll();
            int mines = getMines(board,curr[0], curr[1]);
            // System.out.println(mines);

            if(mines != 0) {

                board[curr[0]][curr[1]] = (char)(mines + '0'); // change the element to '1' if it is besides mine

            }
            else {

                for(int [] dir: dirs) {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];

                    if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E') {

                        q.add(new int[] {r,c});
                        board[r][c] = 'B'; // change other neighbors to revealed

                    }

                }
            }
        }
        return board;

    }

    private int getMines(char[][] board, int i, int j) {

        int count = 0;
        for(int [] dir: dirs) {

            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M') {
                count++;
            }
        }
        return count;
    }
}


// DFS

class Solution {

    int [][] dirs;
    int m, n;

    public char[][] updateBoard(char[][] board, int[] click) {

        if(board == null || board.length == 0 || board[0].length == 0) return board;
        dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};

        m = board.length; n = board[0].length;

        if(board[click[0]][click[1]] == 'M') { // check if the first click is mine then change it to 'X' and return the board
            board[click[0]][click[1]] = 'X';
            return board;

        }

        dfs(board,click[0],click[1]);
        return board;

    }

    private void dfs(char[][] board, int i, int j) {

        // base
        if(i < 0 || i == m || j < 0 || j == n || board[i][j] != 'E') return;
        //logic
        board[i][j] = 'B';
        int mines = getMines(board,i,j);
        if(mines != 0 ){
            board[i][j] = (char)(mines + '0');
        }
        else {
            for(int [] dir: dirs) {

                int r = dir[0] + i;
                int c = dir[1] + j;

                dfs(board,r,c);
            }
        }
    }

    private int getMines(char[][] board, int i, int j) {

        int count = 0;
        for(int [] dir: dirs) {

            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M') {
                count++;
            }
        }
        return count;
    }
}