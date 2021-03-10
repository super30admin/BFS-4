// Time Complexity : The time complexity is O(m*n) where m is the number of rows and n is the number of column
// Space Complexity : The space complexity is O(m*n) where m is the number of rows and n is the number of column
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {
    public int snakesAndLadders(int[][] board) {

        if(board.length == 0){
            return 0;
        }

        int n = board.length;
        int[] newBoard = new int[n*n];

        int dir = 0;
        int i=n-1;
        int j=0;
        int index = 0;

        //convert the board into a 1D board
        while(index < n*n){

            if(board[i][j] == -1){
                newBoard[index++] = board[i][j];
            }
            else{
                newBoard[index++] = board[i][j]-1;
            }

            if(dir%2 == 0){
                j++;
            }
            else{
                j--;
            }

            if(j == n){
                i--;
                j--;
                dir++;
            }

            if(j < 0){
                i--;
                j++;
                dir++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        newBoard[0] = -2;
        int count = 0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int k=0;k<size;k++){
                int cur = q.poll();

                if(cur == n*n-1){
                    return count;
                }

                // traverse through the next 6 positions
                for(int l=1;l<=6;l++){
                    int next = cur+l;

                    if(next < n*n && newBoard[next] != -2){
                        if(newBoard[next] == -1){
                            q.offer(next);
                            newBoard[next] = -2;
                        }
                        else{
                            q.offer(newBoard[next]);
                            newBoard[next] = -2;
                        }
                    }
                }
            }
            count++;
        }
        return -1;

    }
}