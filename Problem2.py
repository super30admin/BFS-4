"""
// Time Complexity : O(n*n)
// Space Complexity : O(n*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
"""
from collections import deque
class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if not board:
            return 0
        
        min_moves = 0
        r = len(board)
        
        n = r * r
        
        moves = [0] * n #converting the grid to 1D array, for easy ccomputation
        
        idx = 0
        even = 0
        i = r - 1 #starting point in the grid
        j = 0
        
        while (i >= 0 and j >= 0):
            
            if board[i][j] == -1:
                moves[idx] = -1
            
            else:
                moves[idx] = board[i][j] - 1 #if not 1, put the number - 1(index based) 
            idx += 1
            
            if even % 2 == 0: #for iterating over the grid in zig-zag manner
                j += 1
            else:
                j -= 1
                
            if j == r:
                i -= 1
                even += 1
                j -= 1
                
            if j <0:
                i -= 1
                even += 1
                j += 1
                
        q = deque() #bfs
        
        q.append(0)
        moves[0] = -2 #visisted
        
        while q:
            size = len(q)
            
            for i in range(size):
                cur = q.popleft()
                
                if cur == n-1: #if the end os reached
                    return min_moves
                for k in range(1,7): #for every child for the current position
                    child = cur + k
                    
                    if child < n and moves[child] != -2:
                        if moves[child] == -1:
                            q.append(child)
                        else:
                            q.append(moves[child])
                        moves[child] = -2
            min_moves += 1 #at each level increase the number of moves
            
        return -1
                        
                
                
                