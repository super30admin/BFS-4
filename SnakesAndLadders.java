// Time Complexity : O(n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Solution {
    public int snakesAndLadders(int[][] board) {
        
        int n = board.length;
        
        int row = n - 1;
        int column =  0;
        int even = 0;
        int index = 0;
        int[] arr = new int[n * n];
        
        //Flattening the board
        while(row >= 0) {
            if(board[row][column] != -1) {
                arr[index] = board[row][column] - 1;
            } else {
                arr[index] = -1;
            }
            
            if(even % 2 == 0) {
                column++;
                if(column == n) {
                    column--;
                    row--;
                    even++;
                }
            } else {
                column--;
                if(column < 0) {
                    column++;
                    row--;
                    even++;
                }
            }
            index++;
        }
        
        // BFS
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        arr[0] = -2;
        int count = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int current = queue.poll();
                
                if(current == n * n - 1) {
                    return count;
                }

                for(int j = 1; j <= 6; j++) {
                    int child = current + j;

                    if(child <= n * n - 1 && arr[child] != -2) {
                        if(arr[child] != -1) {
                            queue.add(arr[child]);
                        } else {
                            queue.add(child);
                        }  
                        arr[child] = -2;
                    }

                }                
            }
            count++;
        }
        
        return -1;
    }
}