class SnakesAndLadder {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] flattenedArray = new int[n*n]; 
        int index = 0;
        int row = n-1;
        int col = 0;
        boolean dir = true;
        while(index < n*n){
            if(board[row][col] == -1)
                flattenedArray[index] = -1;
            else
                flattenedArray[index] = board[row][col] -1;
            index++;

            if(dir){
                col++;
                if(col == n){
                    row--;
                    col--;
                    dir = false;
                }
            }
            else{
                col--;
                if(col == -1 ){
                    row--;
                    col++;
                    dir = true;
                }
            }
        }

        Queue<Integer> myQueue = new LinkedList<>();
        myQueue.add(0);
        int level = 0;
        while(!myQueue.isEmpty()){
            int size = myQueue.size();
                for(int j=0; j<size; j++){
                    int currIdx = myQueue.poll();
                    for(int k=1; k<=6; k++){
                        int newIdx = currIdx + k;

                        if(newIdx == n*n -1 || flattenedArray[newIdx] == n*n -1)
                            return level+1;

                        if(flattenedArray[newIdx] != -2){    
                            if(flattenedArray[newIdx] == -1){
                                myQueue.add(newIdx);
                            }
                            else{
                                myQueue.add(flattenedArray[newIdx]);
                            }
                        flattenedArray[newIdx] = -2;
                        }
                    }
                }
            level++;
        }
        return -1;
    }
}