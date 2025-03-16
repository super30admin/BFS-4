public class Solution {
    // TC => O(m*n)
    // SC => O(m*n)
    int[][] dirs;
    int m, n;
    public char[][] UpdateBoard(char[][] board, int[] click) {
        if(board == null || board.Length == 0){
            return board;
        }
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dirs = [[-1,0],[1,0],[0,-1],[0,1],[-1,-1],[-1,1],[1,-1],[1,1]];
        m = board.Length;
        n = board[0].Length;

        Queue<int[]> queue = new Queue<int[]>();
        queue.Enqueue(click);
        board[click[0]][click[1]] = 'B';

        while(queue.Count > 0){
            var current = queue.Dequeue();
            board[current[0]][current[1]] = 'B';
            
            int countMines = CountMines(board, current);
            if(countMines == 0){
                
                foreach(var dir in dirs){
                var nr = current[0] + dir[0];
                var nc = current[1] + dir[1];

                if(nr < 0 || nr == m || nc < 0 || nc == n){
                    continue;
                }

                if(board[nr][nc] == 'E'){
                    board[nr][nc] = 'B';
                    queue.Enqueue(new int[2]{nr, nc});
                    }
                }
            }
            else{
                board[current[0]][current[1]] = (char)(countMines + '0');
            }
            
        }
        return board;
    }

    public int CountMines(char[][] board, int[] click){
        int count = 0;
        foreach(var dir in dirs){
            var nr = click[0] + dir[0];
            var nc = click[1] + dir[1];

            if(nr < 0 || nr == m || nc < 0 || nc == n){
                continue;
            }
            if(board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}