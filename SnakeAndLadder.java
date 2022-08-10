class SnaleAndLadder {
    //TC is O(n*n)
    //SC is O(n*n)
    public int snakesAndLadders(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] arr = new int[n*n];
        boolean right = true;
        int idx = 0;
        int r = n-1, c=0;
        while(idx < n*n){
            if(board[r][c] != -1){
                arr[idx++]=board[r][c] - 1;
            }else{
                arr[idx++]=board[r][c];
            }
            
            if(right && c==n-1){
                r--;
                right = false;
            }else if(right && c!=n-1){
                c++;
            }else if(!right && c==0){
                right = true;
                r--;
            }else{
                c--;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        int count = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int curr = q.poll();
                if(curr == n*n-1) return count;
                for(int j=1;j<=6;j++){
                    int child = j+curr;
                    if(child >= n*n) break;
                    if(arr[child] != -2){
                        if(arr[child] == -1)
                            q.add(child);
                        else
                            q.add(arr[child]);
                    }
                    arr[child]=-2;
                }
            }
            count++;
        }
        return -1;
    }
}