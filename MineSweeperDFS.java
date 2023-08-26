import java.util.Scanner;

public class MineSweeperDFS {

        // DFS - Time O(m*n) and Space O(m*n)

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

            // dfs from click
            dfs(click[0], click[1]);

            // output
            return board;

        }

        public void dfs(int r, int c) {

            //base
            if(r < 0 || c < 0 || r == m || c == n || board[r][c] != 'E') {

                return;
            }

            //logic
            //action
            board[r][c] = 'B';
            int count = countAdjMines(r, c);

            //recursion
            if(count != 0) {

                // if adjacent mines present, update board with count
                board[r][c] = (char)(count + '0');
            }
            else {
                for(int[] dir: dirs) {

                    // run over neighbours in DFS recursive manner
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    dfs(nr, nc);
                }
            }

        }

        public int countAdjMines(int r, int c) {

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

            MineSweeperDFS obj = new MineSweeperDFS();

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