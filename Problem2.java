//time complexity-O(m*n)
//Space complexity-O(m*n)
//Ran on leetcode-Yes
//Solution with comments-
class Solution {
    public int snakesAndLadders(int[][] board) {//converting the bpard to a 1d array eith indexes as number on the board
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
            if(j>=m){//change direction of moving across the bpard based on even and odd values
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
        while(!q.isEmpty()){//BFS for all the possible next 6 positions and returning the level once we reach last number
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