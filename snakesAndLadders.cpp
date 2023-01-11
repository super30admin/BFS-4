// Time Complexity : O(n*n) 
// Space Complexity : O(n*n) 
// Did this code successfully run on Leetcode : Yes 


// BFS with levels 

/*
6 options at every instance, explore all of them using breadth first search while mantaining the level information to get the moves 
Initially, convert the n * n grid to a flat 1D array
*/

class Solution {
public:
    int n;
    int snakesAndLadders(vector<vector<int>>& board) {
        if(board.size() == 0) return 0;
        n = board.size();

        // convert the board to a 1D array 
        int even = 0;
        vector<int> grid (n*n, -1);
        int row = n-1;
        int col = 0;
        int i = 0;
        while(i<n*n) {
            if(board[row][col] > 0)
                grid[i] = board[row][col]-1;
            if(even == 0)
                col++;
            else
                col--;
            if(col >= n) {
                col--;
                row--;
                even = 1;
            }
            if(col < 0) {
                col++;
                row--;
                even = 0;
            }
            i++;
        }
        cout<<grid[0];

        // BFS starting at grid[0]

        queue<int> q;
        q.push(0);
        int moves = 0;
        while(!q.empty()) {
            // we need the levels since we need the number of moves which is the level
            int size = q.size();
            for(int j=0; j<size; j++) {
                int index = q.front();
                q.pop();
                // if the new index is the end of the grid, we found the solution
                if(index == n*n-1)
                    return moves;

                // explore all the 6 options with every roll of die
                for(int i=1; i<=6; i++) {
                    int newIndex = index + i;
                    if(newIndex >= n*n) 
                        break;
                    // if the new index is -1, its not been explored before
                    // add it to the queue and mark it as visited by changing it to -2
                    if (grid[newIndex] == -1) {
                        q.push(newIndex);
                        grid[newIndex] = -2;
                    }
                    // else its a ladder or a snake - explore it again and change it -2
                    else if (grid[newIndex] > 0) {
                        q.push(grid[newIndex]);
                        grid[newIndex] = -2;
                    }
                }
            }
        moves++;
        }
        return -1;
    }
};