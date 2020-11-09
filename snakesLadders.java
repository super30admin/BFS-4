
public class snakesLadders {

	public int SnakesLadders(int[][] board) {
		
		if(board == null || board.length == 0)
			return 0;
		
		int n = board.length;
		int[] move = new int[board.length];
		int i = n-1, j = 0, idx = 0;
		int even = 0;
		// since we know that the board is in zip zag order and can be stored in 1D array with all the values in the board so that
		// we can directly move ahead like we are moving in the board
		while(i >= 0 && j >= 0) {
			// Condtion 1: place the board values
			if(board[i][j] == -1) {
				move[idx++] = -1;// if the value is -1, place -1 value
			}else { // else place the board value
				move[idx++] = board[i][j];
			}
			// condition 2: check for levels and column moves
			// if the level is even, we need to move ahead in column
			if(even % 2 == 0) {
				j++;
			}else {
				j--; // if odd, we need to move in backward
			}
			// condition 3: check for bounds
			if(j < 0) {
				even++;
				i--;j++;
			}else if( j >= n) {
				even++;
				i--;j--;
			}
			
		}
		// add the 0th value onto queue since we are traversing from 0th index
		Queue<Integer> queue = new LinkedList<>();
		queue.add(0);
		int level = 0;
		// since its visited, update the move to -2 instead of using visited so that we dont check for that value again
		move[0] = -2;
		
		while(!queue.isEmpty()) {
			// move in level order
			int size = queue.size();
			// loop over the size of the queue so that we highest value in a single row and do not end our search in local max value
			for(int x = 0;x<size;x++) {
				// fetch the current position or move
				int curr = queue.poll();
				// if we have reached the end, we return the level
				if(curr == n*n-1)
					return level;
				// move over the 6(x,x+1, x+2, x+3,x+4,x+5) directions and check for the values in the move, add the values onto queue
				// and iterate over the again
				for(int y=1;y<=6;y++) {
					
					int nextMove = curr + y;
					if(nextMove < n*n && move[nextMove] != -2) {
						if(move[nextMove] == -1) {
							queue.add(nextMove);
						}else {
							queue.add(move[nextMove]);
						}
						move[nextMove] = -2;
					}
				}
			}
			// update the level once we have finished all 6 directions for the values in the queue
			level++;
		}
	return -1;		
			
	}
}
