class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        int[][] dirs = new int[][]{{0,1},{1,0},{-1,0},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
        int n=board.length;
        int m = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(click);
        while(!q.isEmpty()){
            int [] curr = q.poll();
            if(board[curr[0]][curr[1]]!='E') continue;
            int total=0;
            for(int[]dir:dirs){
                int r = curr[0]+dir[0];
                int c = curr[1]+dir[1];
                if(r>=0 && c>=0 && r<n && c<m && board[r][c]=='M'){
                    total++;
                }
            }
            board[curr[0]][curr[1]]= (total==0)?'B':(char)(total+'0');
            if(total==0){
                for(int[]dir:dirs){
                    int r = curr[0]+dir[0];
                    int c = curr[1]+dir[1];
                    if(r>=0 && c>=0 && r<n && c<m && board[r][c]=='E'){
                        q.offer(new int[]{r,c});
                    }
                }
            }
        }
        return board;
    }
}
