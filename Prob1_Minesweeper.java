//TC : O(m * n) // Exploring neighbours in all 8 directions for cell
//SC : O(1)

//DFS Solution
class Solution {
    int[][] directions;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)  return board;
        m = board.length;
        n = board[0].length;
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        directions = new int [][]{ {0,1}, {0,-1}, {1,0}, {-1,0}, {-1,-1}, {-1,1}, {1,-1}, {1,1} };
        
        dfs(board, click[0], click[1]);
        return board; 
    }
    public void dfs(char[][] board, int i, int j){
        //Base Cases
        if(i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'E')    return;
        
        //Main Logic
        board[i][j]  = 'B';
        int countMines = getMines(board, i, j);
            
            if(countMines == 0){
                for(int[] dir : directions){
                    int row = dir[0] + i;
                    int col = dir[1] + j;
                    dfs(board, row, col);
                }
            }else{
                board[i][j] = (char)(countMines + '0');
            }
    }
    
    public int getMines(char[][] board, int i, int j){
        int num = 0;
        
        for(int[] dir : directions){
            int row = dir[0] + i;
            int col = dir[1] + j;
            
            if(row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 'M')
                num++;
        }
        
        return num;
    }
}


/*


//TC : O(m * n) // Exploring neighbours in all 8 directions for cell
//SC : O(N) // Queue

//BFS Solution
class Solution {
    int[][] directions;
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0)  return board;
        m = board.length;
        n = board[0].length;
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        directions = new int [][]{ {0,1}, {0,-1}, {1,0}, {-1,0}, {-1,-1}, {-1,1}, {1,-1}, {1,1} };
        
        Queue<int[]> queue = new LinkedList<>();
        queue.add(click);
        board[click[0]][click[1]] = 'B';
        while(!queue.isEmpty()){
            int[] pair = queue.poll();
            
            int countMines = getMines(board, pair[0], pair[1]);
            
            if(countMines == 0){
                for(int[] dir : directions){
                    int row = dir[0] + pair[0];
                    int col = dir[1] + pair[1];
                    if(row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 'E'){
                        queue.add(new int[]{row,col});
                        board[row][col] = 'B';
                    }
                    
                }
            }else{
                board[pair[0]][pair[1]] = (char)(countMines + '0');
            }
        }
       return board; 
    }
    
    public int getMines(char[][] board, int i, int j){
        int num = 0;
        
        for(int[] dir : directions){
            int row = dir[0] + i;
            int col = dir[1] + j;
            
            if(row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 'M')
                num++;
        }
        
        return num;
    }
}


*/