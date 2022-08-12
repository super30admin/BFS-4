// Time Complexity : O(n^2*6)
// Space Complexity : O(n^2)

class Solution {
    public int snakesAndLadders(int[][] board) {

        int n = board.length;
        if (n == 0)
            return 0;
        int even = 0; 
        int r = board.length - 1;
        int c = 0;
        int[] arr = new int[n * n];
        for (int i = 0; i < n * n; i++) {

            if (board[r][c] != -1) {
                arr[i] = board[r][c] - 1; 
                                         
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

        
        for (int i = 0; i < n * n; i++) {
            System.out.print(arr[i] + "  ");
        }

        Queue<Integer> q = new LinkedList<>();
        int counter = 0;

        q.add(0);
        arr[0] = -2; 

        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                int ch = q.poll();

                for (int j = 1; j <= 6; j++) {

                    if (arr[ch + j] == -2)
                        continue;

                    if (ch + j == n * n - 1 || arr[ch + j] == n * n - 1) {
                
                        return counter + 1;
                    }

                    if (arr[ch + j] != -1) { 
                        q.add(arr[ch + j]);
                    } else {
                        q.add(ch + j); 
                    }
                    arr[ch + j] = -2;

                } 
            }
            counter++;

        }

        return -1;
    }
}