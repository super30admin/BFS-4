// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Approach : We need to return the minesweep board when the user performs a click operation. So we perform BFS here, by exploring the
// all 8 direction paths of the input click cell and if they are an unrevealed empty mine 'E' we update to 'B' and add to queue to explore it's
// neighbors as well. We add to queue only if the cell is not having any neighbors that have a mine as it's neighbor. We differentiate this
// by capturing the count of mines surrounded on a cell.

class Solution {
    int[][] dirs;
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.dirs = new int[][]{{-1,0},{-1,-1},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}}; //all 8 directions
        this.m = board.length;
        this.n = board[0].length;
        if(board[click[0]][click[1]] == 'M'){ //if input click is mine
            board[click[0]][click[1]] = 'X';
            return board;
        }
        //BFS
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';
        while(!queue.isEmpty()){
            int[] curr = queue.poll();

            int count = countMines(board, curr[0], curr[1]); //get the count of surrounding mines if any
            if(count == 0){ //no surrounding mines
                for(int[] dir: dirs){
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];

                    if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'E'){ //bounds check and if it is unrevealed empty mine
                        queue.add(new int[]{r,c}); //add to queue
                        board[r][c] = 'B'; //revealed empty mine
                    }
                }
            }else{
                board[curr[0]][curr[1]] = (char)(count + '0');//update the cell with surrounding mines count
            }
        }

        return board;
    }

    private int countMines(char[][] board, int i, int j){ //method to count mines around a cell
        int count = 0;

        for(int[] dir: dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;
            if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'M'){
                count++; //increment count when a mine is found in neighbors
            }
        }
        return count;
    }
}