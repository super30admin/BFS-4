class Solution {
    public int snakesAndLadders(int[][] board) {
        int n=board.length;
        int[] arr= new int[n*n+1];
        boolean flag=true;
        int r=n-1,c=0;
        int idx=1;
        while(idx<n*n){
            
            arr[idx]=board[r][c];
            idx++;
            if(flag){
                c++;
                if(c==n){
                    r--;
                    c--;
                    flag=false;
                }else{
                    c--;
                    if(c < 0){
                        r--;
                        c++;
                        flag=true;
                    }
                }
            }
        
            Queue<Integer> q=new LinkedList<>();
            q.add(1);
            
            arr[1]=-2;
            int moves=0;
            while(!q.isEmpty()){
                int size=q.size();
                for(int k=0;k<size;k++){
                   int currIdx=q.poll();
                    for(int i=1;i<=6;i++){
                        int newIdx=currIdx+i;
                        if(newIdx == n*n || arr[newIdx] == n*n) return moves+1;
                        if(arr[newIdx]!=-2){
                            if(arr[newIdx] == -1){
                                q.add(newIdx);
                            }else{
                                q.add(arr[newIdx]);
                                //arr[arr[newIdx]]=-2;
                            }
                            arr[newIdx]=-2;
                        }
                    }
                }
                moves++;
            }
             
        }
        return -1;
       
    }
}
