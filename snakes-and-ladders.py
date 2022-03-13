'''
TC: O(n*n)
SC: O(n*n)

Intuition

- Convert to 1d array - idx for -1, board[i][j] for else
- bfs
'''
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        dir = 1
        rlen = len(board)
        clen = rlen
        
        if not rlen:
            return 0
        
        arr = [-1 for i in range(rlen * clen)]
        l = 0
        i, j = rlen - 1, 0
        
        while i >= 0:
            while j >= 0 and j < clen:
                if board[i][j] != -1:
                    arr[l] = board[i][j] - 1
                else:
                    arr[l] = l
                # print(board[i][j], i, j, arr[l])
                j += dir
                l += 1
            dir *= -1
            j += dir
            i -= 1
        
        q = deque()
        q.append(0)
        q.append(None)
        visited = set()
        visited.add(0)
        moves = 0
        
        while q:
            top = q.popleft()
            if top == None:
                if not q:
                    break
                moves += 1
                q.append(None)
                continue
            if top == (rlen * rlen) - 1:
                return moves
            for i in range(1, 7):
                c = top + i
                if c >= rlen * rlen:
                    break
                if arr[c] not in visited:
                    q.append(arr[c])
                    visited.add(arr[c])
        
        return -1
        