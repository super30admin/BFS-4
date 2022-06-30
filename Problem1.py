#Time Complexity: O(M*N)
#Space Complexity: O(M*N)
class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        if len(board) == 0 or board == None:
            return board
        dirr = [[1,0],[0,-1],[-1,0],[0,1],[1,1],[-1,-1],[1,-1],[-1,1]]
        row = len(board)
        col = len(board[0])
        if board[click[0]][click[1]] == 'M':
            board[click[0]][click[1]] = 'X'
            return board
        queue = []
        queue.append(click)
        board[click[0]][click[1]] = 'B'
        while queue:
            node = queue.pop(0)
            total = self.CountMines(node[0],node[1],board,dirr,row,col)
            if total == 0:
                for i in dirr:
                    r = i[0] + node[0]
                    c = i[1] + node[1]
                    if r >= 0 and c >=0 and r < row and c < col and board[r][c] == "E":
                        queue.append([r,c])
                        board[r][c] = 'B'
            else:
                board[node[0]][node[1]] = str(total)
        return board    
            
    def CountMines(self,x,y,board,dirr,row,col):
        count = 0
        for i in dirr:
            r = i[0] + x    
            c = i[1] + y
            if r >= 0 and c >=0 and r < row and c < col and board[r][c] == "M":
                count +=1 
        return count
        