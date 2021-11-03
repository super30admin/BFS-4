import java.util.*;
public class BFS{
     // time complexity : n^2
    // space complexity : n^2
    // did it run on leetcode : yes
    // any doubts : no
    //https://leetcode.com/problems/minesweeper/submissions/
    int[][] dirs = {{-1,0},{0,-1},{1,0},{0,1},{1,1},{-1,-1},{1,-1},{-1,1}};
    int m;
    int n;
    public char[][] updateBoard(char[][] board, int[] click) {
        m = board.length;
        n = board[0].length;
       
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{click[0],click[1]});
        board[click[0]][click[1]]='B';
        while(!q.isEmpty()){
            int[] curr = q.poll();
            int mines = countMine(board,curr[0],curr[1]);
            if(mines > 0){
                board[curr[0]][curr[1]]= (char)(mines+'0');
            }else{
                 for(int[] dir : dirs){
                     int r = dir[0]+curr[0];
                     int c = dir[1]+curr[1];
                     if(r>=0 && c>=0 && r<m && c<n && board[r][c]=='E'){
                         q.add(new int[]{r,c});
                         board[r][c]='B';
                }
            }
            }
           
        }
        return board;
        
    }
    
    private int countMine(char[][] board, int i, int j){
        int count =0;
           for(int[] dir : dirs){
                     int r = dir[0]+i;
                     int c = dir[1]+j;
                     if(r>=0 && c>=0 && r<m && c<n && board[r][c]=='M'){
                         count++;
                }
            }
        
        return count;
        
    }
      // time complexity : n*2;
    // space complexity : n*2;
    // did it run on leetcode : yes
    // any doubts : no
    //https://leetcode.com/problems/snakes-and-ladders/submissions/
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] flat = new int[n*n];
        int r = n-1;
        int c = 0;
        int idx = 0;
        int even = 0;
    while(r>=0 && c>=0){
         if(board[r][c]==-1){
             flat[idx]= -1;
         }else{
            flat[idx]= board[r][c]-1;
         }
        idx++;
        if(even % 2 ==0){
            c++;
            if(c==n){
                c--;r--;even++;
            }
        }else{
            c--;
            if(c<0){
                c++;r--;even++;
            }
        }
    }
        int count =0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        flat[0]=-2;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i =0;i<size;i++){
                int curr = q.poll();
                if(curr == n*n-1)return count;
                for(int j =1;j<7;j++){
                    int child = curr+j;
                    if(child >= n*n) continue;
                    if( flat[child]!= -2){
                        if(flat[child]==-1){
                            q.add(child);
                        }else{
                            q.add(flat[child]);
                        }
                        flat[child]=-2;
                    }
                }
            }
            count++;
        
            
        }
        
        return -1;
}
}