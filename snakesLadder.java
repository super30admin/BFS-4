// space complexity : O(m*n)
// time compelxity : O(m*n)
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n  = board.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] seen = new boolean[n*n+1];
        q.add(1);
        for(int move  = 0; !q.isEmpty() ; move++){
            for(int size = q.size() ; size > 0 ; size--){
                int curr = q.poll();
                if(seen[curr]) continue;
                seen[curr] = true;
                if(curr == n*n ) return move;
                for(int i = 1 ; i<=6 && i+curr <= n*n ; i++){
                    int next = getBoardValue(board, curr+i);
                    if(next == -1) next = curr+i;
                    if(!seen[next]) q.add(next);
                }
            }
        }
        return -1;
    }

    private int getBoardValue(int[][] board , int num){
        int n = board.length;
        int r = (num - 1)/n;
        int x = n - 1 - r;
        int y = r%2 == 0 ? num - 1 - r*n : n + r * n - num;
        return board[x][y];
    }
}
