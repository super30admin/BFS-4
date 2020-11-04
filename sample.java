// Problem 1 - Minesweeper
// Time Complexity: O(n * m) where n = rows and m = cloumns
// Space Complexity: O(n * m) extra space for recursive stack

// Code with approach
class Solution {
    
    // creating directions array
    private int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    
    public char[][] updateBoard(char[][] board, int[] click) {
        // base  condition
        if (board == null || board.length == 0 || board[0].length == 0 || click == null || click.length == 0) {
            return board;
        }
        
        int i = click[0]; int j = click[1];
        
        // click is at mine
        if (board[i][j] == 'M') {
            board[i][j] = 'X';
            return board;
        }
        // call to dfs
        dfs(board, i, j);
        
        return board;
    }
    
    private void dfs(char[][] board, int i, int j) {
        // base condition
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'E' ) {
            return;
        }
        // mines surrounding the element
        int mines = getMines(board, i, j);

        if(mines == 0) { // if none mines
            board[i][j] = 'B';

            for (int[] dir : dirs) { // perform dfs on eight elements surrounding current element
                int r = i + dir[0];
                int c = j + dir[1];

                dfs(board,r,c);
            }
        } else { // if mines present
            board[i][j] = (char)(mines + '0');
        }
    }
    
    // to get mines count
    private int getMines(char[][] board, int i, int j) {
        int count = 0;
        
        for (int[] dir : dirs) {
            int r = i + dir[0];
            int c = j + dir[1];
            
            if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'M') {
                count += 1;                
            }
        }
        
        return count;
    }
}

// Problem 2 - Snakes and Ladder
// Time Complexity: O(n * m) where n = row/column count
// Space Complexity: O(n * m) -> O(n * n) where n = row/column count

// Code with approach
class Solution {
    public int snakesAndLadders(int[][] board) {
        // base
        if(board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }

        // initializing pointers
        int n = board.length * board[0].length;
        int[] moves = new int[n];
        int r = board.length; int c = board[0].length;
        int i = r - 1; int j = 0; int k = 0;
        int even = 0;

        // creating moves array
        while(i >= 0 && j >= 0){
            // Entering moves into the array
            if(board[i][j] != -1) {
                moves[k] = board[i][j] - 1;
            } else {
                moves[k] = -1;
            }

            k += 1;

            // incrementing or decrementing j based on direction
            if (even % 2 == 0) {
                j += 1;
            } else {
                j -= 1;
            }

            // out of bounds
            if (j >= c) {
                i -= 1;
                j -= 1;
                even += 1;
            } else if (j < 0) {
                i -= 1;
                j += 1;
                even += 1;
            }
        }

        int minCount = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        moves[0] = -2;

        // performing bfs
        while (!queue.isEmpty()) {
            int size = queue.size();

            for(int x=0; x<size; x++) {
                // getting current index
                int curr = queue.poll();
                // if reached destination
                if (curr == n - 1) {
                    return minCount;
                }
                // looping over next 6 possible moves
                for(int y=1; y<=6; y++) {
                    int next = y + curr;
                    // check for outof bounds and not visited
                    if(next < n && moves[next] != -2){
                        if(moves[next] > -1){
                            queue.add(moves[next]);
                        } else {
                            queue.add(next);
                        }
                        // changing the move to visited
                        moves[next] = -2;
                    }
                }
            }

            // incrementing minCount based on levels
            minCount += 1;

        }

        return -1;
    }
}
