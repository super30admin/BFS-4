// Time Complexity : O(m*n) where m and n are the dimensions of the given board
// Space Complexity : O(m*n) where m and n are the dimensions of the given board
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class snakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0) return 0;  
        int r = board.length;
        int c = board[0].length;
        int n = r * c;
        int[] flatBoard = new int[n];
        int i = r - 1, j = 0, idx = 0, even = 0;
        while (idx < n) {
            if (board[i][j] == -1) flatBoard[idx] = -1;
            else flatBoard[idx] = board[i][j] - 1;
            idx++;
            if (even % 2 == 0) j++;
            else j--;
            if (j >= c) {
                i--;
                j--;
                even++;
            } else if (j < 0) {
                i--;
                j++;
                even++;
            } 
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        flatBoard[0] = -2; // don't forget to mark the starting index as visited
        int minSteps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int curr = q.poll();
                if (curr == n - 1) return minSteps;
                for (int l = 1; l <= 6; l++) {
                    int temp = curr + l;
                    if (temp < n && flatBoard[temp] != -2) {
                        if (flatBoard[temp] > -1) { // if a ladder, jump to that index
                            q.add(flatBoard[temp]);
                        } else {
                            q.add(temp); // if not stay there
                        }
                        flatBoard[temp] = -2; // mark as visited
                    }
                }   
            }
            minSteps++;
        }
        return -1;
    }
}