// Time Complexity : O(n*n)
// Space Complexity : O(n*n)
// n*n is the given matrix elements , at most every element is added once to the queue
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

class Solution {
    public int snakesAndLadders(int[][] board) {
        //null check
        if(board == null || board.length == 0)
            return 0;

        int n = board.length;
        int i = n-1;
        int j = 0;

        int[] moves = new int[n*n];
        //index over moves array
        int idx = 0;
        int even = 0;

        //Make the moves 1-D array
        while(idx < n*n){
            if(board[i][j] == -1){
                moves[idx] = board[i][j];
            }
            else{
                moves[idx] = board[i][j] -1;
            }
            idx++;
            //Left --> right traversal
            if(even %2 == 0){
                j++;
                if(j == n){
                    i--;
                    j--;
                    even++;
                }
            }
            //Right --> Left traversal
            else{
                j--;
                if(j == -1){
                    i--;
                    j++;
                    even++;
                }
            }
        }

        //BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        //mark it visites in moves array
        moves[0] = -2;
        int result = 0;

        while(!q.isEmpty()){
            int size = q.size();
            for(int l = 0 ; l <size ; l++){
                int curr = q.poll();
                if(curr == n*n-1){
                    return result;
                }
                //dice throw can give resutl between 1-6
                for(int k = 1 ; k <= 6 ; k++){
                    int child = curr+k;
                    if(child < n*n){
                        if(moves[child] != -2){
                            if(moves[child] == -1){
                                q.add(child);
                            }
                            else{
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
