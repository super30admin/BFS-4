// Time Complexity : O(n^2*6)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

//909. Snakes and Ladders
//https://leetcode.com/problems/snakes-and-ladders/

class Solution {
    // BFS
    // time: O(n^2*6)
    // space: O(n^2)
    public int snakesAndLadders(int[][] board) {

        int n = board.length;
        if (n == 0)
            return 0;
        int even = 0; // maintains if it's a even row or odd
        int r = board.length - 1;
        int c = 0;
        // make the 1D board first
        int[] arr = new int[n * n];
        for (int i = 0; i < n * n; i++) {

            if (board[r][c] != -1) {
                arr[i] = board[r][c] - 1; // for instance at index 1, you go to 14, and not 15 as in the board matrix
                                          // cause this array is a zero indexed array
            } else {
                arr[i] = board[r][c];
            }

            if (even % 2 == 0) {

                c++;
                if (c == n) {
                    c--;
                    r--;
                    even += 1;
                }

            } else {

                c--;
                if (c == -1) {
                    c++;
                    r--;
                    even += 1;
                }

            }

        }

        // print the 1D array to check if it has been populated correctly
        for (int i = 0; i < n * n; i++) {
            System.out.print(arr[i] + "  ");
        }

        // do BFS over the 1D array
        Queue<Integer> q = new LinkedList<>();
        int counter = 0;

        q.add(0);
        arr[0] = -2; // marking it as visited

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int ch = q.poll();

                for (int j = 1; j <= 6; j++) {

                    if (arr[ch + j] == -2)
                        continue;

                    if (ch + j == n * n - 1 || arr[ch + j] == n * n - 1) {
                        // flag = true;
                        return counter + 1;
                    }

                    if (arr[ch + j] != -1) { // System.out.print(" " + (ch+j) + " " + arr[ch+j] + " *** ");
                        q.add(arr[ch + j]);
                    } else {
                        q.add(ch + j); // System.out.print(" " + (ch+j) + " " + arr[ch+j] + " &&& ");
                    }
                    arr[ch + j] = -2;

                } // System.out.println("-----------------------");
            }
            counter++;

        }

        // return the number of levels it took to get to the destination
        return -1;
    }
}
