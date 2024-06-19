// Time Complexity : O(N^2)
// Space Complexity : O(N^2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] arr = new int[n*n];
        int r = n-1;
        int c = 0;
        int i=0;
        boolean flag = true; //left to right

        while(i < n*n){
            if(board[r][c] == -1){
                arr[i]= -1;
            }else{
                arr[i] = board[r][c] -1;
            }
            i++;
            if(flag){
                c++;
                if(c == n){
                    r--;
                    c=n-1;
                    flag = false;
                }
            }else{
                c--;
                if(c == -1){
                    r--;
                    c=0;
                    flag= true;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        int moves = 0;

        while(!q.isEmpty()){
            int size = q.size();

            for(int j=0;j<size;j++){
                int curr = q.poll();
                for(int k=1;k<=6;k++){
                    int newIdx = curr + k;
                    if(newIdx == n*n-1 || arr[newIdx] == n*n-1){
                        return moves+1;
                    }
                    if(arr[newIdx] != -2){
                        if(arr[newIdx] == -1){
                            q.add(newIdx);
                        }else{
                            q.add(arr[newIdx]);
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