class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board
        m = len(board)
        n = len(board[0])
        #       top    topR       R    botR    bot     botL    L       topL
        dirs = [(-1,0), (-1, 1), (0,1), (1,1), (1,0), (1,-1), (0,-1), (-1, -1)]
        visited=set()
        q=deque([])

        q.append(tuple(click))
        visited.add(tuple(click))


        while q:
            cr, cc = q.popleft()
            adj = 0
            eList = []
            for dr, dc in dirs:
                nr, nc = cr+dr, cc+dc
                if nr>=0 and nr<m and nc>=0 and nc<n and (nr,nc) not in visited:
                    if board[nr][nc]=='E':
                       eList.append((nr,nc))
                    
                    elif board[nr][nc]=='M':
                        adj+=1
            if adj==0:
                board[cr][cc]='B'
                q+=eList
                for t in eList:
                    visited.add(t)
                    
            else:
                board[cr][cc]=str(adj)
        
        return board


