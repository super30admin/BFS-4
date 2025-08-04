# Time Complexity:O(n^2)
# Space Complexity: O(n^2)
# Flatten 2D into 1D array in zigzag order
# Use BFS to explore all the six position from each cell
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        arr = [-1] * (n * n)
        
        r, c = n - 1, 0
        i = 0
        flag = True
        
        while i < n * n:
            if board[r][c] == -1:
                arr[i] = -1
            else:
                arr[i] = board[r][c] - 1
            i += 1
            
            if flag:
                c += 1
                if c == n:
                    r -= 1
                    c -= 1
                    flag = False
            else:
                c -= 1
                if c == -1:
                    r -= 1
                    c += 1
                    flag = True

        q = deque([0])
        level = 0

        while q:
            for _ in range(len(q)):
                currIdx = q.popleft()
                for k in range(1, 7):
                    newIdx = currIdx + k
                    if newIdx >= n * n:
                        continue
                    if arr[newIdx] == n * n - 1 or newIdx == n * n - 1:
                        return level + 1
                    if arr[newIdx] != -2:
                        if arr[newIdx] == -1:
                            q.append(newIdx)
                        else:
                            q.append(arr[newIdx])
                        arr[newIdx] = -2
            level += 1

        return -1