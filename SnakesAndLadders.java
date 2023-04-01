public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        // null case
        if(board==null || n<0) return 0;
        int[] moves = new int[n*n];
        int idx = 0;
        int even = 0; // direction variable
        int i = n-1; // i - row
        int j = 0; // j - column

        // making 1-D array - moves
        while(idx < n*n){
            if(board[i][j] == -1){
                moves[idx] = board[i][j];
            }else{
                moves[idx] = board[i][j]-1;
            }
            idx++;
            if(even%2==0){ // traversing from left to right
                j++;
                if(j==n){ // reached out of bounds
                    i--;
                    j--;
                    even++;
                }
            }else{ // traversing from right to left
                j--;
                if(j==-1){
                    i--;
                    j++;
                    even++;
                }
            }
        }

        // starting bfs
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2; // marking visited as -2
        int result = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int l=0;l<size;l++){
                int curr = q.poll();
                if(curr == n*n-1) return result; // reached end of board?
                // 6 options of dice
                for(int k=1;k<=6;k++){
                    int child = curr+k;
                    if(child < n*n){
                        if(moves[child] != -2){ // if its not visited
                            if(moves[child] == -1){
                                q.add(child);
                            }else{
                                q.add(moves[child]);
                            }
                            moves[child] = -2;
                        }
                    }
                }
            }
            result++;
        }
        return -1;
    }
}

// TC - 
// SC - 