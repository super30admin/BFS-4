// Time: O(n^2)
// Space: O(n^2)
// Idea here is to flatten all the cells and identify from each cell
// which moves are gifing us destination in least number of moves
// We use queue to traverse whole board and identify farthest point a move makes for each jump
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board==null || board.length==0) return 0;
        int n = board.length;
        int[] moves = new int[n*n];
        int r = n-1; int c = 0;
        int even = 0; int idx = 0;
        while(r>=0 && c>=0) {
            if(board[r][c]==-1) {
                moves[idx] = -1;
            } else {
                moves[idx] = board[r][c]-1;
            }
            idx++;
            if(even%2==0) {
                c++;
                if(c==n){
                    r--; even++; c--;
                }
            } else {
                c--;
                if(c==-1) {
                    r--; even++; c++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        q.add(0); moves[0] = -2;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i<size; i++) {
                int curr = q.poll();
                if(curr == n*n-1) return count;
                for(int j = 1; j<7; j++) {
                    int child = curr + j;
                    if(child >= n*n) continue;
                    if(moves[child]!=-2) {
                        if(moves[child]==-1){
                            q.add(child);
                        } else {
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