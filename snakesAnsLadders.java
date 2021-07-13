//Time Complexity : O(r*c)
//Space Complexity : O(r*c)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : no
class Solution {
    public int snakesAndLadders(int[][] board) {
        
        if(board.length == 0){
            
            return -1;
        }
        
        int r = board.length;
        int[] moves = new int[r*r];
        int idx = 0;
        int i = r - 1;
        int j = 0;
        int even = 0;
        
        while(i >= 0 && j >=0 ){
            
            if(board[i][j] == -1){
                
                moves[idx] = -1;
            }
            
            else{
                
                moves[idx] = board[i][j] - 1;
            }
            
            idx ++;
            
            if(even % 2 == 0){ // for even row
                j ++;
                if(j == r){
                    
                    j = r - 1;
                    i --;
                    even ++;
                }
                
                
            }
            
            else{    // for odd row
                j --;
                if(j < 0 ){
                    
                    j = 0;
                    i --;
                     even ++;
                }
               
            }
        }
        Queue<Integer> q1 = new LinkedList<>();
        q1.add(0);
        moves[0] = -2;
        int minMoves = 0;
        
        while(!q1.isEmpty()){
            
            int size = q1.size();
            
            for(int l = 0; l < size; l ++){
                
                int curr = q1.poll();
                
                if(curr == r * r - 1){
                    
                    return minMoves;
                }
                
                for(int k = 1; k <= 6; k ++){
                    
                    int child = curr + k;
                    
                    if(child > r*r - 1){
                        
                        break;
                    }
                    
                    if(moves[child] != -2){
                       
                        if(moves[child] == -1){
                            
                            q1.add(child);
                        }
                        else{
                            
                            q1.add(moves[child]);
                            
                        }
                        moves[child] = -2;
                    }
                   
                }
            }
            minMoves ++;
        }
        
        return -1;
    }
}