# Time complexity : O(m*n)
# Space complexity : O(m*n)
# Works on leetcode : Yes
#Approach - We use DFS to expose the board on click. If its a mine, the game is over. Else we if it's empty - we count mines
#in 8 directions and assign number of mines to the position and return. But if the position is blank, we recursiverly call DFS
#in the 8 directions
class Solution(object):
    def updateBoard(self, board, click):
        """
        :type board: List[List[str]]
        :type click: List[int]
        :rtype: List[List[str]]
        """
        if not board:
            return []
        i,j = click[0], click[1]
        if board[i][j]=='M':
            board[i][j]='X'
            return board
        self.dfs(board,i,j)
        return board
    
    def dfs(self,board,i,j):
        if board[i][j]!='E':
            return 
        m,n = len(board), len(board[0])
        directions = [(1,1),(-1,-1),(-1,1),(1,-1),(0,1),(1,0),(-1,0),(0,-1)]
        mines= 0 
        for d in directions:
            di,dj = i+d[0], j+d[1]
            if 0<=di<m and 0<=dj<n and board[di][dj]=='M':
                mines+=1
        if mines==0:
            board[i][j]='B'
        else:
            board[i][j]=str(mines)
            return
        for d in directions:
            di,dj = i+d[0], j+d[1]
            if 0<=di<m and 0<=dj<n:
                self.dfs(board,di,dj)