//Time - O(N*N)
//Space - O(N*N)

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {
        int[][] dirs = new int[][]{{0,1}, {1,1}, {1,0}, {1,-1}, {-1,0}, {-1,1}, {0,-1}, {-1,-1}};
        int m = board.length; int n = board[0].length;

        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        board[click[0]][click[1]] = 'B';
        q.add(new int[]{click[0], click[1]});



        while(!q.isEmpty()){
            int[] cur = q.poll();
            int mines = getMines(board, cur[0], cur[1]);
            if(mines != 0){
                board[cur[0]][cur[1]] = (char)(mines + '0');
            } else {
                for(int[] dir: dirs){
                    int newR = cur[0] + dir[0];
                    int newC = cur[1] + dir[1];
                    if(newR >= 0 && newR < m && newC >= 0 && newC < n && board[newR][newC] == 'E'){
                        board[newR][newC] = 'B';
                        q.add(new int[]{newR, newC});
                    }
                }
            }
        }
        return board;
    }

    public int getMines(char[][] board, int r, int c){
        int result = 0;
        int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}, {1,1},  {1,-1},  {-1,1},  {-1,-1}};
        for(int[] dir: dirs){
            int newR = r + dir[0];
            int newC = c + dir[1];
            if(newR >= 0 && newR < board.length && newC >= 0 && newC < board[0].length && board[newR][newC] == 'M')
                result++;
        }
        return result;
    }
}