// Time Complexity : O(2*N) where N = number of tiles on board
// Space Complexity : O(2*N) where N = number of tiles on board
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n= board.length;
        int m=board[0].length;
        int[] memo= new int[n*m];
        int i=n-1,j=0,counter=0,even=0;
        while(i>=0 && j>=0){
            if(board[i][j]== -1){
                memo[counter]=counter;
            }
            else{
                memo[counter]=board[i][j]-1;
            }
            counter++;
            if(even%2==0)j+=1;
            else j-=1;
            if(j>=m){
                j-=1;
                i-=1;
                even+=1;
            }
            else if(j<0){
                j+=1;
                i-=1;
                even+=1;
            }

        }
        Queue<Integer> q= new LinkedList<>();
        int level=0;

        q.add(0);
        memo[0]=-1;
        while(!q.isEmpty()){
            int size=q.size();
            for(int l=0;l<size;l++){
                int curr=q.poll();
                if(curr==n*m-1)
                    return level;
                for(int k=1;k<=6;k++){
                    int nextPos=curr+k;
                    if(nextPos<n*m && memo[nextPos]!=-1){
                        q.add(memo[nextPos]);
                        memo[nextPos]=-1;
                    }
                }
            }
            level++;
        }
        return -1;  
    }
}
