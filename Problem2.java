
// Time Complexity: O(N^2) where N is the length of the board
// Space Complexity: O(N^2) where N


// We can use BFS to solve this problem. We start from the first cell and explore all possible moves (1 to 6 steps ahead).
// For each move, we check if there is a snake or ladder at the destination cell. If there is, we move to the end of the snake or ladder 
// and mark it as visited.else we move to the destination cell and mark it as visited.
// We keep track of the number of moves taken to reach each cell using a queue. If we reach the last cell, we return the number of moves taken.
// We also calculate the row and column of the cell based on the index, taking into account the zigzag pattern of the board.

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n=board.length;
        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        int level=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                int curr=q.poll();
                for(int k=1;k<=6;k++){
                    int newIdx=curr+k;
                    int pos[]=getPos(newIdx,n);
                    int r=pos[0],c=pos[1];

                    if(newIdx==n*n-1||board[r][c]==n*n) return level+1;

                    if(board[r][c]!=-2){  
                        if(board[r][c]==-1)   //no snake or ladder push the new index
                            q.add(newIdx);
                        else
                            q.add(board[r][c]-1);  //snake or ladder present push the end of snake or ladder
                        board[r][c]=-2;    //mark as visited
                    }
                }
            }
            level++;
        }
        return -1;
    }
    int[] getPos(int idx,int n){
        int r=idx/n;
        int c=idx%n;
        if(r%2==0)
            return new int[]{n-r-1,c};
        else
            return new int[]{n-r-1,n-c-1};
    }
}