class SnakesAndLadders {
    
    // Time Complexity: O(nm)   (where n -> number of rows and m -> number of cols)
    // Space Complexity: O(nm)  
    
    public int snakesAndLadders(int[][] board) {
        // Edge Case Checking
        if(board == null || board.length ==0 || board[0] == null || board[0].length == 0)
            return -1;
        
        int n = board.length;
        int m = board[0].length;
        
        int i = n-1;
        int j = 0;
        int count = 0;
        int[] moves = new int[n*m];
        int even = 0;
        
        // Flattening into a 1D array --> at each board index we store the next position we can go from the current board index
        while(i >= 0 && j >= 0){
            // If we can't go anyhwere --> store the same index (indicating that we stay in the same place)
            if(board[i][j] == -1){
                moves[count] = count;
            }else{
                moves[count] = board[i][j] - 1;
            }
            count++;
            
            // Toggle the direction to move (left/right)
            if(even % 2 == 0)
                j++;
            else
                j--;
            
            if(j >= m){
                i--;
                j--;
                even++;
            }else if(j < 0){
                i--;
                j++;
                even++;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        int level = 0;
        q.offer(0);
        moves[0] = -2;
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int x = 0; x < size; x++){
                int curr = q.poll();
                if(curr == moves.length - 1)
                    return level;
                
                // We calculate the next six moves and add it to the queue if they are not visited
                for(int k = 1; k <= 6; k++){
                    int nextPos = curr + k;
                    if(nextPos < moves.length && moves[nextPos] != -2){
                        q.offer(moves[nextPos]);
                        moves[nextPos] = -2;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}