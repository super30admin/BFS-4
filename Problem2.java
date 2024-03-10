class Solution {
    //TC: O(n^2)
    //SC : O(n^2)
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int [] arr = new int[n*n];
        int idx = 0;
        int r = n-1;
        int c = 0;
        boolean dir = true;
        while(idx < n*n){
            if(board[r][c] == -1){
                arr[idx] = -1;
            }else{
                arr[idx] = board[r][c] - 1;
            }
            idx++;
            if(dir){
                c++;
                if(c == n){
                    c--; r--;
                    dir = false;
                }
            }else{
                c--;
                if(c < 0){
                    c++; r--;
                    dir = true;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0); arr[0] = -2; //represemts visited
        int moves = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curr = q.poll();
                if(curr == n*n - 1) return moves;
                for(int j =1; j <=6; j++){
                    int baby = curr + j;             
                    if(baby >= n*n) break;
                    if(arr[baby] != -2){
                        if(arr[baby] == -1){
                                q.add(baby);
                                //
                        }else{
                            q.add(arr[baby]);
                        }
                        arr[baby] = -2;                   
                    }
                }
                
            }
            moves++;
        }
        return -1;
    }
}
