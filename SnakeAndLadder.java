/**
Time Complexity - O(n) where n is the number of cells in the board.  
Space Complexity - O(n).
 */
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0 || board.length == 1)
            return 0;
        
        int n = board[0].length;
        int[] dist = new int[n * n + 1];
        Arrays.fill(dist, -1);
        dist[1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);

        Pair<Integer, Integer>[] cellIndex = new Pair[n * n + 1];
        int label = 1, isOdd = 1;
        for(int row = n - 1; row >= 0; row--) {
            if(isOdd == 1)
                for(int col = 0; col < n; col++)
                    cellIndex[label++] = new Pair<>(row, col);
            else
                for(int col = n - 1; col >= 0; col--)
                    cellIndex[label++] = new Pair<>(row, col);
            isOdd = isOdd ^ 1;
        }
            
        while(!queue.isEmpty()) {
            Integer cur = queue.poll();
            for(int next = cur + 1; next <= Math.min(cur + 6, n * n); next++) {
                int nextRow = cellIndex[next].getKey(), nextCol = cellIndex[next].getValue();
                int nextHop = board[nextRow][nextCol] != -1 ? board[nextRow][nextCol] : next;
                if(dist[nextHop] == -1) {
                    dist[nextHop] = dist[cur] + 1;
                    queue.add(nextHop);
                }
            }
        }
        return dist[n * n];
    }
}
