//time - O(m*n), space - O(m*n)
class Solution {
    int m, n;
    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null || board.length==0) return board;
        m = board.length;
        n = board[0].length;

        dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {1, 1}, {1,-1}, {-1,1}};

        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        dfs(board, click[0], click[1]);

        return board;
    }

    private void dfs(char[][] board, int r, int c){
        if(r < 0 || r==m || c < 0 || c==n || (board[r][c]!='E' && board[r][c]!='B')) return;

        board[r][c] = 'B';

        int mines = countMines(board, r, c);
        if(board[r][c]=='E' || board[r][c]=='B'){
            if(mines==0){
                for(int[] dir : dirs){
                    int row = r + dir[0];
                    int col = c + dir[1];
                    if(row>=0 && row<m && col>=0 && col<n){
                        if(board[row][col]=='E'){
                            board[row][col] = 'B';
                            dfs(board, row, col);
                        }
                    }
                }
            }
            else{
                board[r][c] = (char)(mines+'0');
            }
        }
        else if(board[r][c]=='M'){
            board[r][c] = 'X';
            return;
        }
    }

    private int countMines(char[][] board, int r, int c){
        int count = 0;
        for(int[] dir : dirs){
            int row = r + dir[0];
            int col = c + dir[1];
            if(row>=0 && col>=0 && row<m && col<n && board[row][col]=='M'){
                count++;
            }
        }

        return count;
    }
}
