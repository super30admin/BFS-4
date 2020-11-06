class Solution {
    
    // Approach1: Forming a new array move[], and evaluating as per the input
    // TC:  O(V+E) or O(N^2)
    // SC:  O(N^2), where N is length of board
    
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0 )
            return 0;
        
        int r = board.length, c = board[0].length;
        int dest = r*c;
        int[] move = new int[dest];
        
        int i = r - 1, j = 0, even = 0, index = 0;
        
        //Evaluating the move[] as per the input
        while(i >= 0 && j >= 0){
            
            if(board[i][j] != -1){      // If not regular square => ladder or snake
                move[index] = board[i][j] - 1;
            } else 
                move[index] = -1;
            index++;
            
            if(even % 2 == 0){          // even row => forward
                j += 1;
            } else                      // odd row => backward
                j -= 1;
            
            if( j >= c){                // exceeds the rightmost column
                i -= 1;
                even += 1;
                j -= 1;
            }else if( j < 0){          // exceeds the leftmost column
                i -= 1;
                even += 1;
                j += 1;
            }
        }
        
        // Evaluating the minimum moves required to reach N*N square
        int min = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);           //start position --> 0
        move[0] = -2;       // Marking visited
        
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int x = 0; x < size; x++){              // BFS
                int curr = q.poll();
                
                if(curr == dest - 1)
                    return min;
                
                for(int dice = 1; dice <= 6; dice++){   // All next 6 directions ==> dice
                    int child = curr + dice;
                    System.out.println("child: "+child);
                    
                    if(child < dest && move[child] != -2){
                        if(move[child] > -1) 
                            q.add(move[child]);    // If not regular square 2-->14
                        else
                            q.add(child);           // If regualr square with -1   
                        move[child] = -2;               // visited
                    }
                }
            }
            min++;
        }
        return -1;
    }
}