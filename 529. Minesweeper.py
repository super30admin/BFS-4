#time: O(m*n)
#space: O(1)
class Solution:
    
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        self.dirs=[[-1,-1],[-1,0],[-1,1],[0,1],[1,1],[1,0],[1,-1],[0,-1]]
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]] = 'X'
            return board
        
        return self.dfs(board, click[0], click[1])

    def dfs(self, board,row,col):
        if row <0 or col<0 or row >= len(board) or col >= len(board[0]) or board[row][col] !='E':
            return

        count= self.getmines(board,row,col)
        if count == 0:
            board[row][col] = 'B'
            for d in self.dirs:
                nr= row+d[0]
                nc= col+d[1]
                self.dfs(board,nr,nc)
        else:
            board[row][col] = str(count)
        return board



    
    def getmines(self, board,row,col):
        cnt=0    
        for d in self.dirs:
            nr= row+d[0]
            nc= col+d[1]
            if nr >=0 and nc>=0 and nr < len(board) and nc < len(board[0]):
                if board[nr][nc] == 'M':
                    cnt+=1
        return cnt
