class Solution(object):
    def updateBoard(self, board, click):
        
        R = len(board)
        C = len(board[0])
        if board[click[0]][click[1]] == "M": 
            board[click[0]][click[1]] = "X"
        dir = [1,0], [0,1], [-1,0],[0,-1],[1,1],[-1,-1],[1,-1],[-1,1]
        q = deque()
        q.append(click)
        seen = set(click)
        
        def helper(board, i, j):
            count = 0 
            for x, y in dir: 
                if 0 <= i + x < R and 0 <= j + y < C and board[i+x][y+j] == "M":
                    count += 1 
            return count 

        while q: 
            for tup in range(len(q)):
                x, y = q.popleft()
                if board[x][y] == "E":
                    bombsNextTo = helper(board, x, y)
                    
                    if bombsNextTo == 0:
                        board[x][y] = "B" 
                    else:
                        board[x][y] = str(bombsNextTo)
                    if bombsNextTo == 0:
                        for a, b in dir:
                            if 0 <= a + x < R and 0 <= b + y < C and (a+x,b+y) not in seen:
                                q.append((a+x, b+y))
                                seen.add((a+x, b+y))
        return board 