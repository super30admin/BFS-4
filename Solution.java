import java.util.*;
class Solution {
    public int snakesAndLadders(int[][] board) {

        //o(n*n) time and o(n) space
        int n = board.length;
        int[] moves = new int[n*n];
        int r = n - 1;
        int c = 0;
        int even = 0;
        int idx = 0;// index in the moves array
        //flatten the matrix
        while(r >= 0 && c >=0){
            if(board[r][c] == -1){
                moves[idx] = -1;
            } else {
                moves[idx] = board[r][c] - 1;
            }
            idx++;
            if(even % 2 == 0){
                c++;
                if(c == n) {
                    r--;
                    even++;
                    c--;
                }
            } else {
                c--;
                if(c == -1){
                    r--;
                    c++;
                    even++;
                    
                }
            }
            
        }
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        q.add(0);
        moves[0] = -2;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curr = q.poll();
                if(curr == n*n - 1) return count;
                for(int j = 1; j < 7; j++){
                    int child = curr + j;
                    if(child >= n*n) continue;
                    if(moves[child] != -2){
                        if(moves[child] == -1){
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

        // o(m*n) time and o(m*n) space
        int m; int n;
        int[][] dirs;
        public char[][] updateBoard(char[][] board, int[] click) {
            m = board.length;
            n = board[0].length;
            dirs = new int[][] {{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
            if(board[click[0]][click[1]] == 'M'){
                board[click[0]][click[1]] = 'X';
                return board;
            }
            Queue<int[]> q = new LinkedList<>(); 
            q.add(new int[] {click[0], click[1]});
             board[click[0]][click[1]] = 'B';
            while(!q.isEmpty()){
                int[] curr = q.poll();
                int countmines = countMines(board, curr[0],curr[1]);
                if(countmines > 0){
                    board[curr[0]][curr[1]] = (char) (countmines + '0');
                } else {
                    for(int [] dir: dirs){
                        int r = curr[0] + dir[0];
                        int c = curr[1] + dir[1];
                        if(r >=0 && c>=0 && r < m && c < n && board[r][c] == 'E'){
                            q.add(new int[] {r,c});
                            board[r][c] = 'B';
                        
                        }
                    }
                }
                
            }
            return board;
                  
        }
        private int countMines(char[][] board, int i, int j){
            int count = 0;
            for(int [] dir: dirs){
                int r = i + dir[0];
                int c = j + dir[1];
                if(r >=0 && c>=0 && r < m && c < n && board[r][c] == 'M') count++;
            }
            return count;
            
        }
    
}