# Time Complexity : O(n^2) 
# Space Complexity :O(n^2)
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
# Your code here along with comments explaining your approach
class Solution:
    def snakesAndLadders(self, board):
        minValue = 0 
        n = len(board) * len(board)
        i = len(board) -1
        j = 0 
        visited = []
        
        while i>= 0 and j >= 0:
            while j < len(board):
                if board[i][j] == -1:
                    visited.append(-1)
                else:
                    visited.append(board[i][j]-1)
                j += 1 
            
            j -= 1 
            i -= 1 
            
            while j >= 0:
                if board[i][j] == -1:
                    visited.append(-1)
                else:
                    visited.append(board[i][j]-1)
                j -= 1 
            
            j += 1 
            i -= 1 
        
        q = [0] 
        visited[0] = -2
        while len(q) != 0 :
            size = len(q)
            
            for i in range(size):
                curr = q.pop(0)
                if curr == n-1:
                    return minValue
                for j in range(1, 7):
                    r = curr + j
                    if r < n and visited[r] != -2:
                        if visited[r] == -1:
                            q.append(r)
                        else:
                            q.append(visited[r])
                        visited[r] = -2 
            minValue += 1 
        return -1