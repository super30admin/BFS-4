//Time Complexity : O(r*c)
//Space Complexity : O(r*c)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : no
class Solution {
    
    int[] dirX; 
    int[] dirY; 
    int r;
    int c;
    public char[][] updateBoard(char[][] board, int[] click) {
        
        if(board.length == 0){
            
            return board;
        }
        
         if(board[click[0]][click[1]] == 'M'){
                
               board[click[0]][click[1]] = 'X';
                return board;
            }
            
         r = board.length;
         c = board[0].length;
        Queue<Integer> q1 = new LinkedList<>();
        
        q1.add(click[0]);
        q1.add(click[1]);
        board[click[0]][click[1]] = 'B';
        
        dirX = new int[]{0,0,1,-1,1,1,-1,-1};
        dirY = new int[]{1,-1,0,0,1,-1,1,-1};
        
        
        while(!q1.isEmpty()){
            
            int currX = q1.poll();
            int currY = q1.poll();
            int mines = getMines(board, currX, currY);
           
           if(mines == 0){
                
              for(int i = 0; i < 8; i ++){
                  
               int ndX = currX + dirX[i];
               int ndY = currY + dirY[i];
                  
            if(ndX < r && ndY < c && ndX > -1 && ndY > -1 && board[ndX][ndY] != 'B')
            {
                    
                board[ndX][ndY] = 'B';
                
                q1.add(ndX);
                q1.add(ndY);
            
            }  
                }
           }
              
               
               else{
                   
                   //put the mines count
                   board[currX][currY] = (char)(mines + '0');
               }
            }
            
            
            
        
        
        return board;
    }
        
        private int getMines(char[][] board, int currX, int currY){
            
            int mines = 0;
            
             for(int i = 0; i < 8; i ++){
                  
               int ndX = currX + dirX[i];
                int ndY = currY + dirY[i];
                  
            if(ndX < r && ndY < c && ndX > -1 && ndY > -1 && board[ndX][ndY] == 'M')
            {
                    
                mines ++;
            
            }  
                }
            
            return mines;
        }
}