//BFS
//Time Complexity : O(m*n)
//Space Complexity : O(m*n)
// Did this code successfully run on Leetcode :yes
// Your code here along with comments explaining your approach
class Solution {
    int[][] dirs;
    int row,col;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null||board.length==0){
            return board;
        }
        row = board.length;
        col = board[0].length;
        //if the click is a mine then change it to X and return
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        //use directions array to get to neighbouring cells from the current cell
        dirs = new int[][]{{0,1},{0,-1},{-1,0},{1,0},{-1,-1},{1,1},{-1,1},{1,-1}};
        //start iterating from the click
        Queue<int[]> q = new LinkedList<>();
        //add click to queue
        q.add(new int[]{click[0],click[1]});
        board[click[0]][click[1]] = 'B';
        while(!q.isEmpty()){
            //determine the mines around the current cell
            int temp[] = q.poll();
            
            int mine = getMine(board,temp[0],temp[1]);
        //if there are no neighbouring mines add the neighbouring cells into the queue
            if(mine==0){
                for(int[] dir : dirs){
                    int nr = temp[0] + dir[0];
                    int nc = temp[1] + dir[1];
                    if(nr>=0 && nc>=0 && nr<row && nc<col && board[nr][nc] == 'E'){
                        //if the neighbour is E change to B
                        q.add(new int[]{nr,nc});
                        board[nr][nc] = 'B';
                    }
                }
            }
            else{
                //if mines are found, the neighbours are not visited  and the current cell is marked with the number of mines
                board[temp[0]][temp[1]] = (char) (mine + '0');
            }
        }
        return board;
        
    }
    private int getMine(char[][] board,int r,int c){
        int count=0;
        for(int[] dir : dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];
            if(nr>=0 && nc>=0 && nr<row && nc<col && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}
//DFS
//Time complexity : O(m*n)
//Space Complexity : O(m*n)
class Solution {
    int[][] dirs;
    int row,col;
    public char[][] updateBoard(char[][] board, int[] click) {
        if(board==null||board.length==0){
            return board;
        }
        row = board.length;
        col = board[0].length;
        //if the click is a mine then change it to X and return
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        //use directions array to get to neighbouring cells from the current cell
        dirs = new int[][]{{0,1},{0,-1},{-1,0},{1,0},{-1,-1},{1,1},{-1,1},{1,-1}};
        dfs(board,click[0],click[1]);
        return board;
    }
    private void dfs(char[][] board,int r,int c){
        //base case
        if(r<0 || c<0 || r==row || c==col || board[r][c] != 'E'){
            return;
        }
        //logic
        board[r][c] = 'B';
        int mine = getMine(board,r,c);
        //if there are no neighbouring mines add the neighbouring cells into the queue
            if(mine==0){
                for(int[] dir : dirs){
                    int nr = r + dir[0];
                    int nc = c + dir[1];
                    //for each neighbouring cell call dfs recursively
                    dfs(board,nr,nc);
                }
            }
        else{
            //if mines are found, the neighbours are not visited  and the current cell is marked with the number of mines
            board[r][c] = (char)(mine + '0');
        }
    }
    private int getMine(char[][] board,int r,int c){
        int count=0;
        for(int[] dir : dirs){
            int nr = r + dir[0];
            int nc = c + dir[1];
            //for each neighbour count the number of mines
            if(nr>=0 && nc>=0 && nr<row && nc<col && board[nr][nc] == 'M'){
                count++;
            }
        }
        return count;
    }
}