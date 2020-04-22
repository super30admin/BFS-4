// Time Complexity : O(n*n) size of the Matrix
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// Convert the matrix to flattened Array
// Use the Flattened Array and perform BFS by adding the value to queue and mark them visited with value -2.
// Each level gives the jump from one point to another
// finally return the level. If all the values in queue is completed without reaching the final destination return -1.

class Solution {
    public int snakesAndLadders(int[][] Board) {
        int n = Board.length;
        int m = Board[0].length;
        
        int N = n*m;
        
        int index = 0;
        int[] flattenedBoard = new int[N];
        int row = n-1;
        int col = 0;
        
        Boolean LeftToRight = true;
        
        while(row >= 0 && col >=0){
            // System.out.println("ROW" + row + "Col" + col);
            if(Board[row][col] > 0 )
                flattenedBoard[index] = Board[row][col] -1;
            else 
                flattenedBoard[index] = Board[row][col];            
            
           System.out.println( flattenedBoard[index]);
            if(LeftToRight){
                col++;
            }else{
                col--;
            }
            
             if(col >= m ){
                row--;
                col--;
                LeftToRight = !LeftToRight;
            }else if(col < 0 ){
                row--;
                col++;
                LeftToRight = !LeftToRight;
            }
            
            index++;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        flattenedBoard[0] = -2;
        int minMove = 0;
        while(!queue.isEmpty()){
            int level = queue.size();
           
            for(int i=0; i<level; i++ ){
                 int currentPos = queue.poll();    
                
                if(currentPos == N-1)
                    return minMove;
                
                for(int j = 1; j<7; j++){
                    int child = currentPos + j;
                    System.out.println(child + " Value in child " + flattenedBoard[child] );
                    if(child < N && flattenedBoard[child] != -2){
                        if(flattenedBoard[child] >= 0){
                            queue.add(flattenedBoard[child]);    
                        }else{
                            queue.add(child);
                        }
                         flattenedBoard[child] = -2;
                }
            }   
        }  
        minMove++;
    }
        return -1;
}

}


