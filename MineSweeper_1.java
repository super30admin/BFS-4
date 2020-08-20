/**
 * Algo: #Start from the clicked position
 *       # if clicked position itself is a mine "M" then make it "X" and return
 *       # Push the clicked positon inside a queue and do the following
 *              #poll from queue, check its neighbours for mines
 *                  #if mines present, just update it with the no of mines and move on
 *                  #else if make it visited with "B" and push all its neighbours ********while pushing its neigbors make them B to avoid repeated visiting of that cell
 *  Time: O(m*n) m- rows and n -colns of the given board array
 *  Space: O(m+n)
 */
class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;

        dirs = new int[][] {{0,1},{0,-1},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
        m = board.length; n = board[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        int cn = 0;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            //System.out.print(curr[0]+" "+curr[1]+" ");
            if(board[curr[0]][curr[1]] == 'M') {
                board[curr[0]][curr[1]] = 'X';
                return board;
            }

            int mines = helper(board,curr[0],curr[1]);
            if(mines == 0){
                board[curr[0]][curr[1]] = 'B';
                for(int[] dir:dirs){
                    int r = curr[0]+dir[0];
                    int c = curr[1]+dir[1];
                    if(r>=0 && r<m && c>=0 && c<n && board[r][c] == 'E'){
                        q.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }

                }
            }else
                board[curr[0]][curr[1]] = (char)(mines+'0');
            //System.out.println(board[curr[0]][curr[1]]);
        }
        return board;
    }
    private int helper(char[][] b,int i,int j){
        int cnt = 0;
        for(int[] dir:dirs){
            int r = i+dir[0];
            int c = j+dir[1];
            if(r>=0 && r<m && c>=0 && c<n && b[r][c] == 'M')
                cnt++;
        }
        return cnt;
    }

}