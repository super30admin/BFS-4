//Time Complexity = O(n*n) {each cell has 6 options, but TC isn't 6^n because we add just the new entry
//Space Complexity = O(n*n)
//Was able to run in LeetCode: yes

package com.madhurima;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadders {
}

class SnakeAndLaddersBFS {

    // using bfs
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0){
            return 0;
        }

        int n = board.length;
        int[] grid = new int[n*n];
        int row = n-1;
        int col = 0;
        int index = 0;
        int even = 0;

        Queue<Integer> q = new LinkedList<>();
        int moves = 0;

        //flatten 2d array into 1d array
        while(index < n*n){
            if(board[row][col] == -1){
                grid[index] = -1;
            }else{
                grid[index] = board[row][col] - 1;
            }
            index++;
            if(even%2 == 0){
                col++;
                if(col == n){
                    col--;
                    row--;
                    even++;
                }
            }else{
                col--;
                if(col == -1){
                    col++;
                    row--;
                    even--;
                }
            }
        }

        q.add(0); //adding 0th index of grid

        //start bfs
        while(!q.isEmpty()){
            int size = q.size();

            for(int i = 0; i < size; i++){ //number of options for one move
                int curr  = q.poll();
                if(curr == n*n-1){
                    return moves;
                }
                //simulating a dice roll
                for(int j = 1; j <= 6; j++){ //if we pick that option, we can land in 6 places - add them to queue
                    int baby = curr + j;
                    if(baby > n*n -1){
                        continue;
                    }
                    if(grid[baby] == -1){//normal cell
                        q.add(baby);
                        grid[baby] = -2; //visited
                    }else if(grid[baby] > 0){ //not -1 and -2
                        q.add(grid[baby]);
                        grid[baby] = -2;
                    }
                }
            }

            moves++;
        }

        return -1;

    }
}
