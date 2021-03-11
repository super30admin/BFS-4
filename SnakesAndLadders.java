class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        
        int r = board.length;
        
        int i = r-1;
        int j = 0;
        int idx = 0;
        int moveRight = 0;
        int[] arr = new int[r*r];
        
        while(i >= 0){
            if(board[i][j] == -1){
                arr[idx] = -1;
            }else{
                arr[idx] = board[i][j] - 1;
            }
            
            if(moveRight % 2 == 0){
                j++;
            }else{
                j--;
            }
            
            if(j == r){
                j--;
                i--;
                moveRight++;
            }
            
            if(j < 0){
                j++;
                i--;
                moveRight++;
            }
            
            idx++;
            
        }
        
        System.out.println(Arrays.toString(arr));
        
        //After we reshaped the issue 
        
        //We will check out all 6 steps in bfs fashion.
        //This way, if any of our nodes hit n*n, we are done. DFS would take wayyyy longer.
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        int min = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            for(int s = 0; s < size; s++){
                int curr = q.poll();
                if(curr == arr.length-1){
                    return min;
                }
                for(int k = 1; k <= 6; k++){
                    int sum = curr+k;
                    if(sum <= arr.length-1 && arr[sum] != -2){
                        if(arr[sum] == -1){
                            q.add(sum);
                        }else{
                            q.add(arr[sum]);
                        }
                        arr[sum] = -2;
                    }
                }  
            }
            
            min++;

        }
        
        return min;
        
    }
}