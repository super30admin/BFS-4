//Time Complexity: o(m*n)
//Space Complexity: o(m*n)
//Expln: First Flatten the whole 2d array into 1d so that we keep moving only in forward dir instead if we deal with 2d we move zigzag
// so move zigzag and store it in 1d. Also mark the indexes added in queue as -2. Move 6 elements each time which is the max of size
// and add the the index to queue if its -1 and if not -1 and -2 add the index where it is moved like 15 and 20 etc where ladder or
//snake takes us. So for each level when we process the queeue increment moves. Return moves when we hit destination.
class Solution {
    public int snakesAndLadders(int[][] board) {
        if(board.length == 0) return 0;
        int m = board.length;
        int n = board[0].length;
        int[] arr = new int[m * n];
        
        boolean flag = false; // indicating starting to move from r -> l
        int index = 0;
        for(int i = m -1; i >=0 ; i--)
        {
            if(!flag)
            {
                for(int j = 0; j < n; j++)
                {
                    arr[index] = board[i][j];
                    index++;
                }
                flag = true;
            }
            else
            {
                for(int j = n-1; j >= 0; j--)
                {
                    arr[index] = board[i][j];
                    index++;
                }
                flag = false;        
            }
        }
        int destination = arr.length - 1;
        Queue<Integer> q = new LinkedList<>();
        q.add(0); 
        arr[0] = -2;
        int moves = 0;
        while(!q.isEmpty())
        {
            int size = q.size();
            for(int i = 0; i < size; i++)
            {
                int curr  = q.poll();
                if(curr == destination) return moves;
                for(int k =1; k <= 6; k++)
                {
                    int val = curr+k;
                    if(val <= destination && arr[val] != -2){
                        if(arr[val] == -1) {
                            q.add(val);
                        }
                        else
                        {
                            q.add(arr[val] -1);
                        }
                        arr[val] = -2;
                    }
                }
            }
            moves++;
        }  
        return -1;
    }
}