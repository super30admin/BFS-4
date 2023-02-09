//Time Complexity: O(m*n)
//Space Complexity: O(m*n)

/*
 * we move through the board from the click position. add it ot the queue.
 * if there are antany mines arount it count the number and replace that value.
 * else move in all 8 directions and add the children. and mark it visited as B.
 * return the board
 */

 class Solution {
    int[][] dirs;
    int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        int m = board.length;
        int n = board[0].length;
        dirs = new int[][]{{0,1}, {1,0}, {-1,0}, {0,-1}, {-1,-1}, {-1,1}, {1,-1}, {1,1}};

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(click[0]);
        q.add(click[1]);
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            int cr = q.poll();
            int cc = q.poll();
            int count = countMines(board, cr, cc, m, n);
            if(count == 0){
                for(int[] dir: dirs){
                    int nr = cr + dir[0];
                    int nc = cc + dir[1];
                    if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'E'){
                        q.add(nr);
                        q.add(nc);
                        board[nr][nc] = 'B';
                    }
                }
            }
            else{
                board[cr][cc] = (char) (count + '0');
            }
        }
        return board;
    }

    public int countMines(char[][] board, int i, int j, int m, int n){
        int count = 0;
        for(int[] dir: dirs){
            int nr = i + dir[0];
            int nc = j + dir[1];
            if(nr >= 0 && nc >= 0 && nr < m && nc < n && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}