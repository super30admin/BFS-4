class Problem909 {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        HashSet<Integer> vistedNumber=new HashSet<>();
        if (board == null || n < 0)
            return 0;
        int[] flattenArray = new int[n * n];
        int idx = 0;
        boolean dir=true;
        int r=n-1;
        int c=0;
        while (idx < n * n) {
            if (board[r][c] == -1) {
                flattenArray[idx] = -1;
            } else {
                flattenArray[idx] = board[r][c] - 1;
            }
            idx++;
            if (dir) {
                c++;
                if (c == n) {
                    c--;
                    r--;
                    dir=false;
                }
            } else {
                c--;
                if (c == -1) {
                    c++;
                    r--;
                    dir=true;
                }
            }
        }
        Queue<Integer> bfsQue=new LinkedList<>();
        bfsQue.add(0);
        vistedNumber.add(0);
        int moves=0;
        while(!bfsQue.isEmpty()){
            int size=bfsQue.size();
            for(int i=0;i<size;i++){
                int currNumber=bfsQue.poll();
                if(currNumber==n*n-1)
                    return moves;
                for(int j=1;j<7;j++){
                    int newNumber=currNumber+j;
                    if(newNumber<n*n){
                        if(flattenArray[newNumber]==-1){
                            if(!vistedNumber.contains(newNumber)){
                                bfsQue.add(newNumber);
                                vistedNumber.add(newNumber);
                            }
                        }
                        else{
                            if(!vistedNumber.contains(flattenArray[newNumber])){
                                bfsQue.add(flattenArray[newNumber]);
                                vistedNumber.add(flattenArray[newNumber]);
                            }
                        }
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}
