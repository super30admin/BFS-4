// TC: O(N*M) to traverse the board
// SC: O(N) to add indices onto queue

public class mineSweeper {

	public char[][] updateboard(char[][] board, int[] click){
		
		Queue<int[]> queue = new LinkedList<>();
		// initialize the dirs 2D array
		int[][] dirs = {{0,1},{1,0},{-1,0},{1,-1},{-1,-1},{-1,1},{0,-1},{1,1}};
		
		int r = click[0];
		int c = click[1];
		// if the use is clicking on Mine, we will change it to X and return the board;
		if(board[r][c] == 'M') {
			board[r][c] = 'X';
			return board;
		}
		
		// if the click is not M, we will add the given click onto queue so that we can start our BFS traversal from the click
		// and move in 8 directions around the click and check if the 8 directions have Mine or they have empty spots
		queue.add(click);
		while(!queue.isEmpty()) {
			
			int[] curr = queue.poll();
			int cnt = 0;
			// calculate the nnumber of mines so that we can update the empty spots surrounding the mine with the count of Mines
			for(int[] dir: dirs) {
				int x = dir[0] + curr[0];
				int y = dir[1] + curr[1];
				
				if(x < 0 || y < 0 || x >= board.length || y>= board[0].length)
					continue;
				if(board[x][y] == 'M')
					cnt++;
			}
			// if the count is greater than 0, we know that there is a mine, we update the board with count
			if(cnt > 0) {
				board[curr[0]][curr[1]] = (char) ( cnt + '0');
			}else{	
				// if the board has Empty, we update the board to revealed blank
				board[curr[0]][curr[1]] = 'B';
				for(int[] dir: dirs) {
					
					int x = dir[0] + curr[0];
					int y = dir[0] + curr[1];
					if(x < 0 || y < 0 || x >= board.length || y >= board[0].length)
						continue;
					// if the board is empty in any of the 8 valid directions, we update the board to revealed blank and add it the queue
					// since the questions mentions to traverse 8 directions recursively and update the empty spots
					if(board[x][y] == 'E') {
						queue.add(new int[] {x, y});
						board[x][y] = 'B';
					}
					
				}
			}
		}
		return board;
	}
}
