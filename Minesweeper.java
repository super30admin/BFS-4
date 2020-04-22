// DFS
// Time Complexity - O(m * n)
// Space Complexity - O(m * n)
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = {{0,1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        //STEP1: check if there is an explosion
        int r = click[0], c = click[1];
        if(board[r][c] == 'M'){
            board[r][c] = 'X';
            return board;
        }
        
        
        if(board[r][c] != 'E' && board[r][c] != 'B') return board;
        
        dfs(board, r, c, dirs);
        
        return board;
        
    }
    
    private void dfs(char[][] board, int r, int c, int[][] dirs){
        
        int count = countMines(board, r, c, dirs);
        if(count == 0){
            board[r][c] = 'B';
            for(int[] dir: dirs){
                int nR = r + dir[0];
                int nC = c + dir[1];
                //check for bounds and if the node is not already visited
                if(isValidBounds(board, nR, nC) && board[nR][nC] != 'B'){
                    dfs(board, nR, nC, dirs);    
                }

            }
        }else{
             board[r][c] = (char) (count + '0');
        }
    }
    
    private int countMines(char[][] board, int r, int c, int[][] dirs){
        int count = 0;
        for(int[] dir: dirs){
            int nR = r + dir[0];
            int nC = c + dir[1];
            //check for bounds
            if(isValidBounds(board, nR, nC) && board[nR][nC] == 'M')
                count++;
            
        }
        return count;
    }
    
    private boolean isValidBounds(char[][] board, int r, int c){
        if(r >= 0 && r < board.length && c >= 0 && c < board[0].length)
            return true;
        return false;
    }
}

/**
 * 
 * 
 */

 // BFS
// Time Complexity - O(m * n)
// Space Complexity - O(m * n)
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = {{0,1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        //STEP1: check if there is an explosion
        int r = click[0], c = click[1];
        if(board[r][c] == 'M'){
            board[r][c] = 'X';
            return board;
        }
        
        //STEP2: check if the current cell is a digit then return
        if(board[r][c] != 'E' && board[r][c] != 'B'){
            return board;
        }
        
        Queue<int[]> q = new LinkedList<>();
        //STEP3: mark the cell as visited and add it to the Queue for processing
        board[r][c] = 'B';
        q.add(new int[]{r, c});
       
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1], dirs);
            if(count == 0){
                //STEP3: do BFS on all 8 neighbours
                for(int[] dir: dirs){
                    int nR = curr[0] + dir[0];
                    int nC = curr[1] + dir[1];
                    //check for bounds
                    if(!isValidBounds(board, nR, nC) || board[nR][nC] == 'B') continue;
                    board[nR][nC] = 'B';
                    q.add(new int[]{nR, nC});
                }
                
            }else{
                board[curr[0]][curr[1]] = (char) (count + '0');
            }
        }
        
        
        return board;
        
        
    }
    
    private int countMines(char[][] board, int r, int c, int[][] dirs){
        int count = 0;
        for(int[] dir: dirs){
            int nR = r + dir[0];
            int nC = c + dir[1];
            //check for bounds
            if(!isValidBounds(board, nR, nC)) continue;
            if(board[nR][nC] == 'M') count++;
        }
        return count;
    }
    
    private boolean isValidBounds(char[][] board, int r, int c){
        if(r >= 0 && r < board.length && c >= 0 && c < board[0].length)
            return true;
        return false;
    }
}