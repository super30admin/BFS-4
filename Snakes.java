// Time Complexity : O(nn) Go over all positions on the board
// Space Complexity : O(nn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

import java.util.*;

class Snakes {
    public int snakesAndLadders(int[][] board) {
        if(board == null || board.length==0) return 0;
        int n = board.length;
        int[] arr = new int[n*n];
        int idx = 0;
        int even = 0;
        int r=n-1;int c=0;
        while(idx<n*n){
            //System.out.println(r+" "+c);
            arr[idx]=board[r][c];
            idx++;
            if(even==0){
                c++;
                if(c==n){
                    r--;
                    even=1;
                    c--;
                }
            }
            else{
                c--;
                if(c<0){
                    r--;
                    even=0;
                    c++;
                }
            }

        }

        Queue <Integer> q = new LinkedList<>();
        q.add(0); arr[0]=-2;
        int level = 0;
        int flag=0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int j=0;j<size;j++){
                int pos = q.poll();
                if(pos==((n*n)-1)){
                    //System.out.println(next);
                    flag=1;
                    return level;
                    //break;
                }
                //if(pos==((n*n)-1)) break;
                for(int i=1;i<=6;i++){
                    int next = pos+i;
                    if(next>=n*n) break;
                    if(arr[next]==-2)continue;
                    // if(next==((n*n)-1)){
                    //     //System.out.println(next);
                    //     flag=1;
                    //     return level+1;
                    //     //break;
                    // }
                    if(arr[next]==-1){
                        q.add(next);
                    }
                    else if(arr[next]>0){
                        q.add(arr[next]-1);
                    }
                    arr[next]=-2;
                }
                //System.out.println(q);
                //if(flag==1) break;
            }
            level++;
        }

        return -1;
    }
}