/*
* Approach1: BFS -> connected components
- Idea is to start by the cell that was clicked and explore all 8 neighbours
    - firstly we need to check if the neightbours has mines, if they do we cannot update any neighbours
    - if there are no mines, go ahead and process the neighbours and add it to the queue it is not processed
- TC: O(8*m*n) -> O(m*n) -> as we are exploring 8 directions
- SC: O(m*n) -> queue size for processing

* Approach2: DFS: 
    - This can be solved using DFS as well. The stopping condition will be if the rows and columns are out of bound or we have already processed the cell (current cell!= 'E')
    - And do the dfs on the neighbours
    - TC and SC remain same as BFS
*/
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        //handle the case where the cell clicked is a mine
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        int m = board.length;
        int n = board[0].length;
        int[][] dirs = new int[][] { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, -1 },
                { -1, 1 } };
        q.add(click);
        board[click[0]][click[1]] = 'B';

        while (!q.isEmpty()) {
            int[] curr = q.poll();

            //count mines before updating/processing the neighbours because if there mine adjacent to the current cell we cannot process any of the neighbours
            int cnt = countMines(board, curr[0], curr[1], dirs);

            if (cnt == 0) {
                //explore all the neighbours
                for (int[] dir : dirs) {
                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];

                    //bounds check
                    if (r >= 0 && c >= 0 && r < m && c < n) {
                        if (board[r][c] == 'E') {
                            q.add(new int[] { r, c });
                            board[r][c] = 'B';
                        }
                    }
                }
            } else { //if there is no mine adjacent to the curr cell, change the curr cell val to total mines encountered around it 
                board[curr[0]][curr[1]] = (char) (cnt + '0');
            }

        }
        return board;
    }

    private int countMines(char[][] board, int i, int j, int[][] dirs) {
        int res = 0;
        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];

            //bounds check
            if (r >= 0 && c >= 0 && r < board.length && c < board[0].length && board[r][c] == 'M') {
                res++;
            }
        }
        return res;
    }
}