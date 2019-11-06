//Time complexity: O(M*N)*M
//Space Complexity: O(M*N)
//CHeck if the clicked cell is a mine if it is then mark it as 'X' as the mine is exposed.
//else count the mines adjacent to the cell using couontMines function by counting all the mines 'M' adjacent to the particular cell
//if the count is > 0 then the count should be stored at the position.Else update the board using updateBoard function.
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];
        
        if(board[x][y]=='M')
        {
            board[x][y] = 'X';
            return  board;
        }
        int count = countMines(board, x, y);
        if(count>0)
        {
            board[x][y] =(char) (count + '0');
        }
        else
        {
            updateBoard(board,x, y);
        }
        return board;
    }
    
    public int countMines(char[][] board, int x, int y)
    {
        int N = board.length;
        int M = board[0].length;
        int count = 0;
        for(int i=Math.max( x-1,0); i<=Math.min(x+1,N-1); i++)
        {
            for(int j=Math.max(y-1,0); j<=Math.min(y+1,M-1); j++)
            {
                if(board[i][j] == 'M')
                {
                    count++;
                }
            }
        }
        return count;
    }
    
    public void updateBoard(char[][] board, int x, int y)
    {
        if(x<0 || y<0 || y>=board[0].length || x>=board.length) return;
        int count = countMines(board, x, y);
        if(board[x][y] == 'E')
        {
            if(count ==0)
            {
                 board[x][y] = 'B';
                 for(int i = Math.max(0, x-1); i<=Math.min(x+1,board.length-1);i++)
                 {
                     for(int j = Math.max(0, y-1); j<=Math.min(y+1, board[0].length-1);j++ )
                     {
                         updateBoard(board, i, j);
                     }
                 }
            }
            else
            {
                board[x][y] = (char)(count+'0');
            }
        }
    }
}