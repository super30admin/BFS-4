//Time: O(6 * n * n)
//Space: O(n * n)

//BFS Solution

class Solution {
    public int snakesAndLadders(int[][] board) {
        //null case check
        if(board == null || board.length == 0) return 0;
        int n = board.length;
    
        //we will first start by converting this 2D matrix to a 1D array
        int [] arr = new int [n*n];//***************
        int idx = 0;//index that we will use to iterate through the 1D array
        int evenCount = 0; int r = n-1; int c = 0; //initializing r and c to the bottom left element
        
        //we loop until we reach the last element
        while(idx < n*n) {
            
            if(board[r][c] >= 1) {
                arr[idx] = board[r][c] - 1; //*********
            } else {
                arr[idx] = board[r][c]; 
            }
            idx++;
            
            if(evenCount%2 == 0) {
                c++;
                if(c == n) {
                    c--; r--;
                    evenCount++;
                } 
                
            } else {
                c--;
                if(c == -1) {
                    c++; r--;
                    evenCount++;
                } 
            }
        }
        
        //now the 1D array is filled
        //performing bfs now
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0); arr[0] = -2;//setting the first element in arr as visited
        int count = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int curr = q.poll();
                for(int j = 1; j <= 6; j++) {
                    int child = curr + j;
                    
                    //if(child > n*n - 1) break;
                    
                    if(arr[child] != -2) {
                        if(arr[child] == -1) {
                            if(child == n*n - 1) return count + 1;
                            q.add(child);
                        } else {
                            if(arr[child] == n*n - 1) return count + 1;
                            q.add(arr[child]);
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
