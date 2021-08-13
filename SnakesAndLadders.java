class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board==null || board.length==0) return 0;
        
        int r=board.length;
        int[] moves=new int[r*r];
        int i=r-1;
        int j=0;
        int even=0;
        int idx=0;
        
        while(i>=0 && j>=0){
            if(board[i][j]==-1){
                moves[idx]=-1;
            }else{
                moves[idx]=board[i][j]-1;
            }
            idx++;
            if(even%2==0){
                j++;
                if(j==r){
                    i--;
                    even++;
                    j--;
                }
            }else{
                j--;
                if(j==-1){
                    i--;
                    even++;
                    j++;
                }
            }
        }
        
        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        moves[0]=-2;
        int min=0;
        
        while(!q.isEmpty()){
            int size=q.size();
            for(int l=0;l<size;l++){
                int curr=q.poll();
                if(curr == r*r-1) return min;
                
                for(int k=1;k<7;k++){
                    int child=curr+k;
                    if(child> r*r-1) break;
                    if(moves[child] !=-2){
                        if(moves[child] == -1){
                            q.add(child);
                        }else{
                            q.add(moves[child]);
                        }
                        moves[child]=-2;
                    }
                }
            }
            min++;
        }
        return -1;
    }
}