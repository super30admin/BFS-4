    /*  Explanation
    # Leetcode problem link : https://leetcode.com/problems/minesweeper/
    Time Complexity for operators : o(n*n) 
    Extra Space Complexity for operators : o(n*n) for move array
    Did this code successfully run on Leetcode : NA
    Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
        # Basic approach : 
        # Optimized approach: 
                              
            # 1. 
                    A) 


    */ 

class Solution {
    public int snakesAndLadders(int[][] board) {
        
        if(board == null || board.length == 0 || board[0].length == 0)
            return 0;
        
        int n = board.length;
        int m = board[0].length;
        int move[] = new int[n*m];
        
        
        int even = 0;
        int index = 0;
        int i = n-1;
        int j = 0;
        
        while(i>=0 && j>=0){
            
            if(board[i][j] == -1){
                move[index] = -1;
            }else{
                move[index] = board[i][j] - 1;
            }
            
            // use even variable to move foawrd in the given 2 d matrix
            if(even % 2 == 0){
                j += 1;
            }else{
                j -= 1;
            }
            
            if(j >= m){
                even += 1;
                i -= 1;
                j -= 1;
            }else if (j < 0){
                even += 1;
                i -= 1;
                j += 1;
            }
            
            index += 1;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        int minLevel = 0;
        queue.add(0);
        move[0] = -2;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int x = 0; x < size; x++){
                int front = queue.poll();
                
                if(front == n*m -1){
                    return minLevel;
                }
                
                for(int y = 1; y<=6;y++){
                    int nextMove = front+y;
                    
                    if(nextMove < (n*m) && move[nextMove] != -2){
                        if(move[nextMove] == -1){
                            queue.add(nextMove);
                        }else{
                            queue.add(move[nextMove]);
                        }
                        
                        move[nextMove] = -2;
                    }
                }
            }
            
            minLevel += 1;
        }
        
        return -1;
    }
}