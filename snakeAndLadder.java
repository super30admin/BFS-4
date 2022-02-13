// Time Complexity = O(N*N)
// Space Complexity = O(N*N)
// Did this code successfully run on Leetcode :Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
// We use a BFS here since we want to find the answer in the min no. of moves
// We also maintain visited nodes, since we don't want to recompute the values
// We will flatten the array to a 1D array to make it easy for us to evaluate
// In order to change the direction of traversal, we maintain a variable (even) which will increase when we change the level, in even levels we traverse from left to right, and in odd the opposite direction
class Solution {
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0) return -1;
        int n = board.length;

        // start traversal from last row and 0th col
        int r=n-1, c=0;

        // idx=start index of flattened array. even=to track the direction of array traversal
        int idx=0, even=0;

        // Step-1: flatten the matrix
        int[] moves = new int[n*n];
        while (r>=0 && c<=n-1) {
            // update the values
            if(board[r][c] == -1) {
                moves[idx] = -1;
            }
            else {
                moves[idx] = board[r][c]-1;
            }

            // manipulate the directions of traversal
            if (even%2 == 0) {
                c++;
                if (c == n) {
                    c=n-1;
                    r--;
                    even++;
                }
            }
            else {
                c--;
                if (c < 0) {
                    c=0;
                    r--;
                    even++;
                }
            }

            idx++;
        }

        // Step-2: traverse the 1D matrix to see if we can reach the last spot with minimum jumps
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2;
        int count=0;

        while(!q.isEmpty()) {
            int size = q.size();

            for (int i=0; i<size; i++) {
                int curr = q.poll();
                if(curr == n*n-1) return count;

                for (int j=1; j<=6; j++) {
                    int child = curr+j;
                    if (child > n*n-1) continue;

                    if (moves[child] != -2) {
                        if(moves[child] == -1) {
                            q.add(child);   // add the index
                        }
                        else {
                            q.add(moves[child]);    //add the index of the snake/ladder destination
                        }
                        moves[child] = -2;
                    }
                }
            }
            count++;
        }

        return -1;
    }
}