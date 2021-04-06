//time complexity-O(m*n)
//Space complexity-O(m*n)
//Ran on leetcode-Yes
//Solution with comments-
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]]=='M'){//update if first click is mine
            board[click[0]][click[1]]='X';
            return board;
        }
        dfs(board,click[0],click[1]);
        return board;
    }
    int[][] dirs={{1,0},{0,1},{-1,0},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1}}; 
    
    public void dfs(char [][] board, int i, int j){//dfs for all 8 directions and visiting nodes only marked E
        int mines=getmines(board,i,j);
        if(mines==0){
            board[i][j]='B';
            for(int[] dir :dirs){
                int x=i+dir[0];
                int y=j+dir[1];
                if(x>=0 && x<board.length && y>=0 && y<board[0].length && board[x][y]=='E'){
                    dfs(board,x,y);
                }     
            }
        }
        else{
            board[i][j]=(char)(mines+'0');
        }
    }
    public int getmines(char[][] board, int i, int j){//couunt all the mines surrounding current position
        int mines=0;
        for(int[] dir :dirs){
            int x=i+dir[0];
            int y=j+dir[1];
            if(x>=0 && x<board.length && y>=0 && y<board[0].length && board[x][y]=='M')
                mines++;
        }
        return mines;
    }
}