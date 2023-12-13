// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class Solution {
    public int snakesAndLadders(int[][] board) {
        int m = board.length;
        int[] arr = new int[m*m];
        int idx = 0;
        int row = m - 1; int col = 0; int even = 0;
        while(idx < m*m){
            if(board[row][col] == -1)
                arr[idx] = -1;
            else{
                arr[idx] = board[row][col] - 1;
            }
            System.out.println("Row :"+row+"Col :"+col);
            idx++;
            if(even%2 == 0){
                col++;
                if(col == m){
                    row--;
                    col--;
                     even++;
                }
            }
            else{
                col--;
                if(col == -1){
                    row--;
                    col++;
                     even++;
                }
            }
           
            
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int moves = 0;
        while(!q.isEmpty()){
            int sz = q.size();
            for(int j = 0; j < sz; j++){
                int curr = q.poll();
                for(int i = 1; i <= 6; i++){
                    int baby = curr + i;
                    if(baby == m*m - 1)
                        return moves+1;
                    if(baby > m*m - 1)
                        break;
                    if(arr[baby] != -1){
                        if(arr[baby] != -2){
                        if(arr[baby] == m*m - 1)
                            return moves+1;
                        q.add(arr[baby]);
                        arr[baby] = -2;
                    }}
                    else{
                    
                        q.add(baby);
                        arr[baby] = -2;
                    }
                }
                
            }
            moves++;

        }
        return -1;
    }
}