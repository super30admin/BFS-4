# Time:- O(m*n)
# Space:- O(m*n)
# Approach:- At every position check the neighbours of that position for a mine, if there is a mine we will not explore the neighours of the position. If we click on a mine the game is over. If there are no mines around a position we will explore all the neighbours of this 
# position.
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        dirs=[[0,1],[1,0],[0,-1],[-1,0],[1,1],[-1,-1],[-1,1],[1,-1]]
        q=collections.deque([])
        # Edge Case We click on a mine the game is over
        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]]='X'
            return board
        q.append(click)
        seen=set()
        # function to check if there are mines around a position
        def checkmines(i,j):
            mines=0
            for k,l in dirs:
                m,n=i+k,j+l
                if 0<=m<len(board) and 0<=n<len(board[0]):
                    if board[m][n]=='M':
                        mines+=1
            if mines>0:
                board[i][j]=str(mines)
                return False
            return True
        while(q):
            k,l=q.popleft()
            # we are processing this position change the position to a revealed blank square
            board[k][l]='B'
            # check if there are mines around the position, if yes we will not explore the neighbours of this position
            if checkmines(k,l):
                for i,j in dirs:
                    m,n=k+i,l+j
                    if 0<=m<len(board) and 0<=n<len(board[0]) and (m,n) not in seen:
                        q.append([m,n])
                        seen.add((m,n))
        return board