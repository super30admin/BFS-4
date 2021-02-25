/// Time, Space - O(N^2), O(N^2)
class Solution {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null||board.length==0){
            return new char[][]{};
        }
        
        dirs = new int[][] {{-1,-1},{1,1},{1,-1},{-1,1},{0,1},{0,-1},{1,0},{-1,0}};
        
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click);
        
        return board;
    }
    
    private void dfs(char[][] board, int[] click) {
        
        if(click[0]<0||click[1]<0||click[0]>=board.length||click[1]>=board[0].length ||board[click[0]][click[1]]!='E'){
            return;
        }
        int mines = getMines(board, click[0], click[1]);
        board[click[0]][click[1]] = 'B';
        if(mines ==0) {
            for(int[] dir : dirs) {
                int row = dir[0]+click[0];
                int col = dir[1]+click[1];
                
                dfs(board, new int[]{row, col});
            }
        }
        else {
            board[click[0]][click[1]] = (char)(mines + '0');
        }
    }
    
    private int getMines(char[][] board, int i, int j) {
        int mines = 0;
        
        for(int[] dir:dirs) {
            int row = i + dir[0];
            int col = j + dir[1];
               if(row>=0&&col>=0&&row<board.length&&col<board[0].length &&board[row][col]=='M') {
                        mines++;
               }
            
        }
        
        return mines;
    }
}
