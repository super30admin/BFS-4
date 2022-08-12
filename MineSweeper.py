#529. Minesweeper
"""
Time Complexity : O(m * n) #processing queue and countMines and directions
Space Complexity : O(m * n) #queue
"""
class Solution:
    dirs = [[0, -1], [0 , +1], [-1 ,0], [+1, 0], [-1, -1], [-1, +1], [+1, -1], [+1 ,+1]]
    #         L.         R.       T.       B.        TL.       TR.       BL.      BR.  
    
    def countMines(self, board, r, c, m, n):
        #go in all 8 directions and count board[nr][nc] == 'M'
        noMines = 0
        for d in self.dirs:
            nr = r + d[0]
            nc = c + d[1]
            
            #bounds and 'M'
            if nr >= 0 and nr < m and nc >= 0 and nc < n and board[nr][nc] == 'M':
                noMines += 1
                
        return noMines
        
        
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        #null case
        if board == None or len(board) == 0:
            return None
        
        #if click is mine
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        #else
        q = deque()
        q.append((click[0], click[1]))
        board[click[0]][click[1]] = 'B'
        m = len(board)
        n = len(board[0])
        
        while len(q) != 0:
            #print(board)
            r, c = q.popleft()
            #print(r, c)
            #check for no of mines in all neighbours
            noMines = self.countMines(board, r, c, m, n)
            
            #if there are mines, then all that number to r, c. Do not go for all neighbours then
            if noMines > 0:
                board[r][c] = str(noMines)
            else:
                #if no mines then go for all 8 neighbours and convert them from E -> B
                for d in self.dirs:
                    nr = r + d[0]
                    nc = c + d[1]

                    #check for bounds and board[nr][nc] == 'E'
                    if nr >= 0 and nr < m and nc >= 0 and nc < n and board[nr][nc] == 'E':
                        q.append((nr, nc))
                        board[nr][nc] = 'B'
                    
        return board
