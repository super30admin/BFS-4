// Time Complexity : O(M*N)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<Integer> row=new LinkedList<>();
        Queue<Integer> col=new LinkedList<>();
          if(board[click[0]][click[1]]=='M'){
                board[click[0]][click[1]]='X';
                return board;
            }
        row.add(click[0]);
        col.add(click[1]);
         board[click[0]][click[1]]='B';
        int[][] dirs={{1,1},{-1,-1},{0,-1},{-1,0},{0,1},{1,0},{1,-1},{-1,1}};
        while(!row.isEmpty()){
            int currRow=row.poll();
            int currCol=col.poll();
          
            int mines=getMines(board,dirs,currRow,currCol);
            if(mines!=0){
              board[currRow][currCol]=(char)(mines+'0');
            }else{
                              
                for(int[] dir:dirs){
                    int newRow=currRow+dir[0];
                    int newCol=currCol+dir[1];
                    if(newRow>=0 && newRow<board.length && newCol>=0 && newCol<board[0].length && board[newRow][newCol]=='E'){
                            board[newRow][newCol]='B';
                            row.add(newRow);
                            col.add(newCol);

                    }
                }
            }
        }
        return board;
    }
    
    private int getMines(char[][] board,int[][] dirs,int row,int col){
        int count=0;
        for(int[] dir:dirs){
                int newRow=row+dir[0];
                int newCol=col+dir[1];
                if(newRow>=0 && newRow<board.length && newCol>=0 && newCol<board[0].length && board[newRow][newCol]=='M'){
                        count++;
                }
                
        }
        return count;
    }
}