// Time Complexity : The time complexity is O(m*n) where m is the number of rows and n is the number of column
// Space Complexity : The space complexity is O(m*n) where m is the number of rows and n is the number of column
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach

class Solution {

    int[][] dirs = {{1,0},{0,1},{-1,0},{0,-1},{-1,-1},{1,1},{1,-1},{-1,1}};

    public char[][] updateBoard(char[][] board, int[] click) {

        int rows = board.length;
        int columns = board[0].length;

        Queue<Integer> q = new LinkedList<>();
        q.offer(click[0]);
        q.offer(click[1]);

        if(board[click[0]][click[1]] == 'E'){
            board[click[0]][click[1]] = 'B';
        }

        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        while(!q.isEmpty()){

            int row = q.poll();
            int column = q.poll();

            int count = mines(board,row,column);

            //if the cell has adjacent mines
            if(count != 0){
                board[row][column] = (char)(count+'0');
                continue;
            }

            //if the cell doesnt have adjacent mines
            for(int[] dir:dirs){

                int nextRow = row + dir[0];
                int nextColumn = column + dir[1];

                if(nextRow>=0 && nextRow<rows && nextColumn>=0 && nextColumn<columns){
                    if(board[nextRow][nextColumn] == 'E'){
                        q.offer(nextRow);
                        q.offer(nextColumn);
                        board[nextRow][nextColumn] = 'B';
                    }
                }
            }
        }
        return board;
    }

    public int mines(char[][] board,int row,int column){

        int count = 0;
        int rows = board.length;
        int columns = board[0].length;

        for(int[] dir:dirs){

            int nextRow = row + dir[0];
            int nextColumn = column + dir[1];

            if(nextRow>=0 && nextRow<rows && nextColumn>=0 && nextColumn<columns){
                if(board[nextRow][nextColumn] == 'M'){
                    count++;
                }
            }
        }

        return count;
    }
}