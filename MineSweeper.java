// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Approach : Use BFS. Add click position to queue.For each position in queue add all it's 8 neighbors if not visited and not mine.
/// place number of mines in neighbors, mark it visited and check it's neighbors and so on until queue becomes empty 

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<Integer> q = new LinkedList<>();
        int dirs[][] = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]] = 'X';
        }
        else{
            q.add(click[0]);
            q.add(click[1]);
            board[click[0]][click[1]] = 'B';
            while(!q.isEmpty()){
                int r = q.poll();
                int c = q.poll();
                int count = countMines(board, r, c, dirs);
                if(count==0){
                    for(int dir[]:dirs){
                        int nr = r+dir[0];
                        int nc = c+dir[1];
                        if(nr>=0 && nr<board.length && nc>=0 && nc<board[0].length && board[nr][nc] == 'E'){
                            q.add(nr);
                            q.add(nc);
                            board[nr][nc] = 'B';
                        }
                    }
                }
                else{
                    board[r][c] = (char)(count+'0');
                }
            }
        }
        return board;
    }
    private int countMines(char board[][], int r, int c, int dirs[][]){
        int count = 0;
        for(int dir[]:dirs){
            int nr = r+dir[0];
            int nc = c+dir[1];
            if(nr>=0 && nr<board.length && nc>=0 && nc<board[0].length && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}