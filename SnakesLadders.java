//Time Complexity : O(n^2)
//Space Complexity : O(n^2)
class SnakesLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        int r = n-1, c = 0;
        int i = 0;
        boolean flag = true;
        while(i < n*n) {
            if(board[r][c] == -1) {
                arr[i] = -1;
            } else {
                arr[i] = board[r][c] - 1;
            }
            i++; 
            if(flag) {
                c++;
                if(c == n) {
                    r--;
                    c = n-1;
                    flag = false;
                }
            } else {
                c--;
                if(c == -1) {
                    r--;
                    c = 0;
                    flag = true;
                }
            }
        }
        int moves = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int j = 0; j < size; j++) {
                int currIdx = queue.poll();
                for(int k = 1; k <= 6; k++) {
                    int newIdx = currIdx + k;
                    if(newIdx == n*n-1 || arr[newIdx] == n*n-1) return moves+1;
                    if(arr[newIdx] != -2) {
                        if(arr[newIdx] == -1) {
                            queue.add(newIdx);
                        } else {
                            queue.add(arr[newIdx]);
                        }
                        arr[newIdx] = -2;
                    }
                }
                
            }
            moves++;
        }
        return -1;
    }
}