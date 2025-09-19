// Time Complexity: O(m * n) — each cell is processed at most once; counting neighbors is O(1) (up to 8 checks).
// Space Complexity: O(m * n) — in the worst case the queue can hold most of the board; dirs array is O(1).

// If the clicked cell is 'M', flip it to 'X' and return immediately.
// Otherwise, BFS from the click: mark start as 'B', pop cells, and count adjacent mines.
// If count > 0, write the digit; if count == 0, mark 'B' and enqueue all unrevealed 'E' neighbors.

class Solution {
    int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.dirs = new int[][]{{-1,0},{-1,-1},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        this.m = board.length;
        this.n = board[0].length;

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while(!q.isEmpty()){
            int[] curr = q.poll();

            int count = countMines(board, curr[0], curr[1]);
            if(count == 0){
                for(int[] dir: dirs){
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];

                    if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'E'){
                        q.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }
                }
            }else{
                board[curr[0]][curr[1]] = (char)(count + '0');
            }
        }

        return board;
    }

    private int countMines(char[][] board, int i, int j){
        int count = 0;

        for(int[] dir: dirs){
            int r = dir[0] + i;
            int c = dir[1] + j;

            if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'M'){
               count++;
            }
        }

        return count;
    }
}