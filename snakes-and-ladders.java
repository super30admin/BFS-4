//Time - O(N*N)
//Space - O(N*N)

class Solution {
    public int snakesAndLadders(int[][] board) {
        int minMoves = 0;
        int m = board.length;
        int[] moves = new int[m*m];

        // flatten the board
        int even = 0;
        int r = m-1; int c = 0; int index = 0;
        while(r>=0 && c>=0){
            if(board[r][c] == -1){
                moves[index] = -1;
            } else {
                moves[index] = board[r][c]-1;
            }
            index++;
            if(even%2 == 0){
                c++;
                if(c == m){
                    r--;
                    c--;
                    even++;
                }
            } else {
                c--;
                if(c == -1){
                    r--;
                    c++;
                    even++;
                }
            }
        }


        // process the board
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int cur = q.poll();
                if(cur == m*m-1) return minMoves;
                for(int j=1; j<7; j++){
                    int nextMove = cur+j;
                    if(nextMove > m*m-1) break;
                    if(moves[nextMove] != -2){
                        if(moves[nextMove] == -1){
                            q.add(nextMove);
                            moves[nextMove] = -2;
                        } else {
                            q.add(moves[nextMove]);
                            moves[nextMove] = -2;
                        }
                    }
                }
            }
            minMoves++;
        }
        return -1;
    }
}