// Time Complexity : O(n*n)
// Space Complexity :O(n*n)
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board==null || board.length==0) return 0;
        Queue<Integer> q=new LinkedList<>();
        int res=0;
        int n=board.length;
        int[] matrix=new int[n*n];
        int even=0;
        int row=n-1, col=0;
        int index=0;
        
        while(index<n*n){
            if(board[row][col]==-1){
                matrix[index]=-1;
            }else{
                 matrix[index]=board[row][col]-1;
            }
            index++;
            if(even%2==0){
                col++;
                if(col==n){
                    col--;
                    row--;
                    even++;
                }
                
            }else{
                col--;
                if(col==-1){
                    col++;
                    row--;
                    
                    even++;
                }
                
            }
            
        }
        
        q.add(0);
        matrix[0]=-2;
        while(!q.isEmpty()){ 
            int size=q.size();
            for(int i=0;i<size;i++){
                 int curr=q.poll();
                 if(curr==n*n-1) return res;
                for(int j=1;j<=6;j++){
                    int baby= curr+j;
                    if(baby > n * n-1) continue;
                    if(matrix[baby]==-1){
                        q.add(baby);
                        matrix[baby]=-2;
                    }
                    else if(matrix[baby]>0){
                        q.add(matrix[baby]);
                        matrix[baby]=-2;
                    }
                }
            }
            res++;
        }
        return -1;
    }
}