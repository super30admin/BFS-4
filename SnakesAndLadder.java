//TC : O(n*n)
//SC : O(n*n)
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int n = board.length;
        int [] moves = new int[n*n];
        int i = n - 1; int j = 0;
        int index = 0;
        boolean flag =false;
        //flatten the 2d array
        while(index < moves.length){
            if(board[i][j] != -1) moves[index] = board[i][j] -1;
            else moves[index] = -1;
            index++;
            if(flag){
                j--;
                if(j==-1){
                    i--;j++;
                    flag= false;
                }
            }else{
                j++;
                if(j == n){
                    i--;j--;
                    flag = true;
                }
            }
        }
        int cnt = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k = 0; k < size; k++){
                int curr = q.poll();
                if(curr == n*n - 1) return cnt;
                for(int l = 1; l < 7; l++){
                    int child = l + curr;
                    if(child < n*n && moves[child] != -2){
                        if(moves[child] > -1){
                            q.add(moves[child]);
                        } else {
                            q.add(child);
                        }
                        moves[child] = -2;
                    }
                }
            }
            cnt++;
        }
        return -1;
    }
}
