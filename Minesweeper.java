/*
# bfs
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board) == 0:
            return board
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        self.rows, self.cols = len(board), len(board[0])
        self.dirs = [[0,1], [1,0], [0,-1], [-1,0], [1,1], [-1,-1], [1,-1], [-1,1]]
        queue = collections.deque()
        queue.append((click[0], click[1]))
        board[click[0]][click[1]] = 'B'
        while len(queue) > 0:
            popped_row, popped_col = queue.popleft()
            mines = self.findmines(popped_row, popped_col, board)
            
            if mines == 0:
                for d in self.dirs:
                    new_row = popped_row + d[0]
                    new_col = popped_col + d[1]
                    if new_row >= 0 and new_row < self.rows and new_col >= 0 and new_col < self.cols and board[new_row][new_col] == 'E':
                        queue.append((new_row, new_col))
                        board[new_row][new_col] = 'B'
            else:
                board[popped_row][popped_col] = str(mines)
        return board
    
    def findmines(self, r, c, board):
        mine = 0
        for i in self.dirs:
            row = r + i[0]
            col = c + i[1]
            if row >= 0 and row < self.rows and col >= 0 and col < self.cols and board[row][col] == 'M':
                mine += 1
        return mine

# dfs         
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board) == 0:
            return board
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        self.rows, self.cols = len(board), len(board[0])
        self.dirs = [[0,1], [1,0], [0,-1], [-1,0], [1,1], [-1,-1], [1,-1], [-1,1]]
        self.dfs(board, click[0], click[1])
        return board

       
    def dfs(self, board, r, c):
        if r < 0 or r >= self.rows or c < 0 or c >= self.cols or board[r][c] != 'E':
            return
        
        board[r][c] = 'B'
        mines = self.findmines(r, c, board)
        if mines == 0:
            for d in self.dirs:
                new_r = r + d[0]
                new_c = c + d[1]
                self.dfs(board, new_r, new_c)
        else:
            board[r][c] = str(mines)
            
    def findmines(self, r, c, board):
        mine = 0
        for i in self.dirs:
            row = r + i[0]
            col = c + i[1]
            if row >= 0 and row < self.rows and col >= 0 and col < self.cols and board[row][col] == 'M':
                mine += 1
        return mine
*/

// bfs
/*
class Solution {
    int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
    int rows, cols;
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
            
        }
       
        rows = board.length;
        cols = board[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.add(click);
        board[click[0]][click[1]] = 'B';
        while (!q.isEmpty()){
            int[] popped = q.poll();
            int mines = findmines(popped[0], popped[1], board);
            if (mines == 0){
                for (int[] d:dirs){
                    int new_row = popped[0] + d[0];
                    int new_col = popped[1] + d[1];
                    if (new_row >= 0 && new_row < rows && new_col >= 0 && new_col < cols && board[new_row][new_col] == 'E'){
                        board[new_row][new_col] = 'B';
                        q.add(new int[]{new_row, new_col});
                    }
                }
            }
            else{
                board[popped[0]][popped[1]] = (char)(mines+'0');
            }
        }
        return board;
    }
    private int findmines(int r, int c, char[][] board){
        int mine = 0;
        for (int[] d:dirs){
            int new_r = r + d[0];
            int new_c = c + d[1];
            if (new_r >= 0 && new_r < rows && new_c >= 0 && new_c < cols && board[new_r][new_c] == 'M')
                mine ++;
        }
        return mine;
    }
}
*/

// time - O(rows * cols)
// space - O(rows*cols)
// logic - started dfs with given position and then marked visited squares with B and if they have mines as its neighbors then those squares
// are not explored further
// dfs
class Solution {
    int[][] dirs = {{0,1}, {1,0}, {0,-1}, {-1,0}, {1,1}, {1,-1}, {-1,1}, {-1,-1}};
    int rows, cols;
    public char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M'){
            board[click[0]][click[1]] = 'X';
            return board;
            
        }
       
        rows = board.length;
        cols = board[0].length;
        dfs(board, click[0], click[1]);
        return board;
    }
    private void dfs(char[][] board, int r, int c){
        // base
        if (r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != 'E')
            return;
        
        
        // logic
        board[r][c] = 'B';
        int mines = findmines(r, c, board);
        if (mines == 0){
            for (int[] d:dirs){
                int new_r = r + d[0];
                int new_c = c + d[1];
                dfs(board, new_r, new_c);
            }
        }
        else{
            board[r][c] = (char)(mines+'0');
        }
    }
    
    private int findmines(int r, int c, char[][] board){
        int mine = 0;
        for (int[] d:dirs){
            int new_r = r + d[0];
            int new_c = c + d[1];
            if (new_r >= 0 && new_r < rows && new_c >= 0 && new_c < cols && board[new_r][new_c] == 'M')
                mine ++;
        }
        return mine;
    }
}