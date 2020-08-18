//Time complexity:O(n*n)
//Space complexity:O(n*n)

class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board==null || board.length==0){
            return 0;
        }
        int r=board.length;
        int n=r*r;
        int[] moves=new int[n];
        int minMoves=0;
        int idx=0;
        int i=r-1;
        int j=0;
        int even=0;
        while(i>=0 && j>=0){
            if(board[i][j]==-1){
                moves[idx]=-1;
            }
            else{
                moves[idx]=board[i][j]-1;
            }
            idx++;
            if(even%2==0){
                j++;
            }
            else{
                j--;
            }
            if(j==r){
                i--;
                j--;
                even++;
            }
            if(j<0){
                i--;
                j++;
                even++;
            }
        }
        
        Queue<Integer> q=new LinkedList();
        q.add(0);
        moves[0]=-2;
        while(!q.isEmpty()){
            int size=q.size();
            for(int k=0;k<size;k++){
                int curr=q.poll();
                if(curr==n-1){
                    return minMoves;
                }
                for(int l=1;l<7;l++){
                    int child=l+curr;
                    if(child<n && moves[child]!=-2 ){
                        if(moves[child]==-1){
                            q.add(child);
                        }
                        else{
                            q.add(moves[child]);
                        }
                        moves[child]=-2;
                    }
                    
                }
            }
            minMoves++;
        }
        return -1;
    }
}