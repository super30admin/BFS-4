class Solution {
    //TC-O(NM),SC-O(NM)
    //add click to the queue, check its count of mines, if found, change the cell value to count,not found then process its neighbours
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board==null || board.length ==0){
            return board;
        }
        int m = board.length; int n = board[0].length;
        dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,1},{1,-1},{-1,-1}};
        Queue<int []> q = new LinkedList<>();
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        q.add(click);
        board[click[0]][click[1]]='B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countmines(board,curr[0],curr[1],m,n);
            if(count>0){
                board[curr[0]][curr[1]]=(char)(count+'0');
                // dont process the neighbours
            }
            else{
                // process the neighbours
                for(int[] dir:dirs){
                    int nr=curr[0]+dir[0];
                    int nc = curr[1]+dir[1];
                     if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc]=='E'){
                         q.add(new int[]{nr,nc});
                         board[nr][nc]='B';

                }
            }

        }
    
        
    }
    return board;
    }

    public int countmines(char[][] board, int i,int j,int m,int n){
        int count =0;
        for(int [] dir:dirs){
            int nr=i+dir[0];
            int nc = j+dir[1];
            if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc]=='M'){
                count++;
            }
        }
        return count;
    }
}