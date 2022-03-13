'''
TC: O(m*n)
SC: O(m*n)
'''
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        rlen = len(board)
        if not rlen:
            return board
        clen = len(board[0])
        dirs = [(0, 1), (0, -1), (1, 1), (1, -1), (1, 0), (-1, 0), (-1, 1), (-1, -1)]
        q = deque()
        
        q.append(click)
    
        def countMines(a, b):
            c = 0
            
            for d in dirs:
                newx = a + d[0]
                newy = b + d[1]
                if newx >= 0 and newx < rlen and newy >= 0 and newy < clen:
                    if board[newx][newy] == "M":
                        c += 1
            return c
                    
        while q:
            x, y = q.popleft()
            
            if board[x][y] == "M":
                board[x][y] = "X"
                return board
            c = countMines(x, y)
            
            if c == 0:
                board[x][y] = "B"
                
                for d in dirs:
                    newx = x + d[0]
                    newy = y + d[1]
                    if newx >= 0 and newx < rlen and newy >= 0 and newy < clen:
                        if board[newx][newy] == "E":
                            q.append([newx, newy])
                            board[newx][newy] = "#"
            else:
                board[x][y] = str(c)
        
        return board
