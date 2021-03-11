class Solution {

    int [][]dirs;
    int m;
    int n;
    
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        dirs = new int[][]{{-1,0}, {-1,-1}, {0,-1}, {1,-1}, {1,0}, {-1,1}, {0,1}, {1,1} };
        m = board.length;
        n = board[1].length;
        
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<int []> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int i = curr[0];
            int j = curr[1];
            int mines = getMines(board, i,j);
            if(mines == 0){
                for(int[] dir : dirs){
                    int r = i+dir[0];
                    int c = j+dir[1];
                    if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E'){
                        board[r][c] = 'B';
                        q.add(new int[]{r,c});
                    }
                }
            } else {
                board[i][j] = (char)(mines + '0');
            }
        }
        
        return board;  
        
    }
    
    private int getMines(char[][] board, int i, int j){
        int result = 0;
        for(int[] dir: dirs){
            int r = i+dir[0];
            int c = j+dir[1];
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M'){
                result++;
            }
        }
        return result;
    }
}