import java.util.Queue;
import java.util.LinkedList;

// Time O(M*N)
//Space O(M*N)  -- Visited array and Queue 
public class SnakeAndLadders {
	 public int snakesAndLadders(int[][] board) {
	        // flat this 2d matrix to one 1d array and 
	        int n = board.length;
	        int[] arr = new int[n*n];
	        int index =0;
	        boolean leftToRight = true;
	        // start from bottom row 
	        for(int i=n-1;i>=0;i--){
	            if(leftToRight){
	                for(int j=0;j<n;j++){
	                    arr[index++] = board[i][j];
	                }
	            }else{
	                 for(int j=n-1;j>=0;j--){
	                    arr[index++] = board[i][j];
	                }    
	            }
	            leftToRight = !leftToRight;
	        }

	        // Create Queue for BFS and add arr[0] to the queue 
	        Queue<Integer> q = new LinkedList<>();
	        boolean[] visited = new boolean[n*n-1];
	        if(arr[0]==-1)
	            q.add(0);
	         else
	            q.add(arr[0]-1);
	        visited[0]=true;    
	        // Apply BFS
	        int moves=0;
	        while(!q.isEmpty()){
	            moves++;
	            int size =q.size();
	            for(int i=0;i<size;i++){
	                int curr = q.poll();
	                for(int next=1;next<=6;next++){
	                   int nextSq = curr+next;
	                   if(nextSq==n*n-1)return moves;
	                   else if(arr[nextSq]==n*n) return moves;
	                   else if(visited[nextSq])continue;
	                   else{
	                       visited[nextSq]=true;
	                       if(arr[nextSq]==-1)
	                         q.add(nextSq);
	                        else
	                         q.add(arr[nextSq]-1);
	                   } 

	                }
	            }
	        }

	       
	        return -1;

	    }
}
