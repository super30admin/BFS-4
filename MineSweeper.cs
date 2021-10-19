using System;
using System.Collections.Generic;
using System.Text;

namespace BFSnDFS
{
    class MineSweeperLC
    {
        //BFS
        //TC: O(m *n)
        //SC: O(m *n)
        int[][] dirs;
        int m ,n;
        public char[][] UpdateBoard(char[][] board, int[] click)
        {
            if (board == null || board.Length == 0) return board;
            m = board.Length;
            n = board[0].Length;
            dirs = new int[][]{new int[]{0,1}
                            ,new int[]{0,-1}
                            ,new int[]{1,0}
                            ,new int[]{-1,0}
                            ,new int[]{-1,-1}
                            ,new int[]{1,1}
                            ,new int[]{1,-1}
                            ,new int[]{-1,1}
                            };

            if (board[click[0]][click[1]] == 'M')
            {
                board[click[0]][click[1]] = 'X';
                return board;
            }
            //BFS approach
            Queue<int[]> q = new Queue<int[]>();
            q.Enqueue(new int[] { click[0], click[1] });
            board[click[0]][click[1]] = 'B';
            while (q.Count != 0)
            {
                int[] curr = q.Dequeue();
                int mines = getMines(board, curr);
                if (mines != 0)
                {
                    board[curr[0]][curr[1]] = (char)(mines + '0');
                }
                else
                {
                    foreach (int[] dir in dirs)
                    {
                        int row = curr[0] + dir[0];
                        int col = curr[1] + dir[1];
                        if (row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 'E')
                        {
                            q.Enqueue(new int[] { row, col });
                            board[row][col] = 'B';
                        }
                    }
                }
            }
            return board;
        }

        private int getMines(char[][] board, int[] curr)
        {
            int total = 0;
            foreach (int[] dir in dirs)
            {
                int row = curr[0] + dir[0];
                int col = curr[1] + dir[1];
                if (row >= 0 && row < m && col >= 0 && col < n && board[row][col] == 'M')
                {
                    total++;
                }
            }
            return total;
        }

        //DFS

        public char[][] UpdateBoardDFS(char[][] board, int[] click)
        {
            if (board == null || board.Length == 0) return board;
            m = board.Length;
            n = board[0].Length;
            dirs = new int[][]{new int[]{0,1}
                                ,new int[]{0,-1}
                                ,new int[]{1,0}
                                ,new int[]{-1,0}
                                ,new int[]{-1,-1}
                                ,new int[]{1,1}
                                ,new int[]{1,-1}
                                ,new int[]{-1,1}
                                };

            if (board[click[0]][click[1]] == 'M')
            {
                board[click[0]][click[1]] = 'X';
                return board;
            }
            //DFS approach
            dfs(board, click[0], click[1]);
            return board;
        }

        private void dfs(char[][] board, int row, int col)
        {
            //base case
            if (row < 0 || row == m || col < 0 || col == n || board[row][col] != 'E')
            {
                return;
            }
            //logic
            board[row][col] = 'B';
            int mines = getMines(board, new int[] { row, col });
            if (mines != 0)
            {
                board[row][col] = (char)(mines + '0');
            }
            else
            {
                foreach (int[] dir in dirs)
                {
                    int nr = row + dir[0];
                    int nc = col + dir[1];
                    dfs(board, nr, nc);
                }
            }
        }

    }
}
