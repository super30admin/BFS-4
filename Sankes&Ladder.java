class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        int r = n - 1;
        int c = 0;
        int ptr = 0;
        boolean flag = true;
        while(ptr < n*n){
            if(board[r][c] == -1) arr[ptr++] = board[r][c];
            else arr[ptr++] = board[r][c] - 1;
            
            if(flag){
                c++;
                if(c == n){
                    flag = false;
                    c--;
                    r--;
                }
            }else{
                c--;
                if(c < 0){
                    flag = true;
                    c++;
                    r--;
                }
            }
        }
        int moves = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int currIdx = q.poll();
                if(currIdx == n*n-1) return moves;
                for(int j = 1; j < 7; j++){
                    int newIdx = currIdx + j;
                    if(newIdx >= n*n) break;
                    if(newIdx < n*n){
                        if(arr[newIdx] != -2){
                            if(arr[newIdx] == -1){
                                q.add(newIdx);
                            }else {
                                q.add(arr[newIdx]);
                            }
                            arr[newIdx] = -2;
                        }
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}
