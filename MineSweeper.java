// Time Complexity : O(m^n)
// Space Complexity :O(m^n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
            
    int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
        int m,n;
        public char[][] updateBoard(char[][] board, int[] click) {
            
            m = board.length;
            n = board[0].length;
            Queue<int[]> q = new LinkedList<>();
            q.add(click);
            if(board[click[0]][click[1]] == 'M') 
            {
                board[click[0]][click[1]] = 'X';
                return board;
            }
            while(!q.isEmpty())
            {
                int size = q.size();
                for(int i = 0 ; i <size;i++)
                {
                    int[] block = q.poll();
                    int mines = getMines(board,block);
                    board[block[0]][block[1]] = mines == 0 ? 'B' : (char)(mines+'0');
                   if(mines == 0)
                   {
                        for(int [] dir:dirs)
                    {
                        int r = dir[0] + block[0];
                        int c = dir[1] + block[1];
                        if(r>=0 && r < m && c>=0 && c< n && board[r][c]=='E')
                        {
                            board[r][c] ='B';
                            q.add(new int[]{r,c});
                        }
                    }
                   }
                }
            }
            return board;
        }
        private int getMines(char[][] board, int[] block)
        {
            int mines = 0;
            for(int [] dir:dirs)
            {
                int r = dir[0] + block[0];
                int c = dir[1] + block[1];
                if(r>=0 && r < m && c>=0 && c< n && board[r][c]=='M')
                {
                    mines +=  1;
                }
            }
            return mines;
        }
    }