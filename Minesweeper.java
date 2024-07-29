import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Time complexity: O (m * n)
//space complexity: O(m * n) - for the visited matrix, can be optimized to O(1) if the input array can be mutated
//Did this run on Leetcode: yes
public class Minesweeper {
    private int[][] neighbors;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0){
            return board;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        this.neighbors = new int[][] {{0,-1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1,1}, {1,0}, {1,-1}};

        Queue<int[]> q = new LinkedList<>();
        visited[click[0]][click[1]] = true;
        q.add(click);

        while(! q.isEmpty()){
            int[] currentCell = q.poll();
            int currentRow = currentCell[0];
            int currentCol = currentCell[1];

            if(board[currentRow][currentCol] == 'M'){
                board[currentRow][currentCol] = 'X';
                break; //game over
            }else if(board[currentRow][currentCol] == 'E'){
                int adjacentMinesCount = 0;
                boolean mineFound = false;
                List<int[]> neighborsToExplore = new ArrayList<>();
                for(int[] neighbor : neighbors){
                    int nr = currentRow + neighbor[0];
                    int nc = currentCol + neighbor[1];

                    if(isInBounds(board, nr, nc)){
                        if(board[nr][nc] == 'M'){
                            adjacentMinesCount++;
                        }
                        if(! visited[nr][nc] && isUnRevealed(board, nr, nc)){
                            neighborsToExplore.add(new int[] {nr, nc});
                        }
                    }
                }
                if(adjacentMinesCount == 0){
                    board[currentRow][currentCol] =  'B';
                    for(int[] next : neighborsToExplore){
                        visited[next[0]][next[1]] = true;
                        q.add(next);
                    }
                }else{
                    board[currentRow][currentCol] =  (char) (adjacentMinesCount + '0');
                }
            }
        }
        return board;
    }

    private boolean isUnRevealed(char[][] board, int rowIdx, int colIdx){
        return board[rowIdx][colIdx] == 'E';
    }

    private boolean isInBounds(char[][] board, int rowIdx, int colIdx){
        return rowIdx >=0 && rowIdx < board.length && colIdx >=0 && colIdx < board[0].length;
    }
}
