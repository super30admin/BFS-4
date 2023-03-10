// Approach 1: BFS
// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Missed marking visited while adding to the queue and was getting TLE error

// Your code here along with comments explaining your approach
// Do a BFS, at each node count no of mines
// if count>0, update its value as count and do not process neighbors
// else change value to 'B', process its neighbors. 

class Solution {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        int n = board.length, m = board[0].length;
        dirs = new int[][]{{-1,-1},{-1,0},{-1,1},{1,0},{1,-1},
            {1,1},{0,-1},{0,1}};
        if(board[click[0]][click[1]]=='M')
        {
            board[click[0]][click[1]] = 'X'; return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]]='B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int mines = countMines(curr, board, m, n);
            if(mines==0){
                // board[curr[0]][curr[1]]='B';
                for(int[] dir:dirs){
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    if(r>=0&&r<n&&c>=0&&c<m&&board[r][c]=='E')
                        {q.add(new int[]{r,c});
                        board[r][c]='B';}
                }
            }
            else
                board[curr[0]][curr[1]] = (char)('0'+mines);
        }
        return board;
    }
    private int countMines(int[] curr, char[][] board, int m, int n){
        int count=0;
        for(int[] dir:dirs){
            int r = curr[0] + dir[0];
            int c = curr[1] + dir[1];
            if(r>=0&&r<n&&c>=0&&c<m&&board[r][c]=='M')
                count++;
        }
        return count;
    }
}

// Approach 2: DFS
// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Yes

// Your code here along with comments explaining your approach
// Similar to Approach 1 but using DFS

class Solution {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        int n = board.length, m = board[0].length;
        dirs = new int[][]{{-1,-1},{-1,0},{-1,1},{1,0},{1,-1},
            {1,1},{0,-1},{0,1}};
        if(board[click[0]][click[1]]=='M')
        {
            board[click[0]][click[1]] = 'X'; return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int mines = countMines(curr, board, m, n);
            if(mines==0){
                board[curr[0]][curr[1]]='B';
                for(int[] dir:dirs){
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];
                    if(r>=0&&r<n&&c>=0&&c<m&&board[r][c]=='E')
                        q.add(new int[]{r,c});
                }
            }
            else
                board[curr[0]][curr[1]] = (char)('0'+mines);
        }
        return board;
    }
    private int countMines(int[] curr, char[][] board, int m, int n){
        int count=0;
        for(int[] dir:dirs){
            int r = curr[0] + dir[0];
            int c = curr[1] + dir[1];
                    if(r>=0&&r<n&&c>=0&&c<m&&board[r][c]=='M')
                        count++;
        }
        return count;
    }
}