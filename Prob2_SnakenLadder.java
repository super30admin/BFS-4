//TC : O(n*n) // Iterating on board - each cell (Upto 6 possibilities to traverse)
//SC : O(n) // Using moves 1D array

class Solution {
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0)
            return -1;

        int n = board.length;
        int[] moves = new int[n * n];

        int index = 0;

        int i = n - 1, j = 0;
        int dir = 0;

        while (index < n * n) { // Inserting board values in 1D moves array
            if (board[i][j] == -1) {
                moves[index] = board[i][j];
            } else {
                moves[index] = board[i][j] - 1;
            }

            index++;

            if (dir % 2 == 0) { // Left to Right
                j++;
                if (j == n) {
                    // jump to previous row and also reduce column by 1
                    i--;
                    j--;
                    dir++;
                }
            } else { // Right to Left
                j--;
                if (j < 0) {
                    // jump to previous row and increse column by 1
                    i--;
                    j++;
                    dir++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        moves[0] = -2;

        int minRounds = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();

            while (len-- > 0) {
                int curr = queue.poll();
                if (curr == n * n - 1)
                    return minRounds;
                for (int k = 1; k <= 6; k++) {
                    int temp = curr + k;

                    if (temp < n * n) {
                        if (moves[temp] != -2) {
                            if (moves[temp] == -1) {
                                if (temp == n * n - 1)
                                    return minRounds + 1;

                                queue.add(temp);
                            } else {
                                if (moves[temp] == n * n - 1)
                                    return minRounds + 1;

                                queue.add(moves[temp]);
                            }

                            moves[temp] = -2;
                        }
                    }
                }
            }
            minRounds++;

        }

        return -1;

    }
}