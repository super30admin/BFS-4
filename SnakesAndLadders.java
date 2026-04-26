// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
Flatten the 2D array to 1D array in a zigzag order.Next, do BFS traversal from the start position to destination
until 6 steps per turn.Whenever snake or ladder is observed, we jump accordingly to that cell using the 1D
array. Mark the visited cells to ensure not to visit them again.
 */
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n * n];
        int[] arr = new int[n * n];
        boolean dir = true; //left-right

        int r = n - 1, c = 0;

        for(int i = 0 ; i < n * n ; i++) {
            if(board[r][c] == -1) {
                arr[i] = board[r][c];
            }
            else {
                arr[i] = board[r][c] - 1;
            }

            if(dir) {
                c++;
                if(c == n) {
                    dir = false;
                    r--; c--;
                }
            }
            else { //right-left
                c--;
                if(c == -1) {
                    dir = true;
                    r--; c++;
                }
            }
        }

        q.add(0);
        int level = 0;
        visited[0] = true;

        while(!q.isEmpty()) {
            int size = q.size();
            for(int k = 0 ; k < size ; k++) {
                int currIdx = q.poll();
                if(currIdx == n * n - 1)
                    return level;
                for(int d = 1 ; d <= 6 ; d++) {
                    int newIdx = currIdx + d;
                    if(newIdx >= n * n)
                        continue;
                    if(!visited[newIdx]) {
                        if(arr[newIdx] == -1)
                            q.add(newIdx);
                        else
                            q.add(arr[newIdx]);
                        visited[newIdx] = true;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}