// Time Complexity : O(V+E), V-> no of cells on board, E-> no of neighbors/connections
// Space Complexity : O(V+E)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Approach:
// We need to find the least number of moves to reach the destination. This suggests the BFS pattern wherein we can explore the immediate neighbors and reach the destination in minimum number of moves.
// Since the matrix traversal in this problem is alternatively from left to right and right to left starting from leftmost bottom cell, appropriate checks and validations needs to be done at every step.
// To simplify things, given matrix can be flattened to a 1-D array and the respective cell positions for snake and ladder can be added by decrementing by 1. (Start value is 1, so the values need to be offset)


class Solution {
    public int snakesAndLadders(int[][] board) {
        // edge case
        if(board == null || board.length == 0)
            return 0;
        int m = board.length;
        int n = board[0].length;
        
        // create 1-D array from given matrix
        int[] transformedBoard = getNewBoard(board, m, n);
        
        // Initialize Queue
        Queue<Integer> queue = new LinkedList<>();
        // create visited array to avoid processing of same node again
        boolean[] visited = new boolean[m*n];
        // create counter to track no of moves
        int movesCount = 0;
        queue.offer(0);
        visited[0] = true;
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            while(size > 0) {
                int curr = queue.poll();
                if(curr == m*n-1)
                    return movesCount;
                // add neighbors : curr+1, curr+2.....curr+6
                for(int i = 1; i <=6; i++) {
                    int neighbor = curr+i;
                    if(neighbor < m*n && !visited[neighbor]) {
                        if(transformedBoard[neighbor] >= 0)
                            queue.offer(transformedBoard[neighbor]);
                        else
                            queue.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
                size--;
            }
            movesCount++;
        }
        
        return -1;
        
    }
    
    private int[] getNewBoard(int[][] board, int m, int n) {
        int[] transformedBoard = new int[m*n];
        
        int i = m-1, j = 0, k = 0;
        while(i >= 0) {
            while(j < n) {
                updateBoard(board, transformedBoard, i, j, k);
                j++;
                k++;
            }
            if(j == n) {
                j--;
                i--;
                while(j >= 0) {
                    updateBoard(board, transformedBoard, i, j, k);
                    j--;
                    k++;
                }
            }
            if(j < 0)
                j++;
            i--;
        }
        return transformedBoard;
    }
    
    // method to validate and update new board
    private void updateBoard(int[][] board, int[] transformedBoard, int i, int j, int k) {
        if(i >= 0 && j >= 0 && i < board.length && j < board[0].length) {
            if(board[i][j] == -1)
                transformedBoard[k] = -1;
            else
                transformedBoard[k] = board[i][j]-1;
        }
    }
}

