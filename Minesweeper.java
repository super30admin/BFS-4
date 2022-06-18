
//BFS approach
class Solution {

//Time Complexity: 0(m*n) where ms is the length of the row and n is the length of the column
    //Space Complexity: 0(m*n)
    //Did it successfully run on leetcode: Yes
    //Did you face any problem while coding: no

    //In brief explain your code:

    int dirs[][];   //taking dirs array to store all possible 8 direction
    int m, n;   //m is the row length and n is the column length
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0){
            return board;
        }
        m = board.length;
        n = board[0].length;
        dirs = new int[][]{{-1, 0}, {-1, -1}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {1,0}};
        Queue<int []> q = new LinkedList<>();   //queue to run bfs and put unprocessed indexes in it
        if(board[click[0]][click[1]] == 'M'){   //if the 1st click is mine then return
            board[click[0]][click[1]] = 'X';
            return board;
        }
        q.add(new int[]{click[0], click[1]});   //adding the 1st click to my queue
        board[click[0]][click[1]] = 'B';    //marking it as visited
        while(!q.isEmpty()){
            int [] curr = q.poll();
            int count = countMines(board, curr[0], curr[1]);    //counting the mines around it
            if(count > 0){  //if there are mines around the index
                board[curr[0]][curr[1]] = (char)(count + '0');  //adding the no. of mines to the current index
            }
            else{   //else checking in all 8 directions for the unprocessed indexes
                for(int [] dir: dirs){
                    int r = dir[0] + curr[0];   //getting new row and column
                    int c = dir[1] + curr[1];
                    if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E'){ //and if the status at that index is E means it's not processed so adding it to the queue
                        q.add(new int []{r,c});
                        board[r][c] = 'B';  //and marking it as visited
                    }
                }
            }
        }
        return board;   //returning the state of the board
    }
    public int countMines(char[][] board, int i, int j){
        int count = 0;  //to count the total no. of mines
        for(int [] dir: dirs){
            int r = i + dir[0];
            int c = j + dir[1];
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M'){ //if the character at that index says mines, that means it has mines
                count++;    //increasing the count of mines by 1
            }
        }
        return count;   //returning total no. of mines
    }
}

//DFS

class Solution {
    int dirs[][];
    int m, n;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board == null || board.length == 0){
            return board;
        }
        m = board.length;
        n = board[0].length;
        dirs = new int[][]{{-1, 0}, {-1, -1}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {1,0}};
        if(board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
        }
        dfs(board, click);
        return board;
    }
    public void dfs(char[][] board, int [] click){
        //base
        if(click[0] < 0 || click[1] < 0 || click[0] == m || click[1] == n || board[click[0]][click[1]] != 'E'){
            return;
        }
        //logic
        int count = countMines(board, click[0], click[1]);
        board[click[0]][click[1]] = 'B';
        if(count > 0){
            board[click[0]][click[1]] = (char)(count + '0');
        }
        else{
            for(int [] dir: dirs){
                int r = dir[0] + click[0];
                int c = dir[1] + click[1];
                dfs(board, new int []{r,c});
            }
        }

    }
    public int countMines(char[][] board, int i, int j){
        int count = 0;
        for(int [] dir: dirs){
            int r = i + dir[0];
            int c = j + dir[1];
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M'){
                count++;
            }
        }
        return count;
    }
}