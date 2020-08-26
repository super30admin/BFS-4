// Time Complexity : O(mn) m = no of rows, n = no of cols
// Space Complexity : O(mn) m = no of rows, n = no of cols
// Approach
// Go over the squares beginnning from the start cell. If you encounter a -1, implies
// there is no ladder or snake, take it in the queue. We would flatten the board into a single array for better traversal. We are using j pointer to
// move columnwise and i pointer to move rowwise, if j gets out of the bounds, take it in and depending on the value of even variable that indicates
// the board row number that will decide whether j will go forward or backward (because numbers in the board are also written in reversed form). Process
// the cells in the queue level wise, if the cell if not -1, implies its a number that the ladder takes you to, go to that index and mark that as visited.
// Find all possible moves (6) from each of the sqaure regardless of the number it carries. Always select the fathest move (min steps we need). If the
// square reaches N-1 implies we have reached our destination.
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int N = board.length * board.length;
        int[] moves = new int[N];
        int i = board.length-1, j = 0, k= 0;
        int even = 0;
        while(i >= 0 && j >= 0){
            if(board[i][j] == -1){
                moves[k] = board[i][j];  
            } else {
                moves[k] = board[i][j] - 1;    
            }
            k++;
            if(even % 2 == 0){                               
                j++;                                                     
            } else {    
                j--;                                                                              
            }
            if(j >= board[0].length){
                j--; even--;i--;                     
            }
            if(j < 0){
                j++; even++;i--;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2;
        int steps = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int m = 0; m <  size; m++){                                                                          
                int move = q.poll();
                if(move == N-1){return steps;}                                                                   
                for(int n = 1; n <=6 ; n++){
                  int index = move + n;                                                                         
                  if(index < N && moves[index] != -2){                                                                         
                  if(moves[index] > 0){                                                                                 
                      q.add(moves[index]);
                  }
                 else{if(moves[index] == -1)                                                                         
                      q.add(index);
                  }
                  moves[index] = -2;        
                }
                }
            }
            steps++;                                                                                   
        }
        return -1;
    }
}