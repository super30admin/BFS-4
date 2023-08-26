import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

public class SnakesAndLadders {

        // BFS - Conditional - Time O(n*n) and Space O(n*n)

        public int snakesAndLadders(int[][] board) {

            // board square dimension
            int n = board.length;

            // null case
            if(n == 0) {

                return 0;
            }

            // flattened board
            int[] flatArr = new int[n*n];       //  O(n*n) space

            // start from bottom-left of board
            int r = n - 1;      int c = 0;

            // true direction = left to right = rightwards
            boolean dir = true;

            int idx = 0;

            // building flattened board array

            while(idx < n*n) {              //  O(n*n)

                // if board element is not -1, there is snake/ ladder
                if(board[r][c] != -1) {

                    // destination of snake/ladder be inserted (-1 due to 0-indexed flattened array) in flattened array
                    flatArr[idx] = board[r][c] - 1;
                }

                // if board element is -1, keep the same board value (-1) in flattened array
                else {

                    flatArr[idx] = board[r][c];
                }

                // move to next index of flattened array to fill
                idx++;

                // updating row and column for iterator idx
                // if going rightwards
                if(dir) {

                    // increment column number
                    c++;

                    // if column goes out of upper bound
                    if(c == n) {

                        // bring column back in bounds
                        c--;

                        // go to previous row by decrementing row number
                        r--;

                        // make direction false for next traversal on board so that it runs leftwards
                        dir = false;
                    }
                }

                // if going leftwards
                else {

                    // decrement column number
                    c--;

                    // if column goes out of lower bound
                    if(c < 0) {

                        // bring column back in bounds
                        c++;

                        // go to previous row by decrementing row number
                        r--;

                        // make direction true for next traversal on board
                        dir = true;
                    }
                }

            }

            // Now the BFS run on Flattened board array indices

            // Queue of Flattened board array indices
            Queue<Integer> q = new LinkedList<>();

            q.add(0);
            // -2 means visited, make first index visited
            flatArr[0] = -2;

            // no moves before bfs run
            int moves = 0;

            // actual bfs starts
            while(!q.isEmpty()) {

                // size of level
                int sizeQ = q.size();

                // iterate over all indices in a level
                for(int i = 1; i <= sizeQ; i++) {

                    // to avoid null-pointer exception
                    int curr = 0;
                    if(!q.isEmpty()) {

                        // poll
                        curr = q.poll();
                    }

                    // possible 6-sided die roll results
                    for(int m = 1; m <= 6; m++) {

                        int next = curr + m;

                        // neglect if new index out of bounds
                        if(next > n*n) {

                            continue;
                        }

                        // if new index reaches the last index of flattened board array
                        if(next == n*n - 1) {

                            // output number of moves
                            return moves + 1;
                        }

                        // if new index position is not visited already
                        if(flatArr[next] != -2) {

                            // if it is not -1
                            if(flatArr[next] != -1) {

                                // check if reached square n^2
                                if(flatArr[next] == n*n -1) {

                                    // output if success
                                    return moves + 1;
                                }

                                // continue bfs if value at next index is not -1 and square n^2 not reached, go to destination of snake/ladder obtained from flattened array value at that index and add it to queue
                                q.add(flatArr[next]);

                            }

                            // if new index has -1 only, add it to queue
                            else{

                                // add next index to queue
                                q.add(next);
                            }

                            // make the next index of flattened array visited as it is processed for bfs
                            flatArr[next] = -2;
                        }
                    }
                }
                //increment number of moves after each level
                moves++;
            }

            //  output failure if square n^2 not reached after bfs
            return -1;
        }

        public static void main(String[] args) {

            SnakesAndLadders obj = new SnakesAndLadders();

            Scanner scanner = new Scanner(System.in);

            System.out.println("board size: ");
            int n = scanner.nextInt();

            int[][] board = new int[n][n];

            System.out.println("board: ");
            for(int i = 0; i < n; i++) {
                System.out.println("level " + (i+1) + ": ");
                for(int j = 0; j < n; j++) {

                    board[i][j] = scanner.nextInt();
                }
            }

            int answer = obj.snakesAndLadders(board);
            System.out.println("the least number of moves required to reach the square n^2: " + answer);
        }


}

/*
Time Complexity =  O(n*n)
Space Complexity =  O(n*n)
*/