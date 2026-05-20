/****
 * Flattens the board into a 1D array and runs BFS to find minimum dice throws.
 * Each cell is visited at most once using a queue and visited marking.
 * Time: O(n^2), Space: O(n^2)
 */

public class SnakesAndLadders {

        int n = board.length;

        int[] arr = new int[n * n];

        int r = n - 1;
        int c = 0;
        int i = 0;

        boolean flag = true;


        //flaten it to 1d array
        while (i < n * n) {
            if (board[r][c] == -1) {
                arr[i] = board[r][c];
            } else {
                arr[i] = board[r][c] - 1;
            }
            i++;

            if (flag) {
                c++;
                if (c == n) {
                    r--;
                    c--;
                    flag = false;
                } else {
                    c--;
                    if (c == -1) {
                        r--;
                        c++;
                        flag = true;
                    }
                }
            }
        }

        //perform BFS
        Queue<Integer> q = new LinkedList<>(); 
        q.add(0); 

        int level = 0; 

        while(!q.isEmpty()) {
            int size = q.size();
            for(int j = 0; j< size; j++) {
                int currIdx = q.poll(); 

                for(int k = 1; k <=6 ; k++) {
                    int newIdx = currIdx + k; 

                    if(newIdx == n *n-1 || arr[newIdx] == n*n-1) return level+1;

                    if(arr[newIdx] == -1) {
                        q.add(newIdx);
                    } else {
                        q.add(arr[newIdx]);
                    }
                    arr[newIdx] = -2;
                }
            }
            level++;
        }

        return -1;
    
}
