//Problem 1: MineSweeper Game
// Time Complexity :O(8mn)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No problem


// Your code here along with comments explaining your approach
//Check click board if its M-> return, if its B(already visited, dont do recursive calls or neighbor check.), if its E, check all 8 neighbors if we have a mine, if we dont have a mine recursively check for all 8 unvisited neighbors
//BFS,DFS O(8*mn)
class Solution {
    private int[][] dirs;
    int m,n;
    public char[][] updateBoard(char[][] board, int[] click) {
        this.dirs=new int[][]{{0,1} , {1,0} , {0,-1} , {-1,0} , {1,1} , {1,-1} , {-1,1} , {-1,-1}};

        this.m=board.length; this.n=board[0].length;
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        // Queue<int[]> q= new LinkedList<>();
        // q.add(click);
        // board[click[0]][click[1]]='B';
        // while(!q.isEmpty()){
        //     int[] cur=q.poll();
        //     int count=countMines(board, cur[0], cur[1]);
        //     if(count==0){
        //         for(int[] dir: dirs){
        //             int nr=cur[0]+dir[0];
        //             int nc=cur[1]+dir[1];

        //             if(nr>=0 && nc>=0 && nr<m && nc<n && board[nr][nc]=='E'){
        //                 q.add(new int[]{nr,nc});
        //                 board[nr][nc]='B';
        //             }
        //         }
        //     }
        //     else{
        //         board[cur[0]][cur[1]]=(char)(count+'0');
        //     }
        // }
        // return board;
        dfs(board, click[0], click[1]);
        return board;
    }

    private void dfs(char[][] board, int r, int c){
        //base
        if(r<0 || c<0 || r==m || c==n || board[r][c]!='E') return;
        int count=countMines(board, r, c);
        board[r][c]='B';
        if(count==0){
            for(int[] dir: dirs){
                int nr=dir[0]+r;
                int nc=dir[1]+c;
                dfs(board, nr, nc);
            }
        }
        else
            board[r][c]=(char)(count+'0');
        
    }

    private int countMines(char[][] board, int r, int c){
        int count=0;
        for(int[] dir:dirs){
            int nr=r+dir[0];
            int nc=c+dir[1];

            if(nr>=0 && nc>=0 && nr<m && nc<n && board[nr][nc]=='M')
                count++;
        }
        return count;
    }
}

//Problem 2: Snake and Ladders
// Time Complexity :O(6n^2)
// Space Complexity : O(n^2)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : No problem


// Your code here along with comments explaining your approach
//flatten the matrix in a way snake would move. then at each cell, we can have 6 options, do recursive calls for all 6 options.
class Solution {
    
    public int snakesAndLadders(int[][] board) {
        int n=board.length;
        int [] arr=new int[n*n];

        int i=0;
        int r=n-1, c=0;
        boolean flag=true; //to change direction
        //true-> left to right

        //flatten and store in array
        while(i<n*n){
            if(board[r][c]==-1){
                arr[i]=-1;
            }else{
                arr[i]=board[r][c]-1;
            }
            i++;
            if(flag){
                c++;
                if(c==n){
                    flag=false;
                    r--; c=n-1;
                }
            }else{
                c--;
                if(c==-1){
                    flag=true;
                    r--; c=0;
                }
            }
        }
        //bfs O(6 * N^2) O(N*2)
        Queue<Integer> q= new LinkedList<>();
        q.add(0);
        arr[0]=-2;
        int moves=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int j=0;j<size;j++){
                int curIdx=q.poll();
                //roll the dices
                for(int k=1;k<=6;k++){
                    int nextIdx=curIdx+k;
                    if(nextIdx==arr.length-1) //if already reached 
                        return moves+1; 

                    if(arr[nextIdx]!=-2){ //if not visited already
                        if(arr[nextIdx]==-1){ //ordinary cell
                            q.add(nextIdx);
                        }else{ //snake or ladder
                            if(arr[nextIdx]==arr.length-1)
                                return moves+1;
                            q.add(arr[nextIdx]);
                            // arr[arr[nextIdx]]=-2;
                        }
                        arr[nextIdx]=-2;
                    }
                }//for O(6)
            }//inner while
            moves++;
        }//outer while
        return -1;
    }
}