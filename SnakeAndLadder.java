// TC: O(n*n)
// SC: O(n*n)
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        boolean dirLR = true;
        int idx = 0;
        int r = n-1;
        int c = 0;
        while(idx < n*n){
            if(board[r][c] == -1){
                arr[idx] = board[r][c];
            }else{
                arr[idx] = board[r][c] -1;
            }

            idx++;
            if(dirLR){
                c++;
                if(c == n){
                    r--;
                    c--;
                    dirLR = false;
                }
            }else{
                c--;
                if(c == -1){
                    r--;
                    c++;
                    dirLR = true;
                }
            }
        }

        Queue<Integer> bfs = new LinkedList<>();

        bfs.add(0);
        arr[0] = -2;
        int steps = 0;
        while(!bfs.isEmpty()){
            int size = bfs.size();

            for(int i=0; i< size; i++){
                int curr = bfs.poll();

                for(int j=1; j<= 6 && curr + j <= n*n ; j++){
                    int diceRoll = curr + j;

                    // reaching end with diceRoll
                    if(diceRoll == n*n -1){
                        return steps +1;
                    }

                    if(arr[diceRoll] != -2){

                        if(arr[diceRoll] == -1){
                            // normal cell
                            bfs.add(diceRoll);
                            arr[diceRoll] = -2;
                        }else{
                            // snake or ladder

                            // reaching end with ladder
                            if(arr[diceRoll] == n*n -1){
                                return steps +1;
                            }
                            bfs.add(arr[diceRoll]);
                            arr[diceRoll] = -2;
                        }
                    }
                }
            }
            steps++;
        }

        return -1;
    }
}