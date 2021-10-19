using System;
using System.Collections.Generic;
using System.Text;

namespace BFSnDFS
{
    class SnakeAndLaddersLC
    {
        //TC: O(n^2)
        //SC: O(n^2)
        public int SnakesAndLadders(int[][] board)
        {
            if (board == null || board.Length == 0) return - 1;
            int n = board.Length;
            int[] moves = new int[n * n];
            int i = n - 1, j = 0;
            int idx = 0;
            int even = 0;
            while (i >= 0 && j >= 0)
            {
                if (board[i][j] == -1)
                {
                    moves[idx] = board[i][j];
                }
                else
                {
                    moves[idx] = board[i][j] - 1;
                }
                idx++;
                if (even % 2 == 0)
                {
                    j++;
                    if (j == n)
                    {
                        j--;
                        even++;
                        i--;
                    }
                }
                else
                {
                    j--;
                    if (j < 0)
                    {
                        j++;
                        even++;
                        i--;
                    }
                }
            }
            Queue<int> q = new Queue<int>();
            int moveCount = 0;
            q.Enqueue(0);
            moves[0] = -2;
            while (q.Count != 0)
            {
                int size = q.Count;
                for (int k = 0; k < size; k++)
                {
                    int curr = q.Dequeue();
                    if (curr == n * n - 1) return moveCount;
                    for (int l = 1; l <= 6; l++)
                    {
                        int pos = curr + l;
                        if (moves[pos] != -2)
                        {
                            if (moves[pos] == -1)
                            {
                                moves[pos] = -2;
                                q.Enqueue(pos);
                            }
                            else
                            {
                                q.Enqueue(moves[pos]);
                                moves[pos] = -2;
                            }
                        }
                    }
                }
                moveCount++;
            }
            return -1;
        }
    }
}
