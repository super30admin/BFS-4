class Solution {//Time and space of O(N*M)
    public int snakesAndLadders(int[][] board) {
        //Edge 
        if(board == null || board.length == 0) return 0;
        
        //create Moves Array
        int n = board.length * board[0].length ;
        int[] moves = new int[n];
        
        int r = board.length;
        int c = board[0].length ;
        
        int row = r-1;
        int col = 0;
        int even = 0;
        int counter = 0;
        
        while(row>= 0 && col>= 0){
            if(board[row][col]!=-1){
                moves[counter] = board[row][col] -1;
            }else{
                moves[counter] = -1;
            }
            counter++;
            
            //switch row and column direction
            if(even%2 == 0){
                col+=1;
            }else{
                col-=1;
            }
            if(col>= c){
                row--;
                even +=1;
                col-=1;
            }else if(col<0){
                row--;
                even+=1;
                col+=1;
            }
        }
        // for(int i= 0 ; i< moves.length ; i++){
        //     System.out.print(moves[i]+ " ");
        // }
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        
        moves[0] = -2;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int k = 0 ; k<size ; k++){
                int curr = queue.poll();
                if(curr == n-1){
                    return result;
                }
                for(int num = 1 ; num< 7 ; num++){
                    int child = curr + num;
                    if(child<n && moves[child] != -2){
                        // -1 or its not
                        if(moves[child]>-1){
                            queue.add(moves[child]);
                        }else{
                            queue.add(child);
                        }
                        moves[child] = -2;
                    }
                    
                }
            }
           result++;
        }
         return -1;
    }
}