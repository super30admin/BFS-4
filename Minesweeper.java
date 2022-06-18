// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    
    int m,n;
    int[][] dirs;
    
    public char[][] updateBoard(char[][] board, int[] click) {
    
        if(board == null || board.length == 0) return board;
        
        m = board.length;
        n = board[0].length;
        dirs = new int[][] {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
            {1, 1},
            {-1, 1},
            {1, -1},
            {-1, -1}
        };
        
        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(click);
        board[click[0]][click[1]] = 'B';
        
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            
            int mines = countMines(board, current); 
            // System.out.println(mines + "  : " + current[0] + ", " + current[1]);
            if(mines > 0) {
                board[current[0]][current[1]] = (char)(mines + '0');
                
            } else {
                for(int[] dir: dirs) {
                    int r = current[0] + dir[0];
                    int c = current[1] + dir[1];
                    
                    if(r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'E') {
                        board[r][c] = 'B';
                        queue.add(new int[] {r, c});                        
                    }
                }
            }
        }
        return board;
    }
    
    private int countMines(char[][] board, int[] click) {
        
        int count = 0;

        for(int[] dir: dirs) {
            int r = click[0] + dir[0];
            int c = click[1] + dir[1];

            if(r >= 0 && c >= 0 && r < m && c < n && board[r][c] == 'M') {
                
                count++;
                // System.out.println(r + ", " + c + " : " + count + " = " + click[0] + ", " + click[1]);
            }
        }

        
        return count;
    }
}