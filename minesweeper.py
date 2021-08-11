from collections import deque
class Solution:
    """DFS and BFS Implementation
    Time complexity-O(m*n)
    Space complexity-O(m*n)"""
    def __init__(self):
        self.dirs=[[1,0],[0,1],[1,1],[-1,-1],[-1,0],[0,-1],[-1,1],[1,-1]]
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if board[click[0]][click[1]]=="M":
            board[click[0]][click[1]]="X"
            return board
        else:
            self.dfs(board, click[0], click[1])
        return board
    
    def dfs(self, board, row, col):
        if row<0 or row>len(board)-1 or col<0 or col>len(board[0])-1 or board[row][col]!="E":
            return
        mine=self.mines(board, row, col)
        if mine!=0:
            board[row][col]=str(mine)
        else:
            board[row][col]="B"
            for dir in self.dirs:
                row1=dir[0]+row
                col1=dir[1]+col
                self.dfs(board, row1, col1)
        # q=deque()
        # q.append([click[0],click[1]])
        # if board[click[0]][click[1]]=="M":
        #     board[click[0]][click[1]]="X"
        #     return board
        # else:
        #     board[click[0]][click[1]]="B"
        # while q:
        #     size=len(q)
        #     for i in range(size):
        #         row, col=q.popleft()
        #         mine=self.mines(board, row, col)
        #         if mine!=0:
        #             board[row][col]=str(mine)
        #         else:   
        #             for dir in self.dirs:
        #                 row1=dir[0]+row
        #                 col1=dir[1]+col
        #                 if row1>=0 and row1<len(board) and col1>=0 and col1<len(board[0]) and board[row1][col1]=="E":
        #                     board[row1][col1]="B"
        #                     q.append([row1,col1])
        # return board
    
    def mines(self, board, row, col):
        cnt=0
        for i in self.dirs:
            row1=i[0]+row
            col1=i[1]+col
            if row1>=0 and row1<len(board) and col1>=0 and col1<len(board[0]) and board[row1][col1]=="M":
                cnt+=1
        return cnt