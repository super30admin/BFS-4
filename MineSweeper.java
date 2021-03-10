class Solution {
    int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1},{1,-1},{-1,1},{-1,-1},{1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {
//if the click index is a mine then mark it X and return the board
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int r=0;
        int c=0;
//Initialize a queue and add the click index to start with
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0], click[1]});
        while(!q.isEmpty()){
            int[] arr = q.poll();
//get the total number of adjacent mines
            int mines = findMines(board, arr[0], arr[1]);
//if number of adjacent mines is 0 then add the adjacent cells which are empty to queue and mark the curr cell as B
            if(mines == 0){
                for(int[] dir : dirs){
                    r = dir[0]+arr[0];
                    c = dir[1]+arr[1];
                    if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[arr[0]][arr[1]] == 'E')
                    q.add(new int[]{r,c});
                }
                board[arr[0]][arr[1]] = 'B';
            }
//else assing the number of adjacent mines to current cell 
            else{
                board[arr[0]][arr[1]] = (char)(mines+'0');
            }
        }
        return board;
    }
    public int findMines(char[][] board, int row, int col){
//check all the 8 different directions and increment count if amine is found, finally return count
        int count = 0;
        for(int[] dir : dirs){
            int r = dir[0]+row;
            int c = dir[1]+col;
            if(r>=0 && c>=0 && r<board.length && c<board[0].length && board[r][c] == 'M')
                count++;
        }
        return count;
    }
}

//Time complexity : O(M*N)
//Space complexity : O(M*N)
