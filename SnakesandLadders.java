/*
* Approach: As we have to find the minimum number of moves to reach the end of the board, we can do BFS
- We cant go greedy here because there can be snakes or ladders at any point. Hence we have to be exhaustive and explore all the possibilities 
- The idea is to flatten the board into an array and then perform BFS on the array
- TC: O(n^2) -> iterating over the board to make it 1d array + BFS traversal
- SC: O(n^2) -> flattening the board and storing it in 1D array
 */
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n * n];
        boolean flag = true;
        int idx = 0; //idx to fill the array
        int r = n - 1;
        int c = 0;
        //flatten the board into an array
        while (idx < n * n) {
            if (board[r][c] == -1) {
                arr[idx] = -1;
                idx++;
            } else {
                //if there is a snake or a ladder we take their actual index hence -1
                arr[idx] = board[r][c] - 1;
                idx++;
            }
            if (flag) {
                //left to right
                //increment the col and if we are the last col, we have to go up and left so decrement the row and decrement the col as well and set the flag to false as we are moving in the opposite direction
                c++;
                if (c == n) {
                    r--;
                    c--;
                    flag = false;
                }
            } else {
                //right to left
                //similarly, if col is out of bound, decrement the row and increment the col to reach the cell just above the one we have processed and set the flag to true 
                c--;
                if (c == -1) {
                    r--;
                    c++;
                    flag = true;
                }
            }
        }

        //BFS to find the shortest number of moves to reach the n^2
        Queue<Integer> q = new LinkedList<>(); //for maintaining the indices
        q.add(0);
        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int currIdx = q.poll();

                //get new possible indices at most 6
                for (int i = 1; i <= 6; i++) {
                    int newIdx = currIdx + i;

                    if (newIdx >= n * n) //handle index going out of bound
                        break;

                    if (newIdx == n * n - 1 || arr[newIdx] == n*n-1)
                        return moves + 1;

                    if (arr[newIdx] != -2) {
                        if(arr[newIdx] == -1){
                            q.add(newIdx);
                        }
                        else{
                            q.add(arr[newIdx]);
                        }
                        arr[newIdx] = -2; //mark as visited
                    }
                }
            }
            moves++;
        }
        //if there is no way to reach n^2
        return -1;
    }
}