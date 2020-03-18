class Solution {
    public int snakesAndLadders(int[][] board) {
        Map<Integer, Integer> mp = new HashMap<>();
        
        int n = board.length;
        int c = n*n;
        
        if(n % 2 == 0)
        {
            for(int i = 0; i < n; ++i)
            {
                if(i % 2 == 0)
                {
                    for(int j = 0; j < n; ++j)
                    {
                        mp.put(c, board[i][j]);
                        --c;
                    }
                }
                else
                {
                    for(int j = n-1; j >=0; --j)
                    {
                        mp.put(c, board[i][j]);
                        --c;
                    }
                }
            }
        }
        else
        {
            for(int i = 0; i < n; ++i)
            {
                if(i % 2 == 1)
                {
                    for(int j = 0; j < n; ++j)
                    {
                        mp.put(c, board[i][j]);
                        --c;
                    }
                }
                else
                {
                    for(int j = n-1; j >=0; --j)
                    {
                        mp.put(c, board[i][j]);
                        --c;
                    }
                }
            }
        }
        
        Queue<Integer> q = new LinkedList();
        q.add(1);
        
        int moves = 0;
        
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int l = 0; l < size; ++l)
            {
                int currSq = q.poll();
                
                if(currSq == n*n) return moves;
                
                for(int m = 1; m <= 6; ++m)
                {
                    int nxtSq = currSq + m;
                    
                    if(mp.containsKey(nxtSq))
                    {
                        if(mp.get(nxtSq) == -1) q.add(nxtSq);
                        else q.add(mp.get(nxtSq));
                        
                        mp.remove(nxtSq);
                    }
                }
            }
            ++moves;
        }
        
        return -1;
    }
}
