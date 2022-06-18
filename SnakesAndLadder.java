//TC : O(n2)
//SC : O(n2)
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board==null || board[0].length ==0) return 0;

        int n = board.length;
        int[] result = new int[n*n];
        int dir = 0;
        int c = 0;
        int r = n-1;
        int index = 0;
        while(r>=0){
            if(board[r][c] != -1){
                result[index] = board[r][c]-1;
            }
            else{
                result[index] = -1;
            }

            if(dir%2==0){
                c++;
                if(c==n){
                    c--;
                    r--;
                    dir++;
                }
            }
            else{
                c--;
                if(c<0){
                    c++;
                    r--;
                    dir++;
                }
            }
            index++;
        }

        //BFS
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        result[0] = -2;
        int count = 0;
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i=0;i<size;i++)
            {
                int curr = q.poll();
                if(curr==n*n-1) return count;

                for(int j=1;j<=6;j++)
                {
                    int child = curr+j;
                    if(child<=n*n-1 && result[child]!=-2)
                    {
                        if(result[child]!=-1)
                            q.add(result[child]);
                        else
                            q.add(child);

                        result[child] =-2;
                    }
                }
            }
            count++;
        }
        return -1;
    }
}