import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class MineSweeperBFS {

        // BFS - with no level size variable - Time O(m*n) and Space O(m*n)

        // global
        int[][] dirs = new int[][] {{1,0}, {-1, 0}, {0, 1}, {0, -1}, {-1, 1}, {-1, -1}, {1, 1}, {1, -1}};

        int m, n;
        char[][] board;

        public char[][] updateBoard(char[][] board, int[] click) {

            this.board = board;
            m = board.length;             n = board[0].length;

            // if click has mine, make it revealed and return board as game is over
            if(board[click[0]][click[1]] == 'M') {

                board[click[0]][click[1]] = 'X';
                return board;
            }

            // queue of positions
            Queue<int[]> q = new LinkedList<>();

            // add click to queue and make it B as it is visited
            q.add(click);
            board[click[0]][click[1]] = 'B';

            // bfs
            while(!q.isEmpty()) {

                int[] curr = q.poll();

                // count number of adjacent mines
                int currCount = countAdjMines(curr);

                // if there are adjacent mines to current position, update board value
                if(currCount > 0) {

                    board[curr[0]][curr[1]] = (char)(currCount + '0');
                }

                // if current position has no adjacent mines, process its neighbors
                else {

                    for(int[] dir: dirs) {

                        int nr = curr[0] + dir[0];
                        int nc = curr[1] + dir[1];

                        // make new position with empty square visited as B and add to queue
                        if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E') {

                            board[nr][nc] = 'B';
                            q.add(new int[]{nr, nc});
                        }
                    }
                }
            }
            // output
            return board;

        }

        public int countAdjMines(int[] position) {

            int r = position[0];
            int c = position[1];
            int count = 0;

            // check in 8 directions
            for(int[] dir: dirs) {

                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M') {

                    // increment count if mines are found
                    count++;
                }
            }
            // return to where it is called in bfs
            return count;
        }

        public static void main(String[] args) {

            MineSweeperBFS obj = new MineSweeperBFS();

            Scanner scanner = new Scanner(System.in);

            System.out.println("click: ");
            int i = scanner.nextInt();
            int j = scanner.nextInt();
            int[] click = new int[] {i, j};

            System.out.println("board height: ");
            int m = scanner.nextInt();
            System.out.println("board width: ");
            int n = scanner.nextInt();

            char[][] board = new char[m][n];

            for(int k = 0; k < m; k++) {
                System.out.println("board row " + (k + 1) + ": ");
                for(int l = 0; l < n; l++) {

                    board[k][l] = scanner.next().charAt(0);
                }
            }

            char[][] answer = obj.updateBoard(board, click);

            System.out.println("board after the game: ");
            for(int k = 0; k < m; k++) {
                for(int l = 0; l < n; l++) {

                    System.out.print(answer[k][l] + " ");
                }
                System.out.println(" ");
            }

        }


}

/*
Time Complexity = O(m*n)
Space Complexity = O(m*n)
*/