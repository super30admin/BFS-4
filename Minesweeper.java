//Time Complexity:0(m*n)^2
//Space Complexity; o(m*n)
//Expln: If the click position is a mine turn 'X' and return if not add the click position in the queue and perform bfs on 8 neighbors
//If we find a mine in any of the neighbors turn the current cell with number of neighbor mines found and dont add neighbors to queue
//If no mine found in neighbor then add those to the queue by turning them visited'B'
class Solution {
    int[][] dirs = {{-1,-1}, {-1,0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board.length == 0) return board;
        m = board.length;
        n = board[0].length;

        Queue<int[]> q = new LinkedList<>();
        if(board[click[0]][click[1]] == 'M') 
        {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty())
        {
            int[] curr = q.poll();
            int mines = getMines(board, curr);
            if(mines == 0){
                for(int[] dir: dirs)
                {
                    int[] neigh = {curr[0] + dir[0], curr[1]+dir[1]};
                    if( neigh[0] >=0 && neigh[0] <m  && neigh[1] >=0 && neigh[1] <n && (board[neigh[0]][neigh[1]]== 'E') )
                    {
                        q.add(neigh);
                        board[neigh[0]][neigh[1]] = 'B'; 
                    }
                }
            }
            else
            {
                    board[curr[0]][curr[1]] = (char) (mines + '0');
            }
            
        }
    return board;
    }
    public int getMines(char[][] board, int[] curr)
    {
            int mines = 0;
            for(int[] dir: dirs)
            {
                int[] neigh = {curr[0] + dir[0], curr[1]+dir[1]};
                if( neigh[0] >=0 && neigh[0] <m  && neigh[1] >=0 && neigh[1] <n )
                {
                    if(board[neigh[0]][neigh[1]]== 'M' || board[neigh[0]][neigh[1]]== 'X' )              
                        mines++;
                }
            }
        return mines;
    }
}