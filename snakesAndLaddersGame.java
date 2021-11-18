// Time Complexity : O(n*m)
// Space Complexity : O(n*m)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class snakesAndLaddersGame {
    class Solution {
        public int snakesAndLadders(int[][] board) {
    
        int n = board.length , index = 1;
       
        int arr[] = new int[n*n+1];
        int e = 0;
        if(n % 2 == 0) e = 1;
        
       for(int i=n-1 ; i >= 0 ; i-- ) {
           if(i % 2 == e) {
               for(int j = 0 ; j < n;  j++) {
                   arr[index] = board[i][j];
                   index++;
               }
           } else {
               for(int j = n - 1; j >= 0; j--) {
                   arr[index] = board[i][j];
                   index++;
               }
           }
       }
        
        return getMin(arr);
    }
    
    //BFS
    public int getMin(int[] arr)
    {
        int steps = 0;
        int V = arr.length;
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        boolean[] visited = new boolean[V+1];
        
        visited[1] = true;
        while(!q.isEmpty())
        {
            int delimiter = q.size();
            while(delimiter-->0)
            {
                int curr = q.poll();
                if(curr == V-1)   return (steps);
                for(int i=curr+1;i<=curr+6 && i<arr.length;i++)
                {
                    if(!visited[i])
                    {
                        visited[i] = true;
                        int destination = arr[i] == -1 ? i:arr[i];
                        q.add(destination);
                    }
                }
                
            }
            steps++;
        }
        
        return -1;
      }
    }
}
