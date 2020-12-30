//time complexity:O(m*n)
//space complexity:O(m*n)

class Solution {;
     int[][] dirs; int m; int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        //edge 
        if(board==null ||board.length==0) return board;
        m=board.length;
        n=board[0].length;
        if(board[click[0]][click[1]]=='M')
        {
            board[click[0]][click[1]]='X';
            return board;
        }
        dirs=new int[][]{{0,1},{0,-1},{1,0},{-1,0},{1,1},{-1,-1},{1,-1},{-1,1}};
        helper(board,click[0],click[1]);
        return board;
       
        }
    private int mines(char[][] board, int i,int j)
    {
        int mine=0;
        for(int dir[]:dirs)
        {
             int r=i+dir[0];
             int c=j+dir[1];
             if(r>=0 && r<m && c>=0 &&c<n &&board[r][c]=='M')
             {
                 mine++;
             }
        }
        return mine;
        
    }
     private void helper(char[][] board, int i,int j)
     {
         //edge
         if(i<0 || i==m || j<0 ||j==n || board[i][j]!='E') return ;
         //logic
         int mine=mines(board,i,j);
         if(mine==0)
         {
             for(int dir[]:dirs)
             {
                 board[i][j]='B';
                 helper(board,i+dir[0],j+dir[1]);
             }
         }
         else
         {
             board[i][j]=(char)(mine+'0');
         }
     }
                  
    }
