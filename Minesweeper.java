// Time: O(N)
// Space: O(1)
class Solution {
    int[][] dirs;
    public char[][] updateBoard(char[][] board, int[] click) {
        dirs = new int[][]{{0,1},{1,0},{-1,0},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        
        q.add(click);
        
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int mines = checkMines(board, cur);
            if(mines>0){
                board[cur[0]][cur[1]] = (char)(mines+ '0');
            }else{
                
                for(int[] dir: dirs){
                    int ro = dir[0] + cur[0];
                    int co = dir[1] + cur[1];
                    if(ro<board.length && ro>=0 && co< board[0].length && co>=0 && board[ro][co] == 'E'){
                        q.add(new int[]{ro,co});
                        board[ro][co] = 'B';
                    }
                }
            }
        }
        return board;
    }

    private int checkMines(char[][] board , int[] pos){
            int count = 0;
            for(int[] dir : dirs){
                int r = pos[0] + dir[0];
                int c = pos[1] + dir[1];
                if(r<board.length && r>=0 && c< board[0].length && c>=0){
                    if(board[r][c] == 'M')
                        count++;
                }
            }
            return count;
    }
}