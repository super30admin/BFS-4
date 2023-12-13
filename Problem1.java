// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<int[]> q = new LinkedList<>();
        board[click[0]][click[1]]='B';
        q.add(click);
        int[][] dirs = {{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int count = findMines(board,curr[0],curr[1],dirs);
            if(count == 0){
                for(int[] dir : dirs){
                    int nr = curr[0]+dir[0];
                    int nc = curr[1]+dir[1];
                    if(nr >= 0 && nr < m && nc >=0 && nc < n && board[nr][nc] == 'E'){
                        q.add(new int[]{nr,nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
            else{
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
        }
        return board;
    }
    private int findMines(char[][] board,int r, int c,int[][] dirs){
        int count = 0;
        for(int[] dir : dirs){
                    int nr = r+dir[0];
                    int nc = c+dir[1];
                    if(nr >= 0 && nr < board.length && nc >=0 && nc < board[0].length && board[nr][nc] == 'M'){
                        count++;
                    }
                }
                return count;
    }
}