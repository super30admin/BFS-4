/*
TC : O(m*m), square board
SC : O(m*m), using moves array
*/
class Solution {
    
    int m; 
    public int snakesAndLadders(int[][] board) {
        
        //edge case
        if(board == null || board.length == 0)return 0;
        
        this.m = board.length;  //square matrix both m and n will be same
        
        
        int[]moves = preprocess(board);
        
        Queue<Integer> queue = new LinkedList<>();  
        int minMoves = 0;
        
        queue.add(0);
        moves[0] = -2; //mark as visited
          
         while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size;i++){
                 int curr = queue.poll();
                if(curr == moves.length-1) return minMoves;
                  for(int k = 1; k <= 6;k++){
                      int child = curr+k;
                      if(child < moves.length && moves[child] != -2){
                          //not visited
                          if(moves[child] == -1){
                              queue.add(child);
                          }else{
                              queue.add(moves[child]);
                          }
                          moves[child] = -2;
                      }
                  }
            }
            minMoves++;
        }
        
        return -1;
        
    }
   
    
    
    private int[] preprocess(int[][]board){
        int[]res = new int[m * m];
        int index = 0; //ptr for res array
        
        int i = this.m-1;
        int j = 0;
        int even = 0; //this is for direction
        while(i >=0 && j >= 0){
            if(board[i][j] == -1){
                res[index] = -1;
                
            }else{
                res[index] = board[i][j]-1; //one value less
            }
            
            index++;
            //to go backard or forward in same row
             if(even % 2 == 0)j++; 
             else j--;
            
            //herre to change the row
            if(j == this.m){ //go up and poniter is at right boundary
                i--;
                even++;
                j--;
            }
            if(j < 0){ // go up and pointer is at left boundary
                i--;
                even++;
                j++;
            }
        }
        
        return res;    
    }
}