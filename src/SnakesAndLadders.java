// Time Complexity:  O(n*n)
// Space Cmoplexity: O(n*n)


class Solution {
    
    public int snakesAndLadders(int[][] board) {
        
        int n = board.length;
        int[] arr = new int[n*n];
        boolean right = true;
        int r = n-1;
        int c = 0;
        int count = 0;

        for(int ind=0; ind<n*n; ind++) {                                     // Converting from 2-D to 1-D array
            arr[ind] = board[r][c]>=1 ? board[r][c]-1 : board[r][c];         // taking one less next index for sake of 0-indexed array 
            if(right) {
                c++;
                if(c==n) {
                    r--;
                    c--;
                    right = false;
                }
            }
            else {
                c--;
                if(c==-1) {
                    r--;
                    c++;
                    right = true;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);                                                         // adding first index to queue
        arr[0] = -2;                                                          // visiting first index

        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) {                                               // level order
                int curr = queue.poll();                                      // taking indexes
                for(int j=1; j<7; j++) {                                      // adding dice values (1-6)
                    int child = curr+j;
                    if(child < n*n && arr[child] != -2) {                     // checking bound and visited
                        if(arr[child] > -1) {                                 // if ladder
                            if(arr[child] == n*n-1) return count+1;           // ladder leads to end, return
                            queue.add(arr[child]);                            // else add ladder destination in queue
                        }
                        else {                                                // if visited or normal case
                            if(child == n*n-1) return count+1;                // we reach end, return
                            queue.add(child);                                 // else add it to queue
                        }
                        arr[child] = -2;                                      // make it visited
                    }
                }
            }
            count++;                                                          // increment count each level
        }
        
        return -1;                                                            // invalid case

    }
}
