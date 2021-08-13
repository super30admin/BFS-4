class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null || board.length==0) return board;
        dirs= new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        m=board.length;
        n=board[0].length;
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        Queue<Integer> r= new LinkedList<>();
        Queue<Integer> c= new LinkedList<>();
        r.add(click[0]);
        c.add(click[1]);
        board[click[0]][click[1]]='B';
        
        while(!r.isEmpty()){
            int i=r.poll();
            int j=c.poll();
            int mines=getMines(board,i,j);
            
            if(mines!=0){
                board[i][j]=(char)(mines+'0');
            }else{
                for(int[] dir:dirs){
                    int nr=i+dir[0];
                    int nc=j+dir[1];
                    if(nr>=0 && nr<m&&nc>=0&&nc<n&&board[nr][nc]=='E'){
                        r.add(nr);
                        c.add(nc);
                        board[nr][nc]='B';
                    }
                }
            }
        }
        
        return board;
    }
    private int getMines(char[][] board,int i,int j){
        int result=0;
        for(int[] dir:dirs){
            int nr=i+dir[0];
            int nc=j+dir[1];
            if(nr>=0 && nr<m&&nc>=0&&nc<n&&board[nr][nc]=='M'){ 
                result++;
            }
        }
        return result;
    }
}