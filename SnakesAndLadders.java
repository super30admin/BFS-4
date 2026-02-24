// Time Complexity : O(n*n)
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// convert the zig zag board to 1 indexed 1D array
// use BFS to process the board after each roll of dice (+1-+6)
// add the location of the next move to the queue if it is a snake or ladder 
// add index if its a normal move
// BFS returns the number of minimum moves required to reach the end at level

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = convert1D(board);

        Queue<Integer> q = new LinkedList();
        HashSet<Integer> set = new HashSet();
        q.add(1);
        set.add(1);
        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int curr = q.poll();
                if (curr == n * n)
                    return moves;
                for (int i = 1; i <= 6; i++) {
                    int nextMove = curr + i;
                    if (nextMove > n * n)
                        continue;
                    if (arr[nextMove] != -1)
                        nextMove = arr[nextMove];
                    if (!set.contains(nextMove)) {
                        q.add(nextMove);
                        set.add(nextMove);
                    }
                }
            }
            moves++;
        }

        return -1;
    }

    private int[] convert1D(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n + 1];
        int idx = 1;
        int r = n - 1;
        int c = 0;
        boolean right = true;

        while (idx <= n * n) {
            arr[idx] = board[r][c];
            if (right) {
                c++;
                if (c == n) {
                    r--;
                    c = n - 1;
                    right = false;
                }
            } else {
                c--;
                if (c == -1) {
                    c = 0;
                    r--;
                    right = true;
                }
            }
            idx++;
        }

        return arr;
    }
}