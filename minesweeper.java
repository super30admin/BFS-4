// Time Complexity : O(M*N)
// Space Complexity : O(M*N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    int[][] dirs = new int[][]{{0,1},{0,-1},{1,0},{-1,0},{0,0},{1,1},{-1,1},{1,-1},{-1,-1}};
    private int getMines(char[][] board,int[] ele){
        int m = board.length;
        int n = board[0].length;
        int mines = 0;
        for(int[] dir : dirs){
                int i = ele[0]+dir[0];
                int j = ele[1]+dir[1];
                if(i >= 0 && j >= 0 && i < m && j < n && board[i][j] == 'M') mines++;
            }
        return mines;
    }
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0) return board;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int m = board.length;
        int n = board[0].length;
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0],click[1]});
        while(!q.isEmpty()){
            int[] ele = q.poll();
            int mines = getMines(board,ele);
            if(mines == 0){
                board[click[0]][click[1]] = 'B';
                for(int[] dir : dirs){
                    int i = ele[0]+dir[0];
                    int j = ele[1]+dir[1];
                    if(i >= 0 && j >= 0 && i < m && j < n && board[i][j] == 'E'){
                        q.add(new int[]{i,j});
                        board[i][j] = 'B';
                    }                
                }
                
            }else{
                board[ele[0]][ele[1]] = (char)(mines+'0');
            }
        }
        return board;
    }
}
