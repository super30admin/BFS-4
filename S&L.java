//Time COmplexity:O(n*n)
//SPace Complexity:O(n)
//Approch- maintain a queue to keep track of where to move next. A visited queue to mark all the visited numbers. Whenever the value of polled element from the queue is equal to the square of the size of the board, the moves will be returned. Else, the next move will be anywhere from 1 to 6 from the current position depending on the closest index that has the value greater than 0. getNext function will return all possible next values and the condition val>0 will determine the next value. If its is not possible to reach the end, -1 eill be returned.
//This code was executed successfully and got accepted in leetcode.

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n=board.length;
        Queue<Integer> q =new LinkedList<>();
        boolean[] visited=new boolean[n * n +1];
        
        q.offer(1);
        for(int move=0;!q.isEmpty();move++){
            for(int size=q.size();size>0;size--){
                int num=q.poll();
                if(visited[num]){
                    continue;
                }
                visited[num]=true;
                if(num==n*n){
                    return move;
                }
                for(int i=1;i<=6&&num+i<=n*n;i++){
                    int next=num+i;
                    int val=getNext(board,next);
                    if(val>0){
                        next=val;
                    }
                    if(!visited[next]){
                        q.offer(next);
                    }
                }
            }
        }
        return -1;
    }
    public int getNext(int[][] board,int num){
        int n=board.length;
        int oldRow=(num-1)/n;
        int row=n-1-oldRow;
        int oldCol=(num-1)%n;
        int col=oldRow%2==0?oldCol:n-1-oldCol;
        return board[row][col];
    }
}