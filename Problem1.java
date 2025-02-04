class Solution {


    public char[][] updateBoard(char[][] board, int[] click) {

        int[][] dirs = {{-1,0},{0,-1},{1,-1},{-1,1},{1,1},{-1,-1},{0,1},{1,0},};
        int m = board.length;
        int n= board[0].length;
        if(board[click[0]][click[1]]== 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0],click[1]});
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()){

            int[] curr = q.poll();
            int count = countMines(board , curr[0],curr[1],m , n,dirs);
            if(count ==0) {
                for(int[] dirc: dirs){
                    int cr = dirc[0] + curr[0];
                    int cc = dirc[1] + curr[1];
                    if(cr >=0 && cr < board.length && cc >=0 &&
                            cc < board[0].length && board[cr][cc] == 'E'){
                        q.add(new int[]{cr,cc});
                        board[cr][cc] = 'B';
                    }
                }
            } else{
                board[curr[0]][curr[1]] = (char)(count + '0');
            }

        }
        return board;
    }

    private int countMines(char[][] board ,int i ,int j ,int m ,int n ,int[][] dirs ){
        int count = 0;
        for(int[] dir : dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr >=0 && nr < m && nc >=0 &&
                    nc < n && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;

    }
}