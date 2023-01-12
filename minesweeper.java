// Time Complexity : O(m*n)
// Space Complexity :O(m*n)
class Solution {
    int m,n;
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null || board.length==0) return new char[][]{};
        m= board.length;
        n= board[0].length;
        dirs=new int[][]{{-1,0} , {0,-1}, {1,0}, {0,1} , {-1,-1}, {-1,1},{1,-1},{1,1}};
        //U L D R UL UR LL LR
        
        Queue<int[]> q=new LinkedList<>();
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        q.add(new int[]{click[0],click[1]});
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int[] curr=q.poll();
            int count= countMines(board,curr[0],curr[1]);
            if(count!=0){
                board[curr[0]][curr[1]] = (char)(count + '0');
                   
            }else{
                for(int[] dir:dirs){
                   int nr=dir[0] + curr[0];
                   int nc= dir[1] + curr[1];
                   if(nr>=0 && nc>=0 && nr<m && nc<n && board[nr][nc]=='E'){
                       board[nr][nc] = 'B';
                       q.add(new int[]{nr,nc});
                      
                   }
                 }
            }
           
        }
        return board;
    }
                        
        private int countMines(char[][] board,int r,int c ){
            int total=0;
            for(int[] dir:dirs){
                   int nr=dir[0] + r;
                   int nc= dir[1] + c;
                   if(nr>=0 && nc>=0 && nr<m && nc<n && board[nr][nc]=='M'){
                       total++;
                   }
           }
            return total;
        }
}