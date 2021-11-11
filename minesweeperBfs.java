class Solution {
    int[][] dirs; int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length==0) return board;
        dirs = new int[][]{{0,1}, {0, -1}, {-1, 0}, {1,0}, {-1,-1}, {1,1}, {-1, 1}, {1, -1}};
        m = board.length; n = board[0].length;
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int countmines = countMines(board, curr[0], curr[1]);
            if(countmines>0) {
                board[curr[0]][curr[1]] = (char)(countmines +'0');
            } else {
                for(int[] dir: dirs){
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];
                    if(r>=0 && r<m && c>=0 && c< n && board[r][c] == 'E'){
                        q.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }
                }
            }
        }
        return board;
    }
    
    private int countMines(char[][]board, int i, int j) {
        int count = 0;
        for(int [] dir: dirs) {
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r>= 0 && r<m && c>=0 && c<n && board[r][c]=='M') count++;
        }
        
        return count;
    }
}