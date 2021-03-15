//Problem 148:Snakes and Ladders
//TC:O(n*n)
//SC:O(n*n)

/*
Steps: Data preprocessing is must, just covert 2d array to 1d array so that jump to index should be easier. While converting, if the element is -1 then just add the 1d index otherwise just add element -1, because it will be considered as the index for jump

*/
import java.util.*;
class Solution {
    public int snakesAndLadders(int[][] board) {
        
        //TC:O(n*n) || SC:O(n*n)
        if(board==null || board.length==0) return 0;
        
        int n = board.length;
        
        int[] game = new int[n*n];
        
        int r=n-1;
        int c=n-1;
        boolean even = true;
        int idx =0;
        int j=0;
        //data preprocessing, coverting 2d array to 1d array so that jump to index should be easier
        while(r>=0){
            
            while(j>=0 && j<=c){
                
                if(board[r][j]<0) game[idx] = idx;
                else game[idx] = board[r][j]-1;    

                idx++;
                
                if(even) j++;
                else j--;
              }
            r--;
            if(even){
              j--; even = !even;   
            }else{
              j++; even = !even;  
            }
        }
     
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        game[0] = -2;
        
        int levels = 0;
        int size   = 0;
        while(!q.isEmpty()){
            
            size = q.size();
            
            for(int k=0;k<size;k++){
                
                int currIdx = q.poll();
                
                if(currIdx == game.length-1) return levels;
                
                for(int i=1;i<=6;i++){//O(6N*N)//dice is thrown
                    
                    int child = currIdx + i;
                    
                    if(child>game.length-1 || game[child]==-2) continue;
                    //just add value at that index
                    q.offer(game[child]);
                    
                    //no need of this
                   // if(game[child]==-1) q.offer(child);//just add the index
                    //else q.offer(game[child]); //adding the next jump means ladder case
                    
                    game[child] = -2;
                }
                
            }
            levels++;
        }
        
        return -1;
    }
}