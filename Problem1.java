import java.util.*;
class Problem1 {
    public int check(char board[][], int x, int y){
         int dirs[][]={{0,1},{0,-1},{1,-1},{-1,-1},{1,1},{-1,1},{-1,0},{1,0}};
        int count=0;
        
 for(int[]d:dirs){
                     
                     int newx=x+d[0];
                     int newy=y+d[1];
                     
     if( newx>=0 &&newx<=board.length-1 &&newy>=0 && newy<=board[0].length-1 && board[newx][newy]=='M'){
                         count++;
                     }
                 }
        return count;
     }
     
     public char[][] updateBoard(char[][] board, int[] click) {
      int dirs[][]={{0,1},{0,-1},{1,-1},{-1,-1},{1,1},{-1,1},{-1,0},{1,0}};
         int visited[][]= new int[board.length][board[0].length];
        
         Queue<Integer> x= new LinkedList<>();
         Queue<Integer> y= new LinkedList<>();
         
         if(board[click[0]][click[1]]=='M'){board[click[0]][click[1]]='X'; return board;}
        
         x.add(click[0]); y.add(click[1]);
         
         while(!x.isEmpty()){
             
             int outx=x.poll();
             int outy=y.poll();
             
        
             int ch=check(board, outx, outy);
             
             if (ch==0){
                 board[outx][outy]='B';
                 for(int[]d:dirs){
                     
                     int newx=outx+d[0];
                     int newy=outy+d[1];
                     
     if( newx>=0 &&newx<=board.length-1 &&newy>=0 && newy<=board[0].length-1 && board[newx][newy]=='E'&&visited[newx][newy]==0){
                         x.add(newx);
                         y.add(newy);
                         visited[newx][newy]=1;
                     }
                 }
             }else{
                 
                 board[outx][outy]=(char)(ch+48);
             }
             
         }
         
         
      return board;   
     }
 }