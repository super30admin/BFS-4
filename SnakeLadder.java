class Solution {

    //Time Complexity: 0(n^2) where n is the size of the board
    //Space Complexity: 0(n^2)
    //Did it successfully run on leetcode: Yes
    //Did you face any problem while coding: no

    //In brief explain your code:

    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length == 0){
            return -1;
        }
        int n = board.length;
        int [] moves = new int [n * n]; //transforming the 2-d matrix to 1-d
        int r = n-1;    //I start from last row as the game starts from bottom and moves to top
        int c = 0;  //column starts from 0, goes upto the last column then again column gets-- and comes down to 0, then again goes from right to left
        int even = 0;   //to check if the column goes from right to left or left to right
        int index = 0;  //to traverse through moves matrix adding the values
        while(r >= 0 && c >= 0){    //till indexes are valid
            if(board[r][c] == -1){  //if the current index holds -1, I add it to my moves array
                moves[index] = -1;
            }
            else{
                moves[index] = board[r][c] -1;  //if it holds a snake or a ladder, I add that index to my moves array, -1 is done as array starts from 0
            }
            index++;    //increasing the index of moves array to store the next entry
            if(even % 2 == 0){  //if it it even means column moves from right to left and goes
                c++;    //increasing the column
                if(c == n){ //if the column and column length become the same, then
                    even++; //change the direction and move from left to right
                    c--;    //decrease column count as it becomes n
                    r--;    //process next row
                }
            }
            else{
                c--;    //moving left to right, column decreases
                if(c == -1){    //if column value becomes -1, means the end of the board and column again moves from left to right
                    c++;    //sinve value of column is -1, I get it down to 0 so that it doesn't go out of bounds
                    even++; //make the direction positive
                    r--;    //process the next row
                }
            }
        }
        Queue<Integer> q = new LinkedList<>();  //queue to run bfs where I will store the index for next processing
        q.add(0);   //adding 1st index to the queue
        int count = 0;  //counts the level or gives out the result
        moves[0] = -2;  //mark 1st column as visited
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int curr = q.poll();
                if(curr == n * n - 1){  //if I am at the last index, means I have completed the game and return the no. of chances
                    return count;
                }
                for(int j = 1; j <= 6; j++){    //for each dice roll, there are 6 possibilities
                    int child = j + curr;   //add the digit that I got after rolling the dice to the current index that has popped out of the queue
                    if(child >= n*n){   //It can happen that index can go out of bounds. For example if I am at 33 index and got a 6, 39 is not a valid index so I won't add it to my queue
                        continue;
                    }
                    if(moves[child] != -2){ //if the index is not already seen then only I process
                        if(moves[child] == -1){ //if the index holds -1
                            q.add(child);   // it means I need to process it
                        }
                        else{
                            q.add(moves[child]);    //if there is a snake or a ladder, I need to jump to that index
                        }
                        moves[child] = -2;  //marking the index as visited once I process it
                    }
                }
            }
            count++;    //increasing the level
        }
        return -1;  //this case will not be hit
    }
}