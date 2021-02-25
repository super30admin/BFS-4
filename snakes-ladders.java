// Time, Space - O(N^2), (N^2)
class Solution {
    public int snakesAndLadders(int[][] board) {
        
        if(board == null || board.length == 0) {
            return 0;
        }
        
        Queue<Integer> q = new LinkedList<>();
        
        int row = board.length;  
        boolean isOdd = row % 2 == 1;   
        int[] moves = new int[row * row];
        int j =0;
        int idx = 0;
        int i =row-1;
        while(i>=0 && j >=0) {
            if(board[i][j] == -1) {
                moves[idx] = -1;
            }
            else {
                moves[idx] = board[i][j] - 1;
            }
            idx++;
            if(isOdd) {
                j--;
            }
            else {
                j++;
            }
            if(j==row) {
                i--;
                isOdd = !isOdd;
                j--;
            }
            if(j<0) {
                i--;
                isOdd=!isOdd;
                j++;
            }
        }
        q.add(0);
        int minMoves = 0;
        moves[0]=-2;
        while(!q.isEmpty()) {
            int size = q.size();
            
            for(int k=0;k<size;k++) {
                int choice = q.poll();
                if(choice == row*row-1) {
                    return minMoves;
                }
                for(int l=1;l<=6;l++) {
                    int child = choice+l;
                    if(child < row*row&&moves[child]!=-2) {
                        if(moves[child]==-1) {                            
                            q.add(child);   
                        } 
                        else {
                            q.add(moves[child]);
                        }
                        moves[child] = -2;
                    }                    
                }
            }
            minMoves++;
        }
        
        return -1;
    }
}
