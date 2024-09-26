/*
 * Time complexity : O(n) --> total elements of board
 * Space Complexity : O(n)
 */
class Solution {
    public int snakesAndLadders(int[][] board) {
        
        //Edge condition
        if(board == null || board.length == 0){
            return 0;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        int index = 0;
        int i = board.length - 1;
        int j = 0;
        int even = 0;
        
        int[] moves = new int[m*n];
        while(i >= 0 && j >= 0){
            
            //System.out.println(i+" "+j);
            if(board[i][j] != -1){
                moves[index] = board[i][j] - 1;
            }else{
                moves[index] = -1;
            }
            
            index++;
            
            if(even % 2 == 0){
                j = j + 1;
            }else{
                j = j - 1;
            }
            
            if(j >= n){
                i--;
                j--;
                even++;
            }else if(j < 0){
                j++;
                i--;
                even++;
            }
            
           
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(0);
        moves[0] = -2;
        
        int totalMoves = m * n;
        int minMoves = 0;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int q = 0; q < size; q++){
                int curr = queue.poll();
                
                if(curr == totalMoves - 1){
                    return minMoves;
                }
                
                
                for(int k = 1; k<7; k++){
                    int child = k + curr;
                    
                    if(child < totalMoves && moves[child] != -2){
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
}