// Problem1: Minesweeper (https://leetcode.com/problems/minesweeper/)

// Time Complexity : O(m*n) 
// Space Complexity : O(m*n) 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach in three sentences only
/*
 * Here, start from the click and if click is already mine change it to x and return board. If not add click to queue and make it to B. While
 * queue is not empty, take the top element out and find the count of mines around it. If count is 0 then take all 8 dirs and if its E add it to 
 * queue and make it B. Else make board[nr][nc] = count in character datatype. Finally return count.
 */
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int [][] dirs = {{-1,0},{1,0},{0,1},{0,-1},{-1,1},{-1,-1},{1,-1},{1,1}};
        int m = board.length;
        int n = board[0].length;
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int []> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int []curr = q.poll();
            int count = countMines(board, curr[0], curr[1], dirs);
            if(count == 0){
                for(int []dir: dirs){
                    int nr = dir[0] + curr[0];
                    int nc = dir[1] + curr[1];
                    if(nr >=0 && nc >=0 && nr < m && nc < n && board[nr][nc] == 'E'){
                        board[nr][nc] = 'B';
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            else{
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
            
        }
        return board;
    }
    private int countMines(char[][] board, int i, int j, int [][]dirs){
        int count = 0;
        for(int []dir: dirs){
            int nr = dir[0] + i;
            int nc = dir[1] + j;
            if(nr >=0 && nc >=0 && nr < board.length && nc < board[0].length && board[nr][nc] == 'M'){
                count ++;
            }
        }
        return count;
    }
}