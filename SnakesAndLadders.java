import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {
     //bfs no flatten o(n^2)
    public int snakesAndLadders(int[][] board) {
        int n = board.length, m = board[0].length;
        int[] arr = new int[n*m];
        boolean flag = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        int level = 0; 
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int currIdx = q.poll();
                for(int k = 1; k <= 6; k++){
                    int newIdx = currIdx + k;
                    int curr[] = getIndex(newIdx,n);
                    int r = curr[0], c = curr[1];
                    if(newIdx == n*n - 1 || board[r][c] == n*n){
                        return level+1;
                    }

                    if(board[r][c] != -2){
                        if(board[r][c] == -1){
                            q.offer(newIdx);
                        }else{
                            q.offer(board[r][c]-1);
                        }
                        board[r][c] = -2;
                    }
                }
            }
            level++;
        }

        return -1;
    }

    public int[] getIndex(int idx, int n){
        int r = idx / n;
        int c = idx % n;
        if(r % 2 != 0){
            //left to right only for odd index 
            c = n - 1 - c;
        }
        r = n -1 - r;
        return new int[]{r,c};
    }


    //bfs - flatten o(n^2)
    // public int snakesAndLadders(int[][] board) {
    //     int n = board.length, m = board[0].length;
    //     int[] arr = new int[n*m];
    //     boolean flag = true;
    //     int r = n-1, c = 0;
    //     for(int i = 0; i < m*n; i++){
    //         if(board[r][c] == -1){
    //             arr[i] = board[r][c];
    //         }else{
    //             arr[i] = board[r][c]-1;
    //         }

    //         if(flag){
    //             //moving left to right
    //             c++;
    //             if(c == n){
    //                 //right to left 
    //                 flag = false;
    //                 c = n-1;
    //                 r--;
    //             }
    //         }else{
    //             c--;
    //             if(c == -1){
    //                 flag = true;
    //                 c = 0;
    //                 r--;
    //             }
    //         }
    //     }

    //     Queue<Integer> q = new LinkedList<>();
    //     q.offer(0);
    //     int level = 0; 
    //     while(!q.isEmpty()){
    //         int size = q.size();
    //         for(int i = 0; i < size; i++){
    //             int currIdx = q.poll();
    //             for(int k = 1; k <= 6; k++){
    //                 int newIdx = currIdx + k;
    //                 if(newIdx == n*n - 1 || arr[newIdx] == n*n -1){
    //                     return level+1;
    //                 }

    //                 if(arr[newIdx] != -2){
    //                     if(arr[newIdx] == -1){
    //                         q.offer(newIdx);
    //                     }else{
    //                         q.offer(arr[newIdx]);
    //                     }
    //                     arr[newIdx] = -2;
    //                 }
    //             }
    //         }
    //         level++;
    //     }

    //     return -1;
    // }
}
