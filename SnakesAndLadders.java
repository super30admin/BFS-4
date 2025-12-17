// Time Complexity : O(n*2)
// Space Complexity : O(n*2)
// Did this code successfully run on Leetcode : Yes
// Approach : We need to return the minimum number of moves to reach the end of snake and ladders board. We can achieve this by performing
// BFS by starting from 1st cell on the board and exploring all the six dice roll possibilities. While exploring the 6 possibilities, we
// mark the visited ones as -2 so that we can avoid them if we come across in the next level as we only need the minimum moves. We have
// written a getPos function that helps in getting the index of the zig zag transformation of the snake and ladders board.

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        //BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        int moves = 0; //BFS level tracking for to find minimum dice rolls
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int j=0; j<size; j++){
                int currIdx = queue.poll();
                for(int k=1; k<=6; k++){
                    int newIdx = currIdx + k;
                    int[] temp = getPos(newIdx, n); //get the corresponding index after zig zig transformation
                    int r = temp[0], c = temp[1];
                    if(newIdx == n*n-1 || board[r][c] == n*n) return moves+1; //reached end of board
                    if(board[r][c] != -2){ //if not visited
                        if(board[r][c] == -1){ //no ladder or snake
                            queue.add(newIdx); //add to queue
                        }else{
                            queue.add(board[r][c]-1); //add the corresponding position after ladder or snake
                        }
                        board[r][c] = -2;//mark it visited
                    }
                }
            }
            moves++; //increment dice rolls after each level
        }
        return -1;
    }

    private int[] getPos(int idx, int n){ //zig zig transformation to snake and ladder board
        int r = idx / n;
        int c = idx % n;

        if(r %2 == 0){ //even index row, direction is left to right
            return new int[]{n-r-1, c};
        }else{
            return new int[]{n-r-1, n-c-1}; //odd index row, direction is right to left
        }
    }
}