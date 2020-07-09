// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


// Your code here along with comments explaining your approach
we start at the click and then traverse the all neighbouring locations and check if any mines are present in the neigboruing locations.If yes and the current cell is 'E' then replace it with the count, else replace it with 'B'.If we click directly on the cell 'M' then game over.

# BFS solution
# Time complexity --> o(n*n)
# space complexity --> o(n)
from collections import deque
class Solution(object):
    def __init__(self):
        self.dirs=[[0,1],[0,-1],[1,0],[-1,0],[1,1],[-1,1],[-1,-1],[1,-1]]
        #To check if the cell is within bounds or not
    def isvalid(self,row,col,board):
        rows=len(board)
        cols=len(board[0])
        if row>=0 and row<rows and col>=0 and col<cols:
            return True
        return False
        #count the number of mines around each cell
    def countmine(self,row,col,board):
        count=0
        for i in self.dirs:
            # print("i is",i[0])
            rowindex=i[0]
            colindex=i[1]
            dx=row+rowindex
            dy=col+colindex
            if self.isvalid(dx,dy,board) and board[dx][dy]=='M':
                count=count+1
        return count
        
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        #If the first click is M then game over
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board
        queue=deque([click])
        board[click[0]][click[1]]='B'
        while len(queue)!=0:
            ele=queue.popleft()
            # print(ele)
            row=ele[0]
            col=ele[1]
            count=self.countmine(row,col,board)
            if count==0:
                for i in self.dirs:
                    dx=row+i[0]
                    dy=col+i[1]
                    if self.isvalid(dx,dy,board) and board[dx][dy]=='E':
                        board[dx][dy]='B'
                        queue.append([dx,dy])
            else:
                board[row][col]=str(count)
        return board
        
 # DFS solution
# Time complexity --> o(n*n)
# space complexity --> o(n)
class Solution(object):
    def __init__(self):
        self.dirs=[[0,1],[0,-1],[1,0],[-1,0],[1,1],[-1,1],[-1,-1],[1,-1]]
    def isvalid(self,row,col,board):
        rows=len(board)
        cols=len(board[0])
        if row>=0 and row<rows and col>=0 and col<cols:
            return True
        return False
    def countmine(self,row,col,board):
        count=0
        for i in self.dirs:
            # print("i is",i[0])
            rowindex=i[0]
            colindex=i[1]
            dx=row+rowindex
            dy=col+colindex
            if self.isvalid(dx,dy,board) and board[dx][dy]=='M':
                count=count+1
        return count
    def dfs(self,ele,board):
        row=ele[0]
        col=ele[1]
        board[row][col]='B'
        count=self.countmine(row,col,board)
        if count==0:
            for i in self.dirs:
                dx=row+i[0]
                dy=col+i[1]
                if self.isvalid(dx,dy,board) and board[dx][dy]=='E':
                    board[dx][dy]='B'
                    self.dfs([dx,dy],board)
        else:
            board[row][col]=str(count)
        return board
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board
        return self.dfs(click,board)
        