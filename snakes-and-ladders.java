//TC:O(n^2)
//SC:O(n^2)
//running on leetcode: yes
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0) return -1;
        int n = board.length;
        int[] moves = new int[n*n];
        int i = n-1, j=0;
        int idx=0;
        int even=0;
        while(i>=0 && j>=0){
            if(board[i][j] == -1) {
                moves[idx] = board[i][j];
            }
            else {
                moves[idx] = board[i][j] -1;
            }
            idx++;
            if(even % 2 ==0){
                j++;
                if(j==n){
                    j--;
                    even++;
                    i--;
                }
            }
            else{
                j--;
                if(j<0){
                    j++;
                    even++;
                    i--;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        int moveCount = 0;
        q.add(0);
        moves[0]=-2;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k=0; k<size; k++) {
                int curr = q.poll();
                if(curr == n*n-1) return moveCount;
                for(int l=1; l<=6; l++) {
                    int pos = curr+l;
                    if(pos>n*n-1) break;
                    if(moves[pos] != -2) {
                        if(moves[pos] == -1) {
                            moves[pos] = -2;
                            q.add(pos);
                        }
                        else{
                            q.add(moves[pos]);
                            moves[pos] = -2;
                        }
                    }
                }
            }
            moveCount++;
        }
        return -1;
        }
    }
