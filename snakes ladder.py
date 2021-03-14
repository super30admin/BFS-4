"""
Time Complexity : O(6^n) Exponential
Space Complexity : O(n*n)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No

Explaination
we are using DFS over here. Starting from 1st index we are going to last index
1. flattern a matrix in 1D array whenever there is -1 we assign same value but whenever there is other number we 
assign number-1
2. from each element go to next 6 indexes
3. if there is no ladder or snake we add the current house into the queue
4. if we found a snake or ladder we add into queue
5. at each step we increse the steps variable 
6. when we reach at last index we return steps var
7. if we dont reach we return -1

"""

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        if board == None or len(board) == 0: return 
        q = deque()
        m = len(board)
        #Flatten the matrix
        even = True
        i,j,index = m-1,0,0
        flat = [-1]*(m*m)
        
        while index<m*m-1:
            if board[i][j] == -1:
                flat[index] = -1
            else:
                flat[index] = board[i][j]-1
            index += 1
            if even:
                j += 1
            else:
                j -= 1
            if j>m-1:
                j = m-1
                i -= 1
                even = not even
            if j<0:
                j = 0
                i -= 1
                even = not even
        
        
        # flat = boardArray
        print(flat)
        q.append(0)
        flat[0] = -2
        maxsteps = 0
        while q:
            size = len(q)
            for i in range(size):
                cur = q.popleft()
                if cur == m*m-1: return maxsteps
                for k in range(1,7):
                    baby = cur + k
                    if baby<len(flat) and flat[baby] != -2:
                        if flat[baby] == -1:
                            q.append(baby)
                        else:
                            q.append(flat[baby])
                        flat[baby] = -2
            maxsteps += 1
        return -1
     