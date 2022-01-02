// snakes and ladder

// time - O(n2)
// space - O(n2)
class Solution {
    public int snakesAndLadders(int[][] board) {
        
        int n = board.length;
        int[]moves = new int[n * n];
        
        int row = n - 1;
        int col = 0;
        int even = 0;
        int index = 0;
        
        
        int minMoves = 0;
        
        while(row >= 0){
            
            if(board[row][col] == -1){
                moves[index] = index;
            }else{
                moves[index] = board[row][col] - 1;
            }
            
            index++;
            
            if(even % 2 == 0){
                col++;
            }else{
                col--;
            }
            
            if(col == n){
                col--;
                row--;
                even++;
            }
            if(col == -1){
                col++;
                row--;
                even++;
            }
        }
        
        
        Queue<Integer> q = new LinkedList<>();
        
        q.add(moves[0]);
        moves[0] = -2;
        
        while(!q.isEmpty()){
            
            int size = q.size();
            
            for(int i = 0; i < size; i++){
                
                int curr = q.poll();
                System.out.println(curr);
                
                for(int j = 1; j <= 6; j++){
                    
                    int newPosition = curr + j;
                    
                    if(moves[newPosition] == n * n - 1){
                        System.out.println("Enter..");
                        return minMoves + 1;
                    }
                    
                    if(newPosition  < n * n  && moves[newPosition] != -2){
                        q.add(moves[newPosition]);
                        moves[newPosition] = -2;
                    }
                }
            }
            
            minMoves++;
        }
        
       return -1;
    }
}