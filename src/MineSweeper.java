// Time Complexity:  O(m*n)
// Space Complexity: O(m*n)


// **************************************** BFS Approach ****************************************

class Solution {

    int[][] dirs;

    public char[][] updateBoard(char[][] board, int[] click) {
        
        if(board==null || board.length==0) return board;                                              // null board
        int m = board.length;       
        int n = board[0].length;
        dirs = new int[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};    // directions
        
        if(board[click[0]][click[1]]=='M') {                                                          // if clicked on mine
            board[click[0]][click[1]]='X';                                                            // make it revealed
            return board;                                                                             // and return board
        }

        Queue<int[]> q = new LinkedList<>();                                                          // else make queue
        board[click[0]][click[1]] = 'B';                                                              // make that empty box revealed
        q.add(click);                                                                                 // add it in queue

        while(!q.isEmpty()) {
            int[] curr = q.poll();                                                                    // take all indexes one by one
            int neighborMines = countMines(board, curr[0], curr[1]);
            if(neighborMines > 0) {
                board[curr[0]][curr[1]] = (char)(neighborMines+'0');
            }
            else {
                for(int[] dir : dirs) {
                    int newI = curr[0] + dir[0];
                    int newJ = curr[1] + dir[1];
                    if(newI>=0 && newI<m && newJ>=0 && newJ<n && board[newI][newJ]=='E') {
                        board[newI][newJ] = 'B';
                        q.add(new int[]{newI, newJ});
                    }
                }
            }
        }

        return board;

    }

    private int countMines(char[][] board, int i, int j) {
        int count = 0;
        for(int[] dir : dirs) {
            int newI = i + dir[0];
            int newJ = j + dir[1];
            if(newI>=0 && newI<board.length && newJ>=0 && newJ<board[0].length && board[newI][newJ]=='M')
                count++;
        }
        return count;
    }

}






// // **************************************** DFS Approach ****************************************

// class Solution {

//     int[][] dirs;

//     public char[][] updateBoard(char[][] board, int[] click) {
        
//         if(board==null || board.length==0) return board;                                              // null board
//         int m = board.length;       
//         int n = board[0].length;
//         dirs = new int[][] {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};    // directions
        
//         if(board[click[0]][click[1]]=='M') {                                                          // if clicked on mine
//             board[click[0]][click[1]]='X';                                                            // make it revealed
//             return board;                                                                             // and return board
//         }

//         dfs(board, click[0], click[1]);

//         return board;

//     }

//     private void dfs(char[][] board, int i, int j) {

//         if(i<0 || i==board.length || j<0 || j==board[0].length || board[i][j]!='E') 
//             return;

//         board[i][j] = 'B';

//         int neighborMines = countMines(board, i, j);
//         if(neighborMines > 0) {
//             board[i][j] = (char)(neighborMines+'0');
//         }
//         else {
//             for(int[] dir : dirs) {
//                 int newI = i + dir[0];
//                 int newJ = j + dir[1];
//                 dfs(board, newI, newJ);
//             }
//         }

//     }

//     private int countMines(char[][] board, int i, int j) {
//         int count = 0;
//         for(int[] dir : dirs) {
//             int newI = i + dir[0];
//             int newJ = j + dir[1];
//             if(newI>=0 && newI<board.length && newJ>=0 && newJ<board[0].length && board[newI][newJ]=='M')
//                 count++;
//         }
//         return count;
//     }

// }
