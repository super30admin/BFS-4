//https://leetcode.com/problems/minesweeper/
/*
Time: O(M*N) where M=rows and N=columns
Space: O(M*N) 
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/
public class Minesweeper {
    // define directions
    int[] dx = new int[] { 0, -1, 0, 1, 1, -1, 1, -1 };
    int[] dy = new int[] { 1, 0, -1, 0, 1, -1, -1, 1 };

    public char[][] updateBoard(char[][] board, int[] click) {

        int clickX = click[0];
        int clickY = click[1];

        // 1. If a mine 'M' is revealed, then the game is over. You should change it to
        // 'X'.
        if (board[clickX][clickY] == 'M') {
            board[clickX][clickY] = 'X';
            return board;
        }

        int m = board.length;
        int n = board[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<int[]> q = new LinkedList<>();

        q.add(click); // add click to q
        visited[clickX][clickY] = true; // mark click as visited

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            int adjacentMines = getAdjacentMines(board, x, y);

            // 2.If an empty square 'E' with no adjacent mines is revealed, then change it
            // to a revealed blank 'B' and all of its adjacent unrevealed squares should be
            // revealed recursively.
            if (adjacentMines == 0) {
                board[x][y] = 'B';

                for (int i = 0; i < 8; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && ny >= 0 && nx < board.length && ny < board[0].length && !visited[nx][ny]
                            && board[nx][ny] == 'E') {
                        q.offer(new int[] { nx, ny });
                        visited[nx][ny] = true;
                    }
                }

            }

            // 3.If an empty square 'E' with at least one adjacent mine is revealed, then
            // change it to a digit ('1' to '8') representing the number of adjacent mines.
            else {
                board[x][y] = (char) (adjacentMines + '0');
            }

        }

        // 4.Return the board when no more squares will be revealed
        return board;

    }

    int getAdjacentMines(char[][] board, int x, int y) {
        int count = 0;

        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx == x && ny == y) // ignore
                continue;

            // if you see a Mine
            if (nx >= 0 && nx < board.length && ny >= 0 && ny < board[0].length && board[nx][ny] == 'M')
                count++;

        }

        return count;
    }

}
