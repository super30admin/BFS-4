class Solution {
    public int snakesAndLadders(int[][] board) {
        // base case
        if (board == null)
            return -1;

        int n = board.length; // we are given a square matrix.
        int[] arr = new int[n * n + 1]; // because array is 0-indexed, and we'll start from 1.
        boolean flag = true; // go from left to right, false = right to left.
        int i = 1, r = n - 1, c = 0;

        // ***** flatten array logic *****
        while (i < n * n + 1) {
            arr[i++] = board[r][c];
            if (flag) {
                c++;
                if (c == n) {
                    r--;
                    c = n - 1;
                    flag = false; // move Right to Left
                }
            } else {
                c--;
                if (c == -1) {
                    r--;
                    c = 0;
                    flag = true; // Move Left to Right
                }
            }
        }
        // ***** Flatten logic ends*****

        // ***** BFS approach Intial condition *****
        Queue<Integer> q = new LinkedList<>();

        q.add(1); // Intial element added
        arr[1] = -2; // mark visited

        int moves = 0;

        // ***** BFS approach *****
        while (!q.isEmpty()) {
            // we'll need a move variable as we have to return the minimum number of moves.
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int curridx = q.poll();

                // ** explore 6 babies **
                for (int d = 1; d <= 6; d++) {
                    int newidx = curridx + d;
                    if ((newidx == arr.length - 1) || arr[newidx] == arr.length - 1) {
                        return moves + 1; // if reached the end index return
                    }
                    if (arr[newidx] != -2) { // not visited
                        if (arr[newidx] == -1)
                            q.add(newidx); // if we just find the -1; we go to that index position
                        else {
                            if (arr[newidx] == n * n + 1)
                                return moves + 1;
                            q.add(arr[newidx]); // adding position
                        }
                        arr[newidx] = -2; // mark as visited, the curr index, not the snake/ladder one
                    }
                }
            }
            moves++;
        }
        // ***** BFS approach ends*****
        return -1;
    }
}