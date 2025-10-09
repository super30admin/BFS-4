

// Time Complexity : O(n*m) where n is number of rows and m is number of columns
// Space Complexity : O(n*m) where n is number of rows and m is number of

//We can use either DFS or BFS to solve this problem. Here I have used BFS.
//We start from the clicked cell and check if it is a mine. If it is a mine we change it to 'X' and return the board.
//If it is not a mine we check the number of mines in the adjacent cells. If there are no mines we change it to 'B' and add all the adjacent cells to the queue.
//If there are mines we change it to the number of mines and do not add any adjacent cells to the queue.


class Solution {
    int n,m;
    int directions[][];
    public char[][] updateBoard(char[][] board, int[] click) {
        this.n=board.length;
        this.m=board[0].length;
        this.directions=new int[][]{{-1,0},{-1,-1},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
        Queue<int[]> q=new LinkedList<>();

        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }

        q.add(new int[]{click[0],click[1]});
        board[click[0]][click[1]]='B';  //mark as visited

        while(!q.isEmpty()){
            int curr[]=q.poll();
            int count=getMineCount(board,curr[0],curr[1]);
            if(count==0){
                for(int dir[]:directions){
                    int r=curr[0]+dir[0];
                    int c=curr[1]+dir[1];
                    if(r>=0&&r<n&&c>=0&&c<m&&board[r][c]=='E'){
                        q.add(new int[]{r,c});
                        board[r][c]='B';
                    }
                }
            }else
                board[curr[0]][curr[1]]=(char)(count+'0');
        }
        return board;
    }
   
    int getMineCount(char board[][],int i,int j){
        int count=0;
        for(int dir[]:directions){
            int r=i+dir[0];
            int c=j+dir[1];
            if(r>=0&&r<n&&c>=0&&c<m&&board[r][c]=='M')
                count++;
        }
        return count;
    }
}