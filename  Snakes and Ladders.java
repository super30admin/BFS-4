// Time Complexity :o(mn)
// Space Complexity :o(mn) for flatten array
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public int snakesAndLadders(int[][] board) {
       Queue<Integer> q=new LinkedList<>();
        int N=board.length*board[0].length;
        int[] array=new int[N];
        int row=board.length-1;
        int col=board[0].length-1;
        int index=0;
        int resteps=0;
        boolean flag=true;
        int i=row,j=0;
    //to flatten the matrix    
while(i>=0 &&  j>=0){
    if(board[i][j] == -1){
        array[index]=-1;
    }else{
    array[index]=board[i][j]-1;
        }
     index++;
    if(flag){
        j++;
        
    }else{
        j--;
    }
    
    if(j>col){
        j--;
        i--;
        flag =!flag;
    }
    if(j<0){
        j++;
        i--;
        flag=!flag;
    }
   
   
}
     System.out.println(Arrays.toString(array));  
          q.add(0);
          array[0]=-2;
        
        while(!q.isEmpty()){
        int count=q.size();
           
            for(int p=0;p<count;p++){
                int current=q.poll();
                if(current==N-1) return resteps;
                for(int k=1;k<7;k++){
                    int x=current+k;
                    
                 if(x < N && array[x] != -2){
                     if(array[x] >=0){
                    q.add(array[x]);  
                    }
                    else{
                        q.add(x);
                    }
                    array[x]=-2;
                }
            }
            }
             resteps++;
        }
      return -1;
    }
    

   
       
}