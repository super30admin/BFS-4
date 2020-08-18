-------------------------Update Board------------------------------------------
# Time Complexity : O(2XMXN) as M is the number of rows and N is number of columns 
# Space Complexity : O(1)  No extra space
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# 
# We will use BFS to iterate through the board. first we will have a getMines method which calculate the number of mines around it.
# We will add the starting point nto the queue, and iterate through the queue. Once we get the number of mines , if the matrix has 
# 0 mines we will be adding the child nodes into the queue, else we will not be adding the child nodes instead we will be changing the element to number of mines around it.
# If the enter point is a mine we will change it to 'X' and return board.


class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        
        if not board:
            return board
        
        
                
        m = len(board)
        n = len(board[0])
        dirs = [[1,0], [-1,0], [0,1], [0,-1], [1,1], [-1, 1],[1,-1],[-1,-1]]
        
        def getMines(i,j, board):
            count = 0
            for d in dirs:
                nr = i+d[0]
                nc = j+d[1]
                if 0<=nr<len(board) and 0<=nc<len(board[0]):
                    if board[nr][nc] == 'M':
                        count +=1
            return count
                    
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        else:
            board[click[0]][click[1]] = 'B'
        queue = collections.deque([(click[0], click[1])])
        while queue:
            r, c = queue.popleft()
            
            mines = getMines(r, c, board)
            if mines == 0:
                for d in dirs:
                    nr = r+d[0]
                    nc = c+d[1]

                    if 0<=nr<m and 0<=nc<n and board[nr][nc] == 'E':
                        board[nr][nc] = 'B'
                        queue.append((nr, nc))
            else:
                board[r][c] = str(mines)
        return board