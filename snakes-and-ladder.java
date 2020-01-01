/*


Did it run on leetcode: Yes
Did you face any problem: How we do mimimum  moves with BFS

Time Compelxity: 0(m*n)
Space Complexity: 0(m*n)

Algorithm:
- Convert the matrix into a flatten array
- Then add the first grid of the matrix into the queue
- Then add next 6 positions into the queue and increase the level



*/



class Custom{
    
    public int position;
    public int level;
    
    public Custom(int position,int level){
        this.position = position;
        this.level = level;
    }
}


class Solution {
    public int snakesAndLadders(int[][] board) {
            
        int n = board.length;
        int m = board[0].length;
        int N = n*m;
        
        int[] flattenBoard = new int[N+1];
        boolean visited[] = new boolean[N+1];
       
        
        int i=0,j=m-1,row=n-1,flattenBoardIndex=1;
        flattenBoard[0]=0;
        boolean even=true;
        
        while(row>=0){
            if(even){
                while(i<m){
                    flattenBoard[flattenBoardIndex]=board[row][i];
                    ++i;++flattenBoardIndex;
                }
                i=0;
                even=false;
            }else{
                while(j>=0){
                    flattenBoard[flattenBoardIndex]=board[row][j];
                    --j;++flattenBoardIndex;
                }
                j=m-1;
                even=true;
            }
            row-=1;
        }
        
        Custom start = new Custom(1,0);
        Queue<Custom> queue = new LinkedList<Custom>();
        queue.add(start);
        visited[1]=true;
        
        while(!queue.isEmpty()){
            
            Custom curr = queue.poll();
            int position = curr.position;
            int level = curr.level;
            
            if(position==N){
                return level;
            }
            
            for(int k=position+1;k<=Math.min(position+6,N);++k){
                if(!visited[k]){
                    
                    Custom newPos = new Custom(flattenBoard[k]==-1? k:flattenBoard[k],level+1);
                    queue.add(newPos);
                    visited[k]=true;
                }
                
            }
            
        }
        
        return -1;
    }
}