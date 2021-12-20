//Timecomplexity:- O(n*n);
//space complexity:- 0(n*n);
//ran on leetcode.
//approach is written in comments.
class Solution {
    public int snakesAndLadders(int[][] board) {

    int n = board.length , index = 1;
    int arr[] = new int[n*n+1];
    int e = 0;
    if(n % 2 == 0) e = 1;
    
	//converting 2D to 1D Array
   for(int i=n-1 ; i >= 0 ; i-- ) {
       if(i % 2 == e) {
           for(int j = 0 ; j < n;  j++) {
               arr[index] = board[i][j];
               index++;
           }
       } else {
           for(int j=n-1 ; j >= 0 ; j--) {
               arr[index] = board[i][j];
               index++;
           }
       }
   }
    
    return getMin(arr);
}

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
            {                                                //in bfs children nodes will be current position plus positions untill plus 6 of current. we check if respective visited index is false then we first change to true and 
            //if that value is not -1, adding the orginal value of convewrted array associated with that index or just adding i to queue. this will be broken when after polling if that position  value is last value of board.
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
   
