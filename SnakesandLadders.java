//time: n^2;
//space: n;
//leetcode:yes
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0)return 0;
        int minMoves = 0;
        int r = board.length;
        int n = r*r;
        int [] moves = new int [n];
        int idx = 0;
        int even = 0;
        int  i = r - 1;
        int j = 0;
        //Flatten the array in 1d
        while(i >= 0 && j >= 0 ){
            if(board[i][j] == -1){
                moves[idx] = -1;
            }else{
                moves[idx] = board[i][j] - 1;
            }
            idx++;
            if(even % 2 == 0)j++;
            else j--;
            if(j == r){
                i--;
                even++;
                j--;
            }
            if(j < 0){
                i--;
                even++;
                j++;
            }
        }
        //BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        moves[0] = -2;
        while(!q.isEmpty()){
            int size = q.size();
            for(int l = 0; l < size; l++){
                int curr = q.poll();
                if(curr == n - 1)return minMoves;
                for(int k = 1; k < 7; k++){
                    int child = k + curr;
                    if(child < n && moves[child] != -2){
                        if(moves[child] == -1){
                            q.add(child);
                        }else{
                            q.add(moves[child]);
                        }
                        moves[child] = -2;
                    }
                    
                }
               
                
            } minMoves++;
           
        } return -1;
    }
}