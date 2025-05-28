// TC: O(n^2)
// SC: O(n^2)
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