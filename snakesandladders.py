class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        
        moves = [None for _ in range(len(board)*len(board))]
        row = len(board)-1
        col = 0
        idx= 0
        flag = True
        while(idx < len(moves)):
            if board[row][col] == -1:
                moves[idx] = -1
            else:
                moves[idx] = board[row][col]-1
            idx+=1
            if flag == True:
                col +=1
                if col == len(board):
                    flag = False
                    col-=1
                    row-=1
            else:
                col -=1
                if col <0:
                    flag = True
                    col+=1
                    row-=1
        minmoves = 0
        qu = deque()
        qu.append(0)
        
        while(qu):
            size = len(qu)
            for i in range(size):
                curr = qu.popleft()
                if curr == len(board)*len(board[0])-1:
                    return minmoves
                for l in range(1,7):
                    child = curr + l
                    if child > len(board)*len(board[0])-1:
                        break
                    
                    if moves[child] !=-2:
                        if moves[child] == -1:
                            qu.append(child)
                        else:
                            qu.append(moves[child])
                    moves[child]=-2
            minmoves+=1
        return -1
    
                
        
        
                    
                
            
        
        