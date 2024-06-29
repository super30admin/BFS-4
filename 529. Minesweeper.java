// Time Complexity : O(16 * m * n)
// Space Complexity : O(m * n)
// Method used : BFS

class Solution {

    int[][] dirs;
    int rows, cols;

    public char[][] updateBoard(char[][] board, int[] click) {

        if(board == null || board.length == 0) return board;

        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        // Check for 8 directions
        dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
        rows = board.length;
        cols = board[0].length;

        Queue<int[]> queue = new LinkedList();

        // Add it to the queue and mark it as B as we visited
        queue.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while(!queue.isEmpty())
        {
            int[] curr = queue.poll();

            // This will count us adjacent mines
            int countMines = countMines(board, curr[0], curr[1]);

            // We have adjacent mines so we don't add its neighbours to queue
            if(countMines > 0)
            {
                board[curr[0]][curr[1]] = (char) (countMines + '0');
            }

            // There are no adjacent mines. Now traverse the neighbours once again and check if theya re empty and add them to queue
            else
            {
                for(int[] dir : dirs)
                {
                    int new_row = dir[0] + curr[0];
                    int new_col = dir[1] + curr[1];

                    if(new_row >= 0 && new_row < rows && new_col >= 0 && new_col < cols && board[new_row][new_col] == 'E')
                    {
                        board[new_row][new_col] = 'B';
                        queue.add(new int[]{new_row, new_col});
                    }
                }
            }
        }

        return board;
    }

    private int countMines(char[][] board, int row, int col)
    {
        int count = 0;

        for(int[] dir : dirs)
        {
            int new_row = dir[0] + row;
            int new_col = dir[1] + col;

            if(new_row >= 0 && new_row < rows && new_col >= 0 && new_col < cols && board[new_row][new_col] == 'M')
                ++count;
        }

        return count;
    }
}
