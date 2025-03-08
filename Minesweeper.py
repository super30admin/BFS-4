#Time Complexity : O(M*N)
#Space Complexity : O(M*N)
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        def getMines(board,i,j):
            count = 0
            for direction in dirs:
                r = i + direction[0]
                c = j + direction[1] 
                if(r>=0 and c>=0 and r<m and c<n and board[r][c]=='M'):
                    count+=1
            return count
        dirs = [[-1,-1],[-1,0],[-1,1],[0,-1],[0,1],[1,-1],[1,0],[1,1]]
        m = len(board)
        n = len(board[0])
        if(board[click[0]][click[1]]=='M'):
            board[click[0]][click[1]]='X'
            return board
        q = deque()
        q.append([click[0],click[1]])
        board[click[0]][click[1]]='B'
        while q:
            curr = []
            curr = q.popleft()
            count = getMines(board, curr[0], curr[1])
            if(count!=0):
                board[curr[0]][curr[1]]=str(count)
            else:
                for direction in dirs:
                    r = curr[0] + direction[0]
                    c = curr[1] + direction[1]
                    if(r>=0 and c>=0 and r<m and c<n and board[r][c]=='E'):
                        q.append([r,c])
                        board[r][c]='B'
        return board
        
        


        