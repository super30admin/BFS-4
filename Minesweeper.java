class Solution {
    int dirs[][];
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.dirs=new int[][]{{-1,-1},{-1,1},{-1,0},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        this.m=board.length;
        this.n=board[0].length;
        
        
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        dfs(board,click[0],click[1]);
        return board;
    }
    
    
    private void dfs(char[][] board, int i, int j){
        board[i][j]='B';
        int count=getMines(board,i,j);
        if(count!=0){
            board[i][j]=(char)(count + '0');
        }else{
            for(int[] dir:dirs){
                int r=dir[0]+i;
                int c=dir[1]+j;
                if(r>=0 && c>=0 && r<m && c<n && board[r][c]=='E'){
                    dfs(board,r,c);
                }
            }
        }
    }
    
    
    private int getMines(char[][] board, int i,int j){
        int count=0;
        for(int[] dir:dirs){
            int r=dir[0]+i;
            int c=dir[1]+j;
            if(r>=0 && c>=0 && r<m && c<n && board[r][c]=='M'){
                count++;
            }
        }
        return count;
    }
}