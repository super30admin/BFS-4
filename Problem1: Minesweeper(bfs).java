//O(MxN) time and space complexity

class Solution {
    int dirs[][];
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length; int n = board[0].length;
        dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};
        Queue<int []> q = new LinkedList<>();
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        q.add(click); 
        board[click[0]][click[1]] = 'B'; //marking visited node as blank
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1], m, n);
            if (count == 0){
                for(int[] dir : dirs){
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if (nr >=0 && nr < m && nc >=0 && nc <n && board[nr][nc] == 'E'){
                        q.add(new int [] {nr, nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
            else{ //mark that position in board as count of mines
                      board[curr[0]][curr[1]] = (char)(count + '0'); //typecast count as char 
            }
        }
        return board;
    }
    private int countMines(char[][] board, int i, int j, int m, int n){
        int count = 0;
        for(int[] dir : dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if (nr >=0 && nr < m && nc >=0 && nc <n && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}
