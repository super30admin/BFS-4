class Solution {
    //TC-O(N^2),SC-O(N^2)
    //create a new array with their positions, if no ladder or snake , -1 else the destination from that node,keeping direction in mind.Create s queue and add 0(start point), make its value -2 in arr since its visited and will no longer be used again, take turns of 1-6 on dice for the curr number and check if its not already visited, add to queue, anywhere we reach the fnal point return count.
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int [] arr = new int[n*n];
        int idx =0;
        int r = n-1;
        int c=0;
        boolean dir = true;
        while(idx<n*n){
            // arr has indices
            if(board[r][c]==-1){
                arr[idx]=-1;
            }
            else{
                arr[idx]=board[r][c]-1;
            }
            idx++;
            if(dir){
                c++;
                if(c==n){
                    dir=false;
                    r--;
                    c--;
                }
            }
                else{
                    c--;
                    if(c<0){
                        dir=true;
                        r--;c++;
                    }
                }
            }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        // visited node
        arr[0]=-2;
        int count = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i =0;i<size;i++){
                int curr = q.poll();
                for(int k=1;k<=6;k++){
                    int newnum=curr+k;
                    if((newnum)>(n*n))continue;
                    if(newnum==(n*n)-1) return count+1;
                    if(arr[newnum]!=-2){
                        if(arr[newnum]==-1){
                            q.add(newnum);
                            if(newnum==(n*n)-1) return count+1;
                        }
                        else{
                            q.add(arr[newnum]);
                            if(arr[newnum]==(n*n)-1) return count+1;
                        }
                        arr[newnum]=-2;
                    }
                }
            }
            count++;
        }
         return -1;
    }
   
}