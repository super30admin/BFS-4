// Time Complexity :o(mn)
// Space Complexity :O(mn)
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
//DFS SOLUTION
class Solution {
    int[] dx={0,0,1,-1,1,1,-1,-1};
    int[] dy={1,-1,0,0,1,-1,1,-1};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        int row=click[0];
        int col=click[1];
        Queue<int[]> q=new LinkedList<>();
        if(board[row][col]=='M')
        { board[row][col]='X';
             return board;        
         }
        board[row][col]='B';
       q.add(new int[]{row,col});
        
        while(!q.isEmpty()){
         
            int[] current = q.poll();
            int i=current[0];
            int j=current[1];

            int count=countmine(board,i,j);
            if(count ==0){
            for(int k=0;k<8;k++){
                int x=i+dx[k];
                int y=j+dy[k];
                
                if(isvalid(board,x,y) && board[x][y]=='E'){
                    board[i][j] ='B';
                    q.add(new int[]{x,y});
                }
            }
            }
            else{
                board[i][j]=(char) (count+'0');
            }
        }
        
        
        return board;
    }

    
    public int countmine(char[][] board,int row,int col){
       int count=0;
        for(int j=0;j<8;j++){
        int x=row+dx[j];
        int y=col+dy[j];
            if(isvalid(board,x,y) && board[x][y]=='M') count++;
   
        }
         return count;
    }
    public boolean isvalid(char[][] board,int x,int y){
        return x>=0 && y>=0 && x<board.length && y<board[0].length;
    }
}

// //DFS SOLUTION
// class Solution {
//     int[] dx={0,0,1,-1,1,1,-1,-1};
//     int[] dy={1,-1,0,0,1,-1,1,-1};
//     public char[][] updateBoard(char[][] board, int[] click) {
//         int row=click[0];
//         int col=click[1];
//         if(board[row][col]=='M')
//         { board[row][col]='X';
//              return board;        
//          }
//         board[row][col]='B';
//         dfsvisit(board,row,col);
//         return board;
//     }
//     public void dfsvisit(char[][] board,int row,int col){
//         int count=countmine(board,row,col);
//         if(count ==0){
//         board[row][col]='B';
//         for(int k=0;k<8;k++){
        
//         int x=row+dx[k];
//         int y=col+dy[k];
        
//         if(isvalid(board,x,y) && board[x][y]=='E')
//         {
//             dfsvisit(board,x,y);
//         }
//         }
//         }
//         else{
//             board[row][col]= (char)(count+'0');
//         }
        
//     }
    
//     public int countmine(char[][] board,int row,int col){
//        int count=0;
//         for(int j=0;j<8;j++){
//         int x=row+dx[j];
//         int y=col+dy[j];
//             if(isvalid(board,x,y) && board[x][y]=='M') count++;
   
//         }
//          return count;
//     }
//     public boolean isvalid(char[][] board,int x,int y){
//         return x>=0 && y>=0 && x<board.length && y<board[0].length;
//     }
// }