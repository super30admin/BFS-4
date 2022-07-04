//time-O(m*n), space - O(m*n)
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board==null || board.length==0) return 0;
        int n = board.length;
        int[] moves = new int[n*n];
        int even = 0, r=n-1, c=0, idx = 0;

        while(r>=0 && c<n){
            if(board[r][c]==-1){
                moves[idx] = -1;
            }
            else{
                moves[idx] = board[r][c] - 1;
            }
            idx++;
            if(even%2==0){
                c++;
                if(c==n){
                    even++;
                    r--;
                    c--;
                }
            }
            else{
                c--;
                if(c==-1){
                    even++;
                    r--;
                    c++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        moves[0] = -2;
        q.add(0);

        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0; i<size; i++){
                int curr = q.poll();
                if(curr==n*n-1) return count;
                for(int j=1; j<=6; j++){
                    int child = curr + j;
                    if(child >= n*n) continue;
                    if(moves[child]!=-2){
                        if(moves[child]==-1){
                            q.add(child);
                        }
                        else{
                            q.add(moves[child]);
                        }
                        moves[child] = -2;
                    }
                }
            }
            count++;
        }

        return -1;
    }
}
