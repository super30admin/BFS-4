//Time -O(n^2)
//Space - O(n^2)
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board==null || board.length ==0) return 0;
        Queue<Integer> q = new LinkedList<>();
        int m = board.length;
        int[] game = new int[m*m];
        int i=0;
        int k = m-1;
        int l = 0;
        int even =0;
        while( i< game.length){ 
            if(board[k][l] == -1){
                game[i] = board[k][l];
            }else{
                game[i] = board[k][l]-1;  
            }
            i++;
           if( even%2 ==0){
               l++;
                if(l==m){
                   even++;
                   k--;l--;
               }
           }else{
               l--;
                if(l<0){
                   k--;l++;
                   even++;
               }  
           }
        }
        int count=0;
        q.add(0);
        game[0]= -2;
        while(!q.isEmpty()){
            int size = q.size();
            for(int j=0; j<size; j++){
              int curr = q.poll();
              if(curr == m*m-1) return count;  
              for(int z =1; z< 7; z++){
                  int newidx = curr+z;
                  if(newidx < game.length && game[newidx] != -2){
                      if(game[newidx] >= 0){
                          q.add(game[newidx]);
                      }else{
                            q.add(newidx) ;
                      }
                      game[newidx] = -2;
                   
                  }
              }
            }
            count++;
        }  
        return -1;
    }
}