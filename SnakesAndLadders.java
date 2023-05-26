import java.util.*;
public class SnakesAndLadders {
    // TC = O(n^2) SC = O(n^2)
    public int snakesAndLadders(int[][] board) {
        if(board==null||board.length==0)return 0;
        int n = board.length;
        int[] arr = new int[n*n];
        int idx = 0;
        int dir = 0;
        int i = n-1; int j=0;
        while(idx<n*n){
            if(board[i][j]==-1){
                arr[idx] = -1;
            }
            else{
                arr[idx] = board[i][j]-1;
            }
            idx++;
            if(dir%2==0){
                j++;
                if(j==n){
                    i--;
                    dir++;
                    j--;
                }
            }
            else{
                j--;
                if(j==-1){
                    i--;
                    j++;
                    dir++;
                }
            }
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(0); arr[0] = -2;
        int moves=0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int l=0;l<size;l++){
                int curr = q.poll();
                if(curr == n*n - 1)return moves;
                for(int k=1;k<7;k++){
                    int child = curr+k;
                    if(child<n*n && arr[child]!=-2){
                        if(arr[child] == -1){
                            q.add(child);
                        }
                        else{
                            q.add(arr[child]);
                        }
                        arr[child] = -2;
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}
