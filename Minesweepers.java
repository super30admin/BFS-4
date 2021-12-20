//Time complexity:- O(n*m);
//space complexity:-O(1);
//Did it run on leetcode:- Yes.


class Solution {
    private static final int[][] DIRS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

    public char[][] updateBoard(char[][] board, int[] click) {
        if (board == null || click == null) {
            throw new IllegalArgumentException("Inputs are null");
        }
        if (board[click[0]][click[1]] != 'M' && board[click[0]][click[1]] != 'E') {
            return board;
        }
        if (board[click[0]][click[1]] == 'M') {  //Checking validity of initial click.
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int mines = getMinesCount(board, click[0], click[1]);
        if (mines != 0) {
            board[click[0]][click[1]] = (char) (mines + '0');
            return board; //At first checking surroundings, how many mines are there and depending on that changing that 
                          //value and adding to queue our starting index.
        }
        board[click[0]][click[1]] = 'B';

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : DIRS) {
                int x = cur[0] + d[0]; //Applying bfs where elements in directed arrays,position of that elements with no mines in surroundings are added to queue as children.
                int y = cur[1] + d[1];
                if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E') {
                    continue;
                }
                mines = getMinesCount(board, x, y);
                if (mines != 0) {
                    board[x][y] = (char) (mines + '0');
                    continue;
                }
                board[x][y] = 'B';
                queue.offer(new int[] { x, y });
            }
        }

        return board;
    }

    private int getMinesCount(char[][] board, int x, int y) {
        int mines = 0;
        for (int[] d : DIRS) {
            int r = x + d[0];
            int c = y + d[1];
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'M') {
                mines++;
            }
        }
        return mines;
    }
}