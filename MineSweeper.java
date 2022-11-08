// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// m is the number of rows, n is the number of columns
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

//DFS
//TC: O(m*n)
// SC: O(m*n)
// m is the number of rows, n is the number of columns
class Solution {
    int[][] dirs;
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        //null check
        if(board == null || board.length == 0)
            return board;

        dirs = new int[][] {{0,1},{0,-1},{-1,0},{1,0},{-1,-1},{-1,1},{1,1},{1,-1}};
        m = board.length;
        n = board[0].length;

        //click is a mine
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board,click[0],click[1]);
        return board;
    }

    private void dfs(char[][] board, int i , int j){
        //base case
        if(i < 0 || j < 0 || i == m || j == n || board[i][j] != 'E')
            return;
        //logic
        board[i][j] = 'B';
        int count = countMines(board,i,j);
        if(count == 0){
            for(int[] dir: dirs){
                int nr = i + dir[0];
                int nc = j + dir[1];
                dfs(board,nr,nc);
            }
        }
        else{
            board[i][j] = (char)(count + '0');
        }
    }

    private int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int[] dir : dirs){
            int nr = i + dir[0];
            int nc = j + dir[1];

            if(nr < m && nc < n && nr >= 0 && nc >= 0 && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}


//BFS
//TC: O(m*n)
// SC: O(m*n)
// m is the number of rows, n is the number of columns

class Solution {
    int[][] dirs;
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        //null check
        if(board == null || board.length == 0)
            return board;

        dirs = new int[][] {{0,1},{0,-1},{-1,0},{1,0},{-1,-1},{-1,1},{1,1},{1,-1}};
        m = board.length;
        n = board[0].length;

        //click is a mine
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board,curr[0],curr[1]);
            //no mines in vicinity, then add babies to queue
            if(count == 0){
                board[click[0]][click[1]] = 'B';
                for(int[] dir: dirs){
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if(nr < m && nc < n && nr >= 0 && nc >= 0 && board[nr][nc] == 'E'){
                        q.add(new int[]{nr,nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
            else{
                board[curr[0]][curr[1]] = (char) (count + '0');
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int[] dir : dirs){
            int nr = i + dir[0];
            int nc = j + dir[1];

            if(nr < m && nc < n && nr >= 0 && nc >= 0 && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}