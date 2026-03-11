# Time Complexity : O(N^2)  # BFS over all board cells
# Space Complexity : O(N^2) # Queue and visited set
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

# Explanation:
# Treat each square as a node and perform BFS from square 1.
# From each position explore next 6 dice moves.
# If a snake or ladder exists, jump to the destination cell.

from collections import deque

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        
        def get_pos(num):
            r = (num-1)//n
            c = (num-1)%n
            if r%2:
                c = n-1-c
            return n-1-r, c
        
        q = deque([(1,0)])
        visited = {1}
        
        while q:
            square, moves = q.popleft()
            
            if square == n*n:
                return moves
            
            for i in range(1,7):
                nxt = square + i
                if nxt > n*n:
                    break
                
                r,c = get_pos(nxt)
                if board[r][c] != -1:
                    nxt = board[r][c]
                
                if nxt not in visited:
                    visited.add(nxt)
                    q.append((nxt, moves+1))
        
        return -1