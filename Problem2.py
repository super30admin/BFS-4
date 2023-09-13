#Time Complexity :O(8m*n) 
#Space Complexity :O(m*n)
#Did this code successfully run on Leetcode : Yes
#Any problem you faced while coding this : No

from collections import deque
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if not board:
            return board
        m=len(board)
        n=len(board[0])
        self.dirs=[(-1,-1),(0,-1),(1,-1),(-1,0),(1,0),(-1,1),(0,1),(1,1)]
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board
        q=deque()
        q.append(click)
        board[click[0]][click[1]]='B'
        while q:
            curr=q.popleft()
            count=self.countMines(board,curr[0],curr[1])
            if count==0:
                for dir in self.dirs:
                    nr=dir[0]+curr[0]
                    nc=dir[1]+curr[1]
                    if nr>=0 and nc>=0 and nr<len(board) and nc<len(board[0]) and board[nr][nc]=='E':
                        q.append((nr,nc))
                        board[nr][nc]='B'
            else:
                board[curr[0]][curr[1]]=str(count)
        return board

    def countMines(self,board,i,j):
        count=0
        for d in self.dirs:
            nr=d[0]+i
            nc=d[1]+j
            if nr>=0 and nc>=0 and nr<len(board) and nc<len(board[0]) and board[nr][nc]=='M':
                count+=1
        return count




        