/**
 * Algo: BFS: start from 0th position and add the next 6 values such that if ladder or snake is present consider its resp value.
 *              then the earlier we reach the end point the levels are returned and finsih
 *  Time: O(n^2) n-length of the board
 *  Space:O(n^2)
 */
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return 0;
        int n = board.length;
        int[] moves = new int[n*n];
        int idx = 0,even = 0;

        for(int i=n-1;i>=0;i--){
            if(even == 0){
                for(int j=0;j<n;j++){
                    moves[idx] = board[i][j]!=-1 ? (board[i][j]) -1 : -1;
                    //System.out.print(moves[idx]);
                    idx++;
                }
                even = 1;
            }else{
                for(int j=n-1;j>=0;j--){
                    moves[idx] = board[i][j]!=-1 ? (board[i][j]) -1 : -1;
                    //System.out.print(moves[idx]);
                    idx++;
                }
                even = 0;
            }
            //System.out.println();
        }

        Queue<Integer> q = new LinkedList<>();
        // int add = moves[0] == -1 ? 0: moves[0];
        q.offer(0);
        moves[0] = -2;
        int level = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int curr = q.poll();
                //System.out.print(curr);
                if(curr == (n*n)-1) return level;
                for(int k=1;k<=6 && curr+k < n*n;k++){
                    if(moves[curr+k] != -2){
                        int add = moves[(curr+k)] == -1 ? (curr+k) : moves[(curr+k)];
                        moves[curr+k] = -2;
                        q.add(add);
                    }
                }
            }//System.out.println();
            level++;
        }
        return -1;
    }
}