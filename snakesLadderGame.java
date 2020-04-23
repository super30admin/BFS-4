// Time Complexity : O(m*n) total number of cells
// Space Complexity : O(m*n)  total number of cells     
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : after class solution


// Your code here along with comments explaining your approach

//The idea is to flatten the 2D board into 1D board. We do this to keep track of the cell number. The traversal is zigzag according to the problem definition. Also, we keep track of offset for positive numbers.
//Then we do a simple BFS traversal to reach the end point, which is N-1 in this case.


class Solution {
    public int snakesAndLadders(int[][] board) {
 
        //base check
        if(board == null || board.length == 0) return 0;
        
        //find the total number of rows and cols
        int row = board.length, col = board[0].length;
        int N = row * col;
        //initialize the array to flatten the 2D matrix, that will have all the elements
        int[] flatBoard = new int[N];
        
        int i = row - 1;
        int j = 0;
        
        //order to traverse the col in each row
        boolean leftToRight = true;
        
        //starting index of flatten array
        int index = 0;
        
        while(i>=0 && j >=0){
            
            if(board[i][j] == -1){
                flatBoard[index] = -1;
            }else{
                flatBoard[index] = board[i][j] - 1; //offset because array is from 0 to N-1, whereas board shows from 1 to N
            }
            
            index++;
            
            if(leftToRight){
                j++;//j increments
            }else{
                j--;
            }
            
            if(j>=col){
                j--;
                i--;
                leftToRight = !leftToRight;
            }
            
            if(j<0){
                j++;
                i--;
                leftToRight = !leftToRight;
            }
        }
        
        //we will use BFS to visit the nodes. So we will use queue to keep track of indices of cells
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        //mark it visited in-place by placing an arbitary value
        flatBoard[0] = -2;
        
        //need a value to keep track of minimum number of steps
        int minSteps = 0;
        
        //iterate the queue
        while(!q.isEmpty()){
            
            
            //we need to keep track of node level which is equal to number of dices rolled
            int size = q.size();
            
            for(i=0; i<size; i++){
                int current = q.poll();
                
            //check if end is reached or not
                System.out.println("current is: " + current);
            if(current == N-1) return minSteps;
            
            //find it's neighbours. Here neighbours can be x+1 to x+6 whatever value the dice gives within 6.
            for(int k=1; k<7; k++){
                int neighbour = current + k;                
                //check if neighbour is within bound and not visited
                if(neighbour < N && flatBoard[neighbour] != -2){
                    //if a positive value then either a ladder or snake is present
                   if(flatBoard[neighbour] >=0){
                        q.add(flatBoard[neighbour]);//directly move to that cell
                    }else{
                        q.add(neighbour);//else add the general cell
                        } 
                    //mark it visited
                    flatBoard[neighbour] = -2;
                    }                
                } 
            }        
            //end of one dice, so one step completed
            minSteps++;
        }
        
        return -1;
    }
}
