//TC : O(N^2)
//SC : O(N^2)


public class snakes_ladders {
    public int snakesAndLadders(int[][] board) {
        int N = board.length;
        boolean[][] visited = new boolean[N][N];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        int steps = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i=0; i<sz; i++) {
                int polled = queue.pollFirst();
                if (polled == N*N)
                    return steps;
                for (int j=1; j<=6; j++) {
                    if (polled+j > N*N)
                        break;
                    int[] coords = getCoords(polled+j, N);
                    int x = coords[0];
                    int y = coords[1];
                    if (visited[x][y])
                        continue;
                    visited[x][y] = true;
                    queue.add(board[x][y] == -1 ? polled+j : board[x][y]);
                }
            }
            steps++;
        }
        return -1;
    }
    //imp code since the rest is simple bfs
    private int[] getCoords(int num, int N) {
        int row = N - (num-1)/N - 1;
        int col = (num-1) % N;
        if (row % 2 == N % 2)
            col = N - 1 - col;
        return new int[] {row, col};
    }
    
}
