// Time Complexity : O(n ^ 2)
// Space Complexity : O(n ^ 2)

class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0)
            return -1;
        int n = board.length;
        int[] b = new int[n * n];
        int i = n - 1;
        int j = 0;
        int idx = 0;
        int even = 0;
        int cnt = 0;
        while(i >= 0 && j >= 0){
            if(board[i][j] != -1)
                b[idx] = board[i][j] - 1;
            else
                b[idx] = board[i][j];
            idx++;
            if(even % 2 == 0) {
                j++;
                if(j == n){
                    j--;
                    i--;
                    even++;
                }
            }
            else {
                j--;
                if(j < 0){
                    j++;
                    i--;
                    even++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        b[0] = -2;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int l = 0; l < size; l++) {
                int cur = q.poll();
                if(cur == n * n - 1)
                    return cnt;
                for(int k = 1; k <= 6; k++) {
                    int newCur = cur + k;
                    if(newCur > n * n - 1)
                        break;
                    if(b[newCur] != -2){
                        if(b[newCur] == - 1)
                            q.add(newCur);
                        else
                            q.add(b[newCur]);
                        b[newCur] = -2;
                    }
                }
            }
            cnt++;
        }
        return -1;
    }
}