import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/*
Time Complexity: O(N*M), N is the number of rows, and M is the number of columns
Space Complexity: O(N*M), size of Queue to process every click
Run on Leetcode: yes
Any difficulties: no

Approach:
1. Using BFS to iterate over the given minesweeper board, calculating
 */
public class MinesweeperGame {
    public static int[][] directions = {
            {1,0},
            {0,1},
            {-1,0},
            {0,-1},
            {1,1},
            {-1,1},
            {1,-1},
            {-1,-1}
    };
    public static char[][] minesweeper(char[][] board, int[] click){
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        int n = board.length;
        int m = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // Adding first click
        queue.add(click);

        // Updating board with Blanked Space as 'B'
        board[click[0]][click[1]] = 'B';

        // Breadth First Search on the queue

        while(!queue.isEmpty()){
            // removing first click to process accordingly
            int []curr = queue.remove();

            // calculating number of mines for the current click
            int numOfMines = countNumberOfMines(board, curr);

            /*
                if number of mines is 0 then traversing over the board in 8 directions in order to find out empty
                'E' cell, whenever encounter 'E' cell, updating that cell with 'B' cell

                Otherwise updating cell with the number of mines
             */
            if(numOfMines == 0){
                for(int[] dir: directions){
                    int x = dir[0]+curr[0];
                    int y = dir[1]+curr[1];

                    if(x>=0 && x<n && y>=0 && y<m && board[x][y] == 'E'){
                        queue.add(new int[]{x,y});
                        board[x][y] = 'B';
                    }
                }
            }else{
                board[curr[0]][curr[1]] = (char)('0'+ numOfMines);
            }

        }
        return board;
    }

    public static int countNumberOfMines(char[][] board, int[] curr){
        int n = board.length;
        int m = board[0].length;

        int numberOfMines = 0;

        for(int[] dir: directions){
            int x = dir[0]+curr[0];
            int y = dir[1]+curr[1];
            if(x>=0 && x<n && y>=0 && y<m && board[x][y] == 'M'){
                numberOfMines++;
            }
        }
        return numberOfMines;
    }
    public static void main(String[] args){
        char[][] board = {
                {'E','E','E','E','E'},
                {'E','E','M','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'}
        };

        System.out.println("Minesweeper output: ");

        char[][] result = minesweeper(board, new int[]{3,0});

        System.out.println(Arrays.toString(result[0]));
        System.out.println(Arrays.toString(result[1]));
        System.out.println(Arrays.toString(result[2]));
        System.out.println(Arrays.toString(result[3]));
    }
}
