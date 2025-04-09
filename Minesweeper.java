/*
 * TC: O( m * n )
 * SC: O( m * n )
 */
import java.util.ArrayDeque;
import java.util.Queue;

public class Minesweeper {

    private record Pos(int x, int y) {
    }

    // The 8 possible directions to check for adjacent mines
    static final Pos[] DIRS = {new Pos(-1, -1), new Pos(0, -1), new Pos(1, -1), new Pos(1, 0), new Pos(1, 1), new Pos(0, 1), new Pos(-1, 1), new Pos(-1, 0)};

    public char[][] updateBoard(char[][] board, int[] click) {
        Pos clickPos = new Pos(click[0], click[1]);
        // If the clicked cell was a mine, mark it with 'X'
        // and return the board
        if (board[clickPos.x][clickPos.y] == 'M') {
            board[clickPos.x][clickPos.y] = 'X';
            return board;
        }

        // if the clicked cell had adjacent mines, mark it with the number of adjacent mines
        // and return the board
        int adjacentMines = checkNeighbors(board, clickPos);
        if (adjacentMines > 0) {
            board[clickPos.x][clickPos.y] = (char) (adjacentMines + '0');
            return board;
        }

        // If the clicked cell was empty, we need to perform a BFS to reveal all adjacent cells

        // Initialize a queue for BFS and a visited array to keep track of visited cells
        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<Pos> pq = new ArrayDeque<>();

        // Mark the clicked cell as visited and add it to the queue
        visited[clickPos.x][clickPos.y] = true;
        pq.add(clickPos);

        while (!pq.isEmpty()) {
            Pos curr = pq.poll();

            // Check the number of adjacent mines for the current cell
            adjacentMines = checkNeighbors(board, curr);

            // If there are no adjacent mines, mark the cell as 'B'
            // and add all adjacent cells to the queue
            if (adjacentMines == 0) {
                board[curr.x][curr.y] = 'B';
                for (Pos dir : DIRS) {
                    int nx = curr.x + dir.x;
                    int ny = curr.y + dir.y;
                    if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || visited[nx][ny] || board[nx][ny] != 'E')
                        continue;
                    visited[nx][ny] = true;
                    pq.add(new Pos(nx, ny));
                }
            }
            // If there are adjacent mines, mark the cell with the number of adjacent mines
            else {
                board[curr.x][curr.y] = (char) (adjacentMines + '0');
            }
        }
        return board;
    }

    // Helper function to check the number of adjacent mines for a given cell
    int checkNeighbors(char[][] board, Pos pos) {
        int count = 0;
        for (Pos dir : DIRS) {
            int nx = pos.x + dir.x;
            int ny = pos.y + dir.y;
            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) continue;
            count += board[nx][ny] == 'M' ? 1 : 0;
        }
        return count;
    }
}
