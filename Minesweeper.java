class Solution {
    //Time Complexity:O(m*n)
    //Space Complexity:O(m*n)
    int[][] dirs = new int[][] {{1,0}, {0,1},{-1,0}, {0,-1},{1,1},{-1,-1},{1,-1},{-1,1}};
    int n,m;
    public char[][] updateBoard(char[][] board, int[] click) {
        //bfs
         m = board.length;
        n = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer (click);
            //check 1: if pos = bomb, return
            if (board[click[0]][click[1]] == 'M'){
                board[click[0]][click[1]] = 'X';
                return board;
            }

                board[click[0]][click[1]] = 'B';
        while (!q.isEmpty()){
            //check around and see if a bomb is present or not
            int[] pos = q.poll();
            int noOfBombs = check(board, pos);
            if (noOfBombs == 0){
                for (int[] dir: dirs){
                    int nr = pos[0] + dir[0];
                    int nc = pos[1] + dir[1];
                    if (nr >=0 && nr<m && nc >=0 && nc <n && board[nr][nc] == 'E'){
                        board[nr][nc] = 'B';
                        q.offer(new int[]{nr,nc});
                    }
                }

            } else {
                board[pos[0]][pos[1]] = (char) (noOfBombs + '0');
            }
        }
        return board;
    }
    private int check (char[][] board, int[] pos){
        int count =0;
        for (int[] dir: dirs){
                    int nr = pos[0] + dir[0];
                    int nc = pos[1] + dir[1];
                    if ( nr >=0 && nr<m && nc >=0 && nc <n && board[nr][nc] == 'M')
                    count++;
        } 
        return count;
    }
}
