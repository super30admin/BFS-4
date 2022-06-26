// Time Complexity : O(n*n)
// Space Complexity :O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
// 1) DFS or BFS
// 2) Start with clicked input and explore all option according to DFS or BFS
//     a. When traversing the cells, chane E to B (blank), just to maintain visited cells
//     b. In the end change all B to E
// Return it


int[][] dirs;
int m, n;
public char[][] UpdateBoard(char[][] board, int[] click) {
    
    if(board == null || board.Length == 0)
        return board;
    
    //if clicked position is mine, change to mine, return board
    if(board[click[0]][click[1]] == 'M'){
        board[click[0]][click[1]] = 'X';
        return board;
    }
    
    m = board.Length;
    n = board[0].Length;
    
    //these are possible direction for each element, right left, up, down and diagonal
    dirs = new int[][] {new int[] {0,1}, new int[] {0,-1}, new int[] {1,0}, new int[] {-1,0}, new int[] {1,1}, new int[] {-1, -1}, new int[] {-1, 1}, new int[] {1, -1}};
    
    //BFS
    //BFSUpdateBoard(board, click);
    
    
    //DFS
    DFSUpdateBoard(board, click);
    
    return board;
    
}

private void BFSUpdateBoard(char[][] board, int[] click) {
    
    Queue<int[]> queue = new Queue<int[]>();
    queue.Enqueue(new int[] {click[0], click[1]});
    board[click[0]][click[1]] = 'B';
    
    while(queue.Count > 0)
    {
        int[] curr = queue.Dequeue();
        
        int count = countMines(board, curr[0], curr[1]);
        if(count > 0)
            board[curr[0]][curr[1]] = (char)(count + '0');
        else
        {
            foreach(int[] dir in dirs)
            {
                int r = curr[0] + dir[0];
                int c = curr[1] + dir[1];
                if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E')
                {
                    queue.Enqueue(new int[] {r,c});
                    board[r][c] = 'B';
                }
            }
        }
    }        
}

private int countMines(char[][] board, int i, int j)
{
    int count = 0;
    
    foreach(int[] dir in dirs)
    {
        int r = i + dir[0];
        int c = j + dir[1];
        if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'M')
            count++;
    }
    return count;
}

private void DFSUpdateBoard(char[][] board, int[] click) {
    
    //base
    if(click[0] < 0 || click[1] < 0 || click[0] == m || click[1] == n || board[click[0]][click[1]] != 'E')
        return;
    
    //logic
    board[click[0]][click[1]] = 'B';
    int count = countMines(board, click[0], click[1]);
    if(count > 0)
        board[click[0]][click[1]] = (char)(count + '0');
    else
    {
        foreach(int[] dir in dirs)
        {
            int r = click[0] + dir[0];
            int c = click[1] + dir[1];
            if(r >= 0 && r < m && c >= 0 && c < n && board[r][c] == 'E')
            {
                DFSUpdateBoard(board, new int[] {r,c});
            }
        }
    }
    
}