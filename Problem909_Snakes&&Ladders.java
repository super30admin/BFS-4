/**
 * Time Complexity- O(m*n)
 * Space Complexity  - O(m*n)
 */

class Solution {
    public int snakesAndLadders(int[][] board) {

        int r = board.length, c = board[0].length;

        int[] moves = new int[r*c];

        int index = 0;

        int even = 0, i= r-1, j =0 ;

        while(i>=0 && j>=0){

            if(board[i][j] == -1){
                moves[index] = -1;
            }else{
                moves[index] = board[i][j]-1;
            }
            index++;

            if(even%2==0){
                j++;
            }else{
                j--;
            }

            if(j >= c){
                i--;
                j--;
                even++;
            }

            if(j < 0){
                i--;
                j++;
                even++;
            }
        }
        int N = moves.length;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        moves[0] = -2;
        int minMoves=0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(i =0 ; i<size; i++){
                int curr = queue.poll();
                if(curr == N-1){
                    return minMoves;
                }
                for(int k=1; k<=6; k++){
                    int child = curr + k;
                    if(child <N  && moves[child] != -2){
                        if(moves[child] == -1){
                            queue.add(child);
                        }else{
                            queue.add(moves[child]);
                        }
                        moves[child] = -2;
                    }
                }
            }
            minMoves++;
        }
        return -1;
    }
}