//Time: O(MN) | Space: O(MN)

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        // converting matrix to 1 D array
        int[] arr = new int[n*n];
        // to check if the row is odd or even, upon which we will decide which direction we need to go from the bottom of the matrix
        int even = 0;
        int i=n-1;
        int j=0;
        int idx = 0;
        while(idx< arr.length) {
            if(board[i][j] > 0)
                arr[idx] = board[i][j]-1;
            else arr[idx] = board[i][j];
            idx++;
            if(even%2 == 0){
                j++;
                if(j == n) {
                    i--;
                    j--;
                    even++;
                }
            } else {
                j--;
                if(j<0) {
                    i--;
                    j++;
                    even++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        if(n > 0) {
            // processing from 0
            q.add(0);
            arr[0] = -2;
        }
        int min = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int k = 0;k<size;k++) {
                int curr = q.poll();
                if(curr == n*n-1) return min;
                // we have six possibilites from curr
                for(int m = 1;m<=6;m++) {
                    int newIdx = curr+m;
                    if(newIdx < n*n && arr[newIdx] != -2) {
                        // if there's a ladder we should pick that and move ahead
                        if(arr[newIdx] >= 0) q.add(arr[newIdx]);
                        else
                            // else just go with the index
                            q.add(newIdx);
                        // as sson as we process a value we mark it -2 so that it doesn't get visited again
                        arr[newIdx] = -2;
                    }
                }
            }
            min++;
        }
        return -1;

    }
}