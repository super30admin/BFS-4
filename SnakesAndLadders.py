'''
TC: O(n^2) -  to go through att the elements in the matrix
SC: O(n^2) - Using queue space for storing the index positions
'''
from collections import deque
from typing import List

class Solution:
    def snakesAndLadders(self, board: List[List[int]]) -> int:
        n = len(board)
        arr = [-1]*((n*n)+1)
        arr[0] = 0
        idx = 1
        #Populating 1D array
        i,j=n-1,0
        flag = True
        while i>=0:
            #Left to Right
            if i>=0 and j>=0 and i<n and j<n:
                if flag:
                    arr[idx] = board[i][j]
                    j+=1
                    if j==n:
                        flag = False
                        i-=1
                        j-=1
                else:
                    #Right to Left
                    arr[idx] = board[i][j]
                    j-=1
                    if j<0:
                        flag = True
                        j+=1
                        i-=1 
            idx+=1
        q = deque()
        q.append(1)
        moves = 0
        arr[1] = -2
        while q:
            size = len(q)
            while size>0:
                index = q.popleft()
                for i in range(1,7):
                    newindex = index+i
                    if arr[newindex] == n*n or newindex == n*n:
                        return moves+1
                    if arr[newindex]==-2:
                        continue
                    if arr[newindex] == -1:
                        q.append(newindex)
                        arr[newindex] = -2
                    else:
                        q.append(arr[newindex])
                        arr[newindex] = -2
                size-=1
            moves += 1
        return -1
s = Solution()
print(s.snakesAndLadders([[-1,1,-1],[4,3,2],[-1,1,4]]))
print(s.snakesAndLadders([[-1,-1],[-1,3]]))
print(s.snakesAndLadders([[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,-1,-1,-1,-1,-1],[-1,35,-1,-1,13,-1],[-1,-1,-1,-1,-1,-1],[-1,15,-1,-1,-1,-1]]))
print(s.snakesAndLadders([[1,1,-1],[1,1,1],[-1,1,1]]))
print(s.snakesAndLadders([[-1,-1,-1],[-1,9,8],[-1,8,9]]))