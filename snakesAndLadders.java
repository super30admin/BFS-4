class Solution {
    public int snakesAndLadders(int[][] board) {
        Queue<Integer> q = new LinkedList<>();
        int n=board.length;
        q.offer(1);
        board[n-1][0]=-2;
        int step=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int curr=q.poll();

                for(int j=1;j<7;j++){
                    int newidx=curr+j;
                    if(newidx<=n*n){
                        int[] pos = getPos(newidx-1,n);
                        int r=pos[0],c=pos[1];
                        if(board[r][c]==n*n ||  newidx==n*n) return step+1;
                        if(board[r][c]!=-2){
                            if(board[r][c]==-1)
                                q.offer(newidx);
                            else
                                q.add(board[r][c]);
                            board[r][c]=-2;
                        }
                    }
                    
                }
            }
            step++;
        }
        return -1;
    }
    private int[] getPos(int idx,int n){
        int[] res = new int[2];
        int r=idx/n;
        res[0]=n-1-r;
        int c=idx%n;
        if(r%2==0){
            res[1]=c;
        }
        else{
            res[1]=n-c-1;
        }
        return res;
    }
}
