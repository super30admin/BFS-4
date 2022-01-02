class Solution {

    int xCords[] = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
    int yCords[] = new int[]{0, 1, 0, -1, 1, -1, 1, -1};
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        helper(board, click[0], click[1]);
        return board;
    }

    void helper(char[][] board, int click0, int click1){
        if(!checkValid(board.length, board[0].length, click0, click1))
            return;

        if(board[click0][click1] == 'E'){
            int neighbours = count(board, click0, click1);
            if(neighbours > 0)
                board[click0][click1] = (char)(neighbours+'0');
            else{
                board[click0][click1] = 'B';
                for(int i=0;i<8;i++)
                    helper(board, click0 + xCords[i], click1 + yCords[i]);
            }
        }
    }

    int count(char[][] board, int click0, int click1){
        int counter = 0;
        for(int i=0;i<8;i++)
            if(checkValid(board.length, board[0].length, click0+xCords[i], click1+yCords[i]) && board[click0+xCords[i]][click1+yCords[i]] == 'M')
                counter++;
        return counter;
    }

    boolean checkValid(int n, int m, int x, int y){
        if(x>=0 && x<n && y>=0 && y<m)
            return true;
        return false;
    }
}