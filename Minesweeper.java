//TC: O(m*n)
//SC: O(m*n)
//Running on leetcode: yes
class Solution {
    int[][] dirs;
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null || board.length==0) return board;
        m=board.length;
        n=board[0].length;
        dirs = new int[][] {{-1,0},{0,1},{-1,1},{-1,-1},{1,-1},{1,1},{1,0},{0,-1}};
        if(board[click[0]][click[1]] =='M'){
            board[click[0]][click[1]] ='X';
            return board;
        }
        //BFS
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {click[0],click[1]});
        board[click[0]][click[1]]='B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int mines = getMines(board,curr);
            if(mines != 0){
                board[curr[0]][curr[1]]=(char)(mines+'0');
            }
            else{
                for(int[] dir : dirs){
                    int row = curr[0]+dir[0];
                    int col = curr[1]+dir[1];
                    if(row>=0 && row<m && col>=0 && col<n && board[row][col]=='E'){
                        q.add(new int[] {row,col});
                        board[row][col]='B';
                    }
                }
            }
        }
        return board;
    }
    private int getMines(char[][] board, int[] curr){
        int total=0;
        for(int[] dir : dirs) {
            int row = curr[0]+dir[0];
            int col = curr[1]+dir[1];
            if(row>=0 && row<m && col>=0 && col<n && board[row][col]=='M') {
                total++;
            }
        }
        return total;
    }
}
