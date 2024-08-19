/*
    LC - 909. Snakes and Ladders
    TC - O(n2)
    bcs flatten takes n2
    if there is no snake or ladder then its n2
    SC - O(n2)
*/

class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) {
            return 0;
        }
        Queue<Integer> q = new LinkedList<>();
        int n = board.length;
        int[] nums = new int[n * n];
        int index = 0;
        int row = n - 1;
        int col = 0;
        int even = 0;
        int moves = 0; // to store the levels;
        while(index < n * n) {
            if(board[row][col] != -1) {
                nums[index] = board[row][col] - 1; // bcs array is zero based index.
            } else {
                nums[index] = -1;
            }
            index++;
            if(even % 2 == 0) {
                col++;
                if(col == n) {
                    row--;
                    even++;
                    col = n - 1;
                }
            } else {
                col--;
                if(col == -1) {
                    row--;
                    even++;
                    col = 0;
                }
            }
        }
        q.add(0);
        // start the BFS
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i=0; i < size; i++) {
                int curr = q.poll();
                if(curr == n * n - 1) {
                    return moves;
                }
                for(int j = 1; j<=6; j++) { // roll the dice which is at most 6.
                    int child = curr + j;
                    if(child >= n * n) {
                        continue;
                    }
                    if(nums[child] == -1) {
                        q.add(child);
                        nums[child] = -2;
                    } else {
                        if(nums[child] != -2) {
                            q.add(nums[child]);
                            nums[child] = -2;
                        }
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}