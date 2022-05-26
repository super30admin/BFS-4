// Time Complexity : O(n*n) n = length, width of board
// Space Complexity : O(n*n) n = length, width of board
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {

    //using BFS

    public int snakesAndLadders(int[][] board) {

        int n = board.length;

        //we traverse the matrix and convert to 1D array, then run BFS on this
        int[] moves = new int[n*n];
        int idx=0; //index on moves

        int i=n-1; //rows
        int j=0; //cols
        int even=0; //to keep track of row direction

        while(idx < n*n)
        {
            if(board[i][j] == -1)
                moves[idx] = board[i][j];
            else
                moves[idx] = board[i][j] - 1;
            //0 based index, so instead of 15 (look at ex1) we put 14
            idx++;

            //even row, move left to right
            if(even%2==0)
            {
                j++;
                if(j==n)
                {
                    i--; //go to next row
                    j--; //come back in boundary
                    even++; //make next row odd
                }
            }
            else
            {
                j--;
                if(j == -1)
                {
                    i--; //go to next row
                    j++; //come back in boundary
                    even++; //make next row even
                }
            }
        } //moves array is now populated exactly like the matrix

        //queue for BFS will contain indexes of board sqaures in the moves 1D array
        Queue<Integer> q = new LinkedList();
        q.add(0); //add first square
        moves[0] = -2; //mark it visited, so that we don't return to it

        int result=0; //min no. of moves

        while(!q.isEmpty())
        {
            int size = q.size();
            for(int l=0; l<size; l++)
            {
                int currentSquare = q.poll();

                //reached last square
                if(currentSquare == n*n - 1) //0 based indes
                    return result;

                //create children by rolling the dice - possible no.s 1-6
                for(int k=1; k<=6; k++)
                {
                    int nextSquare =  currentSquare + k; //child

                    if(nextSquare < n*n)
                    {
                        //not visited yet
                        if(moves[nextSquare] != -2)
                        {
                            //no snake or ladder
                            if(moves[nextSquare] == -1)
                                q.add(nextSquare);
                            else
                                q.add(moves[nextSquare]); //to the square that snake/ladder moved us to

                            //mark as visited
                            moves[nextSquare] = -2;
                        }
                    }
                }
            } //level over
            result++;
        } //BFS ends

        return -1; //no moves led to last square
    }
}
