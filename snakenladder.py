#Time Complexity: O(n*n)
#Space Complexity: O(n*n)
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        lst = []
        row = 0
        idx = 0
        i = n-1
        j = 0
        while idx < n*n:
            if board[i][j] == -1:
                lst.append(board[i][j])
            else:
                lst.append(board[i][j]-1)
                
            if row%2 == 0:
                j+= 1
                if j == n:
                    i -= 1
                    j -= 1
                    row += 1
            else:
                j -= 1
                if j<0:
                    i -= 1
                    j += 1
                    row += 1
            idx += 1
        minm = 0 
        q = deque()
        q.append(0)
        while q:
            size = len(q)
            for i in range(size):
                curr = q.popleft()
                if curr == (n*n) - 1:
                    return minm
                for i in range(1,7):
                    newIdx = curr + i
                    if newIdx < n*n and lst[newIdx] != -2:
                        if lst[newIdx] == -1:
                            q.append(newIdx)
                        else:
                            q.append(lst[newIdx])
                            
                        lst[newIdx] = -2
            minm += 1
        return -1