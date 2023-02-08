//https://leetcode.com/problems/minesweeper
//TC : m*n 
//SC : m*n
class Solution {
    int dirs[][] = {{0,1},{1,0},{0,-1},{-1,0},{-1,1},{1,1},{1,-1},{-1,-1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(click[0]);
        q.add(click[1]);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int cr = q.poll();
            int cc = q.poll();
            int mines = mines(board,cr,cc,m,n);
            if(mines == 0){
                for(int[] dir : dirs){
                    int nr = cr + dir[0];
                    int nc = cc + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E'){
                        q.add(nr); 
                        q.add(nc);
                        board[nr][nc] = 'B';
                    }
                }  
            }else{
                board[cr][cc] = (char)(mines+'0');
            }
        }
        return board;
    }
    private int mines(char[][] board,int cr,int cc,int m, int n){
        int mines =0;
        for(int[] dir : dirs){
            int nr = cr + dir[0];
            int nc = cc + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M'){
                mines++;
            }
        }
        return mines;
    }
}
