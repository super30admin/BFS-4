// Time Complexity : O(mn), iterate over the board 
// Space Complexity : O(mn), moves[] + queue size
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
// since the pattern is zig zag better to store num vs num to get to in moves[]
// inorder to calculate moves, put first 6 places, (nums) in queue, calculate next possible pos+6 and put in queue as next move
// whensoever we reach the last place, n via an intermediatry ladder game ends and we return result   

class Solution {
    public int snakesAndLadders(int[][] board) {
        
        int n = board.length * board[0].length; 
        int[] moves = new int[n];
        
        int r = board.length;
        int c = board[0].length;
        int row = r-1;
        int col = 0;
        int even = 0;
        int counter = 0;
        
        while(counter<n){
            if(board[row][col]==-1){
                moves[counter] = -1;     
            }
            else{
                moves[counter] = board[row][col]-1;
            }
            counter++;
            
            if(even%2 == 0){
                col += 1;
            }
            else{
                col -= 1;
            }
            
            if(col==c){
                row -= 1;
                even += 1;
                col -= 1;
            }
            else if(col==-1){
                row -= 1;
                even += 1;
                col += 1;
            }    
        }
        
        int result = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        moves[0] = -2;
        
        while(!queue.isEmpty()){
            int size = queue.size();
            
            for(int i=0; i<size; i++){
                int pos = queue.poll();
                
                if(pos==n-1){
                    return result;
                }
                
                for(int d=1; d<7; d++){
                    if(pos+d<n && moves[pos+d]!=-2){
                        if(moves[pos+d]==-1){
                            queue.offer(pos+d);    
                        }
                        else{
                            queue.offer(moves[pos+d]);
                        }
                        
                        moves[pos+d] = -2;
                    }
                }
            }
            
            result++;
        }
        
        return -1;
    }
}