#time complexity o(n^2)
#space complexity o(n^2)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        arr = [0] * (n*n)
        dirs = True
        r = n-1
        c = 0
        idx = 0 #idx is the index on arr

        while idx < n*n:
            if board[r][c] == -1:
                arr[idx] = board[r][c]
            else:
                arr[idx] = board[r][c] - 1

            idx += 1
        
        #r c is used move to next r,c
            if dirs:
                #left to right
                c += 1
                if c == n:
                    dirs = False
                    r -= 1
                    c -= 1 
            else:
                c -= 1
                if c == -1:
                    dirs = True
                    r -= 1
                    c += 1
        
        q = deque()
        q.append(0)
        arr[0] = -2
        moves = 0
        while q:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                if curr == n*n -1:
                    return moves
                for j in range(1,7):
                    newIdx = curr + j
                    if newIdx > n * n - 1:
                        break
                    if arr[newIdx] == -1:
                        q.append(newIdx)
                        
                    else:
                        if arr[newIdx] != -2:
                            q.append(arr[newIdx])
                            
                    arr[newIdx] = -2


            moves += 1
        
        return -1

        

        