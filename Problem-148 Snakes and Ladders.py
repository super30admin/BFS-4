# 909. Snakes and Ladders
# https://leetcode.com/problems/snakes-and-ladders/

# Logic: Flatten the board into a linear list. Start BFS 
# from first cell and do level order traversal. When you 
# cross the end or reach the end, return the count.

# Time Complexity: O(n*n)
# Space Complexity: O(n)

from collections import deque

class Solution:
    def snakesAndLadders(self, board) -> int:
        n = len(board)
        moves = [1,2,3,4,5,6]
        cells = list()
        flag = True
        
        for i in range(n-1, -1, -1):
            if not flag:
                cells += board[i][::-1]
            else:
                cells += board[i]
            flag = not flag
        
        for idx, i in enumerate(cells):
            if cells[idx] == -1:
                cells[idx] = idx + 1

        visited = set()
        q = deque()
        count = 0
        
        visited.add(cells[0])
        q.append(cells[0])
        
        while q:
            size = len(q)
            count += 1

            for i in range(size):
                cur = q.popleft()
                
                for j in moves:
                    newCell = cur + j - 1
                    
                    if cells[newCell] >= (n**2):
                        return count
                    
                    if newCell < n**2 and cells[newCell] not in visited:
                        visited.add(cells[newCell])
                        q.append(cells[newCell])
                
        return -1

obj = Solution()
print(obj.snakesAndLadders([[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]))
print(obj.snakesAndLadders([[-1,-1],[-1,3]]))