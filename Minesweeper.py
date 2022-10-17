# Time complexity : O(m*n)
# Space complexity : O(m*n)
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if not board or not board[0]:
            return board
        m = len(board)
        n = len(board[0])
        dirs = [[0,1],[1,0],[1,1],[0,-1],[-1,0],[1,-1],[-1,1],[-1,-1]]
        
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        
        q = collections.deque()
        
        q.append(click)
        board[click[0]][click[1]] = 'B'
        
        def CountMines(board,i,j,m,n):
            cnt = 0
            for d in dirs:
                r = d[0] + i
                c = d[1] + j

                if r >=0 and r <m and c >=0 and c <n and board[r][c] == 'M':
                    cnt += 1
            return cnt
        
        while q:
            curr = q.popleft()
            count = CountMines(board,curr[0],curr[1],m,n)
            if count == 0:
                for d in dirs:
                    r = d[0] + curr[0]
                    c = d[1] + curr[1]
                    
                    if r >=0 and r <m and c >=0 and c <n and board[r][c] == 'E':
                        q.append([r,c])
                        board[r][c] = 'B'
            else:
                board[curr[0]][curr[1]] = str(count)
        return board