/*
 * TC = O(M*N)
 * SC = O(M*N)
 * 
 * Approach: BFS traversal is done. Whenever we pop a position from a queue, we check if it is
 * a position having mines around it. If there are mines, we are replacing the index with mines. 
 *  If not, the children are added into the queue. Then we do a BFS traversal.
 */

import java.util.*;

public class Minesweeper {

    public static int m,n;
    public static int[][] dirs; 

    public static char[][] updateBoard(char[][] board, int[] click)
    {
        if(board == null || board.length == 0) return board;

         m = board.length;
         n = board[0].length;

         dirs =  new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{-1,1},{1,-1}};


        if(board[click[0]][click[1]]=='M')
        {
            board[click[0]][click[1]]='X';
            return board;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0],click[1]});

        while(!q.isEmpty())
        {
            int[] curr = q.poll();
            int count = countMines(board,curr[0],curr[1]);

            if(count>0)
            {
                board[curr[0]][curr[1]] = (char)(count+'0');
            }
            else{

                for(int[] dir: dirs)
                {
                    int r = dir[0] + curr[0];
                    int c = dir[1] + curr[1];

                    if(r>=0 && c>=0 && r<m && c<n && board[r][c] =='E')
                    {
                        q.add(new int[]{r,c});
                        board[r][c] = 'B';
                    }
                }
            }
        }

        return board;
    }

    private static int countMines(char[][] board, int i,int j)
    {
        int count  =0;
        for(int[] dir: dirs)
        {
            int r = i + dir[0];
            int c = j + dir[1];
            

            if(r>=0 && c>=0 && r<m && c<n && board[r][c] == 'M')
            {
                count++;
            }
        }

        return count;
    }

    public static void main(String args[])
    {
        char[][] board = {{'E','E','E','E','E'},{'E','E','M','E','E'},
        {'E','E','E','E','E'},{'E','E','E','E','E'}};
        int[] click = {3,0};

        char[][] ans = updateBoard(board, click);

        for(int i =0;i<ans.length;i++)
        {
            for(int j=0;j<ans[0].length;j++)
            {
                System.out.print(ans[i][j]+" ");
            }   
            System.out.println();
        }

    }
}
