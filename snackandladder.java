// Time Complexity: O(n^2) — BFS explores up to n^2 squares; from each square we try up to 6 moves (constant factor).
// Space Complexity: O(n^2) — the queue can hold O(n^2) indices; visited is tracked in-place on the board.

// Perform BFS on the flattened board starting at index 0; for each state, try dice rolls k=1..6 and map the target index to (r,c) using the zig-zag (serpentine) layout.
// If the landing cell has a snake/ladder (board[r][c] != -1), jump to (board[r][c] - 1); otherwise stay at newIdx; skip cells already marked visited (set to -2).
// Count BFS layers as moves; return level+1 when reaching the final square (nn-1 or a jump to nn); if BFS exhausts without reaching it, return -1.

class Solution {
    public int snakesAndLadders(int[][] board) {
       int n = board.length;
       Queue<Integer> q = new LinkedList<>();
       q.add(0);

       int level = 0;

       while(!q.isEmpty()){
            int size = q.size();
            for(int j=0; j<size; j++){
                int currIdx = q.poll();
                for(int k=1; k<=6; k++){
                    int newIdx = currIdx + k;

                    int[] temp = getPos(newIdx, n);
                    int r = temp[0], c = temp[1];

                    if(newIdx == n*n-1 || board[r][c] == n*n) return level+1;
                    
                    if(board[r][c] != -2){
                        if(board[r][c] == -1){
                            q.add(newIdx);
                        }else{
                            q.add(board[r][c]-1);
                        }
                        board[r][c] = -2;
                    }
                }
            }
            level++;
       }

        return -1;

    }

    private int[] getPos(int idx, int n){
        int r = idx / n;
        int c = idx % n;

        if(r %2 == 0){
            return new int[]{n-r-1, c};
        }else{
            return new int[]{n-r-1, n-c-1};
        }
    }
}
