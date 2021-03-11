/*
TC : O(8mn)
SC : O(1)

Here, we do bfs starting from the given click poisiton.
Get the number of mines whihc might be in neighbors of curr cell.
if mines == 0 ,At each cell, check if the cell is already visited, if not mark it as visited and add to queue. 
else board[r][c] = mines



*/
class Solution {
    int m;
    int n;
    int[][]dirs = {{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        
        //edge case
        if(board.length == 0|| board == null) return board;
        this.m = board.length;
        this.n = board[0].length;
        
        
        
        int c1 = click[0];
        int c2 = click[1];
        //if the first click is Mine itself, then retrun
        if(board[c1][c2] == 'M'){
            board[c1][c2]= 'X';
            return board;
        }
        
        Queue<int[]>queue = new LinkedList<>();
        queue.add(new int[] {c1,c2});
        board[c1][c2] = 'B'; //visited
        
        bfs(board,queue);
        return board;
        
    }
     
    //bfs funciton
    private void bfs(char[][]board, Queue<int[]>queue){
        while(!queue.isEmpty()){
            int[]curr = queue.poll();
            int mines = getNumberOfMines(board,curr[0],curr[1]); //get the number of mines
            if(mines == 0){ //if zero
                for(int[]d : dirs){ //check all neighbors
                    int nr = curr[0]+d[0];
                    int nc = curr[1]+d[1];
                    if(isSafe(nr,nc,board)){
                        queue.add(new int[]{nr,nc});
                        board[nr][nc] = 'B'; //mark as visited
                    }
                }
            }else{
                board[curr[0]][curr[1]] = (char) (mines+'0'); //add number of mines
            }
        }
    }
    
    private int getNumberOfMines(char[][]board,int r,int c){
        int mines = 0;
        for(int []d: dirs){
            int nr = r+d[0];
            int nc = c+d[1];
            if(nr >= 0 && nr < m && nc >= 0 && nc< n && board[nr][nc] == 'M')mines++;
             
        }
               return mines;
    }
    
    private boolean isSafe(int nr, int nc, char[][]board){
        return(nr >= 0 && nr < m && nc >= 0 && nc < n && board[nr][nc] == 'E');
    }
}