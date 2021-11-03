// Time Complexity: O(m*n)
// Space Complexity: O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int n = board.length;
        int[] moves = new int[n * n];
        int r = n - 1;
        int c = 0;
        // flatten the matrix
        int even = 0, idx = 0; // idx on moves
        while(r >= 0 && c >= 0) {
            if(board[r][c] == -1) {
                moves[idx] = -1;
            } else {
                moves[idx] = board[r][c] - 1; 
            }
            idx++;
            if(even % 2 == 0) {
                c++;
                if(c == n) {
                    r--;
                    c--;
                    even++;
                }
            } else {
                c--;
                if(c == -1) {
                    r--;
                    c++;
                    even++;
                }
            }
        }
        
        // traverse and add it to queue
        Queue<Integer> q = new LinkedList<>();
        q.add(0); 
        int count = 0;
        moves[0] = -2;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int curr = q.poll();
                if(curr == n*n - 1) return count;
                for(int j = 1; j < 7; j++) {
                    int child = curr + j; // baby idx
                    if(child >= n*n) continue;
                    if(moves[child] != -2) {
                        if(moves[child] == -1) {
                            q.add(child);
                        } else {
                            q.add(moves[child]);
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