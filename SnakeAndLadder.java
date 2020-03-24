//TC : O(m*n) ,m and n are length of rows and cols
//SC : O(m*n) ,m and n are length of rows and cols
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0)
            return 0;
       
        int r = board.length;
        int c = board[0].length;
        int N = r * c;
        int moves[] = new int[N];
        
        //Flatten the matrix
        int idx = 0,even = 0,i=r-1,j = 0;
        
        while(idx<N){
            
            if(board[i][j] == -1) moves[idx]=-1;
            else moves[idx] = board[i][j] - 1;
            
            idx++;
            
            if(even%2==0) j++;
            else j--;
            
            if(j>=c){
                i--;even++;j--;
            }
            else if(j<0){
                i--;even++;j++;
            }
   
        }
        System.out.println(Arrays.toString(moves));
       
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);int minmoves = 0;
        
        while(!q.isEmpty()){
            int size = q.size();
            
            for(int k=0;k<size;k++){
                
                int curr = q.poll();
                
                if(curr == N-1) return minmoves;
                
                for(int l=1;l<7;l++){
                    
                    int child = curr+l;
                  
                    
                    if(child<N && moves[child]!=-2){
                      
                         if(moves[child] > -1) q.add(moves[child]);
                         else q.add(child);
                         moves[child] = -2;
                        
                    }
            
                }
   
            }
            minmoves++;
  
        }
        
        return -1;
    }
}