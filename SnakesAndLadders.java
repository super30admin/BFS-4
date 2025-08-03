// Time Complexity : O(n*n)
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
// Approach : Translate the 2d board into flattened array. USe BFS. Add 1st position to queue. Mark it visted in flattened array using -2
/// For each element in queue, check next positions If is ladder or snake, add their end position to queue. Mark all 6 cells curr+1->curr+6 as visited.
/// Repeat until we reach end of board. Return the number of levels we raversed to reach end
class Solution {
    public int snakesAndLadders(int[][] board) {
        //flatten board to 1D array
        int n = board.length;
        int arr[] = new int[n*n];
        boolean flag = true; //if true, R->L else L->R
        int idx = 0;
        int r = n-1, c=0;
        while(idx<n*n){
            if(flag){
                while(c<n){
                    if(board[r][c]!=-1){
                        board[r][c]--;
                    }
                    arr[idx++] = board[r][c];
                    c++;
                }
                flag = false;
                r--;
                c--;
            }
            else{
                while(c>=0){
                    if(board[r][c]!=-1){
                        board[r][c]--;
                    }
                    arr[idx++] = board[r][c];
                    c--;
                }
                flag = true;
                r--;
                c++;
            }
        }
        System.out.println(Arrays.toString(arr));
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        arr[0] = -2;
        int moves = 0;
        while(!q.isEmpty()){
            int size = q.size();
            System.out.println(q);
            for(int i=0;i<size;i++){
                int curr = q.poll();
                if(curr == n*n-1){
                    return moves;
                }
                for(int d = 1; d<=6;d++){
                    int newIdx = curr +d;
                    if(newIdx<n*n){
                        if(arr[newIdx] != -2){
                            if(arr[newIdx] == -1){
                                q.add(newIdx);
                                
                            }
                            else{
                                q.add(arr[newIdx]);
                            }
                            arr[newIdx] = -2;
                        }
                    }
                }
            
            }
            moves++;
        }
        return -1;
    }
    
}