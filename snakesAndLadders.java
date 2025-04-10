// Problem 2 Snakes and ladders (https://leetcode.com/problems/snakes-and-ladders/)

// Time Complexity : O(n*n) 
// Space Complexity : O(n*n) 
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach in three sentences only
/*
 * Here, take all elements as a 1D array. Then start at 0th index and if element at index is -1 add index to the queue. Else check if element
 * is not euqal to -2, if its not then add it to queue and make arr[newIdx] to -2 and increase no.of moves after each level. If newIdx is >=n*n-1
 * return moves.
 */
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int [] arr = new int[n*n];
        boolean dir = true;
        int r = n-1; int c = 0;
        int idx = 0;
        while(idx < n*n){
            if(board[r][c] == -1){
                arr[idx] = board[r][c];
            }
            else{
                arr[idx] = board[r][c] - 1;
            }
            idx++;
            // r c is used
            if(dir){ // left to right
                c++;
                if(c > n-1){
                    dir = false;
                    r--;
                    c--;
                }
            }
            else{
                c--;
                if(c<0){
                    dir = true;
                    r--;
                    c++;
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        int moves = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curr = q.poll();
                if(curr == n*n-1) return moves;
                for(int j = 1; j <= 6; j++){
                    int newIdx = curr + j;
                    if(newIdx > n*n-1) break;
                    if (arr[newIdx] == -2) continue;
                    if(arr[newIdx] == -1){
                        q.add(newIdx);
                    }
                    else{
                        if(arr[newIdx] !=-2){
                            q.add(arr[newIdx]);
                        }
                    }
                    arr[newIdx] = -2;
                }
            }
            moves++;
        }
        return -1;
    }
}
