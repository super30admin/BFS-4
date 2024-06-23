package BFS-4;
// TC: O(n^2)
// SC: O(1)
public class Problem2 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        int moves=0;
        while(!q.isEmpty()){
            int qSize = q.size();
            for(int a = 0;a<qSize;a++){
                int currElem = q.poll();
                for(int j = 1; j<=6;j++){
                    int newIdx = currElem + j;
                    int[] pos = getPositions(newIdx,n);
                    if(newIdx == n*n || board[pos[0]][pos[1]] == n*n) return moves+1;
                    if(board[pos[0]][pos[1]]!=-2){
                        if(board[pos[0]][pos[1]]==-1){
                            q.add(newIdx);
                        }else{
                            q.add(board[pos[0]][pos[1]]);
                            
                        }
                        board[pos[0]][pos[1]] = -2;
                    }
                }
            }
            moves++;
        }

        return -1;
    }

    private int[] getPositions(int position, int n){
        int r = (position-1)/n;
        int c = (position-1)%n;
        if(r%2!=0){
            c = n-1-c;
        }
        r=n-1-r;
        return new int[]{r,c};
    }
}
