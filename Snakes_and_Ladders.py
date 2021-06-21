class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        maze = [0] * n*n
        i,j=n-1,0
        result = 0
        row = 0
        idx =0        
        q = deque()
        while i >=0:
            
            if board[i][j] == -1:
                maze[idx] = idx
            else:
                maze[idx] = board[i][j] -1
            
            if row %2 == 0:
                j+=1
            else:
                j-=1
                
            if j == n:
                i -=1
                row +=1
                j-=1
            if j < 0:
                i -=1
                row +=1
                j+=1
            idx +=1
                
        #print(maze)
        
        q.append(maze[0])
        maze[0] = -2
        move = 0
        while q:
            #print(q)
            
            size = len(q)
            
            for i in range(size):
                
                curr = q.popleft()
                
                if curr == n*n -1 : return move
                
                for k in range(1,7):
                    pos = k + curr
                    if pos < n*n and maze[pos] != -2 :
                            q.append(maze[pos])
                            maze[pos] = -2
            move +=1
            
        return -1
