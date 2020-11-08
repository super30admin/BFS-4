// Time Complexity : O(n*2)
// Space Complexity : O(n*2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board==null||board.length==0||board[0].length==0){
            return 0;
        }
        
        int n=board.length, m=board[0].length;
        int[] move=new int[n*m];
        
        int i=n-1, j=0, index=0,even=0;
        while(i>=0&&j>=0){
            if(board[i][j]==-1){
                move[index]=-1;
            }
            else{
                move[index]=board[i][j]-1;
            }
            if(even%2==0){
                j++;
            }
            else{
                j--;
            }
            if(j>=m){
                even++;
                i--;j--;
            }
            else if(j<0){
                even++;
                i--;j++;
            }
            index++;
        }
        int minLevel=0;
        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        move[0]=-2;
        
        while(!q.isEmpty()){
            int size=q.size();
        for(int x=0;x<size;x++){
            int curr=q.poll();
            if(curr==n*m-1){
                return minLevel;
            }
            for(int y=1;y<=6;y++){
                int nextMove=curr+y;
                if(nextMove<n*m&&move[nextMove]!=-2){
                    if(move[nextMove]==-1){
                        q.add(nextMove);
                    }else{
                        q.add(move[nextMove]);
                    }
                    move[nextMove]=-2;
                }
            }
        }
          minLevel++;  
        }
        return -1;
        
    }
}