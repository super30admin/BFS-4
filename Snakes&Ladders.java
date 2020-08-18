// Time Complexity : O(m * n) --> where m & n are the lengths of input 2D-matrix
// Space Complexity : O(m * n)
// Did this code successfully run on Leetcode (909): Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public int snakesAndLadders(int[][] board) {
        // edge case
        if (board == null || board.length == 0) return 0;
        
        int r = board.length; int n = r * r;
        int idx = 0; int even = 0;
        int i = r - 1; int j = 0;
        int moves[] = new int[n];
        
        // flattened the board 2D-array in 1-D array
        while (i >= 0 && j >= 0) {
            if (board[i][j] == -1) moves[idx] = -1;
            else moves[idx] = board[i][j] - 1;
            idx++;
            
            if (even % 2 == 0) j++;
            else j--;
            
            if (j == r) {
                i--; j--; even++;
            }
            if (j < 0) {
                i--; j++; even++;
            }
        }
        
        // BFS traversal
        int minMoves = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2; // marking as visited
        
        while (!q.isEmpty()) {
            int size = q.size();
            for (int l = 0; l < size; l++) {
                int curr = q.poll();
                if (curr == n - 1) return minMoves; // if reached at the end of board
                for (int k = 1; k < 7; k++) { // possible 6 moves as contains in dice
                    int child = curr + k;
                    if (child < n && moves[child] != -2) { // not visited
                        if (moves[child] == -1) q.add(child);
                        else q.add(moves[child]);
                        moves[child] = -2; // marking as visited
                    }
                }
            }
            minMoves++;
        }
        return -1;
    }
}