"""
Problem : 1

Time Complexity : 
Approach 1 - O(m*n)
Approach 2 - O(m*n)

Space Complexity : 
Approach 1 - O(m*n)
Approach 2 - O(m*n)

Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No


"""

# Minesweeper

# Approach - 1
# DFS

class Solution(object):
    def __init__(self):
        self.directions=[(1,0),(1,1),(0,1),(-1,1),(-1,0),(-1,-1),(0,-1),(1,-1)]
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        if not board:
            return board
        m=len(board)
        n=len(board[0])

        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board

        self.dfs(board,click[0],click[1],m,n)

        return board
    
    def dfs(self,board,i,j,m,n):
        # base
        if i<0 or i>=m or j<0 or j>=n or board[i][j]!='E':
            return 
        # logic
        count=self.countMines(board,i,j,m,n)
        if count==0:
            board[i][j]='B'
            for dirs in self.directions:
                nr=i+dirs[0]
                nc=j+dirs[1]
                self.dfs(board,nr,nc,m,n)
        else:
            board[i][j]=str(count)

    def countMines(self,board,i,j,m,n):
        count=0
        for dirs in self.directions:
            nr=i+dirs[0]
            nc=j+dirs[1]
            if nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc]=='M':
                    count+=1
        return count


# Approach - 2
# BFS

class Solution(object):
    def __init__(self):
        self.directions=[(1,0),(1,1),(0,1),(-1,1),(-1,0),(-1,-1),(0,-1),(1,-1)]
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        if not board:
            return board
        m=len(board)
        n=len(board[0])

        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board

        q=collections.deque()
        q.append((click[0],click[1]))
        board[click[0]][click[1]]='B'

        while q:
            curr=q.popleft()
            count=self.countMines(board,curr[0],curr[1],m,n)
            if count==0:
                for dirs in self.directions:
                    nr=curr[0]+dirs[0]
                    nc=curr[1]+dirs[1]
                    if nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc]=='E':
                        q.append((nr,nc))
                        board[nr][nc]='B'
            else:
                board[curr[0]][curr[1]]=str(count)
        return board
    def countMines(self,board,i,j,m,n):
        count=0
        for dirs in self.directions:
            nr=i+dirs[0]
            nc=j+dirs[1]
            if nr>=0 and nr<m and nc>=0 and nc<n and board[nr][nc]=='M':
                    count+=1
        return count