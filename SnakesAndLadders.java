//https://leetcode.com/problems/snakes-and-ladders/
/*
Time: O(n^2) where n = board.length
Space: O(n^2)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : None
*/
public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0)
            return -1;

        int n = board.length;
        int[] moves = new int[n * n];
        int i = n - 1, j = 0; // bottom-left is the start position
        int idx = 0; // idx for the moves array
        int rowNumber = 0; // to count the no: of rows

        while (i >= 0 && j >= 0) {
            if (board[i][j] == -1) // normal cell
                moves[idx] = -1;

            else
                moves[idx] = board[i][j] - 1;

            idx++;

            if (rowNumber % 2 == 0) // at even row, go left to right
            {
                j++;
                if (j == n) // if you reach the end
                {
                    j--;
                    rowNumber++; // go to next row
                    i--;
                }
            } else // reverse of if block
            {
                j--;
                if (j < 0) {
                    j++;
                    rowNumber++;
                    i--;
                }
            }

        } // moves array is now set

        Queue<Integer> q = new LinkedList<>(); // BFS
        int movesCount = 0;
        q.add(0);
        moves[0] = -2; // to indicate that it is visited, -2 means visited

        while (!q.isEmpty()) {
            int size = q.size(); // store all possible results of a move at a level
            for (int k = 0; k < size; k++) {
                int curr = q.poll();
                if (curr == n * n - 1) // reached destination
                    return movesCount;

                // throw a die
                for (int l = 1; l <= 6; l++) {
                    int newPos = curr + l;
                    if (newPos > n * n - 1) // invalid index
                        break;
                    if (moves[newPos] != -2) // coming to this cell for first time here
                    {
                        if (moves[newPos] == -1) // normal cell
                        {
                            q.add(newPos);
                            moves[newPos] = -2; // visited
                        } else // there was a snake/ladder here
                        {
                            q.add(moves[newPos]);
                            moves[newPos] = -2;
                        }
                    }
                }
            }
            movesCount++; // change of level
        }

        return -1;
    }

}
