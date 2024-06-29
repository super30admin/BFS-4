// Time Complexity : O(n * n)
// Space Complexity : O(n * n)
// Method used : BFS

class Solution {
    public int snakesAndLadders(int[][] board) {

        if(board == null || board.length == 0) return 0;

        // Let's create a 1D array representing all snakes and ladders

        int n = board.length, index = 0;

        int[] result = new int[n * n];

        // This is because the last row has elements from 1 till 6. We need to add elements from row n - 1 till 0
        // Column is 0 as we first start traversing from left to right
        int row = n - 1, col = 0;

        // Thi svariable helps us in deciding the direction in which we traverse and store the elements
        int even = 0;

        // We do this until we cover all the elements
        while(row >= 0 && col >= 0)
        {
            // Just add it
            if(board[row][col] == -1) result[index] = -1;

            // Now this means it could either be a snake or ladder
            else
            {
                // Assume if we had 15 in the grid, as we are storing it in array the index becomes 14
                result[index] = board[row][col] - 1;
            }

            // This means we are traversing from left to right
            if(even % 2 == 0)
            {
                // column increases from L to R
                col++;

                // We traversed all the elements
                if(col == n)
                {
                    // Make the col as n - 1 again because now we change direction and it should start from col n - 1 and go till 0
                    col = n - 1;
                    // Decrement the row
                    row--;

                    even++;
                }
            }

            // This means we are traversing from right to left
            else
            {
                // column decreases from R to L
                col--;

                // We traversed all the elements
                if(col == -1)
                {
                    // make col as 0 bcoz the next time we need to traverse from 0 till n - 1
                    col = 0;

                    row--;

                    even++;
                }
            }

            index++;
        }

        // Starting a BFS on this array

        Queue<Integer> queue = new LinkedList();

        int steps = 0;

        // Queue stores indices
        queue.add(0);

        // Mark it as visited
        result[0] = -2;

        while(!queue.isEmpty())
        {
            // We need size variable because we need to find minimum steps after each level. We stop at 36 
            int size = queue.size();

            for(int j = 0; j < size; j++)
            {
                int idx = queue.poll();

                // we came to destination
                if(idx == n * n - 1) return steps;

                // Dice has 6 values from 1 till 6
                for(int i = 1; i <= 6; i++)
                {
                    int child = idx + i;

                    // This means we crossed the array by adding i to idx, so the upcoming values of i woul be more so break
                    if(child >= n * n) break;

                    // This means either it is a ladder or snake or -2(already visited)
                    if(result[child] != -1)
                    {
                        if(result[child] != -2)
                        {   
                            queue.add(result[child]);

                            // Mark it as visited
                            result[child] = -2;
                        }
                    }

                    else
                    {
                        queue.add(child);

                        // Mark it as visited
                        result[child] = -2;
                    }
                }
            }

            steps++;
        }

        return -1;
    }
}
