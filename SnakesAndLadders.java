// 909.
// time - O(n * m) - max visit each pos once
// space - O(n * m) - for flattened list
class Solution {
    public int snakesAndLadders(int[][] board) {
        //edge
        if(board == null || board.length == 0 || board[0].length == 0)
        {
            return 0; //empty board
        }
        
        //flatten the grid into a list
        int m = board.length;
        int n = board[0].length;
        int N = m * n; //size of flatten list
        int[] flattenedList = new int[N]; //flattened version of board - also used to mark visited elements
        int i = m - 1; //start from last row first column - start position 
        int j = 0;
        int index = 0; //tracks next location to be filled in flattened list
        int flag = 0; //tracks whether current row is even row or odd row -> start from last row -> considered as even
        
        //as long as all rows are exhausted
        while(i >= 0)
        {
            //store current cell value into index location in flattened list (if cell value is -1, store -1, else store value - 1 to account for index)
            flattenedList[index] = (board[i][j] == -1) ? -1 : board[i][j] - 1; 
            if(flag % 2 == 0)
            {
                j++; //if in even row (last, third last, ...) next cell must be i,j+1
            }
            else
            {
                j--; //if in even row (last, third last, ...) next cell must be i,j-1
            }
            //j can go out of bounds either in right side(i.e crosses last col) or in left side(i.e crosses 1st col)
            //in both cases go to prev row (i--) and increase flag(as we have traversed current row fully)
            //if j is out of bounds in right side, decrement j by 1 to make it within bounds and got to prev row
            //if j is out of bounds in left side, increment j by 1 to make it within bounds and got to prev row
            if(j >= n) 
            {
                i--;
                j--;
                flag++;
            }
            if(j < 0)
            {
                i--;
                j++;
                flag++;
            }
            index++; //increse index to track the next vacant index in flattened list
        }
        
        Queue<Integer> support = new LinkedList<>(); //for bfs
        support.offer(0); //start from pos 1 which is in index 0 in flatten list
        flattenedList[0] = -100; //marking this cell as visited
        int minMoves = 0; //return value
        
        while(!support.isEmpty())
        {
            int layerSize = support.size();
            for(int k = 0; k < layerSize; k++)
            {
                int current = support.poll(); //remove the front
                if(current == N - 1)
                {
                    //reached end of board
                    return minMoves;
                }
                //from current index we can move upto 6 steps
                for(int x = 1; x <= 6; x++)
                {
                    int nextIndex = current + x; //move from current to next post by x steps
                    if(nextIndex < N && flattenedList[nextIndex] != -100) //next pos is not visited
                    {
                        if(flattenedList[nextIndex] == -1) //no snake or ladder here
                        {
                            support.offer(nextIndex);
                        }
                        else //snake or ladder
                        {
                            support.offer(flattenedList[nextIndex]);
                        }
                        flattenedList[nextIndex] = -100; //marking it as visited
                    } 
                }
            }
            minMoves++;
        }
        
        return -1; //never reaches here
    }
}
