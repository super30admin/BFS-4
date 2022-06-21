class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        
        int n = board.length;
        int[] flat = new int[n * n];
        int r = n - 1, c = 0;
        int idx = 0, even = 0;
        
        while(r >= 0 && c >= 0){
            if(board[r][c] == -1){
                flat[idx] = -1;
            }
            else{
                flat[idx] = board[r][c] - 1;
            }
            idx++;
            if(even % 2 == 0){
                c++;
                if(c == n){
                    even++;
                    c--;
                    r--;
                }
            }
            else{
                c--;
                if(c == -1){
                    even++;
                    c++;
                    r--;
                }
            }
        }
        
        Queue<Integer> q = new LinkedList();
        q.add(0);
        flat[0] = -2;
        int count = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curr = q.poll();
                if(curr == n * n - 1) return count;
                for(int j = 1; j <= 6; j++){
                    int child = curr + j;
                    if(child >= n * n) continue;
                    if(flat[child] != -2){
                        if(flat[child] == -1){
                            q.add(child);
                        }
                        else{
                            q.add(flat[child]);
                        }
                        flat[child] = -2;
                    }
                }
            }
            count++;
        }
        return -1;
    }
}

//time complexity O(n^2) where n is given array length of row & column
//space complexity O(n^2)