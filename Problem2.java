
// time - O(n^2)
// space - O(n)

class Solution {

    public int snakesAndLadders(int[][] board) {

        if(board == null || board.length == 0) return 0;
        int r = board.length;
        int [] moves = new int[r*r]; // add elements to 1D array from 2D array
        Queue<Integer> q = new LinkedList<>();

        int i = r - 1; // bottom left row
        int j = 0; // first column
        int even = 0; // maintain even & odd row to identify direction
        int idx = 0;
        while(i >= 0 && j >= 0) {

            if(board[i][j] == -1) { // if the elements at ith & jth position is -1 add -1 to 1D array
                moves[idx] = -1;
            }
            else {
                moves[idx] = board[i][j] - 1; // add element's value - 1
            }
            idx++;

            if(even % 2 == 0) { // if it is even row

                j++; // column++
                if(j == r) { // j reached last element of the column
                    i--; // move up
                    even++; // increment even
                    j--;   // move one level up
                }
            }
            else { // if it is odd row
                j--; // move left
                if(j < 0) { // if it hits > first row
                    i--; // move up
                    even++; // increment even
                    j++; // move towards right
                }
            }
        }

        q.add(0); // add index to the queue
        moves[0] = -2; // mark visited elements in moves array to -2
        int minMoves = 0; // calculate the dice moves to reach destination
        while(!q.isEmpty()) {
            int size = q.size();
            for(int l = 0; l < size; l++) {
                int curr = q.poll();
                if(curr == r*r - 1) return minMoves; // if it reaches the [0,0] return minMoves
                for(int k = 1; k < 7; k++) {
                    int child = curr + k; // 6 dice roles
                    if(child > r*r - 1) break; // if element is at 34th position and the dice roll is 6 then it should break out of for loop
                    if(moves[child] != -2) {
                        if(moves[child] == -1) { // add to the queue corresponding index
                            q.add(child);
                        }
                        else {
                            q.add(moves[child]); // add to the queue corresponding value of child
                        }
                        moves[child] = -2; // mark the element as visited
                    }
                }
            }
            minMoves++; // increment moves pointer
        }
        return -1;
    }
}