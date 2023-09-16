// Time Complexity : O(m*n)
// Space Complexity :O(m*n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
//direction array
    int[][] dirs = new int[][]{{0,-1} , {-1,-1} , {-1,0} , {-1,1} , {0,1} , {1,1} , {1,0} , {1,-1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        //bfs
        while(!q.isEmpty()){
            int[] sq = q.poll();
            //if the square is a mine, return board and mark X
            if(board[sq[0]][sq[1]] == 'M'){
                board[sq[0]][sq[1]] = 'X';
                return board;
            }
            //if square is E, get mines
            //If neighboring mines are 0, then put neighbors in queue
            //else put the number of mines in square
            if(board[sq[0]][sq[1]] == 'E'){
                int mines = mines(board, sq);
                if(mines == 0){
                    board[sq[0]][sq[1]] = 'B';
                    for(int[] dir: dirs){
                        int m = sq[0] + dir[0];
                        int n = sq[1] + dir[1];
                        if(m >=0 && m < board.length && n >= 0 && n < board[0].length && board[m][n] == 'E'){
                            q.add(new int[]{m,n});
                        }
                    }
                }
                else{
                    board[sq[0]][sq[1]] = (char)('0' + mines);
                }
            }
            
        }
        return board;
    }
//calculate the number of mines
    private int mines(char[][] board, int[] click){
        int count = 0;
        for(int[] dir: dirs){
            int m = click[0] + dir[0];
            int n = click[1] + dir[1];
            if(m >=0 && m < board.length && n >= 0 && n < board[0].length && board[m][n] == 'M'){
                count++;
            }
        }

        return count;
    }
}