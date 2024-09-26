class Solution {

    int [][] dirs = {{0,1},{0,-1},{1,0},{-1,0},{1,1},{1,-1}, {-1,1},{-1,-1}};

    public char[][] updateBoard(char[][] board, int[] click) {

        int i = click[0];

        int j = click[1];

        if(board[i][j]=='M'){

            board[i][j]='X';

            return board;

        }

        dfs(board,i,j);

        return board;

    }

    public void dfs(char[][] board, int i, int j){

        //base case

        if( i<0 || j<0 || i>=board.length || j>=board[0].length || board[i][j]!='E' ) return;

        int mines=getMines(board,i,j);

        if(mines==0){

            board[i][j]='B';

            for(int k=0; k<dirs.length; k++){

                int row = i+dirs[k][0];

                int col = j+dirs[k][1];

                dfs(board,row,col);

            }

        }

        else{

            board[i][j]=(char)(mines+'0');

        }

    }

    public int getMines(char[][] board, int i, int j){

        int count=0;

        for(int k=0; k<dirs.length; k++){

                int row = i+dirs[k][0];

                int col = j+dirs[k][1];

                if( row<0 || col<0 || row>=board.length || col>=board[0].length ) continue;

                if(board[row][col]=='M')count++;

            }

        return count;

    }

}



