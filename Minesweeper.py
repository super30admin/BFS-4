class Solution:
    def updateBoard(self, board: List[List[str]], click: List[int]) -> List[List[str]]:
        q = []
        q.append(click)
        ROW = len(board)
        COL = len(board[0])
        dir = [[-1,0],[1,0],[0,-1],[0,1],[-1,-1],[-1,1],[1,-1],[1,1]]

        if board[click[0]][click[1]]=='M':
            board[click[0]][click[1]] = 'X'
            return board

        def CheckDir(row, col):
            if row>=0 and row<ROW and col>=0 and col<COL and board[row][col]!='B':
                return True
            else:
                return False
        
        
        def countMines(row, col):
            count = 0
            for d in dir:
                new_r = row+d[0]
                new_c = col+d[1]
                if CheckDir(new_r, new_c):
                    if board[new_r][new_c]=='M':
                        count+=1
            return count


        while q:
            s = len(q)
            for i in range(s):
                curr = q.pop(0)
                r = curr[0]
                c = curr[1]
                #up, down , left, right, UL, UR, LL, LR

                board[r][c] = 'B'    
                if board[r][c]=='M':
                    board[r][c] = 'X'
                    return
                count_mines = countMines(r,c)
                if count_mines>0:
                    board[r][c] = str(count_mines)

                if count_mines==0:
                    
                    for d in dir:
                        new_r = r+d[0]
                        new_c = c+d[1]
                        if CheckDir(new_r, new_c):
                            if board[new_r][new_c]=='E':
                                board[new_r][new_c] = 'B'
                                q.append([new_r, new_c])

                        
                    
                        
                
                


        return board
                
