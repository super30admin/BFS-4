//problem1
class Solution {
    int[][] dirs={{-1,0},{-1,-1},{-1,1},{0,-1},{0,1},{1,-1},{1,0},{1,1}};
    public char[][] updateBoard(char[][] board, int[] click) {
        int m=board.length;
        int n=board[0].length;
        if(board[click[0]][click[1]]=='M'){
            board[click[0]][click[1]]='X';
            return board;
        }
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{click[0],click[1]});
        board[click[0]][click[1]]='B';
        while(!q.isEmpty()){
            int[] curr=q.poll();
            int cnt=counting(board,curr[0],curr[1]);
            if(cnt==0){
                for(int[] dir:dirs){
                    int nd=dir[0]+curr[0];
                    int d=dir[1]+curr[1];
                    if(nd>=0 && d>=0 && nd<m && d<n && board[nd][d]=='E'){
                        q.add(new int[]{nd,d});
                        board[nd][d]='B';
                    }
                }
            }else{
                board[curr[0]][curr[1]]=(char)(cnt+'0');
            }
        }
        return board;
    }
    public int counting(char[][] board,int i,int j){
        int count=0;
        for(int[] dir:dirs){
            int nr=dir[0]+i;
            int cr=dir[1]+j;
            if(nr>=0 && cr>=0 && nr<board.length && cr<board[0].length && board[nr][cr]=='M'){
                count++;
            }
        }
        return count;
    }
}
//problem2
class Solution {
    public int snakesAndLadders(int[][] board) {
        int n=board.length;
        int[] arr=new int[n*n];
        int r=n-1;
        int c=0;
        int i=0;
        boolean flag=true;
        while(i<n*n){
            if(board[r][c]==-1){
                arr[i]=board[r][c];
            }else{
                arr[i]=board[r][c]-1;
            }
            i++;
            if(flag){
                c++;
                if(c==n){
                    r--;
                    c--;
                    flag=false;
                }
            }else{
                c--;
                if(c==-1){
                    r--;
                    c++;
                    flag=true;
                }
            }
        }
        Queue<Integer> q=new LinkedList<>();
        q.add(0);
        int le=0;
        while(!q.isEmpty()){
            int size=q.size();
            for(int j=0;j<size;j++){
                int curr=q.poll();
                for(int k=1;k<=6;k++){
                    int idx=curr+k;
                    if(idx==n*n-1 || arr[idx]==n*n-1) return le+1;
                    if(arr[idx]!=-2){
                        if(arr[idx]==-1){
                            q.add(idx);
                        }else{
                            q.add(arr[idx]);
                        }
                        arr[idx]=-2;
                    }
                }
            }
            le++;
        }
        return -1;
    }
}
