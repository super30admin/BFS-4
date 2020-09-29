import java.util.LinkedList;
import java.util.Queue;

//DFS Approach
class Solution {
    int[][] directions = {{1,0}, {0,1}, {-1, 0}, {0, -1}, {1,1}, {-1,-1}, {-1, 1}, {1, -1}};
    int m, n;
 
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
             return board;
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        m = board.length; n= board[0].length;
        dfs(board, click[0], click[1]);
        return board;
    }
    //doing depth first traversal on the board
    private void dfs(char[][] board, int i, int j)
    {
        if(i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'E')
            return;
   
        int count = getMines(board, i, j);
        if(count == 0)
        {
            board[i][j] = 'B';
            for(int[] dir: directions)
            {
                int r = dir[0] + i;
                int c = dir[1] + j;  
                
                dfs(board, r, c);;
            }
        }else
        {
            board[i][j] = (char)(count + '0');
            return;
        }
    }
    //iterate through the neighbors and find out the number of mines
    private int getMines(char[][] board, int i, int j)
    {
        int count = 0;
        for(int[] dir: directions)
        {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M')
                count++;
        }
        return count;
    }
}
//Time Complexity : O(m * n)
//Space Complexity : O(m * n) for the recursion stack
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this :

//BFS Approach

class Minesweeper {
    int[][] directions = {{1,0}, {0,1}, {-1, 0}, {0, -1}, {1,1}, {-1,-1}, {-1, 1}, {1, -1}};
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)
             return board;
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        m = board.length; n= board[0].length;
        //iterate using queue to traverse along the board
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        board[click[0]][click[1]] = 'E';
        while(!queue.isEmpty())
        {
            int[] curr = queue.poll();
            int count = getMines(board, curr[0], curr[1]);
            if(count == 0)
            {
                board[curr[0]][curr[1]] = 'B';
                for(int[] dir: directions)
                {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E')
                    {
                        board[r][c] = 'B';
                        queue.add(new int[]{r,c});
                    }
                }
            }else
            {
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
        }
        return board;
    }
    private int getMines(char[][] board, int i, int j)
    {
        int count = 0;
        for(int[] dir: directions)
        {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M')
                count++;
        }
        return count;
    }
}
//Time Complexity : O(m * n)
//Space Complexity : O(m * n) for the queue
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this :