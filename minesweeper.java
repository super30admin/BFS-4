/*
Time Complexity : O(m * n), where m is the number of rows and n is the number of columns in the board.
Space Complexity : O(m * n)
*/
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        
        if(board == null || board.length == 0)
            return board;
        
        int rowLen = board.length;
        int colLen = board[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(click);

        while(!queue.isEmpty()) {
            int[] cell = queue.poll();
            int row = cell[0], col = cell[1];

            if(board[row][col] == 'M') {
                board[row][col] = 'X';
            } else {
                int count = 0;
                for(int[] n : getNeighbors(row, col, rowLen, colLen))
                    if(board[n[0]][n[1]] == 'M' || board[n[0]][n[1]] == 'X')
                        count++;
                        
                if(count > 0) {
                    board[row][col] = (char) (count + '0');
                } else {
                    board[row][col] = 'B'; 
                    for(int[] n : getNeighbors(row, col, rowLen, colLen))
                        if(board[n[0]][n[1]] == 'E') {
                            board[n[0]][n[1]] = 'B';
                            queue.offer(n);
                        }
                }
            }
        }
        return board;
    }

    public List<int[]> getNeighbors(int row, int col, int rowLen, int colLen) {
        List<int[]> neigh = new ArrayList<>();

        for(int i = -1; i <= 1; i++)
            for(int j = -1; j <= 1; j++) {
                if(i == 0 && j == 0)
                    continue;
                int newRow = row + i, newCol = col + j;
                if(newRow < 0 || newCol < 0 || newRow >= rowLen || newCol >= colLen)
                    continue;
                neigh.add(new int[] {newRow, newCol});
            }
        
        return neigh;
    }
}
