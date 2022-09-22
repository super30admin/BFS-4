class Solution {
    public int snakesAndLadders(int[][] board) {
      HashSet<Integer>visited= new HashSet<>();
        
        Queue<Integer> q= new LinkedList<>();
        int count=1;
        q.add(1);
                    int step=0;
        while(!q.isEmpty()){

            int size=q.size();
           // System.out.println(q);
            
            for(int i=0;i<size;i++){
                int out =q.poll();
                
                if(out==board.length*board.length){return step;}
                for( int j=1;j<=6;j++){
                    int next=out+j;
                    
                    if(next>board.length*board.length){break;}
                    
                    int x=getx(next,board);
                    int y=gety(next,board,x);
                   
                    if(board[x][y]!=-1){ //System.out.println(next+" "+x+" "+y);
                     if(!visited.contains(board[x][y])){
                     q.add(board[x][y]); 
                         visited.add(board[x][y]);
                         
                     }
                        
                    }else{
                        if(!visited.contains(out+j)){
                     q.add(out+j); 
                         visited.add(out+j);
                         
                     }
                        
                    }
                   
                }
               
            }
         step++;
        //System.out.println(q);
        
    }
    return -1;
    }
    
    
    
  
    
    //get x coordinate
    public int getx(int n, int board[][]){
        
        if(n%board.length==0){return ((board.length)-n/board.length); }
        return ((board.length)-n/board.length)-1;
        
    }
    
    //get y coordinate
    public int gety(int n,int  board[][], int x){
     int offset=n%board.length;
        if(board.length%2==0){
            if(x%2==0){
              if(offset==0){return 0;}
             
                return board.length-offset;
                
            }else{
              
                 if(offset==0){return board.length-1;}
                   return offset-1;
            }
            
        }else{
            if(x%2==0){
          if(offset==0){return board.length-1;}
                return offset-1;
                
            }else{
                   if(offset==0){return 0;}
                return board.length-offset;
            }
            
        }  
    }    
}
