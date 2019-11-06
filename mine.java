//Time Complexity:O(N) where N is the size of the 2D matrix
//Space Complexity:O(N)
//Approach- First step is to check if the user has clicked on the mine, if so, update that element to X and return. Next is to recursively traverse through the given click to reveal the Empty squares or modify their values. This can be done by traversing through the length of the board and only to those indices whose value is E. and then check the number of adjacent mines in all 8 directions. If the count is greater than 0, update the value of the index to that value, else, simply mark it as B and continue to traverse in all 8 directions, until we reach a point where we can not traverse through any of the E's.
//This code was executed successfully and got accepted in leetcode.

class Solution {
    
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        dfs(board,click[0],click[1]);
        return board;
    }
    public void dfs(char[][] board, int i, int j){
        if(i<0||i>=board.length||j<0||j>=board[0].length||board[i][j]!='E'){
            return;
        }
        int startRow=i-1 < 0 ? 0 : i-1;
        int endRow= i+1 >= board.length ? board.length-1 : i+1;
        int startCol=j-1 < 0 ? 0 : j-1;
        int endCol=j+1 >= board[0].length ? board[0].length-1 : j+1;
        int mine=0;
        for(int m=startRow;m<=endRow;m++){
            for(int n=startCol;n<=endCol;n++){
                if(board[m][n]=='M'||board[m][n]=='X'){
                    mine++;
                }
            }
        }
        if(mine>0){
            board[i][j]=(char)(mine+'0');
        }
        else{
            board[i][j]='B';
            dfs(board,i,j-1);
            dfs(board,i,j+1);
            dfs(board,i-1,j);
            dfs(board,i+1,j);
            dfs(board,i-1,j-1);
            dfs(board,i-1,j+1);
            dfs(board,i+1,j-1);
            dfs(board,i+1,j+1);
        }
    }
}