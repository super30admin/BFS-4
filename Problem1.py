# Problem 1: Snakes and Ladders
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        arr = [0 for _ in range(n*n+1)]
        flag = True
        r,c = n-1,0
        idx = 1

        #flatten input array
        while idx <= n * n :
            arr[idx] = board[r][c]
            idx += 1 
            if flag:
                c += 1
                if c > n-1:
                    c -= 1
                    r -= 1
                    flag = False
            else:
                c -= 1
                if c < 0:
                    c += 1
                    r -= 1
                    flag = True
        
        q = deque()
        q.append(1)
        arr[1] = -2
        moves = 0

        while q:
            size = len(q)
            for i in range(size):
                currIdx = q.popleft()
                for i in range(1,7):
                    newIdx = currIdx + i
                    if newIdx == n * n or arr[newIdx] == n*n: return moves + 1
                    if arr[newIdx] != -2:
                        if arr[newIdx] == -1:
                            q.append(newIdx)
                            arr[newIdx] = -2
                        else:
                            q.append(arr[newIdx])
                            arr[newIdx] = -2
            moves += 1

        return -1

