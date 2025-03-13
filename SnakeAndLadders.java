class Solution {
    //Time Complexity:O(n^2)
    //Space Complexity:O(n^2)
    public int snakesAndLadders(int[][] board) {
      if (board.length ==0 )  {
        return -1;
      }
      //flatten the board
      int len = board.length;
      int[] flatboard = new int [len * len];

      int index = 0;
      int row = len-1;
      int col = 0;
      int even =0;
      while (index < len * len){
        if (board[row][col] != -1){
            flatboard [index] = board[row][col] -1;
        } else {
            flatboard [index] =  -1;
        }
        index++;
        if (even%2 == 0){
            col++;
            if (col == len){
                col = len-1;
                 row--;
                 even++;
            }
        } else {
             col--;
            if (col < 0){
                col = 0;
                 row--;
                 even++;
            }
        }
      }
    Queue<Integer> q = new LinkedList<>();
    q.add (0);
    int moves = 0;
    while(!q.isEmpty()){
        int size = q.size();
        for ( int i =0; i< size;i++){
            int curr = q.poll();
if (curr == len * len -1)
                return moves;

            for (int j = 1; j <=6;j++){
                int child = curr + j;
                
                if (child > len * len -1){
                    continue;
                }
                if (flatboard[child] != -2){
                    if (flatboard[child] == -1){
                        q.add (child);
                        flatboard[child] = -2;
                    } else {
                        q.add (flatboard[child]);
                        flatboard[child] = -2;
                    }
                }
            }
        }
        moves++;
    }
        return -1;
    }
}
