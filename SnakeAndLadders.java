//TC: O(n^2)
//SC: O(n^2)
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int n = board.length;
        int[] arr = new int[n*n];
        int even = 0;
        int idx = 0; //on 1d arr
        int i = n-1;
        int j = 0;

        //fill 1D arr
        while(idx < arr.length){
            if(board[i][j] == -1){
                arr[idx] = board[i][j];
            }else{
                arr[idx] = board[i][j] - 1;
            }
            idx++;
            if(even % 2 == 0){
                //l to r
                j++;
                if(j == n){
                    i--;
                    j--;
                    even++;
                }
            }else{
                //r to l
                j--;
                if(j == -1){
                    i--;
                    j++;
                    even++;
                }
            }
        }

        //bfs
        Queue<Integer> q = new LinkedList<>();
        q.add(0); arr[0] = -2; //mark it visited
        int moves = 0;
        while(!q.isEmpty()){
            int size = q.size(); //require this bcz we increase number of moves after each level
            for(int l=0; l<size; l++){
                int curr = q.poll();
                if(curr == n*n-1) return moves;
                //roll the dice
                for(int k=1; k<7; k++){
                    int child = curr + k;
                    if(child < n*n){
                        if(arr[child] != -2){
                            if(arr[child] == -1){
                                q.add(child);
                            }else{
                                q.add(arr[child]);
                            }
                            arr[child] = -2; //mark it visited
                        }
                    }

                }
            }
            moves++;
        }
        return -1;
    }
}