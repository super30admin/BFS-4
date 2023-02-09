//Time Complexity: O(n*n)
//Space Complexity: O(n*n)

/*
 * create an array that stores the board in 1D array. then we use
 * BFS to travrese theough the array. at each index if the position is not
 * visited we add it to the queue if it is -1 else we move to the 
 * given position and mark visited. at each level increse the moves and return it.
 */

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        int i = n-1; int j = 0;
        int idx = 0;
        boolean dir = true;
        
        while(idx < n*n){
            if(board[i][j] == -1){
                arr[idx] = -1;
            }
            else{
                arr[idx] = board[i][j]-1; 
            }

            idx++;
            if(dir){
                j++;
                if(j == n){
                    i--;
                    dir = false;
                    j--;
                }
            }
            else{
                j--;
                if(j < 0){
                    i--;
                    dir = true;
                    j++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int moves = 0;
        q.add(0); arr[0] = -2;
        while(!q.isEmpty()){
            int size = q.size();
            for(i = 0; i < size; i++){
                int curr = q.poll();
                for(j = 1; j <= 6; j++){
                    int nc = curr + j;
                    if(nc == n*n-1) return moves+1;
                    if(arr[nc] != -2){
                        if(arr[nc] == -1){
                            q.add(nc);
                        }
                        else{
                            if(arr[nc] == n*n-1) return moves+1;
                            q.add(arr[nc]);
                        }
                        arr[nc] = -2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}