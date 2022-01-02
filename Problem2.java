class Solution {
    public int snakesAndLadders(int[][] board) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        int n = board.length;
        q.add(0);
        int level = 0;
        int size, loc;
        int newStart;
        while(!q.isEmpty()){
            size = q.size();
            for(int i=0;i<size;i++){
                loc = q.poll();
                int coord[] = intToCord(loc, n);

                if(board[coord[0]][coord[1]] != -1)
                    newStart = board[coord[0]][coord[1]]-1;
                else
                    newStart = loc;
                for(int j = newStart+1; j<n*n&&j<newStart+7; j++){
                    if(!visited.contains(j)){
                        coord = intToCord(j,n);
                        if(j == n*n-1 || board[coord[0]][coord[1]] == n*n)
                            return level+1;
                        q.add(j);
                        visited.add(j);
                    }
                }
            }
            if(q.isEmpty())
                return -1;
            level++;
        }


        return level;
    }

    public int[] intToCord(int x, int n){
        int row = n-1-x/n;

        return new int[]{row, (n-1-row)%2==0?(x%n): (int)Math.ceil((x+1.0)/(2*n))*(2*n)-1-x};
    }
}