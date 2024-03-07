/*Time Complexity: O(M*N)

Space Complexity: O(M*N)

Did this code successfully run on Leetcode : Yes

Approach: DFS

Prob: 529. Minesweeper
*/

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int [][] dirs = new int [][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,1},{1,-1},{-1,-1}};
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board,click[0],click[1],dirs);
        return board;
    }
    private void dfs(char[][] board,int r,int c,int [][] dirs){
        // base
        if(r < 0 || r == board.length || c < 0 || c == board[0].length 
            || board[r][c] != 'E') return;
        // logic
        board[r][c] = 'B';
        int count = countMines(board,r,c,dirs);
        if(count == 0){
            for(int [] dir : dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];
            dfs(board,nr,nc,dirs);
            }
        }
        else{
            board[r][c] = (char)(count + '0');
            }   
        }
    private int countMines(char[][] board, int r, int c, int [][] dirs){
        int count = 0;
        for(int [] dir : dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr >=0 && nr < board.length && nc >= 0 && nc < board[0].length 
            && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}