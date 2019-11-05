/*
The time complexity is O(m*n) and the space complexity is O(m*n) where m is length of the board and n is breadth of the board

Here, the intuition is to use bfs approch we start with the click index. For each index from the queue we check its eight neighbours
and see if there are any mines. If yes change the index to mine count. No, add the neighbour indexes which are not visited.

Yes, the solution passed all the test cases in leetcode.
 */
class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {

        //Direction array
        int[][] checkdir = {{-1,-1},{-1,0},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};

        int m = board.length; int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(click[0]); queue.add(click[1]);

        while(queue.size()>0){

            //Check if current index value is M
            int x = queue.poll(); int y = queue.poll();
            if(board[x][y]=='M'){
                board[x][y]='X';
                break;
            }
            else if(board[x][y]=='E'){ //If the index value is E
                int mines = 0;

                //Count the mines
                for(int[] each:checkdir){
                    int tempX = x+each[0]; int tempY = y+each[1];
                    if(tempX>=0 && tempX<m && tempY>=0 && tempY<n){
                        if(board[tempX][tempY]=='M'){
                            mines++;
                        }
                    }
                }

                //If no mines are present add the unvisited indexes to the queue.
                if(mines==0){
                    for(int[] each:checkdir){
                        int tempX = x+each[0]; int tempY = y+each[1];
                        if(tempX>=0 && tempX<m && tempY>=0 && tempY<n && !visited[tempX][tempY]){
                            queue.add(tempX); queue.add(tempY);
                            visited[tempX][tempY] = true;
                        }
                    }
                    board[x][y]='B';
                }
                else{//If mines are present change the value of the index
                    board[x][y] = (char)(mines+'0');
                }
            }

            visited[x][y]=true;
        }

        return board;
    }
}