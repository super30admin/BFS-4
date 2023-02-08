//https://leetcode.com/problems/snakes-and-ladders
//TC : n^2
//SC : n^2
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int [] arr = new int[n*n];
        boolean dir = true;
        int i=n-1,j=0,idx=0;
        while(idx < n*n){
            if(board[i][j] != -1){
                arr[idx] = board[i][j]-1;
            }else{
                arr[idx] = -1;
            }
            idx++;
            if(dir){
                j++;
                if(j==n){
                    i--;
                    j--;
                    dir = false;
                }
            }else{
                j--;
                if(j<0){
                    i--;
                    j++;
                    dir = true;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        int steps = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(i = 0; i< size; i++){
                int curr = q.poll();
                for(j =1; j <= 6; j++){
                    int newIdx = curr + j;
                    if(newIdx == n*n -1) return steps+1;
                    if(arr[newIdx]!= -2){
                        if(arr[newIdx] == -1){
                            q.add(newIdx);
                        }else{
                            if(arr[newIdx] == n*n -1) return steps+1;
                            q.add(arr[newIdx]);
                        }
                        arr[newIdx] = -2;
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
