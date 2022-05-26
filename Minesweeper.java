// Time Complexity : O(n*m) n,m = dimensions of board
// Space Complexity : O(n*m) n,m = dimensions of board
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {

    //BFS approach

    int[][] dirs;
    int m; int n;

    public char[][] updateBoard(char[][] board, int[] click) {

        m = board.length;
        n = board[0].length;

        dirs = new int[][] {
            {0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}
        };

        //if first click is itself a mine
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int []> q = new LinkedList();

        //add first click position to queue and mark it visited
        q.add(click);
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty())
        {
            int[] current = q.poll();
            int mines = countMines(board, current[0], current[1]);
            if(mines == 0)
            {
                for(int[] dir : dirs)
                {
                    int r = current[0] + dir[0];
                    int c = current[1] + dir[1];
                    if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'E')
                    {
                        q.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }
                }
            }
            else
                board[current[0]][current[1]] = (char)(mines + '0');
        }

        return board;
    }

    int countMines(char[][] board, int i, int j)
    {
        int count=0;

        for(int[] dir : dirs)
        {
            int r = i + dir[0];
            int c = j + dir[1];

            if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'M')
                count++;
        }

        return count;
    }
}
