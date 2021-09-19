//Time: O(MN)
//Space: O(MN)

import java.util.*;
class snakes {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0){
            return 0;
        }
        
        int[] moves = flatten(board);
        
        Queue<Integer> queue = new LinkedList<>();
        //add the very first index
        queue.add(1);
        moves[1] = -2; //mark visited
        int n = board.length;
        
        int min = 0;
        
        while(!queue.isEmpty()){
            //keep track of size to process all of the added children
            int sz = queue.size();
            
            for(int l = 0; l < sz; l++){
                int curr = queue.poll();
                
                //if polled value is the end of the board
                if(curr == n * n)
                    return min;
                
                //6 possibilities of the rolled dice
                for(int k = 1; k < 7; k++){
                    int child = curr + k;
                    
                    //child is beyond the board
                    if(child > n*n) 
                        break;
                    
                    //if the child is not visited yet
                    if(moves[child] != -2){
                        //if it is a regular tile, we just add the index of child
                        if(moves[child] == -1)
                            queue.add(child);
                        //if it is snake or ladder, we add the value of the tile as the index
                        else
                            queue.add(moves[child]);
                        moves[child] = -2;
                    }
                }
            }
            min++;
        }
        return -1;
    }
    
    private int[] flatten(int[][] board){
        int n = board.length;
        
        //the array that will store the board
        int[] moves = new int[n*n + 1];
        //we start from bottom left corner
        int i = n - 1, j = 0;
        //board starts from value 1 so we start from that idx for filling array
        int idx = 1;
        int row = 0;
        
        while(i >= 0 && j < n){
            moves[idx] = board[i][j];
            idx++;
            //if we are in even row, we move right
            if(row % 2 == 0){
                j++;
                //reached right end, move up one row, move left once
                if(j == n){
                    i--;
                    row++;
                    j--;
                }
            }
            else{
                j--;
                //reached left end, move up one row, move right once
                if(j == -1){
                    i--;
                    row++;
                    j++;
                }
            }
        }
        
        return moves;
    }
}