
// Time Complexity : O(n*m)
// Space Complexity : O(n*m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :

// Your code here along with comments explaining your approach
// BFS
class Solution {
    public int snakesAndLadders(int[][] board) { 
        if(board==null || board.length==0 || board[0].length==0)
            return 0;
        
        int n= board.length;
        int m = board[0].length;
        
        int[] move = new int[n*m];
        
        int even=0;
        int i=n-1;
        int j=0;
        int index=0;
        
        while(i>=0 && j>=0){
            if(board[i][j] == -1){
                move[index] = -1;
            }else{
                move[index] = board[i][j] -1;
            }
            
            //use even to move forward or backward
            if(even %2==0){
                j++;
            }else{
                j--;
            }
            
            //check bounds
            if(j>= m){
                even++;
                i--;
                j--;
            }else if(j<0){
                even++;
                i--;
                j++;
            }
            index++;
        }
        
        // BFS
        Queue<Integer> q = new LinkedList<>();
        int minLevel = 0;
        
        q.add(0);
        move[0] = -2;
        while(!q.isEmpty()){
            int size = q.size();
            for(i=0; i< size; i++){
                int curr = q.poll();
                
                if(curr == n*m-1){
                    return minLevel;
                }
                
                //Explore possibilities
                for(j=1; j<=6; j++){
                    int nextMove = curr + j;
                    
                    if(nextMove < n*m && move[nextMove] != -2){
                        if(move[nextMove] == -1){
                            q.add(nextMove);
                        }else{
                            q.add(move[nextMove]);
                        }
                        
                        move[nextMove] = -2;
                    }
                }
                
            }
            minLevel++;
        }
        return -1; 
    }
}