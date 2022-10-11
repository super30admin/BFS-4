// Time Complexity : O(mn)
// Space Complexity : O(mn)

//using BFS:

class MinesWeeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null || board.length==0) return board;
        int m= board.length;
        int n= board[0].length;
        int[][] dirs = new int [][]{{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = countMines(board, curr[0], curr[1], m, n, dirs);
            if(count==0){
                for(int[] dir : dirs){
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc]=='E'){
                        q.add(new int[]{nr,nc});
                        board[nr][nc]='B';
                    }
                }
            } else {
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
        }
        return board;
    }

    private int countMines(char[][] board, int i, int j, int m, int n, int[][] dirs){
        int count = 0;
        for(int[] dir: dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr>=0 && nr<m && nc>=0 && nc<n && board[nr][nc]=='M'){
                count++;
            }
        }
        return count;
    }
}