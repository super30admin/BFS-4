import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
//TimeComplexity: O(MXN)
//Space complexity: O(MXN)
class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        //flatten the board
        boolean leftToRight = true;
        int n = board.length;
        int row = n-1;
        int col = 0;
        int[] boardFlatten = new int[n*n];

        for(int i = 0;  i < n*n; i++){
            if(leftToRight){
                if(board[row][col] != -1)
                    boardFlatten[i] = board[row][col] - 1;
                else
                    boardFlatten[i] = board[row][col];
                col++;
                if(col == n){
                    leftToRight = !leftToRight;
                    col--;
                    row--;
                }

            }
            else{
                if(board[row][col] != -1)
                    boardFlatten[i] = board[row][col] - 1;
                else
                    boardFlatten[i] = board[row][col];
                col--;
                if(col < 0 ){
                    leftToRight = !leftToRight;
                    col++;
                    row--;
                }
            }
        }


        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        queue.offer(0);
        set.add(0);
        int minMoves = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                int loc = queue.poll();
                if(loc == (n*n)-1) return minMoves;
                for(int j = 1; j< 7 && loc+j < n*n; j++){
                    if(boardFlatten[loc + j] == -1){
                        if(!set.contains(loc+j)){
                            queue.offer(loc+j);
                            set.add(loc+j);
                        }

                    }else{
                        if(!set.contains(boardFlatten[loc+j])){
                            queue.offer(boardFlatten[loc+j]);
                            set.add(boardFlatten[loc+j]);
                        }

                    }

                }
            }
            minMoves++;
        }


        return -1;

    }

}