class Solution {
    //Time O(N^2)
    //Space O(N^2)
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0)
        {
            return 0;
        }
        int n = board.length;
        int[] ar = new int[n*n];
        int index = 0 , i = n-1 , j = 0 , even = 0;
        while(i >= 0 && j >= 0)
        {
            if(board[i][j] == -1)
            {
                ar[index] = -1;
            }
            else
            {
                ar[index] = board[i][j]-1;
            }
            index++;
            if(even % 2 == 0)
            {
                j++;
                if(j == n)
                {
                    j = n-1;
                    i--; even++;
                }
            }
            else
            {
                j--;
                if(j < 0)
                {
                    j = 0;
                    i--; even++;
                }
            }
        }
        int minMoves = 0;
        Queue<Integer> Q = new LinkedList<>();
        Q.add(0);
        ar[0] = -2;
        while(!Q.isEmpty())
        {
            int size = Q.size();
            for(int k = 0; k < size; k++)
            {
                int curr = Q.poll();
                if(curr == ar.length-1)
                {
                    return minMoves;
                }
                for(int l = 1; l <= 6; l++)
                {
                    int nextMove = l + curr;
                    if(nextMove >= ar.length) break;
                    if(ar[nextMove] != -2)
                    {
                        if(ar[nextMove] == -1)
                        {
                            Q.add(nextMove);
                        }
                        else
                        {
                            Q.add(ar[nextMove]);
                        }
                        ar[nextMove] = -2;
                    }
                }
            }
            minMoves++;
        }
        return -1;
    }
}