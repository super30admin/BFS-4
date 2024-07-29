import java.util.LinkedList;
import java.util.Queue;

//Time complexity: O(n * n)
//space complexity: O(n * n)
//Did this run on Leetcode: yes
public class SnakesAndLadders {
    //Using BFS
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0){
            return -1;
        }

        int n = board.length;
        boolean[] visited = new boolean[n * n + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;

        int steps = 0;
        int minSteps = Integer.MAX_VALUE;

        boolean destinationReached = false;
        int[] arr = new int[ n * n ];
        //flattenTheBoard(board, arr, n-1, 0);

        while(! q.isEmpty() && ! destinationReached){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int currentValue = q.poll();
                int[] currentCell = getCellFromValue(currentValue, n);

                int currentRow = currentCell[0];
                int currentCol = currentCell[1];

                if(currentValue == n * n){
                    return steps;
                }
                for(int j = 1; j <= 6; j++){
                    int nextValue = currentValue + j;
                    if( nextValue > n * n){
                        break;
                    }
                    //if there is a snake or ladder present
                    int[] nextCell = getCellFromValue(nextValue, n);
                    int nextRow = nextCell[0];
                    int nextCol = nextCell[1];

                    if(board[nextRow][nextCol] != -1){
                        nextValue = board[nextRow][nextCol];
                    }
                    if(! visited[nextValue]){
                        visited[nextValue] = true;
                        q.add(nextValue);
                    }
                }

            }
            steps++;
        }
        return -1;
    }

    private boolean isInBounds(int rowIdx, int colIdx, int n){
        return rowIdx <=0 && rowIdx < n && colIdx >=0 && colIdx < n;
    }

    private void flattenTheBoard(int[][] board, int[] arr, int rowIdx, int colIdx){
        int idx = 0;
        int numberOfRows = board.length;
        int numberOfCols = board[0].length;

        boolean goingLeftToRight = true;
        while(idx < numberOfRows * numberOfCols){
            if(board[rowIdx][colIdx] == -1){
                arr[idx] = -1;
            }else{
                arr[idx] = board[rowIdx][colIdx];
            }
            idx++;

            if(goingLeftToRight){
                colIdx++;
                if(colIdx == numberOfCols){
                    colIdx--;
                    rowIdx--;
                    goingLeftToRight = false;
                }
            }else{
                colIdx--;
                if(colIdx == -1){
                    colIdx++;
                    rowIdx--;
                    goingLeftToRight = true;
                }
            }
        }
    }

    private int[] getCellFromValue(int val, int n){
        int numberOfRows = n;
        int numberOfCols = n;

        int rowIdxFromBottom = (val-1) / numberOfCols;
        int rowIdx = (numberOfRows-1) - rowIdxFromBottom;

        int colIdx = (val-1) % numberOfCols;
        //odd Indexed rows from the bottom are filled right to left
        if(rowIdxFromBottom % 2 != 0){
            colIdx = numberOfCols - 1 - colIdx;
        }
        return new int[] {rowIdx, colIdx};
    }
}
