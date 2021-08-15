# Time Complexity : O(6**m*n)
# Space Complexity : O(m*n)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No

#using bfs to find all the possible and checking the lowest level in which the game can be completed
from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board == None or len(board) == 0:
            return 0
        
        r = len(board)
        c = len(board)
        even = 0
        i = r - 1 
        j = 0
        idx = 0
        moves = [0]*(r*r)
        while i >= 0 and j >= 0:
            
            if board[i][j] == -1:
                moves[idx] = -1
            else:
                moves[idx] = board[i][j] - 1
            idx += 1    
            if even % 2 == 0:
                j += 1
                
                if j == r:
                    i -= 1
                    even += 1
                    j -= 1
            else:
                j -= 1
                
                if j < 0:
                    i -= 1
                    even += 1
                    j += 1
        q = deque()
        q.append(0)
        moves[0] = -2
        minMoves = 0
        
        while len(q) != 0:
            size = len(q)
            
            for i in range(size):
                curr = q.popleft()
                if curr == r*r-1:
                        return minMoves
                for j in range(1,7):
                    child = curr + j
                    if child > r*r - 1:
                        break
                    if moves[child] != -2:
                        if moves[child] == -1:
                            q.append(child)
                        else:
                            q.append(moves[child])  
                        moves[child] = -2
            minMoves += 1
        return -1