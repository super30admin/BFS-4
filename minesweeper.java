//DFS
//Time complexity:O(mn)
//Space complexity:O(mn)

class Solution {
    int dirs[][];
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null||board.length==0){
            return board;
        }
        dirs=new int[][] {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        m=board.length;
        n=board[0].length;
        int i=click[0];
        int j=click[1];
        if(board[i][j]=='M'){
            board[i][j]='X';
            return board;
        }
        dfs(board,i,j);
        return board;
    }
    
    public void dfs(char[][] board,int i,int j){
        if(i<0 || j<0 || i>=m || j>=n || board[i][j]!='E'){
            return;
        }
        //base
        //logic
        int mines=getMines(board,i,j);
        if(board[i][j]=='M'){
            board[i][j]='X';
            return;
        }
        if(mines==0){
            board[i][j]='B';
            for(int[] dir:dirs){
                int r=dir[0]+i;
                int c=dir[1]+j;
                dfs(board,r,c);
            }
        }
        else{
            board[i][j]=(char)(mines + '0');
        }
        
    }
    
    private int getMines(char[][] board,int i,int j){
        int count=0;
        for(int[] dir:dirs){
            int r=dir[0] + i;
            int c=dir[1] + j;
            if(r>=0 && c>=0 && r<m && c<n && board[r][c]=='M'){
                count++;
            }
        }
        return count;
    }
}

//BFS
//Time complexity:O(mn)
//Space complexity:O(mn)

class Solution {
    int dirs[][];
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null||board.length==0){
            return board;
        }
        dirs=new int[][] {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1},{-1,1},{-1,-1}};
        m=board.length;
        n=board[0].length;
        int i=click[0];
        int j=click[1];
        if(board[i][j]=='M'){
            board[i][j]='X';
            return board;
        }
        Queue<int []> q=new LinkedList();
       
        q.add(click);
         board[i][j]='B';
        while(!q.isEmpty()){
            int[] curr=q.poll();
            int r=curr[0];
            int c=curr[1];
            int mines=getMines(board,r,c);
            if(mines==0){
                for(int[] dir:dirs){
                    int row=r+dir[0];
                    int col=c+dir[1];
                    if(row>=0 && col>=0 && row<m && col<n && board[row][col]=='E'){
                        q.add(new int[] {row,col});
                        board[row][col]='B';
                    }
                }
            }
            else{
                board[r][c]=(char)(mines+'0');
            }
            
        }
        return board;
    }
    
   
    
    private int getMines(char[][] board,int i,int j){
        int count=0;
        for(int[] dir:dirs){
            int r=dir[0] + i;
            int c=dir[1] + j;
            if(r>=0 && c>=0 && r<m && c<n && board[r][c]=='M'){
                count++;
            }
        }
        return count;
    }
}