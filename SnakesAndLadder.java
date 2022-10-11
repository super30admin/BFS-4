// Time Complexity : O(n*n)
// Space Complexity : O(n*n)

class SnakesAndLadder {
    public int snakesAndLadders(int[][] board) {
        if(board==null || board.length==0) return 0;
        int n = board.length;
        boolean flag = false;

        //flattening of board in to new array.
        int[] arr = new int[n*n];
        int idx = 0;
        int i=n-1;
        int j=0;
        while(!(idx == n*n)){
            if(board[i][j]==-1){
                arr[idx] = -1;
            } else {
                arr[idx] = board[i][j]-1;
            }
            idx++;
            if(flag){
                j--;
                if(j==-1){
                    flag = false;
                    i--; j++;
                }
            } else {
                j++;
                if(j==n){
                    flag = true;
                    j--; i--;
                }
            }
        }

        //applied BFS on flatten array:
        Queue<Integer> q = new LinkedList<>();
        q.add(0); arr[0] = -2;
        int moves =0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k=0; k<size; k++){
                int curr = q.poll();
                for(int d=1; d<7; d++){
                    int child = curr + d;

                    if(arr[child] != -2){
                        if(arr[child]>=0){
                            if(arr[child] == n*n - 1) return moves+1;
                            q.add(arr[child]);
                        } else {
                            if(child == n*n - 1) return moves+1;
                            q.add(child);
                        }
                        arr[child] = -2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}