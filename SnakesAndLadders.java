class Solution {
  
    /*
        Time : O( N * N )   | copy board into moves - N*N operation and going over all the moves.
        Space : O(N * N)    | Queue + moves array
        Leetcode : YES
    */
  
    /*
      Approach :
      1. To make iteration over board easier (it's zikzak) copy entire board into new moves 1D array. 
        ( While copying keep in mind we are starting with 0 based index and original array has 1 based index.
      2. We can go over board in DFS BFS any manner, but our target is not to reach the destination but to count
          minimun steps required so BFS will give us ability to to finish currnet level and go to the next. 
          Automatically  each level will be a step taken.
      3. With BFS, we can make possibly 6 moves at each index. so calculate next move,
          - watch for edge cases like :
            1. index >= last element in the moves, 
            2. if index is not already visited 
            3. if currently calculated index is taking us to any other index via (snake Head or ladder base) 
            4. add to the queue only if it is equal to -1 or else take to the specific index using q.add(moves[i]);
    */
    public int snakesAndLadders(int[][] board) {
        if(board == null) return 0;
        int r = board.length;
        int[] moves = new int[r*r];
        int i = r - 1; int j = 0; int even = 0;
        int index = 0;
        
        while(index < moves.length){
            
            if(even % 2 == 0 && j < r){
                if(board[i][j] == -1){
                    moves[index] = board[i][j];
                }else{
                    moves[index] = board[i][j] - 1;
                }
                
                                
                j++;
                
            }else{
                if(board[i][j] == -1){
                    moves[index] = board[i][j];
                }else{
                    moves[index] = board[i][j] - 1;
                }
                j--;
            }
            
            if( j >= r){
                i--;
                j--;
                even++;
            }
            
            if( j < 0){
                i--;
                j++;
                even++;
            }
            index++;
        }
        i = 0;
        int steps = 0;
        Queue<Integer> que = new LinkedList<>();
        que.add(0);
        moves[0] = -2;
        
        while(!que.isEmpty()){
            
            int size = que.size();
            for(int k = 0; k < size; k++){
                int curr = que.poll();                
                
                if(curr == moves.length -1) return steps;
                
                for(int l = 1; l <=6; l++){
                    i = curr + l;
                
                    if(i >= moves.length) continue;

                    if(moves[i] == -1){
                        que.add(i);
                    }else if(moves[i] != -2){
                        que.add(moves[i]);
                    }

                    moves[i] = -2;
                }
            }
            
            steps++;
            
            
        }
        
        return -1;
        
    }
}
