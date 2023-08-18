// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes


class Solution {
    public int snakesAndLadders(int[][] board) {
        int boardLength = board.length;
        int boardArea = boardLength * boardLength;
        int[] flatten = new int[boardArea];
        boolean[] visisted = new boolean[boardArea];

        boolean right = true;
        int counter = 0;
        // flatten the board into a single dimension
        for (int y = boardLength - 1; y >= 0; y--) {
            for (int x = 0; x < boardLength; x++) {
                int actualX = right ? x : boardLength - 1 - x;

                flatten[counter] = board[y][actualX];
                counter++;
            }
            right = !right;
        }

        int moves = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        q.add(null);

        while (!q.isEmpty()) {
            Integer current = q.poll();

            // if null and there are more elements increment and add another null
            if (current == null) {
                if (!q.isEmpty()) {
                    q.add(null);
                    moves++;
                }
            } else {
                // we made it to the end, because this is BFS we can assume this is the best case since we take the values layer by layer
                if (current == boardArea - 1) {
                    return moves;
                }

                // loop through current + 1 to current + 6 or the end of the board
                for (int i = current + 1; i <= Math.min(current + 6, boardArea - 1); i++) {
                    // if the current space has something other than -1 replace with the actual move
                    int actualMove = flatten[i] == -1 ? i : flatten[i] - 1;

                    if (visisted[actualMove]) continue;
                    visisted[i] = true;
                    visisted[actualMove] = true;
                    q.add(actualMove);
                    
                }
            }
        }


        return -1;
    }
}