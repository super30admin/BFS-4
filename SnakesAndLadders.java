class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];
        Queue<int[]> queue = new LinkedList<>(); 
        queue.offer(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cell = curr[0], steps = curr[1];

            if (cell == n * n) return steps;

            for (int i = 1; i <= 6 && cell + i <= n * n; i++) {
                int next = cell + i;
                int[] pos = getPosition(next, n);
                int val = board[pos[0]][pos[1]];

                if (val != -1) next = val;

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, steps + 1});
                }
            }
        }

        return -1;
    }

    private int[] getPosition(int cell, int n) {
        int row = n - 1 - (cell - 1) / n;
        int col = (cell - 1) % n;
        if (((n - row) % 2) == 0) col = n - 1 - col; 
        return new int[]{row, col};
    }
}
