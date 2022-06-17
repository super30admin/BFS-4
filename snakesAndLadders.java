/*
Problem: https://leetcode.com/problems/snakes-and-ladders/
TC: O(n * n)
SC: O(n * n) -> queue
*/
class Solution {
    int moves;
    HashSet<Integer> visitedPositions = null;
    int dirs[] = new int[] {1,2,3,4,5,6};
    int rows, cols;
    
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return 0;
        }
        
        rows = board.length;
        cols = board[0].length;
        moves = -1;
        visitedPositions = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visitedPositions.add(1);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                int position = queue.poll();
                
                int r = getRow(position);
                int c = getCol(position, r);
                
                if (board[r][c] != -1) {
                    position = board[r][c];
                }
                
                // Check position here after updating position if board[r][c] != -1
                if (position == rows * cols) {
                    return moves + 1;
                }
                
                for (int dir : dirs) {
                    int newPosition = position + dir;
                    if (newPosition <= rows * cols && !visitedPositions.contains(newPosition)) {
                        queue.offer(newPosition);
                        visitedPositions.add(newPosition);
                    }
                }
            }
            
            ++moves;
        }
        
        return -1;
    }
    
    private int getRow(int position) {
        return (rows - (position - 1) / rows - 1);
    }
    
    private int getCol(int position, int row) {
        // Remember this condition for cols.
        if (row % 2 == rows % 2) {
            return (cols - (position - 1) % cols - 1);
        } else {
            return (position - 1) % cols;
        }
    }
}