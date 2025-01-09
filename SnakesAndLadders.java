// Approach - convert the board into a 1D Array and perform a normal BFS (level order traversal)
// Time : O(n*n)
// space : O(n*n)
class Solution {
    public int snakesAndLadders(int[][] board) {
        // To convert the board need a 1D Array
        int n = board.length;
        int[] arr = new int[n*n];
        boolean flag = true; // initial left to right

        // Mark starting position in the board
        int r = n-1;
        int c=0;

        // Traverse n*n times to fill the arr
        for(int i=0;i<n*n;i++){
            if(board[r][c] == -1){
                arr[i] = board[r][c]; // regular position
            } else {
                arr[i] = board[r][c] -1; // ladder or snake
            }
            if(flag){
                // moving from left to right
                c++;
                // while moving if you reach to right most end and exit out
                if(c == n){
                    // Go up & move from right to left
                    flag = false;
                    c=n-1;
                    r--;
                }
            } else{
                // moving from right to left
                c--;
                // while moving if you reach left most end and exit out
                if(c == -1){
                    // Go up and move from left to left;
                    flag = true;
                    r--;
                    c=0;
                }
            }
        }

        System.out.println(Arrays.toString(arr));

        // Now perform BFS - level order traversal
        int level =0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0); // add initial position to queue without even rolling the dice
        // we can mark as visited using same inout matrix
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int currIdx = q.poll(); 
                // roll the dice
                for(int k=1;k<=6;k++){
                    int newIdx = currIdx + k;
                    // After roll the dice if we (currIdx + k) lands us on destination -> n*n -1
                    // If we(newIdx) come cross a ladder and that takes us directly to destination
                    if(newIdx == n*n-1 || arr[newIdx] == n*n-1){
                        return level+1;
                    }else{
                       // simply add newIdx to the queue
                       // but check before we add it to visited or not
                       // -2 - visited
                       if(arr[newIdx] != -2){
                        if(arr[newIdx] == -1){
                            q.add(newIdx);
                        } else{
                            // if newIdx landing on a number implies we are climbing the ladders
                            q.add(arr[newIdx]);
                        }
                        // mark index as visited
                       arr[newIdx] = -2;
                       }

                    }
                }
            }
            level++;
        }
    return -1;
    }
}


// Approach - BFS without converting into 1D array
// Time : O(n*n)
// space : O(n*n)
class Solution {
    public int snakesAndLadders(int[][] board) {
        // // To convert the board need a 1D Array
        int n = board.length;
        // int[] arr = new int[n*n];
        // boolean flag = true; // initial left to right

        // // Mark starting position in the board
        // int r = n-1;
        // int c=0;

        // // Traverse n*n times to fill the arr
        // for(int i=0;i<n*n;i++){
        //     if(board[r][c] == -1){
        //         arr[i] = board[r][c]; // regular position
        //     } else {
        //         arr[i] = board[r][c] -1; // ladder or snake
        //     }
        //     if(flag){
        //         // moving from left to right
        //         c++;
        //         // while moving if you reach to right most end and exit out
        //         if(c == n){
        //             // Go up & move from right to left
        //             flag = false;
        //             c=n-1;
        //             r--;
        //         }
        //     } else{
        //         // moving from right to left
        //         c--;
        //         // while moving if you reach left most end and exit out
        //         if(c == -1){
        //             // Go up and move from left to left;
        //             flag = true;
        //             r--;
        //             c=0;
        //         }
        //     }
        // }

        // System.out.println(Arrays.toString(arr));

        // Now perform BFS - level order traversal
        int level =0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0); // add initial position to queue without even rolling the dice
        // we can mark as visited using same inout matrix
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int currIdx = q.poll(); 
                // roll the dice
                for(int k=1;k<=6;k++){
                    int newIdx = currIdx + k; // 1D 
                    int[] pos = getIdx(newIdx, n); // 2D
                    int r = pos[0];
                    int c = pos[1];
                    // After roll the dice if we (currIdx + k) lands us on destination -> n*n -1
                    // If we(newIdx) come cross a ladder and that takes us directly to destination
                    if(newIdx == n*n-1 || board[r][c] == n*n){
                        return level+1;
                    }else{
                       // simply add newIdx to the queue
                       // but check before we add it to visited or not
                       // -2 - visited
                       if(board[r][c] != -2){
                        if(board[r][c] == -1){
                            q.add(newIdx);
                        } else{
                            // if newIdx landing on a number implies we are climbing the ladders
                            q.add(board[r][c]-1);
                        }
                        // mark index as visited
                       board[r][c] = -2;
                       }

                    }
                }
            }
            level++;
        }
    return -1;
    }

    private int[] getIdx(int idx, int n){
        // If you have rowIdx from bottom to top 
        // then case of even row numbers then column traversal is left to right
        // then case of odd row numbers then column traversal is right to left
        // So if it traversal is from left to right then column calculation is as usual ()
        // So if it traversal is from right to left then column calculation is n-1-c ()
        

        //normal calculation - bottom to top traversal
        int r = idx/n; // bottom to top
        int c = idx%n; // bottom to top

        
        
        if(r%2 != 0){
            // odd case - right to left traversal.
            c=n-1-c;
        }
        r = n-1-r; 

        return new int[]{r,c};
    }
}