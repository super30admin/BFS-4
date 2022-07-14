TC:O(m*n)
SC:O(m*n)

class Solution {
    int m,n;
     int [][]dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        dirs=new int[][]{{0,1},{0,-1},{1,0},{-1,0},{-1,1},{1,-1},{-1,-1},{1,1}};
        m = board.length;
        n=board[0].length;
        if(board.length == 0) return board;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int []>q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int [] cur = q.poll();
            int minecount = countmines(board,cur);
            if(minecount > 0)
            {
                
                board[cur[0]][cur[1]] = (char)(minecount + '0');
            }
            else{
                for(int [] dir : dirs){
                    int nr = cur[0]+dir[0];
                    int nc = cur[1]+dir[1];
                    if(nr>=0 && nr<m && nc >=0 && nc<n && board[nr][nc] == 'E'){
                        
                        q.add(new int []{nr,nc});
                        board[nr][nc] = 'B';
                    }

                }
            }
           
        }
        return board;
    }
    private int countmines(char [][]board ,int [] curr){
        dirs=new int[][]{{0,1},{0,-1},{1,0},{-1,0},{-1,1},{1,-1},{-1,-1},{1,1}};
        int count = 0;
         for(int [] dir:dirs){
             int r = curr[0] + dir[0];
             int c = curr[1] + dir[1];
             if(r>=0 && r<m && c>=0 && c<n && board[r][c] == 'M'){
                 count++;
             }
                 
         }
        System.out.println(count);
        return count;
    }
}