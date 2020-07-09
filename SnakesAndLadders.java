//BFS
//Time - O(n * n)
// Space - O(n * n)
class Solution {
    public int SnakesAndLadders(int[][] board) {
        int n = board.length;
        int r = n - 1, c = 0;
        
        int i = r, j = c;
        int len = n * n;
        int[] flattenBoard = new int[len];
        int idx = 0;
        boolean leftToRight = true;
        while(i >= 0 && j >= 0){
            if(board[i][j] == -1){
                flattenBoard[idx++] = -1;
            }else{
                flattenBoard[idx++] = board[i][j] - 1;
            }
            
            if(leftToRight){
                j++;
            }else{
                j--;
            }
            
            if(j >= n){
                j--;
                i--;
                leftToRight = !leftToRight;
            }
            
            if(j < 0) {
                j++;
                i--;
                leftToRight = !leftToRight;
            }
        }
        
        int minSteps = 0;
        Queue<Integer> q = new LinkedList<>();
        //Mark visited and add it to the queue
        flattenBoard[0] = -2;
        
        q.add(0);
        while(!q.isEmpty()){
            int size = q.size();
            for(i = 0; i < size; i++){
                int curr = q.poll();
                if(curr == len - 1) return minSteps;
                for(j = 1; j <= 6; j++){
                    int next = curr + j;
                    if(next < len && flattenBoard[next] != -2){
                        
                        if(flattenBoard[next] == -1){
                            q.add(next);
                            
                        }else{
                             q.add(flattenBoard[next]);
                        }
                        //Mark visited and add it to the queue
                        flattenBoard[next] = -2;
                        
                    }
                }
            }
            minSteps++;
        }
        
        
        return -1;
    }
}