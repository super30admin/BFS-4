// Time: O(n^2) // n = number of blocks 
// Space: O(n^2)

class Solution {
    public int snakesAndLadders(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[] grid = new int[m*n];
        
        int idx = 0;
        int r = board.length-1;
        int c = 0;
        boolean dir = true; // move right 
        while(idx < m*n){
            if(board[r][c] == -1){
                grid[idx] = -1;
            }else{
                grid[idx] = board[r][c]-1;
            }
            if(dir){
                c++;
                if(c == m){
                    c--;
                    r--;
                    dir = false;
                }
            }else{
                c--;
                if(c < 0){
                    c++;
                    r--;
                    dir = true;
                }
            }
            idx++;
        }

        Queue<Integer> q = new LinkedList<>();

        q.add(0);
        int count = 0;

        while(!q.isEmpty()){
            int size  = q.size();
            for(int i = 0 ; i < size ; i++){
                int cur = q.poll();
                
                for(int j = 1 ; j<= 6; j++){
                    // int newIdx = 
                    if(cur + j>= n*n || grid[cur+j] == -2) continue;
                    if(grid[cur+j] == n*n-1 || (cur+j) == n*n-1){
                        return ++count;
                    }
                    if(grid[cur+j] ==-1){
                            q.add(cur+j);
                    }else{
                            q.add(grid[cur+j]);
                    }
                    grid[cur+j] = -2;
                }
            }
            count++;
        }

        // System.out.println(Arrays.toString(grid));
        return -1;
        
    }
}