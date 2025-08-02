class Solution {
    int[][] dirs = new int[][]{
        {0, 1}, {1, 0}, {-1, 0}, {0, -1},{1, -1}, {-1, 1}, {1, 1}, {-1, -1}
    };
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<Integer> q = new LinkedList<>();
        q.add(click[0]);
        q.add(click[1]);
        visited[click[0]][click[1]] = true;
        while (!q.isEmpty()) {
            int r = q.poll();
            int c = q.poll();
            int adjMines = count(r, c, board);
            if (adjMines > 0) {
                board[r][c] = Character.forDigit(adjMines, 10);
            }else{
                board[r][c] = 'B';
                for(int[] dir : dirs){
                    int newR = r + dir[0];
                    int newC = c + dir[1];
                    
                    if(newR < board.length && newR >= 0 && 
                    newC < board[0].length && newC >= 0){
                        if (board[newR][newC] == 'E') {
                            if (!visited[newR][newC]) {
                                visited[newR][newC] = true;
                                q.add(newR);
                                q.add(newC);
                            }
                        }
                    }
                }
            }
        }
        return board;
    }

    private int count(int r, int c, char[][] board){
        int result = 0;
        for(int[] dir : dirs){
            int newR = r + dir[0];
            int newC = c + dir[1];
            if(newR < board.length && newR >= 0 && 
            newC < board[0].length && newC >= 0 && 
            board[newR][newC] == 'M')
            {
                result++;
            }
        }
        return result;
    }
}
