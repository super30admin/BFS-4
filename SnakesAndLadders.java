// Time Complexity : O(N*N)
// Space Complexity : O(N*N)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n=board.length;
        int[] map=new int[n*n+1];
        int index=1;
        int i=n-1;
        int j=0;
        int even=0;
        while(i>=0){
           if(board[i][j]!=-1){
               map[index]=board[i][j];
           }else{
               map[index]=index;
             
           }
              index++;
            
            if(even==0){
                j++;
                if(j==n){
                    i--;
                    j--;
                    even=1;
                }
            }else{
                j--;
                if(j<0){
                    i--;
                    j++;
                    even=0;
                }
            }    
        }
        
        Queue<Integer> queue=new LinkedList<>();
        queue.add(map[1]);
        int min=0;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int k=0;k<size;k++){
                int curr=queue.poll();
                for(int p=1;p<=6;p++){
                    int newNum=curr+p;
                    if(map[newNum]!=-1){
                    
                        if(map[newNum]==map.length-1){
                            return min+1;
                        }
                        queue.add(map[newNum]);
                            map[newNum]=-1;
                    }
                }
            }
            min++;
        }
        
        return -1;
    }
}