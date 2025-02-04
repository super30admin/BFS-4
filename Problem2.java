class Solution {
    public int snakesAndLadders(int[][] board) {
        //null
        if(board == null || board.length == 0) return -1;
        // flatten the array 
        int n = board.length;
        int i = n -1;
        int j = 0;
        int idx = 0;
        int[] arr = new int[n*n];
        boolean flag = true;
        while(idx < arr.length) {
            if(board[i][j] == -1) {
                arr[idx] = -1; 
            }
            else {
                arr[idx] = board[i][j] - 1;
            }
            idx++;
            if(flag) {
                j--;
                if(j == -1) { 
                    j++;
                    i--;
                    flag = false;
                }
                else {
                    j++;
                    if(j == n) {
                        j--;i--;
                        flag = true;
                    }
                }

            }
            int moves = 0;
            Queue<Integer> q = new LinkedList<>();
            q.add(0); arr[0] = -2;
            while(q.isEmpty()) {
                int size =q.size();
                for(int k =0; k < size; k++) {
                    int curr = q.poll();
                    for(int d = 0; d < 7;d++) {
                        int child = curr + d;
                        if(child == n*n-1) 
                            return moves + 1;

                        if(arr[child] != -2){
                            if(arr[child] >= 0 ) {
                                if(arr[child] == n*n -1) return moves + 1;
                                q.add(arr[child]);
                            }
                            else {
                                if(arr[child] == n*n -1) return moves + 1;
                                q.add(child);
                            }
                            arr[child] = -2;
                        }    
                    }
                    
                }
                moves++;
            }
            return moves;

        }
        return -1;

    }
}