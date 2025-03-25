// Tc:O(6n^2)
// SC:O(n^2)

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        int r = n-1; int c =0;
        boolean flag = true; int idx =0;
        while(idx<arr.length){
            if(board[r][c]==-1){
                arr[idx]=-1;
            }else{
                arr[idx]=board[r][c]-1;
            }
            idx++;
            if(flag){
                c++;
                if(c==n){
                    flag=false;
                    r--;c--;
                }
            }else{
                c--;
                if(c<0){
                    flag=true;
                    r--;c++;
                }
            }
        }
        int moves =0 ;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0]=-2;
        while(!q.isEmpty()){
            int size= q.size();
            for(int i=0;i<size;i++){
                int curr = q.poll();
                if(curr==n*n-1) return moves;
                for(int k=1;k<=6;k++){
                    int newIdx =  curr+k;
                    if(newIdx==n*n) break;
                    if(arr[newIdx]!=-2){
                        if(arr[newIdx]==-1){
                            q.add(newIdx);
                        }else{
                            q.add(arr[newIdx]);
                        }
                        arr[newIdx]=-2;
                    }
                }
            }
            moves++;
        }
    return -1;
    }
}