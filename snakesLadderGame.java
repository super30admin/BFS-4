//Can use a BFS approach but easier to convert it fully to 1D array - complex to map the rows and colummns from start point to their actual i,j position 
// convert the whole game to 1D array how the player sees it from [i=max][j=0] - easier 
// use queue to load next six steps ( dice ) from  the current node -> if square not -1 , exhange value on square with the number and add to queue -> add to visited node to avoid repetetions while iterating nodes
// BFS  approach TIME -> O(N^2)  || Space ->  O(N^2)

class Solution {
    public int snakesAndLadders(int[][] board) {
        int N = 0;
        if ( (N = board.length) == 0 ) return 0;
        
        int[] nBoard = new int[N*N + 1];
        //int to use as index for the 1D array
        int t = 1;
        
        //boolean which check which way we have to traverse 
        boolean left2right = true;
        
        // insert into array how the user would discover the numbers 1-> N
        for(int i = N - 1; i >= 0; i--) {
            if (left2right) {
                for(int j = 0; j < N; j++) nBoard[t++] = board[i][j];
            }else {
                for(int j = N - 1; j >= 0; j--) nBoard[t++] = board[i][j];
            }
            left2right = !left2right;
        }
        // System.out.println(Arrays.toString(nBoard));
        
        Queue<Integer> q = new LinkedList<>();
        
        //add 1 for first position 
        q.add(1);
        
        // counting steps along the way 
        int steps = 0;
        boolean[] visited = new boolean[N*N + 1];
        visited[1] = true;
        
        
        while(!q.isEmpty()){
            steps++;
            int curCnt = q.size();
            
            // for all the nodes in the queue 
            for(int i = 0; i < curCnt; i++) {
                int square = q.poll();
                // can add upto 6 to the node 
                for(int j = 1; j <= 6; j++) {
                    int next_square = square + j;
                    //detect snake or ladder 
                    //if the value on that square is not -1 ( go to value pointed on square )
                    if (nBoard[next_square] != -1) next_square = nBoard[next_square];
                    if (visited[next_square]) continue;
                    // if end node reached 
                    if (next_square == N*N) return steps;
                    //add to visited nodes
                    visited[next_square] = true;
                    
                    // add node to queue
                    q.offer(next_square);
                }
            }
        }
        return -1;
    }
}
