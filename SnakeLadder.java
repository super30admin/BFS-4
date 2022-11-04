class Solution {
    //tc-o(n^2) sc-o(n^2)
    public int snakesAndLadders(int[][] board) {
        //null case
        if(board == null || board.length == 0) return 0;
        int n = board.length;
        int[] moves = new int[n*n];
        int idx = 0;//index on 1D matrix
        int even =0;
        int i = n-1,j=0;
        while(idx < n*n)
        {
            if(board[i][j] == -1)
            {
             moves[idx] = board[i][j];
            } else{
                moves[idx] = board[i][j] - 1;
            }
            idx++;
            if(even % 2 ==0)//left to right
            {
              j++;
              if(j == n)
              {
                  i--;
                  j--;
                  even ++;
              }
              
            }else{//righ te left
            j--;
            if(j==-1)
            {
                i--;
                j++;
                even++;
            }
            

            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0); moves[0] = -2;//to make it visited
        int result =0;
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int l=0;l<size;l++)
            {
            int curr = q.poll();
            if(curr == n*n-1) return result;
            for(int k=1;k<=6;k++)
            {
                int child = curr+k;
                if(child < n*n)
                {
                    if(moves[child] != -2)
                    {
                      if(moves[child] == -1)
                      {
                          q.add(child);
                      }
                      else{
                          q.add(moves[child]);
                      }
                      moves[child] = -2;
                    }
                }

            }
            }
            result++;
           
        }
        return -1;
    }
}