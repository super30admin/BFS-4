// Time Complexity: O(V) = O(N*N)
// Space Complexity: O(V) = O(N*N)
class Solution {
    public int snakesAndLadders(int[][] board) {

        if (board == null || board.length == 0) return 0;

        int n = board.length;
        int nums[] = new int[n * n];
        int r = n-1, c= 0, index = 0;
        int even = 0, lvl =0;
        Queue<Integer> q = new LinkedList<>();

        // Flatten Array into a 1 D array
        while(r>=0 && c >=0) {
            if(board[r][c] == -1) {
                nums[index] = -1;
            } else {
                nums[index] = board[r][c] - 1;
            }

            index++;

            if (even % 2 == 0) {
                c++;
                if (c == n) {
                    c--;
                    r--;
                    even++;
                }
            } else {
                c--;
                if (c == -1) {
                    c++;
                    r--;
                    even++;
                }
            }
        }

        // Add first index to queue and mark it as visited
        q.add(0);
        nums[0] = -2;

        // Iterate over queue to do BFS
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i=0; i <size; i++) {
                int curr = q.poll();
                if (curr == n*n -1) {
                    return lvl;
                }
                // iterate over all six possiblities
                for (int j=1; j <=6; j++) {
                    int child = curr + j;
                    if (child >= n*n ) {
                        continue;
                    }
                    if (nums[child] == -1) {
                        nums[child] = -2;
                        q.add(child);
                    } else {
                        if(nums[child] != -2) {
                            q.add(nums[child]);
                            nums[child] = -2;
                        }
                    }
                }
            }
            lvl++;
        }

        return -1;
    }
}