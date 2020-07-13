// Time Complexity : O(mn) where m is hte number of rows and n is the number of columns
// Space Complexity : O(mn) where m is hte number of rows and n is the number of columns
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Hard question to code and understand 
/* Your code here along with comments explaining your approach: Go over the squares beginnning from the start cell. If you encounter a -1, implies
there is no ladder or snake, take it in the queue. We would flatten the board into a single array for better traversal. We are using j pointer to
move columnwise and i pointer to move rowwise, if j gets out of the bounds, take it in and depending on the value of even variable that indicates
the board row number that will decide whether j will go forward or backward (because numbers in the board are also written in reversed form). Process
the cells in the queue level wise, if the cell if not -1, implies its a number that the ladder takes you to, go to that index and mark that as visited.
Find all possible moves (6) from each of the sqaure regardless of the number it carries. Always select the fathest move (min steps we need). If the
square reaches N-1 implies we have reached our destination.
*/
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int N = board.length * board.length;
        int[] moves = new int[N];
        int i = board.length-1, j = 0, k= 0;
        int even = 0;
        while(i >= 0 && j >= 0){
            if(board[i][j] == -1){
                moves[k] = board[i][j];                                                             // If the square is -1
            } else {
                moves[k] = board[i][j] - 1;                                                             // If the square is a positive number
            }
            k++;
            if(even % 2 == 0){                                                                  // Even tracks on whether numbers are moving in forward or backward direction
                j++;                                                                            // Numbers are in increasing sequence
            } else {    
                j--;                                                                                // Numbers are in decreasing sequence
            }
            if(j >= board[0].length){
                j--; even--;i--;                                                                // Out of the bounds, bring them back to the valid bounds
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
            for(int m = 0; m <  size; m++){                                                                             // Process level by level
                int move = q.poll();
                if(move == N-1){return steps;}                                                                      // Reached the destination
                for(int n = 1; n <=6 ; n++){
                  int index = move + n;                                                                         // Find all possible moves
                  if(index < N && moves[index] != -2){                                                                          // Avoid visited cells
                  if(moves[index] > 0){                                                                                 // You got a ladder or snake
                      q.add(moves[index]);
                  }
                 else{if(moves[index] == -1)                                                                            // Nothing is there
                      q.add(index);
                  }
                  moves[index] = -2;                                                                    // Mark the cell as visited
                }
                }
            }
            steps++;                                                                                        // Min number of steps
        }
        return -1;
    }
}