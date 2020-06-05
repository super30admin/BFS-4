// 529.
// time - O(m * n)
// space - O(m * n)

class Solution {
    int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}, {-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        //edge
        if(board == null || board.length == 0 || board[0].length == 0)
        {
            return board; //empty board
        }
        if(board[click[0]][click[1]] == 'M')
        {
            board[click[0]][click[1]] = 'X'; //stepped on a mine, turn M into X and return
            return board;
        }
        
        Queue<int[]> support = new LinkedList<>();
        support.offer(click); //push start pos into queue
        board[click[0]][click[1]] = 'B'; //marking as visited - initially set to 'B' - is cahnged to appropriate value when processed after removing from queue
        int score = 0; //tracks possible number of cliks after  1st click before stepping into mine
        
        while(!support.isEmpty())
        {
            int layerSize = support.size();
            for(int x = 0; x < layerSize; x++)
            {
                int[] current = support.poll(); //poll the current
                int neighborMines = getMines(board, current); //get # of neighboring mines
                //if mines count is positive, dont process the children and set this value in board to mines count
                if(neighborMines > 0) 
                {
                    board[current[0]][current[1]] = (char) (neighborMines + '0'); 
                }
                else // add children if mines count = 0
                {
                    for(int[] dir : dirs)
                    {
                        int nRow = current[0] + dir[0];
                        int nCol = current[1] + dir[1];
                        if(nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length)
                        {
                            //neighbor within bounds and unvisited
                            if(board[nRow][nCol] == 'E')
                            {
                                support.offer(new int[]{nRow, nCol}); //inserting neighbor into queue
                                board[nRow][nCol] = 'B'; //marking as visited
                            }
                        }
                    }
                }
            }
            score++;
        }
        
        return board;
    }
    
    //time - O(1)
    //space - O(1)
    private int getMines(char[][] board, int[] pos) {
        int mines = 0;
        for(int[] dir : dirs)
        {
            int nRow = pos[0] + dir[0];
            int nCol = pos[1] + dir[1];
            if(nRow >= 0 && nRow < board.length && nCol >= 0 && nCol < board[0].length)
            {
                if(board[nRow][nCol] == 'M')
                {
                    mines++;
                }
            }
        }
        return mines;
    }
}
