public class Solution {
    // SC => O(m*n)
    // TC => O(m*n)
    public int SnakesAndLadders(int[][] board) {
        if(board == null || board.Length == 0){
            return 0;
        }
        
        Queue<int> queue = new Queue<int>();
        int n = board.Length, index = 0, column = 0;
        int row = n -1, even = 0, level = 0;
        int[] array = new int[ n * n ];

        while(index < n * n){
            if(board[row][column] != -1){
                array[index] = board[row][column] - 1;
            }
            else{
                array[index] = -1;
            }
            index++;

            if(even % 2 == 0){
                column++;
                if(column == n){
                    row--;
                    column = n-1;
                    even++;
                }
            }
            else{
                column--;
                if(column == -1){
                    row--;
                    column = 0;
                    even++;
                }
            }
        }

        queue.Enqueue(0);
        while(queue.Count > 0){
            int size = queue.Count;
            for(int i = 0; i< size; i++){
                var current = queue.Dequeue();
                 if(current == (n*n) - 1){
                        return level;
                    }
                for(int j = 1; j<= 6; j++){
                    var child = current + j;
                   
                    if(child >= n*n || array[child] == -2){
                        continue;
                    }
                    if(array[child] == -1){
                        queue.Enqueue(child);
                        array[child] = -2;
                    }
                    else{
                        queue.Enqueue(array[child]);
                        array[child] = -2;
                    }
                }
            }
            level++;
        }
        return -1;
    }
}