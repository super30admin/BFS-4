class Solution {

    //Approach 
    // Time Complexity : O(n*n)
    // Space Complexity: O(n)

    public int snakesAndLadders(int[][] board) {
        
        if(board == null || board.length == 0) return 0;                //check if board is null or board length is 0, if so, then return 0

        int n = board.length;

        int[] arr = new int[n*n];                                       //here, we are converting 2D board into 1D board
        
        Arrays.fill(arr, -1);                                           //first fill the whole arr by -1
        
        int even = 0;                                                   //to see we are in even row or add
        int r = n-1;                                                    //points to the row, we have to start from the last row
        int c = 0;                                                      //points to the column

        for(int i=0; i<arr.length; i++){                                //iterating through the 1D board

            if(board[r][c] != -1){                                      //if currentCell is not -1, then we have to update the 1D board with 1 less value than the original oard, as we have 1D board indices starting from zero, and given board starts from 1 
                arr[i] = board[r][c] - 1;
            }

            if(even % 2 == 0){                                          //check if are even row,
                
                c++;                                                    //if so, then we have to go to right side in each iteration
                
                if(c == n){                                             //if we reach the end of the column, then we have to increase the even counter, decrease the row counter and column counter
                    r--;
                    c--;
                    even++;
                }
            }
            else{   
                c--;                                                       //means we are in odd row, so we have to go left side for each iteration

                if(c < 0){                                                 //if we reach the end of the column, then we have to increase the even counter and column counter, decrease the row counter
                    r--;
                    c++;
                    even++;
                }
            }
        }

        int steps = 0;                                                  //stores the steps require to reach the end of the board

        Queue<Integer> queue = new LinkedList<>();                      //creating queue as we are going in BFS manner as we need the minimum number of steps
        queue.add(0);                                                   //add the first cell in the queue, as we are starting the game from box 1
        arr[0] = -2;                                                    //mark that cell as visited

        while(!queue.isEmpty()){                                        //iterate while queue is empty
            
            int size = queue.size();                                    //get the size

            for(int i = 0; i<size; i++){                                //iterate till the size

                int currentCell = queue.poll();                         //remove the element from queue, which is our currentcell

                for(int j = 1; j<=6 && currentCell + j < n*n; j++){     //we have 6-sided die roll, so we can take maximum 6 jumps from the currentCell, so we have to iterate from 1 to 6 also we have to make sure that all the 6 jumps are inside the board

                    int nextCell = currentCell + j;                     //get the nextCell from the currentCell

                    if(nextCell == n*n - 1 || arr[nextCell] == n*n - 1) return steps + 1;           //check if the nextCell is itself the end of the board or the nextCell leads us to the end of the board, if so, then return the steps+1

                    if(arr[nextCell] != -2){                            //check if the nextCell is visited or not
                        
                        if(arr[nextCell] == -1){                       //if it's not visited, then check if there's any ladder or snake
                            queue.add(nextCell);                        //if there's not any snake or ladder then just add the nextCell in the queue
                        }
                        else{
                            queue.add(arr[nextCell]);                   //if there's snake or ladder, means we have to add the destinationCell number in the queue
                        }
                        arr[nextCell] = -2;                             //mark the nextCell as visited
                    }
                }
            }
            steps++;                                                    //increase the step by 1
        }
        return -1;                                                      //if we reach here, that means we can't reach the end of the board, so return -1
    }





    //Approach 
    // Time Complexity : O(n*n)
    // Space Complexity: O(n)
    
    // public int snakesAndLadders(int[][] board) {
        
    //     int n = board.length;       //get the board size
    //     Queue<Integer> q = new LinkedList<>();      //making queue, applying BFS as we need to return the minimum step size to reach the end of the board
        
    //     q.add(1);                   //add starting point in the queue
    //     board[n-1][0] = -2;         //mark that cell as visited cell
        
    //     int steps = 0;              //initialize step as zero
        
    //     while(!q.isEmpty()){        //continue the loop till queue becomes zero
            
    //         int size = q.size();      
    //         steps++;
            
    //         for(int j = 0; j<size; j++){        //iterating through one step
                
    //             int currentCell = q.poll();       //get the current cell
            
    //             for(int i=1; i<=6 && currentCell + i<= n*n; i++){       //calculating the nextCell which is starting from 1 to 6
                    
    //                 int nextCell = currentCell + i;     //calculate the nextCell
    //                 int[] coord = getCoord(n, nextCell);      //get the coordinate of the next cell
                    
    //                 int value = board[coord[0]][coord[1]];      //get the value associated to that next cell
                    
    //                 if(value > 0){                      //if value is greater than zero, initialize the nextCell  = value
    //                     nextCell = value; 
    //                 }
                    
    //                 if(nextCell == n*n){            //if nextCell is the end of the board, then return the step
    //                     return steps;
    //                 }
                    
    //                 if(value != -2){                //check if the nextCell is visited or nor
    //                     q.add(nextCell);        //if not visited then add that to queue
    //                     board[coord[0]][coord[1]] = -2;     //and mark it as visited
                        
    //                 }   
    //             }
    //         }
    //     }
    //     return -1;                      //it means that, we can't reach the end of the board, so return -1
    // }




    //Approach 
    // Time Complexity : O(n*n)
    // Space Complexity: O(n)

    // public int snakesAndLadders(int[][] board) {
        
    //     int n = board.length;       //get the board size
    //     Queue<Integer> q = new LinkedList<>();      //making queue, applying BFS as we need to return the minimum step size to reach the end of the board
        
    //     q.add(1);                   //add starting point in the queue
    //     board[n-1][0] = -2;         //mark that cell as visited cell
        
    //     int steps = 0;              //initialize step as zero
        
    //     while(!q.isEmpty()){        //continue the loop till queue becomes zero
            
    //         Queue<Integer> nextQ = new LinkedList<>();      //creating another queue for storing the next step
    //         steps++;
            
    //         while(!q.isEmpty()){        //while queue is not empty
                
    //             int currentCell = q.remove();       //get the current cell
            
    //             for(int i=1; i<=6 && currentCell + i<= n*n; i++){       //calculating the nextCell which is starting from 1 to 6
                    
    //                 int nextCell = currentCell + i;     //calculate the nextCell
    //                 int[] coord = getCoord(n, nextCell);      //get the coordinate of the next cell
                    
    //                 int value = board[coord[0]][coord[1]];      //get the value associated to that next cell
                    
    //                 if(value > 0){                      //if value is greater than zero, initialize the nextCell  = value
    //                     nextCell = value; 
    //                 }
                    
    //                 if(nextCell == n*n){            //if nextCell is the end of the board, then return the step
    //                     return steps;
    //                 }
                    
    //                 if(value != -2){                //check if the nextCell is visited or nor
    //                     nextQ.add(nextCell);        //if not visited then add that to queue
    //                     board[coord[0]][coord[1]] = -2;     //and mark it as visited
                        
    //                 }   
    //             }
    //         }
    //         q = nextQ;                      //assign q is nextQ  
    //     }
    //     return -1;                      //it means that, we can't reach the end of the board, so return -1
    // }
    
    
    
    // public int[] getCoord(int n, int cellValue){
        
    //     int bRow = (cellValue-1)/n;         //get the bottomRow Count
    //     int row = n - bRow - 1;             //get the row number in board
        
    //     int column = (bRow % 2 == 0)? (cellValue - 1 )%n : n - (cellValue - 1)%n -1;        //get the coulmn number in board
        
    //     return new int[]{row, column};   
    // }
    
    //Another Approach
    // we can convert 2D array into 1D Array
    /* board =  7 8 9
                6 5 4
                1 2 3
                
    we can convert this 2D board into 1D array
    new Board : 1 2 3 4 5 6 7 8 9,
    then apply BFS
    */
}
