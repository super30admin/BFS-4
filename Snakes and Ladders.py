from collections import deque

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        #Approach: Level-order BFS
        #Time Complexity: O(n^2)
        #Space Complexity: O(n^2)
        #where, the board is of n*n size
        
        n = len(board)
        target = n ** 2
        
        de = deque()
        visited = set()
        
        de.append(1)
        visited.add(1)
        
        moves = 0
        while de:
            sz = len(de)
            for _ in range(sz):
                popped = de.popleft()
                if popped == target:
                    return moves
                
                for i in range(6):
                    new = popped + i + 1
                    if new > target:
                        continue
                    
                    r, c = self.coordinates(new, n)
                    new = new if board[r][c] == -1 else board[r][c]
                    if new not in visited:
                        de.append(new)
                        visited.add(new)
                        
            moves += 1
        
        return -1
                
    def coordinates(self, pos, n):
        r = (pos - 1) // n
        r = (n - 1) - r
        
        c = (pos - 1) % n
        c = c if r % 2 != n%2 else (n - 1) - c
        
        return r, c