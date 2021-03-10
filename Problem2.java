// https://leetcode.com/problems/snakes-and-ladders/
// Time complexity : O(MN)
// Space complexity : O(MN)


class Solution {
    public int snakesAndLadders(int[][] board) {
        int m = board.length;
        int boardLinear[] = new int[m*m];
        int r = m-1;
        int c = 0;
        int idx = 0;
        int dir = 1;
        while(r >= 0 && c >= 0){
            if(board[r][c] == -1){
                boardLinear[idx++] = -1;
            }else{
                boardLinear[idx++] = board[r][c]-1;
            }
            c += dir;
            if(c == m || c == -1){
                dir = dir * -1;
                r--;
                c += dir;
            }
        }
        
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        boardLinear[0] = -2;
        int min = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i=0;i<size;i++){
                int index = q.poll();
                if(index == (m*m)-1)
                    return min;
                for(int j=1;j<=6;j++){
                    int next = index + j;
                    if(next < m*m && boardLinear[next] != -2){
                        if(boardLinear[next] == -1){
                            q.add(next);
                        }else{
                            q.add(boardLinear[next]);
                        }
                        boardLinear[next] = -2;
                    }
                }
            }
            min++;
        }
        return -1;
    }
}