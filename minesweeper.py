from collections import deque

#Time Complexity : O(N*M)
#Space Complexity : O(N*M)
from typing import List


class Solution:
    def countMines(self, board, x, y):
        count = 0
        directions = [(0,1),(1,0),(0,-1),(-1,0),(1,1),(-1,-1),(1,-1),(-1,1)]
        for i,j in directions:
            nx = x+i
            ny = y+j
            if nx >=0 and ny >= 0 and nx < len(board) and ny <len(board[0]) and board[nx][ny] == "M":
                count += 1
        return count

    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if not board:
            return [[]]
        if board[click[0]][click[1]] =="M":
            board[click[0]][click[1]] ="X"
            return board
        que = deque([click])
        board[click[0]][click[1]] = "B"
        directions = [(0,1),(1,0),(0,-1),(-1,0),(1,1),(-1,-1),(1,-1),(-1,1)]
        while que:
            size = len(que)
            for i in range(size):
                x,y = que.popleft()
                mines = self.countMines(board, x, y)
                if mines > 0:
                    board[x][y] = str(mines)
                else:
                    for j,k in directions:
                        nx = x+j
                        ny = y+k
                        if nx >=0 and ny >= 0 and nx < len(board) and ny <len(board[0]) and board[nx][ny] == "E":
                            board[nx][ny] = "B"
                            que.append([nx,ny])
        return board