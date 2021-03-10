class Solution {
    public int snakesAndLadders(int[][] board) {
        int r = board.length;
        int[] arr = new int[r*r];
        int ind = 0;
        int i = r-1, j=0;
        int even = 0;
//Flattening the 2d array into a 1d array
        while(i>=0 && j>=0){
            arr[ind] = board[i][j]-1;
            ind++;
            if(even%2 == 0){
                j++;
                if(j==r){
                    i--;
                    j--;
                    even++;
                }
            }
            else{
                j--;
                if(j<0){
                    i--;
                    j++;
                    even++;
                }
            }
        }
//initializing a queue for bfs tarversal and adding the first index
        Queue<Integer> q = new LinkedList<>();
//if first index is -2, it implies to add the index and if it has a number > 0 then add that value
        if(arr[0] == -2)
            q.add(0);
        else
            q.add(arr[0]);
//-3 implies that the cell is viisted
        arr[0] = -3;
        int min = 1;
        while(!q.isEmpty()){
            int size = q.size();
            System.out.println("size "+size);
//loop over all queue elements to complete one round of bfs and increment min
            for(int k=0;k<size;k++){
                int num = q.poll();
                for(i = num+1; i<=Math.min(num+6, r*r-1); i++){
//if the last index is reached or the value in curent index leads to final value then return the min steps
                    if(i==(r*r)-1 || arr[i] == (r*r)-1)
                        return min;
//if the cell is not viisted then add value if arr[i]>0 else add the index and mark the cell as visited
                    if(arr[i] == -2){
                        q.add(i);
                        arr[i] = -3;
                    }
                    else if(arr[i]!=-3){
                        q.add(arr[i]);
                        arr[i] = -3;
                    }
                }
            }
            min++;
        }
//if we can't reach the end then return -1
        return -1;
    }
}


//Time complexity : O(N^2)
//Space complexity : O(N^2)
//Time complexity : O(N^2)
//Space complexity : O(N^2)
