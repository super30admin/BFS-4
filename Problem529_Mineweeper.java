/**
 * Time Complexity - O(m*n)
 * Space Complexity - O(m*n)
 */
class Solution {
    private int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},
            {-1,-1},{1,-1},{-1,1}};
    int m,n;
    char[][] board;
    public char[][] updateBoard(char[][] board, int[] click) {

        this.board = board;

        m = board.length;
        n = board[0].length;

        /**
         * If a mine ('M') is revealed, then the game is over - change it to 'X'.
         */
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        return dfs(click);
        // return bfs(click);
    }

    private char[][]  dfs(int[] click){

        board[click[0]][click[1]] = 'B';

        int mines = getMines(click);

        if(mines == 0){

            for(int[] dir : dirs){

                int r = click[0] + dir[0];
                int c = click[1] + dir[1];

                /**
                 * If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B')
                 * and all of its adjacent unrevealed squares should be revealed recursively.
                 */
                if(r >=0 && r < m && c>=0 && c<n && board[r][c] == 'E'){
                    dfs(new int[]{r,c});
                }
            }
        }else{
            /**
             * If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8')
             * representing the number of adjacent mines.
             */
            board[click[0]][click[1]] = (char)(mines + '0');
        }
        return board;
    }

    private char[][] bfs(int[] click){

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {click[0], click[1]});
        board[click[0]][click[1]] = 'B';

        while(!queue.isEmpty()){

            int[] curr = queue.poll();

            int mines = getMines(curr);


            if(mines == 0){

                for(int[] dir : dirs){

                    int r = curr[0] + dir[0];
                    int c = curr[1] + dir[1];

                    /**
                     * If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B')
                     * and all of its adjacent unrevealed squares should be revealed recursively.
                     */
                    if(r >=0 && r < m && c>=0 && c<n && board[r][c] == 'E'){
                        queue.add(new int[] {r,c});
                        board[r][c] = 'B';
                    }
                }
            }else{
                /**
                 * If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8')
                 * representing the number of adjacent mines.
                 */
                board[curr[0]][curr[1]] = (char)(mines + '0');
            }
        }
        return board;
    }

    private int getMines(int[] curr){
        int count = 0;
        for(int[] dir : dirs){

            int r = curr[0] + dir[0];
            int c = curr[1] + dir[1];

            if(r >=0 && r < m && c>=0 && c<n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}