from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        moves = [0]*(n*n)
        idx = 0
        i = n -1
        j = 0
        row = 0
        while i >= 0 and j >= 0:
            if board[i][j] == -1:
                moves[idx] = -1
            else:
                moves[idx] = board[i][j] - 1
            
            idx += 1
            
            if row % 2 == 0:
                j += 1
                if j == n:
                    j -= 1
                    i -= 1
                    row += 1
                
            else:
                j -= 1
                if j == -1:
                    j += 1
                    i -= 1
                    row += 1
        result = 0
        q = deque()
        q.append(0)
        moves[0] = -2
        
        while q:
            
            size = len(q)
            for _ in range(size):
                curr = q.popleft()
                if curr == (n*n)-1: return  result
                for offset in range(1,7):
                    child = curr + offset
                    if child > n*n-1: break
                    if moves[child] != -2:
                        if moves[child] == -1:
                            q.append(child)
                        else:
                            q.append(moves[child])
                        moves[child] = -2
            result += 1
        return -1
                            
            
                