// Time complexity - O(n^2)
//Space Complexity - O(n^2)

class Solution {
    public int snakesAndLadders(int[][] board) {
      //edge case
      if(board == null || board.length == 0) { 
        return 0;
      }
    /* to find the visited cell as well as to calculate the next possible cell   f  flatten the matrix in to array */
      int r = board.length; int  c = board[0].length;
      int[] moves = new int[r*r];
      int index = 0;
      int even = 0; 
      int i = r-1, j = 0;
      int min = 0;
      // to change the direction 
      while(i>=0 && j >= 0) {
        if(board[i][j] == -1) {
          moves[index] = -1;
        } else {
          moves[index] = board[i][j] -1;
        }
        index ++;
        //change the direction 
        if(even % 2 == 0) {
          j++;
          if(j == r) {
            i--;
            even ++;
            j--;
          }
        } else {
          j--;
          if(j == -1) {
            i--;
            even++;
            j++;
          }
        }
      }
      //Bfs
      Queue<Integer> q = new LinkedList<>();
      q.add(0); //first cell
      moves[0] = -2; //visited
      while(!q.isEmpty()) {
        int size = q.size();
        for(int l = 0; l<size; l++) {
          int curr = q.poll();
          if(curr == r*r - 1) return min;
          //check all 6 possibility
          for(int k = 1; k<7; k++) {
            int child = curr + k;
            if(child > r*r - 1) break;
            if(moves[child] != -2 ){
              if(moves[child] == -1){
                q.add(child);
              }else {
                q.add(moves[child]);
              }
              moves[child] = -2;//mark visited
            }
          }
        }
        min ++;
      }
       return -1;
    }
}