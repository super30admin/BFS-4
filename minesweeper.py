# As taught in class, using BFS to solve the problem
#Time complexity and space complexity: O(m+n)
class Solution:
    def __init__(self):
        self.dirs = [(0,1),(0,-1),(1,0),(-1,0),(1,1),(1,-1),(-1,1),(-1,-1)]
        self.m = 0
        self.n = 0
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board is None or len(board)==0:
            return board
        self.m = len(board)
        self.n = len(board[0])
        if (board[click[0]][click[1]] == "M"):
            board[click[0]][click[1]] = "X"
            return board
        q = list()
        q.append(click)
        board[click[0]][click[0]] = "B"
        while(len(q)!=0):
            curr = q.pop(0)
            mines = self.getMines(board, curr[0], curr[1])
            if mines == 0:
                for dir in self.dirs:
                    r = dir[0] + curr[0]
                    c = dir[1] + curr[1]
                    if (r >=0 and r<self.m and c >=0 and c < self.n and board[r][c] == "E"):
                        q.append((r,c))
                        board[r][c] = "B"
            else:
                board[curr[0]][curr[1]] = str(mines+"0")
        return board
                        
        
    def getMines(self, board, i, j):
        mines = 0
        for dir in self.dirs:
            r = dir[0] + i
            c = dir[1] + j
            if r>=0 and r < self.m and c >=0 and c< self.n and board[i][j] == "M":
                mines += 1
        return board