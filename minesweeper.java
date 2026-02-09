/**
TC: O(n) : each cell is visited once.
SC: O(n) worst case size of the queue.
Approach: Apply BFS and perform operations stated in the question.
 */

class Solution {
    int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1},{-1,-1},{1,1},{-1,1},{1,-1}};
    //int[][] dirs4 = {{0,1},{1,0},{},{}};
    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(click);

        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i< size; i++){
                int[] curr = q.poll();
                int r = curr[0];
                int c= curr[1];

                if(board[r][c] == 'M'){
                    board[r][c] = 'X';
                    return board;
                }
                else if (board[r][c] == 'E'){
                    int adjMines = 0;

                    for(int[] dir : dirs){
                        int newR = r + dir[0];
                        int newC = c + dir[1];

                        if(newR >= 0 && newC >= 0 && newR < board.length && newC < board[0].length && board[newR][newC] == 'M')
                            adjMines++;
                    }

                    if(adjMines > 0)
                        board[r][c] = (char)(adjMines + '0');
                    else{
                        board[r][c] = 'B';
                        for(int[] dir : dirs){
                             int newR = r + dir[0];
                             int newC = c + dir[1];

                        if(newR >= 0 && newC >= 0 && newR < board.length && newC < board[0].length)
                            q.offer(new int[] {newR, newC});
                        }
                    }
                }
            }
        }
     return board;
    }
}